public class Produto {
    public String nome;
    public String codigo;
    public int quantidadeEmEstoque;
    public double precoCusto;
    public double precoVenda;

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