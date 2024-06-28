import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FornecedoresService {
    private final List<Fornecedor> fornecedores = new ArrayList<>();

    // Cadastrar um novo fornecedor
    public Fornecedor cadastrarFornecedor(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptNomeFornecedor();
            String nome = scannerString(scanner);
    
            EstoqueConstantes.exibirPromptCnpjFornecedor();
            String cnpj = scannerString(scanner);
    
            // Verifica se o CNPJ já existe usando Stream
            boolean cnpjExiste = cnpjJaCadastrado(cnpj);
    
            if (cnpjExiste) {
                EstoqueConstantes.exibirPromptFornecedorExistente();
                return null;
            }
            //Adidiciona o novo fornecedor al ista
            Fornecedor novoFornecedor = new Fornecedor(nome, cnpj);
            fornecedores.add(novoFornecedor);
            EstoqueConstantes.exibirPromptFornecedorCadastrado();
            return novoFornecedor;
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner);
            return null;
        }
    }
    // Buscar um fornecedor pelo CNPJ
    public Fornecedor buscarFornecedorPorCNPJ(String cnpj) {
        return fornecedores.stream()
                           .filter(f -> f.cnpj.equals(cnpj))
                           .findFirst()
                           .orElse(null);
    }

    // Atualizar informações de um fornecedor (por CNPJ)
    public void atualizarFornecedor(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCnpjFornecedor();
            String cnpj = scannerString(scanner);
    
            Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);
            if (fornecedor == null) {
                EstoqueConstantes.exibirPromptFornecedorNaoEncontrado();
                return;
            }
            EstoqueConstantes.exibirPromptMenuAtualizarFornecedor();
            int opcao = scannerInt(scanner);
            limpabuffer(scanner); // Consumir a quebra de linha
    
            switch (opcao) {
                case 1 -> {
                    EstoqueConstantes.exibirPromptNomeFornecedor();
                    fornecedor.nome = scannerString(scanner);
                }
                case 0 -> {
                    return; // Volta ao menu principal
                }
                default -> EstoqueConstantes.exibirPromptEntradaInvalida();
            }
    
            EstoqueConstantes.exibirPromptFornecedorAtualizado();
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner);
        }
    }

    // Remover um fornecedor (por CNPJ)
    public void removerFornecedor(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCnpjFornecedor();
            String cnpj = scannerString(scanner);
    
            Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);
            if (fornecedor == null) {
                EstoqueConstantes.exibirPromptFornecedorNaoEncontrado();
                return;
            }
    
            // Verificar se o fornecedor possui produtos cadastrados
            if (!fornecedor.produtosFornecidos.isEmpty()) {
                EstoqueConstantes.exibirPromptFornecedorComProdutos();
                return;
            }
    
            EstoqueConstantes.exibirPromptConfirmarRemocaoFornecedor(fornecedor.nome);
            String confirmacao = scanner.nextLine();
    
            if (confirmacao.equalsIgnoreCase("s")) {
                fornecedores.removeIf(f -> f.cnpj.equals(cnpj)); // Remove o fornecedor com o CNPJ fornecido
                EstoqueConstantes.exibirPromptFornecedorRemovido();
            } else {
                EstoqueConstantes.exibirPromptRemocaoCancelada();
            }
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner);
        }
    }

    // Listar todos os fornecedores cadastrados
    public List<Fornecedor> listarTodosFornecedores() {
        return new ArrayList<>(fornecedores); // Retorna uma cópia da lista
    }

    // Listar produtos fornecidos por um determinado fornecedor (por CNPJ)
    public void listarProdutosFornecedor(Scanner scanner) {
        try {
            EstoqueConstantes.exibirPromptCnpjFornecedor();
            String cnpj = scannerString(scanner);

            Fornecedor fornecedor = buscarFornecedorPorCNPJ(cnpj);

            if (fornecedor == null) {
                EstoqueConstantes.exibirPromptFornecedorNaoEncontrado();
                return;
            }

            System.out.println("\n=== Produtos do Fornecedor " + fornecedor.nome + " ===");
            fornecedor.produtosFornecidos.stream() // Cria um stream dos produtos do fornecedor
                   .forEach(produto -> { // Itera sobre os produtos e os exibe
                       System.out.println("------------------------");
                       System.out.println(produto);
                       System.out.println("------------------------");
                   });
        } catch (InputMismatchException e) {
            EstoqueConstantes.exibirPromptEntradaInvalida();
            limpabuffer(scanner);
        }
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
    private boolean cnpjJaCadastrado(String cnpj) {
        return fornecedores.stream()
                           .anyMatch(f -> f.cnpj.equals(cnpj));
    }
}
