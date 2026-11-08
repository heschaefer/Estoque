import java.util.Scanner;

public class Main {
public static void main(String[] args) {
    MenuService menuService = new MenuService();
    EstoqueService estoqueService = new EstoqueService();
    Scanner scanner = new Scanner(System.in);
    FornecedoresService fornecedoresService = new FornecedoresService();
    menuService.fullMenu(scanner, estoqueService, fornecedoresService);
}
}