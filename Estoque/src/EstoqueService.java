import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EstoqueService {
    private final List<Produto> estoque = new ArrayList<>();

    // Adicionar um produto ao estoque
    public void cadastrarProduto(Scanner scanner, FornecedoresService gerenciadorFornecedores) {
        try {
            EstoqueConstantes.exibirPromptNomeProduto();
            String nome = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptCodigoProduto();
            String codigo = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptQuantidadeEmEstoque();
            int quantidadeEmEstoque = scannerInt(scanner);
            scanner.nextLine(); // Consumir a quebra de linha
    
            EstoqueConstantes.exibirPromptPrecoDeCusto();
            double precoCusto = scannerDouble(scanner);
            scanner.nextLine(); // Consumir a quebra de linha
    
            EstoqueConstantes.exibirPromptPrecoDeVenda();
            double precoVenda = scannerDouble(scanner);
            scanner.nextLine(); // Consumir a quebra de linha
    
            EstoqueConstantes.exibirPromptNomeFornecedor();
            String nomeFornecedor = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptCnpjFornecedor();
            String cnpjFornecedor = scannerString(scanner);
    
            // Buscar ou criar o fornecedor
            Fornecedor fornecedor = gerenciadorFornecedores.buscarFornecedorPorCNPJ(cnpjFornecedor);
            if (fornecedor == null) {
                fornecedor = new Fornecedor(nomeFornecedor, cnpjFornecedor);
                gerenciadorFornecedores.cadastrarFornecedor(fornecedor);
            }
            // Criar o novo produto
        Produto novoProduto = new Produto(nome, codigo, quantidadeEmEstoque, precoCusto, precoVenda, fornecedor);

        // Adicionar o produto ao estoque e ao fornecedor
        estoque.add(novoProduto);
        gerenciadorFornecedores.adicionarProdutoFornecedor(cnpjFornecedor, novoProduto);

        EstoqueConstantes.exibirPromptProdutoSucesso();
    } catch (InputMismatchException e) {
        EstoqueConstantes.exibirPromptEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
    }
    }

    // Remover um produto do estoque (por código)
    public void removerProduto(String codigo) {
        estoque.removeIf(p -> p.codigo.equals(codigo)); // Remove o primeiro produto encontrado com o código
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
        scanner.nextLine(); // Consumir a quebra de linha

        if (quantidadeEntrada <= 0) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            return;
        }

        produto.quantidadeEmEstoque += quantidadeEntrada;
        EstoqueConstantes.exibirPromptEntradaValida();
    } catch (InputMismatchException e) {
        EstoqueConstantes.exibirPromptEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
    }
        }
    

    // Obter um produto pelo código
    public Produto obterProdutoPorCodigo(String codigo) {
        for (Produto p : estoque) {
            if (p.codigo.equals(codigo)) {
                return p;
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }

    // Listar todos os produtos em estoque
    public List<Produto> listarTodosProdutos() {
        return new ArrayList<>(estoque); // Retorna uma cópia da lista para evitar modificações externas
    }

    // Listar produtos com estoque baixo (abaixo de um limite)
    public List<Produto> listarProdutosEstoqueBaixo(int limite) {
        return estoque.stream()
                .filter(p -> p.quantidadeEmEstoque < limite)
                .toList();
    }

    // Calcular o valor total do estoque
    public double calcularValorTotalEstoque() {
        return estoque.stream()
                .mapToDouble(p -> p.quantidadeEmEstoque * p.precoCusto)
                .sum();
    }

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
}
