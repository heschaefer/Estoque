import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EstoqueService {
    private final List<Produto> estoque = new ArrayList<>();

    // Adicionar um produto ao estoque
    public void cadastrarProduto(Scanner scanner, FornecedoresService fornecedoresService) {
        try {
            Fornecedor fornecedor = null;
            //Verificação para saber se o usuario quer um fornecedor novo ou algum ja cadastrado
            EstoqueConstantes.exibirPromptOpcoes();
            int escolhaFornecedor = scannerInt(scanner);
            limpabuffer(scanner);
            if(escolhaFornecedor==1){
                EstoqueConstantes.exibirPromptCnpjFornecedor();
                String cnpjFornecedor = scannerString(scanner);
                fornecedor = fornecedoresService.buscarFornecedorPorCNPJ(cnpjFornecedor);
                if(fornecedor==null){
                    EstoqueConstantes.exibirPromptFornecedorNaoEncontrado();
                    return;
                } else if (escolhaFornecedor == 2) {
                    fornecedor = fornecedoresService.cadastrarFornecedor(scanner);
                } else {
                    EstoqueConstantes.exibirPromptEntradaInvalida();
                    return;
            }
            }
            EstoqueConstantes.exibirPromptNomeProduto();
            String nome = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptQuantidadeEmEstoque();
            int quantidadeEmEstoque = scannerInt(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
    
            EstoqueConstantes.exibirPromptPrecoDeCusto();
            double precoCusto = scannerDouble(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
    
            EstoqueConstantes.exibirPromptPrecoDeVenda();
            double precoVenda = scannerDouble(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
            
        Produto novoProduto = new Produto(nome, codigo, quantidadeEmEstoque, precoCusto, precoVenda, fornecedor);

        // Adicionar o produto ao estoque e ao fornecedor
        estoque.add(novoProduto);
        fornecedoresService.adicionarProdutoFornecedor(fornecedor.cnpj, novoProduto);

        EstoqueConstantes.exibirPromptProdutoSucesso();
    } catch (InputMismatchException e) {
        EstoqueConstantes.exibirPromptEntradaInvalida();
        limpabuffer(scanner); // Limpar o buffer
    }
    }
    
    // Remover um produto do estoque (por código)
    public void removerProduto(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scanner.nextLine();
    
            Produto produto = obterProdutoPorCodigo(codigo);
            if (produto == null) {
                EstoqueConstantes.exibirPromptNaoEncontrado();
                return;
            }
    
            EstoqueConstantes.exibirPromptConfirmarRemocaoProduto(produto.nome); // Exibe mensagem de confirmação com o nome do produto
            String confirmacao = scannerString(scanner);
    
            if (confirmacao.equalsIgnoreCase("s")) {
                estoque.removeIf(p -> p.codigo.equals(codigo)); // Remove o produto com o código fornecido
                EstoqueConstantes.exibirPromptProdutoRemovido();
            } else {
                EstoqueConstantes.exibirPromptRemocaoCancelada();
            }
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner); // Limpar o buffer
        }
    }

    // Atualizar a quantidade em estoque de um produto (por código)
    public void registrarEntradaProduto(Scanner scanner) {
        try{
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
            Produto produto = obterProdutoPorCodigo(codigo);
        if (produto == null) {
            EstoqueConstantes.exibirPromptNaoEncontrado();
            return;
        }

        EstoqueConstantes.exibirPromptQuantidadeEntrada();
        int quantidadeEntrada = scannerInt(scanner);
        limpabuffer(scanner); // Consumir a quebra de linha

        if (quantidadeEntrada <= 0) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            return;
        }

        produto.quantidadeEmEstoque += quantidadeEntrada;
        EstoqueConstantes.exibirPromptEntradaValida();
    } catch (InputMismatchException e) {
        EstoqueConstantes.exibirPromptEntradaInvalida();
        limpabuffer(scanner); // Limpar o buffer
    }
        }
    
    //Resgitrar baixas de produtos
    public void registrarSaidaProduto(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
        
            Produto produto = obterProdutoPorCodigo(codigo);
            if (produto == null) {
                EstoqueConstantes.exibirPromptNaoEncontrado();
                return;
            }
        
            EstoqueConstantes.exibirPromptQuantidadeSaida();
            int quantidadeSaida = scannerInt(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
        
            if (quantidadeSaida <= 0 || quantidadeSaida > produto.quantidadeEmEstoque) {
                EstoqueConstantes.exibirPromptSaidaInvalida();
                return;
            }
        
            produto.quantidadeEmEstoque -= quantidadeSaida;
            EstoqueConstantes.exibirPromptSaidaValida();
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida(); // Reutilizei a mensagem de entrada inválida, já que o erro é semelhante
            limpabuffer(scanner); // Limpar o buffer
        }
    }
    
    //Consulta o estoque de um produto especifico
    public void consultarEstoqueProduto(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
    
            Produto produto = obterProdutoPorCodigo(codigo);
            if (produto == null) {
                EstoqueConstantes.exibirPromptNaoEncontrado();
                return;
            }
            mostrarQuantidadeEmEstoque(produto);
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner);
        }
    }

    //Usado para obeter o produto, usando o código que o usuario digitar.
    public Produto obterProdutoPorCodigo(String codigo) {
        for (Produto p : estoque) {
            if (p.codigo.equals(codigo)) {
                return p;
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }

    // Listar todos os produtos em estoque
    public void listarTodosProdutos() {
        if (estoque.isEmpty()) {
            EstoqueConstantes.exibirPromptEstoqueVazio();
            return;
        }
        EstoqueConstantes.exibirPromptListaProdutos();
        estoque.stream()
               .forEach(produto -> {
                   System.out.println("------------------------");
                   System.out.println(produto);
                   System.out.println("------------------------");
               });
    }

    // Listar produtos com estoque baixo (abaixo de um limite)
    public void listarProdutosEstoqueBaixo(Scanner scanner) {
        EstoqueConstantes.exibirPromptEstoqueBaixo();
        int limite = scannerInt(scanner);
        estoque.stream()
               .filter(produto -> produto.quantidadeEmEstoque < limite)
               .forEach(this::mostrarQuantidadeEmEstoque);
    }
    // Atualizar produtos ja existentes
    public void atualizarProduto(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
    
            Produto produto = obterProdutoPorCodigo(codigo);
            if (produto == null) {
                EstoqueConstantes.exibirPromptNaoEncontrado();
                return;
            }
            EstoqueConstantes.exibirPromptOpcaoAtualizarProduto();
            int opcao = scannerInt(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
    
            switch (opcao) {
                case 1 -> {
                    EstoqueConstantes.exibirPromptNomeProduto();
                    produto.nome = scannerString(scanner);
                }
                case 2 -> {
                    EstoqueConstantes.exibirPromptQuantidadeEmEstoque();
                    produto.quantidadeEmEstoque = scannerInt(scanner);
                    limpabuffer(scanner); // Consumir a quebra de linha
                }
                case 3 -> {
                    EstoqueConstantes.exibirPromptPrecoDeCusto();
                    produto.precoCusto = scannerDouble(scanner);
                    limpabuffer(scanner); // Consumir a quebra de linha
                }
                case 4 -> {
                    EstoqueConstantes.exibirPromptPrecoDeVenda();
                    produto.precoVenda = scannerDouble(scanner);
                    limpabuffer(scanner); // Consumir a quebra de linha
                }
                case 0 -> {
                    return; // Volta ao menu principal
                }
                default -> EstoqueConstantes.exibirPromptEntradaInvalida();
            }
    
            EstoqueConstantes.exibirPromptProdutoAtualizado();
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            scanner.nextLine(); // Limpar o buffer
        }
    }
    //Utilitarios
    private String scannerString(Scanner scanner) {
        String nome = scanner.nextLine();
        return nome;
    }
    private int scannerInt(Scanner scanner) {
        int quantidadeEmEstoque = scanner.nextInt();
        return quantidadeEmEstoque;
    }
    private double scannerDouble(Scanner scanner) {
        double precoCusto = scanner.nextDouble();
        return precoCusto;
    }
    private void limpabuffer(Scanner scanner){
        scanner.nextLine();
    }
    private void mostrarQuantidadeEmEstoque(Produto produto){
        System.out.println("Produto: " + produto.nome);
        System.out.println("Quantidade em estoque: " + produto.quantidadeEmEstoque);
    }
}
