import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste de componente (JUnit 5) da regra de saida de estoque.
 *
 * REGRA SOB TESTE — EstoqueService.registrarSaida(codigo, quantidade):
 *   a quantidade de saida deve ser um inteiro de 1 ate a quantidade atual em
 *   estoque, inclusive. Fora dessa faixa a saida e recusada e o estoque NAO muda.
 *
 * TECNICA USADA — analise de valor limite + particionamento de equivalencia.
 * Com um produto de estoque 10, a faixa valida e [1, 10]. Logo:
 *
 *   quantidade:   -1      0   |   1  ...  10   |   11
 *               |-- invalida --|--- valida ---|-- invalida --|
 *                       ^   ^     ^      ^      ^
 *                       |   |     |      |      limite superior + 1
 *                       |   |     |      limite superior
 *                       |   |     limite inferior
 *                       |   limite inferior - 1
 *                       particao dos negativos
 *
 * Cada teste confere DUAS coisas: o valor retornado pelo metodo E a quantidade
 * final em estoque. Conferir so o retorno nao provaria que o estoque ficou intacto
 * numa recusa, nem que foi debitado corretamente numa aceitacao.
 */
@DisplayName("EstoqueService - regra de saida de estoque")
class EstoqueServiceTest {

    /** Codigo do produto usado no cenario de todos os testes. */
    private static final String CODIGO = "123";

    /** Estoque inicial do cenario. Mudar aqui muda a faixa valida de todos os testes. */
    private static final int ESTOQUE_INICIAL = 10;

    private EstoqueService estoqueService;

    /**
     * Roda ANTES de cada teste, criando um cenario novo e isolado:
     * um produto de codigo "123" com 10 unidades em estoque.
     * Como cada teste ganha a sua propria instancia de EstoqueService,
     * uma saida registrada em um teste nao afeta os demais.
     */
    @BeforeEach
    void criarProdutoComEstoqueDez() {
        estoqueService = new EstoqueService();
        Fornecedor fornecedor = new Fornecedor("Fornecedor Teste", "00000000000000");

        Produto produto = estoqueService.cadastrarProduto(
                "Camisa", CODIGO, ESTOQUE_INICIAL, 40.0, 50.0, fornecedor);

        // Se o cenario nao foi montado, qualquer resultado abaixo seria enganoso.
        assertNotNull(produto, "O cenario de teste deveria ter cadastrado o produto");
        assertEquals(ESTOQUE_INICIAL, estoqueEmEstoque(),
                "O produto deveria comecar com " + ESTOQUE_INICIAL + " unidades");
    }

    @Test
    @DisplayName("Saida de 0 deve ser recusada e o estoque deve continuar 10")
    void saidaDeZeroDeveSerRecusada() {
        boolean registrou = estoqueService.registrarSaida(CODIGO, 0);

        assertFalse(registrou, "Saida de 0 unidades deveria ser recusada");
        assertEquals(10, estoqueEmEstoque(), "O estoque nao pode mudar quando a saida e recusada");
    }

    @Test
    @DisplayName("Saida de 1 (limite inferior valido) deve ser aceita e o estoque deve ficar 9")
    void saidaDeUmDeveSerAceita() {
        boolean registrou = estoqueService.registrarSaida(CODIGO, 1);

        assertTrue(registrou, "Saida de 1 unidade deveria ser aceita");
        assertEquals(9, estoqueEmEstoque(), "O estoque deveria cair de 10 para 9");
    }

    @Test
    @DisplayName("Saida de 10 (todo o estoque) deve ser aceita e o estoque deve ficar 0")
    void saidaDeTodoOEstoqueDeveSerAceita() {
        boolean registrou = estoqueService.registrarSaida(CODIGO, 10);

        assertTrue(registrou, "Saida igual ao estoque atual deveria ser aceita");
        assertEquals(0, estoqueEmEstoque(), "O estoque deveria ficar zerado");
    }

    @Test
    @DisplayName("Saida de 11 (uma unidade acima do estoque) deve ser recusada e o estoque deve continuar 10")
    void saidaMaiorQueOEstoqueDeveSerRecusada() {
        boolean registrou = estoqueService.registrarSaida(CODIGO, 11);

        assertFalse(registrou, "Saida maior que o estoque deveria ser recusada");
        assertEquals(10, estoqueEmEstoque(), "O estoque nao pode mudar quando a saida e recusada");
    }

    @Test
    @DisplayName("Saida negativa (-1) deve ser recusada e o estoque deve continuar 10")
    void saidaNegativaDeveSerRecusada() {
        boolean registrou = estoqueService.registrarSaida(CODIGO, -1);

        assertFalse(registrou, "Saida negativa deveria ser recusada");
        assertEquals(10, estoqueEmEstoque(), "O estoque nao pode mudar quando a saida e recusada");
    }

    @Test
    @DisplayName("Saida com codigo inexistente deve ser recusada e nao pode afetar o estoque existente")
    void saidaComCodigoInexistenteDeveSerRecusada() {
        boolean registrou = estoqueService.registrarSaida("999", 1);

        assertFalse(registrou, "Saida de um produto inexistente deveria ser recusada");
        assertEquals(10, estoqueEmEstoque(),
                "O estoque do produto 123 nao pode ser afetado por uma saida do produto 999");
    }

    /** Atalho para ler a quantidade atual em estoque do produto do cenario. */
    private int estoqueEmEstoque() {
        return estoqueService.obterProdutoPorCodigo(CODIGO).quantidadeEmEstoque;
    }
}
