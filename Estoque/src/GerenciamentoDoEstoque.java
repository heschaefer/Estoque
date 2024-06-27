import java.util.ArrayList;
import java.util.List;

public class GerenciadorEstoque {
    private final List<Produto> estoque = new ArrayList<>();

    // Adicionar um produto ao estoque
    public void cadastrarProduto(Produto produto) {
        estoque.add(produto);
    }

    // Remover um produto do estoque (por código)
    public void removerProduto(String codigo) {
        estoque.removeIf(p -> p.codigo.equals(codigo)); // Remove o primeiro produto encontrado com o código
    }

    // Atualizar a quantidade em estoque de um produto (por código)
    public void registrarEntradaProduto(String codigo, int novaQuantidade) {
        for (Produto p : estoque) {
            if (p.codigo.equals(codigo)) {
                p.quantidadeEmEstoque = novaQuantidade;
                break; // Sai do loop após encontrar o produto
            }
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
}
