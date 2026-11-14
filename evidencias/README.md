# Evidências: trabalho de QTS (B2)

Registros das execuções da **regra de saída de estoque**:

> A quantidade de saída deve ser um inteiro de 1 até a quantidade atual em estoque.
> Fora disso, a saída é recusada e o estoque não muda.

## Resumo dos resultados

| Parte | Ferramenta | Nível | Resultado | Arquivos |
|---|---|---|---|---|
| Qualidade | GitHub Actions | (executa o Teste 1) | ✅ Sucesso | `github-actions-run.txt` |
| Teste 1 | JUnit 5 | Componente | ✅ 6 testes, 0 falhas | `junit-mvn-test.txt`, `surefire-reports/` |
| Teste 2 | Postman (Postman CLI e Newman) | Sistema | ✅ 6 requisições, 18 asserções, 0 falhas | `postman-cli.txt` (Postman CLI 1.90.0, 14/11), `postman-relatorio.html`, `postman-newman.txt`, `postman-junit.xml` |

## Condições da execução local

| Item | Valor |
|---|---|
| Data | 08/11/2026 |
| Versão do sistema | commit `c20e900` (branch `main`) |
| Sistema operacional | Windows 11 Home |
| Java | Oracle JDK 26.0.2 (compilando para Java 17, `maven.compiler.release=17`) |
| Maven | 3.9.9 |
| Node.js / Newman | Node v24.19.0 / Newman 6.2.1 (+ newman-reporter-htmlextra 1.23.1) |
| API | `ApiMain` em `http://localhost:7070`, dados só em memória |

**Condições do CI (GitHub Actions):** `ubuntu-latest`, Java 17 Temurin, Maven, disparado a cada push e pull request.

## Esperado × observado

### Teste 1: JUnit (`Estoque/src/test/java/EstoqueServiceTest.java`)

Cenário: produto `123` com estoque inicial **10**, recriado antes de cada teste (`@BeforeEach`).

| Caso | Técnica | Esperado | Observado |
|---|---|---|---|
| Saída de -1 | Partição inválida (negativos) | Recusa, estoque 10 | ✅ Recusa, estoque 10 |
| Saída de 0 | Limite inferior - 1 | Recusa, estoque 10 | ✅ Recusa, estoque 10 |
| Saída de 1 | Limite inferior | Aceita, estoque 9 | ✅ Aceita, estoque 9 |
| Saída de 10 | Limite superior | Aceita, estoque 0 | ✅ Aceita, estoque 0 |
| Saída de 11 | Limite superior + 1 | Recusa, estoque 10 | ✅ Recusa, estoque 10 |
| Código 999 (inexistente) | Produto inexistente | Recusa, estoque do 123 continua 10 | ✅ Recusa, estoque 10 |

### Teste 2: Postman (`postman/Estoque.postman_collection.json`)

| Passo | Requisição | Esperado | Observado |
|---|---|---|---|
| 1 | `POST /produtos` (estoque 10) | 201, estoque 10 | ✅ 201, estoque 10 |
| 2 | `POST /produtos/{codigo}/saida` com 11 | 400 "Quantidade de saída inválida" | ✅ 400, mensagem esperada |
| 3 | `GET /produtos/{codigo}` | 200, estoque continua 10 | ✅ 200, estoque 10 |
| 4 | `POST .../saida` com 10 | 200, estoque 0 | ✅ 200, estoque 0 |
| 5 | `POST .../saida` com 1 (estoque zerado) | 400 | ✅ 400 |
| 6 | `GET /produtos/{codigo}` | 200, estoque 0 (não negativo) | ✅ 200, estoque 0 |

## O que não foi verificado

- O **menu do console** e a mensagem "Quantidade de saída inválida!" impressa nele (o Teste 2 passa pela API, não pelo menu).
- Entrada de **texto não numérico** na quantidade.
- As **outras funções** do sistema (entrada, cadastro, atualização, remoção, listagens).
- **Persistência**: os dados ficam só em memória.
- **Desempenho e carga**.
- A coleção do Postman **não roda no GitHub Actions**; só o JUnit roda lá.

## Como abrir

- `postman-relatorio.html`: abrir no navegador (dois cliques). É o relatório visual do Postman.
- `surefire-reports/`: relatórios do JUnit gerados pelo Maven (`.txt` e `.xml`).
- `prints/`: capturas de tela (ver `prints/LEIA-ME.md`).

## Como gerar de novo

Ver o `README.md` na raiz do repositório.
