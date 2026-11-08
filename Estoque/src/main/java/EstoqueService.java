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
            switch (escolhaFornecedor) {
                case 1 -> {
                    EstoqueConstantes.exibirPromptCnpjFornecedor();
                    String cnpjFornecedor = scannerString(scanner);
                    fornecedor = fornecedoresService.buscarFornecedorPorCNPJ(cnpjFornecedor);
                    if(fornecedor==null){
                        EstoqueConstantes.exibirPromptFornecedorNaoEncontrado();
                        return;
                    }
                }
                case 2 -> fornecedor = fornecedoresService.cadastrarFornecedor(scanner);
                default -> {
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

            // A busca continua aqui porque a mensagem "Produto nao encontrado!" precisa
            // aparecer ANTES de pedir a quantidade, exatamente como era antes.
            Produto produto = obterProdutoPorCodigo(codigo);
            if (produto == null) {
                EstoqueConstantes.exibirPromptNaoEncontrado();
                return;
            }

            EstoqueConstantes.exibirPromptQuantidadeSaida();
            int quantidadeSaida = scannerInt(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha

            // A regra de negocio em si ficou em registrarSaida(String, int).
            // Aqui so sobrou a leitura das entradas e a exibicao das mensagens.
            if (registrarSaida(codigo, quantidadeSaida)) {
                EstoqueConstantes.exibirPromptSaidaValida();
            } else {
                EstoqueConstantes.exibirPromptSaidaInvalida();
            }
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida(); // Reutilizei a mensagem de entrada inválida, já que o erro é semelhante
            limpabuffer(scanner); // Limpar o buffer
        }
    }

    /**
     * Regra de negocio da saida de estoque, sem Scanner e sem System.out.
     * E este metodo que os testes JUnit e a API HTTP chamam.
     *
     * A quantidade valida e um inteiro de 1 ate a quantidade atual em estoque, inclusive.
     *
     * @return true se a saida foi registrada (estoque foi diminuido);
     *         false se o produto nao existe ou se a quantidade e invalida
     *         (nesse caso o estoque NAO muda).
     */
    public boolean registrarSaida(String codigo, int quantidade) {
        Produto produto = obterProdutoPorCodigo(codigo);
        if (produto == null) {
            return false; // produto inexistente
        }
        if (quantidade <= 0 || quantidade > produto.quantidadeEmEstoque) {
            return false; // quantidade fora da faixa permitida
        }
        produto.quantidadeEmEstoque -= quantidade;
        return true;
    }

    /**
     * Cadastra um produto a partir de parametros, sem Scanner.
     * Serve para montar o cenario dos testes JUnit e para a API HTTP.
     *
     * @return o produto criado, ou null se algum dado for invalido.
     */
    public Produto cadastrarProduto(String nome, String codigo, int quantidadeEmEstoque,
                                    double precoCusto, double precoVenda, Fornecedor fornecedor) {
        if (nome == null || nome.isBlank()) {
            return null;
        }
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        if (quantidadeEmEstoque < 0 || precoCusto < 0 || precoVenda < 0) {
            return null;
        }
        if (fornecedor == null) {
            return null;
        }

        Produto novoProduto = new Produto(nome, codigo, quantidadeEmEstoque, precoCusto, precoVenda, fornecedor);
        estoque.add(novoProduto);
        fornecedor.produtosFornecidos.add(novoProduto);
        return novoProduto;
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
