import java.util.InputMismatchException;
import java.util.Scanner;
public class MenuService {
    public void fullMenu(Scanner scanner, EstoqueService estoqueService, FornecedoresService fornecedoresService){
        int opcao = -1;
            do {
                EstoqueConstantes.exibirMenu();
            try{
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1 -> estoqueService.cadastrarProduto(scanner, fornecedoresService); //
                case 2 -> estoqueService.registrarEntradaProduto(scanner); //
                case 3 -> estoqueService.registrarSaidaProduto(scanner);
                case 4 -> estoqueService.consultarEstoqueProduto(scanner);
                case 5 -> estoqueService.listarTodosProdutos();
                case 6 -> estoqueService.listarProdutosEstoqueBaixo(scanner);
                case 7 -> fornecedoresService.cadastrarFornecedor(scanner);
                case 8 -> fornecedoresService.listarTodosFornecedores();
                case 9 -> fornecedoresService.listarProdutosFornecedor(scanner);
                case 10 -> estoqueService.atualizarProduto(scanner);
                case 11 -> fornecedoresService.atualizarFornecedor();
                case 12 -> estoqueService.removerProduto(scanner);
                case 13 -> fornecedoresService.removerFornecedor(scanner);
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close(); // Fechar o scanner ao sair do sistema
    }
    }