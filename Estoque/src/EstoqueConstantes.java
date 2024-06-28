public class EstoqueConstantes {
    private static final String NOME_DO_PRODUTO = "Nome do produto: ";
    private static final String CODIGO_DO_PRODUTO = "Código do produto: ";
    private static final String QUANTIDADE_EM_ESTOQUE = "Quantidade em estoque: ";
    private static final String PRECO_DE_CUSTO = "Preço de custo: ";
    private static final String PRECO_DE_VENDA = "Preço de venda: ";
    private static final String NOME_DO_FORNECEDOR = "Nome do fornecedor: ";
    private static final String CNPJ_DO_FORNECEDOR = "CNPJ do fornecedor: ";
    private static final String ENTRADA_INVALIDA = "Entrada inválida! Digite os dados corretamente.";
    private static final String PRODUTO_SUCESSO = "Produto cadastrado com sucesso!";
    private static final String NAO_ENCONTRADO = "Produto não encontrado!";
    private static final String QUANTIDADE_ENTRADA = "Digite a quantidade de produtos que entraram no estoque";
    private static final String ENTRADA_VALIDA = "Entrada de produto registrada com sucesso!";
    private static final String QUANTIDADE_SAIDA = "Digite a quantidade de produtos que saíram do estoque: ";
    private static final String SAIDA_VALIDA = "Saída de produto registrada com sucesso!";
    private static final String SAIDA_INVALIDA = "Quantidade de saída inválida!";
    private static final String LISTA_PRODUTOS = "\n=== Lista de Produtos ===";
    private static final String ESTOQUE_VAZIO = "O estoque está vazio.";
    private static final String ESTOQUE_BAIXO = "Digite o limite de estoque para considerar um produto com estoque baixo:";
    private static final String INICIO_MENU = "\n=== Sistema de Controle de Estoque ===";
    private static final String CADASTRAR_PRODUTO = "1. Cadastrar novo produto";
    private static final String REGISTRAR_ENTRADA = "2. Registrar entrada de produtos";
    private static final String REGISTRAR_SAIDA = "3. Registrar saída de produtos (venda)";
    private static final String CONSULTAR_ESTOQUE = "4. Consultar estoque de um produto";
    private static final String LISTAR_PRODUTOS = "5. Listar todos os produtos";
    private static final String LISTAR_ESTOQUE_BAIXO = "6. Listar produtos com estoque baixo";
    private static final String CADASTRAR_FORNECEDOR = "7. Cadastrar novo fornecedor";
    private static final String LISTAR_FORNECEDORES = "8. Listar todos os fornecedores";
    private static final String LISTAR_PRODUTOS_FORNECEDOR = "9. Listar produtos de um fornecedor";
    private static final String ATUALIZAR_PRODUTO = "10. Atualizar informações de um produto";
    private static final String ATUALIZAR_FORNECEDOR = "11. Atualizar informações de um fornecedor";
    private static final String REMOVER_PRODUTO = "12. Remover produto";
    private static final String REMOVER_FORNECEDOR = "13. Remover fornecedor";
    private static final String SAIR = "0. Sair";
    private static final String OPCAO_USUARIO = "/n Qual deseja acessar?";
    
    public static void exibirMenu() {
        System.out.println(INICIO_MENU);
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
        System.out.println(OPCAO_USUARIO);
    }
    public static void exibirPromptNomeProduto() {
        System.out.print(NOME_DO_PRODUTO);
    }
    public static void exibirPromptCodigoProduto() {
        System.out.print(CODIGO_DO_PRODUTO);
    }
    public static void exibirPromptQuantidadeEmEstoque() {
        System.out.print(QUANTIDADE_EM_ESTOQUE);
    }
    public static void exibirPromptPrecoDeCusto() {
        System.out.print(PRECO_DE_CUSTO);
    }
    public static void exibirPromptPrecoDeVenda() {
        System.out.print(PRECO_DE_VENDA);
    }
    public static void exibirPromptNomeFornecedor() {
        System.out.print(NOME_DO_FORNECEDOR);
    }
    public static void exibirPromptCnpjFornecedor() {
        System.out.print(CNPJ_DO_FORNECEDOR);
    }
    public static void exibirPromptEntradaInvalida() {
        System.out.print(ENTRADA_INVALIDA);
    }
    public static void exibirPromptProdutoSucesso() {
        System.out.print(PRODUTO_SUCESSO);
    }
    public static void exibirPromptNaoEncontrado() {
        System.out.print(NAO_ENCONTRADO);
    }
    public static void exibirPromptQuantidadeEntrada() {
        System.out.print(QUANTIDADE_ENTRADA);
    }
    public static void exibirPromptQuantidadeSaida() {
        System.out.print(QUANTIDADE_SAIDA);
    }
    public static void exibirPromptSaidaInvalida() {
        System.out.print(SAIDA_INVALIDA);
    }
    public static void exibirPromptSaidaValida() {
        System.out.print(SAIDA_VALIDA);
    }
    public static void exibirPromptEntradaValida() {
        System.out.print(ENTRADA_VALIDA);
    }
    public static void exibirPromptListaProdutos() {
        System.out.print(LISTA_PRODUTOS);
    }
    public static void exibirPromptEstoqueVazio() {
        System.out.print(ESTOQUE_VAZIO);
    }
    public static void exibirPromptEstoqueBaixo() {
        System.out.println(ESTOQUE_BAIXO);
    }
    
}
