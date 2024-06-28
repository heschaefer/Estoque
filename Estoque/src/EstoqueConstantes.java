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
    public static void exibirPromptEntradaValida() {
        System.out.print(ENTRADA_VALIDA);
    }
}
