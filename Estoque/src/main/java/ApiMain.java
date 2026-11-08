import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * API HTTP minima do controle de estoque, usada para o teste de SISTEMA (Postman).
 *
 * Esta classe NAO substitui a Main do console: ela apenas expoe pela rede a mesma
 * regra de negocio que o console usa. A saida de estoque e decidida por
 * EstoqueService.registrarSaida(codigo, quantidade) — exatamente o mesmo metodo
 * que o teste de componente (EstoqueServiceTest) chama direto.
 *
 * Por isso o teste do Postman e o teste do JUnit cobrem a MESMA regra em dois
 * niveis diferentes: um chamando o metodo, outro passando por HTTP.
 *
 * Os dados ficam so em memoria: ao parar a API, tudo e perdido.
 *
 * Para subir:  mvn exec:java -Dexec.mainClass=ApiMain
 * Porta:       7070
 */
public class ApiMain {

    /** Porta onde a API sobe. A coleção do Postman usa baseUrl = http://localhost:7070 */
    public static final int PORTA = 7070;

    private static final String ERRO_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    private static final String ERRO_SAIDA_INVALIDA = "Quantidade de saída inválida";
    private static final String ERRO_DADOS_INVALIDOS = "Dados inválidos";

    public static void main(String[] args) {
        EstoqueService estoqueService = new EstoqueService();
        FornecedoresService fornecedoresService = new FornecedoresService();

        criarApp(estoqueService, fornecedoresService).start(PORTA);
    }

    /**
     * Monta as rotas da API sobre os services recebidos.
     * Esta separacao deixa a aplicacao montavel sem abrir a porta.
     */
    public static Javalin criarApp(EstoqueService estoqueService, FornecedoresService fornecedoresService) {
        Javalin app = Javalin.create();

        app.post("/produtos", ctx -> cadastrarProduto(ctx, estoqueService, fornecedoresService));
        app.post("/produtos/{codigo}/saida", ctx -> registrarSaida(ctx, estoqueService));
        app.get("/produtos/{codigo}", ctx -> consultarProduto(ctx, estoqueService));

        return app;
    }

    // ---------------------------------------------------------------- rotas

    /**
     * POST /produtos
     * Corpo: {codigo, nome, quantidade, precoCusto, precoVenda, cnpjFornecedor, nomeFornecedor}
     * Respostas: 201 com o produto criado | 400 com {erro}
     */
    private static void cadastrarProduto(Context ctx, EstoqueService estoqueService,
                                         FornecedoresService fornecedoresService) {
        ProdutoRequest req;
        try {
            req = ctx.bodyAsClass(ProdutoRequest.class);
        } catch (Exception e) {
            // JSON ausente ou mal formado
            ctx.status(400).json(new ErroResponse(ERRO_DADOS_INVALIDOS));
            return;
        }

        if (req == null || req.cnpjFornecedor() == null || req.cnpjFornecedor().isBlank()
                || req.nomeFornecedor() == null || req.nomeFornecedor().isBlank()) {
            ctx.status(400).json(new ErroResponse(ERRO_DADOS_INVALIDOS));
            return;
        }

        // Reaproveita o fornecedor se o CNPJ ja existir; senao cadastra um novo.
        Fornecedor fornecedor = fornecedoresService.buscarFornecedorPorCNPJ(req.cnpjFornecedor());
        if (fornecedor == null) {
            fornecedor = fornecedoresService.cadastrarFornecedor(req.nomeFornecedor(), req.cnpjFornecedor());
        }
        if (fornecedor == null) {
            ctx.status(400).json(new ErroResponse(ERRO_DADOS_INVALIDOS));
            return;
        }

        // A validacao dos dados do produto fica no service, nao aqui:
        // cadastrarProduto devolve null quando algum dado e invalido.
        Produto produto = estoqueService.cadastrarProduto(
                req.nome(), req.codigo(), req.quantidade(), req.precoCusto(), req.precoVenda(), fornecedor);

        if (produto == null) {
            ctx.status(400).json(new ErroResponse(ERRO_DADOS_INVALIDOS));
            return;
        }

        ctx.status(201).json(ProdutoResponse.de(produto));
    }

    /**
     * POST /produtos/{codigo}/saida
     * Corpo: {quantidade}
     * Respostas: 200 com {codigo, quantidadeEmEstoque}
     *          | 400 com {erro: "Quantidade de saída inválida"}
     *          | 404 com {erro: "Produto não encontrado"}
     */
    private static void registrarSaida(Context ctx, EstoqueService estoqueService) {
        String codigo = ctx.pathParam("codigo");

        // 404 tem prioridade: primeiro conferimos se o produto existe, para poder
        // diferenciar "produto inexistente" de "quantidade invalida" — registrarSaida
        // devolve false nos dois casos.
        if (estoqueService.obterProdutoPorCodigo(codigo) == null) {
            ctx.status(404).json(new ErroResponse(ERRO_PRODUTO_NAO_ENCONTRADO));
            return;
        }

        SaidaRequest req;
        try {
            req = ctx.bodyAsClass(SaidaRequest.class);
        } catch (Exception e) {
            ctx.status(400).json(new ErroResponse(ERRO_SAIDA_INVALIDA));
            return;
        }
        if (req == null) {
            ctx.status(400).json(new ErroResponse(ERRO_SAIDA_INVALIDA));
            return;
        }

        // AQUI esta o ponto do trabalho: a mesma regra testada pelo JUnit.
        boolean registrou = estoqueService.registrarSaida(codigo, req.quantidade());

        if (!registrou) {
            ctx.status(400).json(new ErroResponse(ERRO_SAIDA_INVALIDA));
            return;
        }

        Produto produto = estoqueService.obterProdutoPorCodigo(codigo);
        ctx.status(200).json(new SaidaResponse(produto.codigo, produto.quantidadeEmEstoque));
    }

    /**
     * GET /produtos/{codigo}
     * Respostas: 200 com o produto | 404 com {erro: "Produto não encontrado"}
     */
    private static void consultarProduto(Context ctx, EstoqueService estoqueService) {
        Produto produto = estoqueService.obterProdutoPorCodigo(ctx.pathParam("codigo"));

        if (produto == null) {
            ctx.status(404).json(new ErroResponse(ERRO_PRODUTO_NAO_ENCONTRADO));
            return;
        }

        ctx.status(200).json(ProdutoResponse.de(produto));
    }

    // ----------------------------------------------------------------- DTOs
    // DTO = objeto usado so para entrar/sair em JSON, separado das classes de dominio.

    /** Corpo esperado em POST /produtos. */
    public record ProdutoRequest(
            String codigo,
            String nome,
            int quantidade,
            double precoCusto,
            double precoVenda,
            String cnpjFornecedor,
            String nomeFornecedor) {
    }

    /** Corpo esperado em POST /produtos/{codigo}/saida. */
    public record SaidaRequest(int quantidade) {
    }

    /**
     * Produto devolvido em JSON.
     *
     * Note que o fornecedor aparece ACHATADO em dois campos de texto, e nao como
     * objeto. Isso e proposital: Produto aponta para Fornecedor, e Fornecedor tem
     * a lista produtosFornecidos que aponta de volta para Produto. Serializar o
     * objeto inteiro entraria nesse ciclo e estouraria a pilha.
     */
    public record ProdutoResponse(
            String codigo,
            String nome,
            int quantidadeEmEstoque,
            double precoCusto,
            double precoVenda,
            String cnpjFornecedor,
            String nomeFornecedor) {

        static ProdutoResponse de(Produto produto) {
            return new ProdutoResponse(
                    produto.codigo,
                    produto.nome,
                    produto.quantidadeEmEstoque,
                    produto.precoCusto,
                    produto.precoVenda,
                    produto.fornecedor == null ? null : produto.fornecedor.cnpj,
                    produto.fornecedor == null ? null : produto.fornecedor.nome);
        }
    }

    /** Resposta de uma saida aceita. */
    public record SaidaResponse(String codigo, int quantidadeEmEstoque) {
    }

    /** Corpo de qualquer resposta de erro: {"erro": "..."} */
    public record ErroResponse(String erro) {
    }
}
