# 📋 Sistema de Cadastro de Pessoas e Tarefas

Sistema CRUD desenvolvido em Spring Boot para gerenciamento de pessoas e suas tarefas. Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em desenvolvimento backend com Java e Spring Framework.

## 📖 Sobre o Projeto

Este sistema permite o cadastro e gerenciamento de pessoas e tarefas, onde cada pessoa pode estar associada a uma tarefa. O relacionamento entre as entidades é do tipo **um-para-muitos** (One-to-Many), onde uma tarefa pode ter várias pessoas associadas.

### Relacionamento entre Entidades

- **Tarefa** → **Pessoas**: Uma tarefa pode ter várias pessoas (One-to-Many)
- **Pessoa** → **Tarefa**: Cada pessoa está associada a uma tarefa (Many-to-One)

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.0**
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Flyway** - Controle de versão do banco de dados
- **Lombok** - Redução de boilerplate
- **SpringDoc OpenAPI (Swagger)** - Documentação interativa da API
- **Thymeleaf** - Template engine para views
- **Maven** - Gerenciamento de dependências

## 📁 Estrutura do Projeto

```
Cadastro/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/pessoalprojects/cadastro/
│   │   │       ├── CadastroApplication.java
│   │   │       ├── Pessoas/
│   │   │       │   ├── PessoaModel.java
│   │   │       │   ├── PessoaDTO.java
│   │   │       │   ├── PessoaMapper.java
│   │   │       │   ├── PessoaController.java
│   │   │       │   ├── PessoaService.java
│   │   │       │   └── PessoaRepository.java
│   │   │       └── Tarefas/
│   │   │           ├── TarefasModel.java
│   │   │           ├── TarefasDTO.java
│   │   │           ├── TarefasMapper.java
│   │   │           ├── TarefasController.java
│   │   │           ├── TarefasService.java
│   │   │           └── TarefasRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   └── test/
└── pom.xml
```

## 🗄️ Modelos de Dados

### PessoaModel
Representa uma pessoa cadastrada no sistema.

**Campos:**
- `id` (Long) - Identificador único (gerado automaticamente)
- `nome` (String) - Nome da pessoa
- `email` (String) - Email único da pessoa
- `idade` (int) - Idade da pessoa
- `cidade` (String) - Cidade onde a pessoa reside
- `tarefas` (TarefasModel) - Tarefa associada à pessoa (relacionamento Many-to-One)

**Tabela:** `tb_cadastro_de_pessoa`

### TarefasModel
Representa uma tarefa cadastrada no sistema.

**Campos:**
- `id` (Long) - Identificador único (gerado automaticamente)
- `nome` (String) - Nome da tarefa
- `dificuldade` (String) - Nível de dificuldade da tarefa
- `pessoas` (List<PessoaModel>) - Lista de pessoas associadas à tarefa (relacionamento One-to-Many)

**Tabela:** `tb_cadastro_tarefas`

## 📦 DTOs (Data Transfer Objects)

O projeto utiliza DTOs para transferência de dados entre as camadas, seguindo boas práticas de arquitetura.

### PessoaDTO
DTO utilizado para transferência de dados de Pessoa.

**Campos:**
- `id` (Long)
- `nome` (String)
- `email` (String)
- `idade` (int)
- `cidade` (String)
- `tarefas` (TarefasModel)

### TarefasDTO
DTO utilizado para transferência de dados de Tarefa.

**Campos:**
- `id` (Long)
- `nome` (String)
- `dificuldade` (String)
- `pessoas` (List<PessoaModel>)

## 🔄 Mappers

O projeto utiliza Mappers para conversão entre DTOs e Models:

- **PessoaMapper** - Converte entre `PessoaDTO` e `PessoaModel`
- **TarefasMapper** - Converte entre `TarefasDTO` e `TarefasModel`

## 🔌 Endpoints da API

> 💡 **Dica:** Todos os endpoints podem ser testados através do **Swagger UI** em `http://localhost:8080/swagger-ui/index.html#/`

### Pessoas

#### Criar Pessoa
```
POST /pessoas/criar
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "idade": 30,
  "cidade": "São Paulo",
  "tarefas": {
    "id": 1
  }
}
```

**Resposta:**
```json
{
  "message": "Pessoa criada com sucesso: João Silva"
}
```

#### Listar Todas as Pessoas
```
GET /pessoas/listar
```

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "idade": 30,
    "cidade": "São Paulo",
    "tarefas": {
      "id": 1,
      "nome": "Desenvolvimento",
      "dificuldade": "Média"
    }
  }
]
```

#### Buscar Pessoa por ID
```
GET /pessoas/listar/{id}
```

**Resposta (sucesso):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "idade": 30,
  "cidade": "São Paulo",
  "tarefas": {
    "id": 1,
    "nome": "Desenvolvimento",
    "dificuldade": "Média"
  }
}
```

**Resposta (não encontrado):**
```json
{
  "message": "ID não encontrado"
}
```

#### Atualizar Pessoa
```
PUT /pessoas/alterar/{id}
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "email": "joao.novo@email.com",
  "idade": 31,
  "cidade": "Rio de Janeiro",
  "tarefas": {
    "id": 1
  }
}
```

**Resposta (sucesso):**
```json
{
  "id": 1,
  "nome": "João Silva Atualizado",
  "email": "joao.novo@email.com",
  "idade": 31,
  "cidade": "Rio de Janeiro",
  "tarefas": {
    "id": 1
  }
}
```

**Resposta (não encontrado):**
```json
{
  "message": "ID não encontrado"
}
```

#### Deletar Pessoa
```
DELETE /pessoas/deletar/{id}
```

**Resposta (sucesso):**
```json
{
  "message": "Usuario(a): João Silva deletada com sucesso"
}
```

**Resposta (não encontrado):**
```json
{
  "message": "Id não encontrado para deleção"
}
```

### Tarefas

#### Criar Tarefa
```
POST /tarefas/criar
Content-Type: application/json

{
  "nome": "Desenvolvimento de API",
  "dificuldade": "Alta"
}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API",
  "dificuldade": "Alta",
  "pessoas": []
}
```

#### Listar Todas as Tarefas
```
GET /tarefas/listar
```

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Desenvolvimento de API",
    "dificuldade": "Alta",
    "pessoas": [
      {
        "id": 1,
        "nome": "João Silva",
        "email": "joao@email.com",
        "idade": 30,
        "cidade": "São Paulo"
      }
    ]
  }
]
```

#### Buscar Tarefa por ID
```
GET /tarefas/listar/{id}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API",
  "dificuldade": "Alta",
  "pessoas": []
}
```

#### Atualizar Tarefa
```
PUT /tarefas/alterar/{id}
Content-Type: application/json

{
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Média"
}
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Média",
  "pessoas": []
}
```

#### Deletar Tarefa
```
DELETE /tarefas/deletar/{id}
```

**Resposta:** `204 No Content` (sem corpo de resposta)

## 📚 Documentação Swagger

A API está documentada com **Swagger/OpenAPI** e pode ser acessada através da interface interativa:

### Acessar Swagger UI

Após iniciar a aplicação, acesse:

**URL:** `http://localhost:8080/swagger-ui/index.html#/`

### Recursos do Swagger

- 📖 Visualização completa de todos os endpoints
- 🧪 Teste interativo de todas as rotas
- 📋 Visualização dos modelos de dados (DTOs e Models)
- 🔍 Documentação automática dos parâmetros e respostas
- ✅ Validação de requisições em tempo real

### Screenshots do Swagger

 <img src="https://raw.githubusercontent.com/Giulianom95/Giulianom95/main/assets/Swagger.jpg" width="1250" style="vertical-align: middle;" /> 


## ⚙️ Configuração

### Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configuração do banco de dados. Configure as seguintes variáveis:

- `DATABASE_URL` - URL de conexão com o banco H2
- `DATABASE_USERNAME` - Usuário do banco de dados
- `DATABASE_PASSWORD` - Senha do banco de dados

### application.properties

O arquivo de configuração está localizado em `src/main/resources/application.properties` e contém:

- Configuração do H2 Console (habilitado)
- Configuração do JPA/Hibernate (ddl-auto: update)
- Configuração do Flyway para migrações de banco de dados

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+ (ou use o Maven Wrapper incluído: `mvnw` ou `mvnw.cmd`)

### Passos para Execução

1. **Clone o repositório** (se aplicável)

2. **Configure as variáveis de ambiente**:
   ```bash
   export DATABASE_URL=jdbc:h2:mem:cadastro
   export DATABASE_USERNAME=sa
   export DATABASE_PASSWORD=
   ```

   No Windows (PowerShell):
   ```powershell
   $env:DATABASE_URL="jdbc:h2:mem:cadastro"
   $env:DATABASE_USERNAME="sa"
   $env:DATABASE_PASSWORD=""
   ```

3. **Compile o projeto**:
   ```bash
   ./mvnw clean install
   ```
   ou no Windows:
   ```cmd
   mvnw.cmd clean install
   ```

4. **Execute a aplicação**:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou no Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

5. **Acesse a aplicação**:
   - API Base URL: `http://localhost:8080`
   - **Swagger UI:** `http://localhost:8080/swagger-ui/index.html#/`
   - H2 Console: `http://localhost:8080/h2-console`

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📝 Notas de Desenvolvimento

- O projeto utiliza **Lombok** para reduzir código boilerplate (getters, setters, construtores)
- O banco de dados **H2** é usado em memória, ideal para desenvolvimento e testes
- O **Flyway** está configurado para gerenciar migrações do banco de dados
- O projeto utiliza **DTOs** para separação de responsabilidades entre camadas
- **Mappers** são utilizados para conversão entre DTOs e Models
- A API está totalmente documentada com **Swagger/OpenAPI**

## 🔄 Próximos Passos

- [ ] Adicionar autenticação JWT
- [ ] Implementar tratamento de erros personalizado com Exception Handlers
- [ ] Adicionar testes unitários e de integração
- [ ] Implementar validações com Bean Validation
- [ ] Adicionar paginação nas listagens
- [ ] Implementar filtros e busca avançada

## 👤 Autor

Projeto desenvolvido para fins de estudo e consolidação de conhecimentos em Spring Boot e desenvolvimento backend.

**Giuliano M**

---

**Versão:** 0.0.1-SNAPSHOT
