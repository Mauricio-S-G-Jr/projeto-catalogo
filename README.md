# Catálogo de livros, séries e filmes

Aplicação web em Java com JSP + Servlets para cadastrar, consultar, editar e excluir itens do catálogo.

## Funcionalidades

- Cadastro de novos itens
- Listagem dos itens cadastrados
- Detalhes de cada item
- Edição das informações
- Exclusão de itens
- Busca por título ou autor/diretor
- Persistência em banco MySQL

## Tecnologias

- Java 17
- Maven
- Servlet + JSP
- MySQL 8 em Docker

## Pré-requisitos

- JDK 11+ (use o caminho do JDK completo em `JAVA_HOME`)
- Maven 3.9+
- Docker e Docker Compose

## Banco de dados em Docker

```bash
docker compose up -d
```

A aplicação usa as seguintes conexões por padrão:

- URL: `jdbc:mysql://localhost:3306/catalogo_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- Usuário: `catalogo`
- Senha: `catalogo123`

## Executar a aplicação

O Cargo inicia um Tomcat 10 embarcado; não é necessário instalar o Tomcat separadamente. Se o Maven foi instalado no diretório do usuário e o sistema seleciona versões diferentes para `java` e `javac`, configure ambos antes de executar:

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-temurin-jdk
export PATH="$JAVA_HOME/bin:$HOME/.local/apache-maven-3.9.9/bin:$PATH"
mvn clean package cargo:run
```

Se o JDK ou o Maven estiver instalado em outro caminho, ajuste `JAVA_HOME` e o `PATH` de acordo.

Depois acesse:

- http://localhost:8080/

## Estrutura principal

- `src/main/java/br/edu/unisinos/catalogo/model/ItemCatalogo.java`
- `src/main/java/br/edu/unisinos/catalogo/dao/ItemDAO.java`
- `src/main/java/br/edu/unisinos/catalogo/servlet/ItemServlet.java`
- `src/main/webapp/WEB-INF/views/`

## Observação

O projeto foi desenvolvido seguindo o escopo da proposta de CRUD web com persistência em banco MySQL.
