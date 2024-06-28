import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private GerenciadorEstoque gerenciadorEstoque;
    private GerenciadorFornecedores gerenciadorFornecedores;
    private Scanner scanner = new Scanner(System.in);-=

    public InterfaceEstoque(GerenciadorEstoque gerenciadorEstoque, GerenciadorFornecedores gerenciadorFornecedores) {
        this.gerenciadorEstoque = gerenciadorEstoque;
        this.gerenciadorFornecedores = gerenciadorFornecedores;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        MenuService menu = new MenuService();
        menu.fullMenu(scanner);
}
}