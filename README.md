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
│   │   │       │   ├── PessoaController.java
│   │   │       │   ├── PessoaService.java
│   │   │       │   └── PessoaRepository.java
│   │   │       └── Tarefas/
│   │   │           ├── TarefasModel.java
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

## 🔌 Endpoints da API

### Pessoas

#### Criar Pessoa
```
POST /pessoas/criar
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com",
  "idade": 30,
  "tarefas": {
    "id": 1
  }
}
```

#### Listar Todas as Pessoas
```
GET /pessoas/listar
```

#### Buscar Pessoa por ID
```
GET /pessoas/listar/{id}
```

#### Atualizar Pessoa
```
PUT /pessoas/alterar/{id}
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "email": "joao.novo@email.com",
  "idade": 31,
  "tarefas": {
    "id": 1
  }
}
```

#### Deletar Pessoa
```
DELETE /pessoas/deletar/{id}
```

### Tarefas

#### Criar Tarefa
```
POST /tarefas/criar
```
*Nota: Endpoint em desenvolvimento*

#### Listar Todas as Tarefas
```
GET /tarefas/listar
```

#### Buscar Tarefa por ID
```
GET /tarefas/listar/{id}
```

#### Atualizar Tarefa
```
PUT /tarefas/alterarID
```
*Nota: Endpoint em desenvolvimento*

#### Deletar Tarefa
```
DELETE /tarefas/deletarID
```
*Nota: Endpoint em desenvolvimento*

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
- Alguns endpoints de Tarefas ainda estão em desenvolvimento (retornam mensagens de texto)

## 🔄 Próximos Passos

- [ ] Implementar completamente os endpoints de criação, atualização e deleção de tarefas
- [ ] Adicionar autenticação JWT
- [ ] Implementar tratamento de erros personalizado
- [ ] Adicionar testes unitários e de integração
- [ ] Documentar API com Swagger/OpenAPI
- [ ] Implementar DTOs

## 👤 Autor

Projeto desenvolvido para fins de estudo e consolidação de conhecimentos em Spring Boot e desenvolvimento backend.

---

**Versão:** 0.0.1-SNAPSHOT
