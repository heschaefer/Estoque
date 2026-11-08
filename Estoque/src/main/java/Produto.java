public class Produto {
    public String nome;
    public String codigo;
    public int quantidadeEmEstoque;
    public double precoCusto;
    public double precoVenda;
    public Fornecedor fornecedor;

    public Produto(String nome, String codigo, int quantidadeEmEstoque, double precoCusto, double precoVenda, Fornecedor fornecedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "\nCodigo: " + codigo +
                "\nQuantidade em estoque: " + quantidadeEmEstoque +
                "\nPreço de custo: " + precoCusto +
                "\nPreço de venda: " + precoVenda +
                "\nFornecedor: "+ fornecedor.nome;
    }
}