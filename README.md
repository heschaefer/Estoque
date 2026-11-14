# Controle de Estoque: trabalho de Qualidade e Teste de Software (B2)

**Grupo:** Henryque, Ederson, Rafaela (SINF) · UNIMATER

Sistema de controle de estoque em Java (console), desenvolvido em outra disciplina.
No trabalho de QTS analisamos e testamos a **regra de saída de estoque**:

> A quantidade de saída deve ser um inteiro de 1 até a quantidade atual em estoque.
> Fora disso, a saída é recusada e o estoque não muda.

| Parte | Ferramenta | Técnica / ideia | Nível (Modelo V) |
|---|---|---|---|
| Qualidade | GitHub Actions | PDCA, garantia da qualidade | (executa o Teste 1 a cada push) |
| Teste 1 | JUnit 5 | Valores limite + particionamento de equivalência | Componente |
| Teste 2 | Postman | Teste funcional via API HTTP | Sistema |

Resultados e esperado × observado: **[`evidencias/`](evidencias/README.md)**.

## Onde estão os arquivos

| Arquivo | O que é |
|---|---|
| `Estoque/src/main/java/EstoqueService.java` | Contém a regra: método `registrarSaida(codigo, quantidade)` |
| `Estoque/src/test/java/EstoqueServiceTest.java` | Teste 1 (JUnit) |
| `Estoque/src/main/java/ApiMain.java` | API HTTP mínima usada pelo Teste 2 |
| `postman/Estoque.postman_collection.json` | Teste 2 (coleção do Postman) |
| `.github/workflows/testes.yml` | Workflow do GitHub Actions (Qualidade) |
| `Estoque/pom.xml` | Configuração do Maven (dependências e plugins) |
| `evidencias/` | Saídas, relatórios e prints das execuções |

## Pré-requisitos

- Java 17 ou mais novo (`java -version`)
- Maven 3.9+ (`mvn -v`)
- Postman, ou Node.js para rodar a coleção com Newman

## Como repetir

### Teste 1: JUnit (componente)

```
cd Estoque
mvn test
```

Esperado: `Tests run: 6, Failures: 0, Errors: 0, Skipped: 0` e `BUILD SUCCESS`.
Relatórios em `Estoque/target/surefire-reports/`.

### Teste 2: Postman (sistema)

1. Subir a API na porta 7070 e **deixar o terminal aberto**:
   ```
   cd Estoque
   mvn exec:java "-Dexec.mainClass=ApiMain"
   ```
   Para conferir: abrir `http://localhost:7070/produtos/x` deve mostrar `{"erro":"Produto não encontrado"}`.
2. No Postman: **Import** → `postman/Estoque.postman_collection.json` → botão direito na coleção → **Run collection**, com as requisições na ordem.

   Ou pelo terminal, na raiz do repositório:
   ```
   npx newman run postman/Estoque.postman_collection.json
   ```

Esperado: 6 requisições, 18 asserções, 0 falhas. Para parar a API: `Ctrl+C`.

### Qualidade: GitHub Actions

O workflow `.github/workflows/testes.yml` roda `mvn test` (Java 17) a cada push e pull request
e publica os relatórios do Surefire como artefato (`relatorios-surefire`).
Resultados na aba **Actions** do repositório.

### Gerar as evidências de novo (opcional)

Com a API rodando em outro terminal, na raiz do repositório:

```
npx -p newman -p newman-reporter-htmlextra newman run postman/Estoque.postman_collection.json -r cli,htmlextra --reporter-htmlextra-export evidencias/postman-relatorio.html
```

## Rodar o sistema original (console)

```
cd Estoque
mvn exec:java
```

O manual do menu está em [`Estoque/README.md`](Estoque/README.md).
