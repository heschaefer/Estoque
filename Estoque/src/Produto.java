public class Produto {
    public final String nome;
    public final String codigo;
    public final int quantidadeEmEstoque;
    public final double precoCusto;
    public final double precoVenda;

    public Produto(String nome, String codigo, int quantidadeEmEstoque, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                ", precoCusto=" + precoCusto +
                ", precoVenda=" + precoVenda +
                '}';
    }
}