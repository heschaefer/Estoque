import java.util.ArrayList;
import java.util.List;

public class Fornecedor {
    String nome;
    String cnpj;
    List<Produto> produtosFornecidos = new ArrayList<>();

    public Fornecedor(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome + 
                "\nCnpj: " + cnpj +
                "\nProdutos Fornecidos: " + produtosFornecidos; 
    }
}
