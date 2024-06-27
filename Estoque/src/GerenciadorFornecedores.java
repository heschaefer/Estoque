import java.util.ArrayList;
import java.util.List;

public class GerenciadorFornecedores {
    private final List<Fornecedor> fornecedores = new ArrayList<>();

    // Cadastrar um novo fornecedor
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    // Buscar um fornecedor pelo CNPJ
    public Fornecedor buscarFornecedorPorCNPJ(String cnpj) {
        for (Fornecedor f : fornecedores) {
            if (f.cnpj.equals(cnpj)) {
                return f;
            }
        }
        return null; // Retorna null se o fornecedor não for encontrado
    }

    // Atualizar informações de um fornecedor (por CNPJ)
    public void atualizarFornecedor(String cnpj, Fornecedor novosDados) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).cnpj.equals(cnpj)) {
                fornecedores.set(i, novosDados);
                break; // Sai do loop após encontrar o fornecedor
            }
        }
    }

    // Remover um fornecedor (por CNPJ)
    public void removerFornecedor(String cnpj) {
        fornecedores.removeIf(f -> f.cnpj.equals(cnpj));
    }

    // Listar todos os fornecedores cadastrados
    public List<Fornecedor> listarTodosFornecedores() {
        return new ArrayList<>(fornecedores); // Retorna uma cópia da lista
    }

    // Listar produtos fornecidos por um determinado fornecedor (por CNPJ)
    public List<Produto> listarProdutosFornecedor(String cnpj) {
        Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);
        if (fornecedor != null) {
            return new ArrayList<>(fornecedor.produtosFornecidos); // Retorna uma cópia da lista de produtos
        }
        return new ArrayList<>(); // Retorna uma lista vazia se o fornecedor não for encontrado
    }

    // Adicionar um produto à lista de produtos fornecidos por um fornecedor
    public void adicionarProdutoFornecedor(String cnpj, Produto produto) {
        Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);
        if (fornecedor != null) {
            fornecedor.produtosFornecidos.add(produto);
        }
    }

    // Remover um produto da lista de produtos fornecidos por um fornecedor
    public void removerProdutoFornecedor(String cnpj, String codigoProduto) {
        Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);
        if (fornecedor != null) {
            fornecedor.produtosFornecidos.removeIf(p -> p.codigo.equals(codigoProduto));
        }
    }
}
