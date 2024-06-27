public class MenuService {
        public static final String INICIO_MENU = "\n=== Sistema de Controle de Estoque ===";
        public static final String CADASTRAR_PRODUTO = "1. Cadastrar novo produto";
        public static final String REGISTRAR_ENTRADA = "2. Registrar entrada de produtos";
        public static final String REGISTRAR_SAIDA = "3. Registrar saída de produtos (venda)";
        public static final String CONSULTAR_ESTOQUE = "4. Consultar estoque de um produto";
        public static final String LISTAR_PRODUTOS = "5. Listar todos os produtos";
        public static final String LISTAR_ESTOQUE_BAIXO = "6. Listar produtos com estoque baixo";
        public static final String CADASTRAR_FORNECEDOR = "7. Cadastrar novo fornecedor";
        public static final String LISTAR_FORNECEDORES = "8. Listar todos os fornecedores";
        public static final String LISTAR_PRODUTOS_FORNECEDOR = "9. Listar produtos de um fornecedor";
        public static final String ATUALIZAR_PRODUTO = "10. Atualizar informações de um produto";
        public static final String ATUALIZAR_FORNECEDOR = "11. Atualizar informações de um fornecedor";
        public static final String REMOVER_PRODUTO = "12. Remover produto";
        public static final String REMOVER_FORNECEDOR = "13. Remover fornecedor";
        public static final String SAIR = "0. Sair";
        public void exibirMenu() {
            System.out.println("\n=== Sistema de Controle de Estoque ===");
            System.out.println(CADASTRAR_PRODUTO);
            System.out.println(REGISTRAR_ENTRADA);
            System.out.println(REGISTRAR_SAIDA);
            System.out.println(CONSULTAR_ESTOQUE);
            System.out.println(LISTAR_PRODUTOS);
            System.out.println(LISTAR_ESTOQUE_BAIXO);
            System.out.println(CADASTRAR_FORNECEDOR);
            System.out.println(LISTAR_FORNECEDORES);
            System.out.println(LISTAR_PRODUTOS_FORNECEDOR);
            System.out.println(ATUALIZAR_PRODUTO);
            System.out.println(ATUALIZAR_FORNECEDOR);
            System.out.println(REMOVER_PRODUTO);
            System.out.println(REMOVER_FORNECEDOR);
            System.out.println(SAIR);
        }
    }