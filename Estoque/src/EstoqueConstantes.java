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
    private static final String OPCOES_MENU = """
    === Sistema de Controle de Estoque ===
    1. Cadastrar novo produto
    2. Registrar entrada de produtos
    3. Registrar saída de produtos (venda)
    4. Consultar estoque de um produto
    5. Listar todos os produtos
    6. Listar produtos com estoque baixo
    7. Cadastrar novo fornecedor
    8. Listar todos os fornecedores
    9. Listar produtos de um fornecedor
    10. Atualizar informações de um produto
    11. Atualizar informações de um fornecedor
    12. Remover produto
    13. Remover fornecedor
    0. Sair
    
    Qual deseja acessar?
    """;
    
    private static final String FORNECEDOR_EXISTENTE = "Já existe um fornecedor com este CNPJ!";
    private static final String FORNECEDOR_CADASTRADO = "Fornecedor cadastrado com sucesso!";
    private static final String OPCOES_1_2 = "Deseja usar um fornecedor existente (1) ou cadastrar um novo(2)?";
    private static final String FORNECEDOR_NAO_ENCONTRADO = "Fornecedor não encontrado.";
    private static final String OPCOES_ATUALIZAR_PRODUTO = """
    === Atualizar Produto ===
    1. Nome
    2. Quantidade em Estoque
    3. Preço de Custo
    4. Preço de Venda
    0. Voltar
    """;
    private static final String PRODUTO_ATUALIZADO = "Produto atualizado com sucesso!";
    private static final String MENU_ATUALIZAR_FORNECEDOR = """
    "\n=== Atualizar Fornecedor ===
    1. Nome
    0. Voltar

    Escolha o dado que deseja atualizar:
    """;
    private static final String FORNECEDOR_ATUALIZADO = "Fornecedor atualizado com sucesso!";
    private static final String CONFIRMAR_REMOCAO_PRODUTO = "Deseja realmente remover o produto %s? (s/n): ";
    private static final String PRODUTO_REMOVIDO = "Produto removido com sucesso!";
    private static final String REMOCAO_CANCELADA = "Remoção cancelada.";
    private static final String CONFIRMAR_REMOCAO_FORNECEDOR = "Deseja realmente remover o fornecedor %s? (s/n): ";
    private static final String FORNECEDOR_REMOVIDO = "Fornecedor removido com sucesso!";
    private static final String FORNECEDOR_COM_PRODUTOS = "Não é possível remover o fornecedor, pois ele possui produtos cadastrados.";

    public static void exibirMenu() {
        System.out.println(OPCOES_MENU);
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
    public static void exibirPromptFornecedorExistente(){
        System.out.println(FORNECEDOR_EXISTENTE);
    }
    public static void exibirPromptFornecedorCadastrado(){
        System.out.println(FORNECEDOR_CADASTRADO);
    }
    public static void exibirPromptOpcoes(){
        System.out.println(OPCOES_1_2);
    }
    public static void exibirPromptFornecedorNaoEncontrado(){
        System.out.println(FORNECEDOR_NAO_ENCONTRADO);
    }
    public static void exibirPromptOpcaoAtualizarProduto(){
        System.out.println(OPCOES_ATUALIZAR_PRODUTO);
    }
    public static void exibirPromptProdutoAtualizado(){
        System.out.println(PRODUTO_ATUALIZADO);
    }
    public static void exibirPromptMenuAtualizarFornecedor(){
        System.out.println(MENU_ATUALIZAR_FORNECEDOR);
    }
    public static void exibirPromptFornecedorAtualizado(){
        System.out.println(FORNECEDOR_ATUALIZADO);
    }
    public static void exibirPromptProdutoRemovido(){
        System.out.println(PRODUTO_REMOVIDO);
    }
    public static void exibirPromptRemocaoCancelada(){
        System.out.println(REMOCAO_CANCELADA);
    }
    public static void exibirPromptFornecedorRemovido(){
        System.out.println(FORNECEDOR_REMOVIDO);
    }
    public static void exibirPromptFornecedorComProdutos(){
        System.out.println(FORNECEDOR_COM_PRODUTOS);
    }
    public static void exibirPromptConfirmarRemocaoProduto(String nomeProduto) {
        System.out.printf(CONFIRMAR_REMOCAO_PRODUTO, nomeProduto);
    }
    public static void exibirPromptConfirmarRemocaoFornecedor(String nomeFornecedor) {
        System.out.printf(CONFIRMAR_REMOCAO_FORNECEDOR, nomeFornecedor);
    }
}
