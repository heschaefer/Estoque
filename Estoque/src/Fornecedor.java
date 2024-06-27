import java.util.ArrayList;
import java.util.List;

public class Fornecedor {
    public String nome;
    public String cnpj;
    public List<Produto> produtosFornecidos = new ArrayList<>();

    public Fornecedor(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", produtosFornecidos=" + produtosFornecidos +
                '}';
    }
}
