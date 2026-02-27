# 📋 Sistema de Cadastro de Pessoas e Tarefas

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-green?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue?logo=apachemaven)


Sistema CRUD completo desenvolvido em **Spring Boot** para gerenciamento de pessoas e tarefas. O projeto foi desenvolvido com o objetivo de consolidar conhecimentos em desenvolvimento backend com Java, arquitetura em camadas e boas práticas de desenvolvimento.

## 📖 Sobre o Projeto

Este é um sistema REST API que permite o cadastro, listagem, atualização e exclusão de pessoas e tarefas. O projeto demonstra o uso de padrões de design como **DTO (Data Transfer Object)**, **Mapper**, **Service Layer** e **Repository Pattern**, seguindo boas práticas de desenvolvimento.

### 🔗 Relacionamento entre Entidades

O sistema utiliza um relacionamento **One-to-Many (Um-para-Muitos)**:

```
Tarefas (1) ──── (N) Pessoas
  └─ Uma tarefa pode ter várias pessoas associadas
  └─ Cada pessoa está associada a apenas uma tarefa
```

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.0 | Framework web e injeção de dependência |
| **Spring Data JPA** | - | Persistência de dados e ORM |
| **H2 Database** | - | Banco de dados em memória |
| **Flyway** | - | Versionamento de schema do banco de dados |
| **Lombok** | - | Redução de código boilerplate |
| **SpringDoc OpenAPI** | 3.0.1 | Documentação interativa (Swagger) |
| **Thymeleaf** | - | Template engine |
| **Maven** | 3.8+ | Gerenciamento de dependências |
| **Docker** | - | Containerização da aplicação |

## 📁 Estrutura do Projeto

```
Cadastro1/
├── src/
│   ├── main/
│   │   ├── java/dev/pessoalprojects/cadastro/
│   │   │   ├── CadastroApplication.java          # Classe principal
│   │   │   ├── Pessoas/                           # Módulo de Pessoas
│   │   │   │   ├── PessoaModel.java               # Entidade JPA
│   │   │   │   ├── PessoaDTO.java                 # Objeto de transferência
│   │   │   │   ├── PessoaMapper.java              # Mapeador DTO ↔ Model
│   │   │   │   ├── PessoaController.java          # Endpoints REST
│   │   │   │   ├── PessoaService.java             # Lógica de negócio
│   │   │   │   └── PessoaRepository.java          # Acesso a dados
│   │   │   └── Tarefas/                           # Módulo de Tarefas
│   │   │       ├── TarefasModel.java              # Entidade JPA
│   │   │       ├── TarefasDTO.java                # Objeto de transferência
│   │   │       ├── TarefasMapper.java             # Mapeador DTO ↔ Model
│   │   │       ├── TarefasController.java         # Endpoints REST
│   │   │       ├── TarefasService.java            # Lógica de negócio
│   │   │       └── TarefasRepository.java         # Acesso a dados
│   │   └── resources/
│   │       ├── application.properties             # Configurações da aplicação
│   │       └── db/migration/                      # Scripts Flyway
│   │           ├── V1__Add_tb_cadastro_de_pessoa.sql
│   │           └── V2__Add_cidade_tb_cadastro_de_pessoa.sql
│   └── test/java/...                              # Testes unitários
├── pom.xml                                        # Dependências Maven
├── Dockerfile                                     # Configuração Docker
├── mvnw / mvnw.cmd                                # Maven Wrapper
└── README.md                                      # Este arquivo
```

## 🏗️ Arquitetura em Camadas

O projeto segue a arquitetura em camadas (Layered Architecture):

```
┌─────────────────────────────────────┐
│      Controller (REST API)           │  ← Entrypoint da API
├─────────────────────────────────────┤
│      Service (Lógica de Negócio)     │  ← Regras de negócio
├─────────────────────────────────────┤
│      Repository (Acesso a Dados)     │  ← Persistência
├─────────────────────────────────────┤
│      Database (H2)                   │  ← Armazenamento
└─────────────────────────────────────┘
```

**Fluxo de Dados:**
1. **Controller** → Recebe requisições HTTP
2. **Mapper** → Converte DTO para Model
3. **Service** → Aplica lógica de negócio
4. **Repository** → Persiste dados no banco
5. **Resposta** → Mapper converte Model para DTO

## 📊 Modelos de Dados

### PessoaModel
Representa uma pessoa cadastrada no sistema.

| Campo | Tipo | Restrições |
|-------|------|-----------|
| `id` | Long | PK, Auto-generated |
| `nome` | String | NOT NULL |
| `email` | String | NOT NULL, UNIQUE |
| `idade` | int | NOT NULL |
| `cidade` | String | - |
| `tarefas` | TarefasModel | FK (Many-to-One) |

**Tabela:** `tb_cadastro_de_pessoa`

### TarefasModel
Representa uma tarefa cadastrada no sistema.

| Campo | Tipo | Restrições |
|-------|------|-----------|
| `id` | Long | PK, Auto-generated |
| `nome` | String | - |
| `dificuldade` | String | - |
| `pessoas` | List<PessoaModel> | One-to-Many |

**Tabela:** `tb_cadastro_tarefas`

## 🔌 API Endpoints

> 📘 **Dica:** Todos os endpoints podem ser testados através do **Swagger UI** em `http://localhost:8080/swagger-ui/index.html` após iniciar a aplicação.

### Pessoas

#### ✅ Criar Pessoa
```http
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

**Status:** `201 Created`

**Resposta:**
```json
{
  "message": "Pessoa criada com sucesso: João Silva"
}
```

---

#### 📖 Listar Todas as Pessoas
```http
GET /pessoas/listar
```

**Status:** `200 OK`

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
  },
  {
    "id": 2,
    "nome": "Maria Santos",
    "email": "maria@email.com",
    "idade": 28,
    "cidade": "Brasília",
    "tarefas": {
      "id": 1,
      "nome": "Desenvolvimento",
      "dificuldade": "Média"
    }
  }
]
```

---

#### 🔍 Buscar Pessoa por ID
```http
GET /pessoas/listar/{id}
```

**Status:** `200 OK` (sucesso) ou `404 Not Found` (não encontrado)

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

---

#### ✏️ Atualizar Pessoa
```http
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

**Status:** `200 OK` (sucesso) ou `404 Not Found` (não encontrado)

**Resposta (sucesso):**
```json
{
  "id": 1,
  "nome": "João Silva Atualizado",
  "email": "joao.novo@email.com",
  "idade": 31,
  "cidade": "Rio de Janeiro",
  "tarefas": {
    "id": 1,
    "nome": "Desenvolvimento",
    "dificuldade": "Média"
  }
}
```

---

#### 🗑️ Deletar Pessoa
```http
DELETE /pessoas/deletar/{id}
```

**Status:** `200 OK` (sucesso) ou `404 Not Found` (não encontrado)

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

---

### Tarefas

#### ✅ Criar Tarefa
```http
POST /tarefas/criar
Content-Type: application/json

{
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Alta"
}
```

**Status:** `201 Created`

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Alta",
  "pessoas": []
}
```

---

#### 📖 Listar Todas as Tarefas
```http
GET /tarefas/listar
```

**Status:** `200 OK`

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Desenvolvimento de API REST",
    "dificuldade": "Alta",
    "pessoas": [
      {
        "id": 1,
        "nome": "João Silva",
        "email": "joao@email.com",
        "idade": 30,
        "cidade": "São Paulo",
        "tarefas": null
      },
      {
        "id": 2,
        "nome": "Maria Santos",
        "email": "maria@email.com",
        "idade": 28,
        "cidade": "Brasília",
        "tarefas": null
      }
    ]
  }
]
```

---

#### 🔍 Buscar Tarefa por ID
```http
GET /tarefas/listar/{id}
```

**Status:** `200 OK`

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Alta",
  "pessoas": []
}
```

---

#### ✏️ Atualizar Tarefa
```http
PUT /tarefas/alterar/{id}
Content-Type: application/json

{
  "nome": "Desenvolvimento de API REST com Spring Boot",
  "dificuldade": "Média"
}
```

**Status:** `200 OK`

**Resposta:**
```json
{
  "id": 1,
  "nome": "Desenvolvimento de API REST com Spring Boot",
  "dificuldade": "Média",
  "pessoas": []
}
```

---

#### 🗑️ Deletar Tarefa
```http
DELETE /tarefas/deletar/{id}
```

**Status:** `204 No Content` (sem corpo de resposta)

---

## 📚 Documentação Swagger

A API está completamente documentada com **Swagger/OpenAPI** 3.0.

### Acessar a Documentação

Após iniciar a aplicação, acesse:

**URL:** `http://localhost:8080/swagger-ui/index.html`

A interface do Swagger permite:
- ✅ Visualizar todos os endpoints
- ✅ Ver modelos de requisição e resposta
- ✅ Testar os endpoints diretamente
- ✅ Visualizar códigos de status HTTP

## ⚙️ Configuração

### Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para configuração do banco de dados:

| Variável | Padrão | Descrição |
|----------|--------|-----------|
| `DATABASE_URL` | `jdbc:h2:mem:cadastro` | URL de conexão H2 |
| `DATABASE_USERNAME` | `sa` | Usuário do banco |
| `DATABASE_PASSWORD` | `` | Senha do banco |

### application.properties

```properties
spring.application.name=Cadastro

# H2 Console
spring.h2.console.enable=true

# Datasource
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver=org.h2.Driver
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Flyway
spring.flyway.enable=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

## 🚀 Como Executar

### Pré-requisitos

- ✅ Java 17 ou superior
- ✅ Maven 3.6+ (ou use o Maven Wrapper incluído)
- ✅ Git (para clonar o repositório)

### Execução Local

#### 1️⃣ Clone o repositório
```bash
git clone https://github.com/seu-usuario/Cadastro1.git
cd Cadastro1
```

#### 2️⃣ Configure as variáveis de ambiente

**No Linux/macOS:**
```bash
export DATABASE_URL=jdbc:h2:mem:cadastro
export DATABASE_USERNAME=sa
export DATABASE_PASSWORD=
```

**No Windows (PowerShell):**
```powershell
$env:DATABASE_URL="jdbc:h2:mem:cadastro"
$env:DATABASE_USERNAME="sa"
$env:DATABASE_PASSWORD=""
```

**No Windows (CMD):**
```cmd
set DATABASE_URL=jdbc:h2:mem:cadastro
set DATABASE_USERNAME=sa
set DATABASE_PASSWORD=
```

#### 3️⃣ Compile e execute

```bash
# Compilar
./mvnw clean install

# Executar
./mvnw spring-boot:run
```

**No Windows:**
```cmd
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

#### 4️⃣ Acesse a aplicação

| Recurso | URL |
|---------|-----|
| **API Base** | http://localhost:8080 |
| **Swagger UI** | http://localhost:8080/swagger-ui/index.html |
| **H2 Console** | http://localhost:8080/h2-console |

> **Dica:** No H2 Console, use `sa` como usuário (sem senha).

---

## 🐳 Execução com Docker

### 📦 Build da Imagem

#### 1️⃣ Gerar o JAR
```bash
./mvnw clean package -DskipTests
```

#### 2️⃣ Construir a imagem Docker
```bash
docker build -t cadastro-app:latest .
```

### ▶️ Executar o Container

```bash
docker run --name cadastro-container \
  -p 8080:8080 \
  -e DATABASE_URL="jdbc:h2:mem:cadastro" \
  -e DATABASE_USERNAME="sa" \
  -e DATABASE_PASSWORD="" \
  cadastro-app:latest
```

**Acessar a aplicação:**
- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui/index.html`

---

## 🧪 Testes

Execute os testes unitários com:

```bash
./mvnw test
```

No Windows:
```cmd
mvnw.cmd test
```

---

## 📚 Padrões de Design Utilizados

| Padrão | Descrição | Localização |
|--------|-----------|------------|
| **DTO** | Separação entre camada de apresentação e persistência | `*DTO.java` |
| **Mapper** | Conversão entre DTOs e Models | `*Mapper.java` |
| **Service** | Lógica de negócio centralizada | `*Service.java` |
| **Repository** | Abstração do acesso a dados | `*Repository.java` |
| **Dependency Injection** | Injeção de dependência via construtor | Controllers, Services |

---

## 📝 Notas de Desenvolvimento

- ✅ Utiliza **Lombok** para reduzir boilerplate (getters, setters, construtores)
- ✅ Banco de dados **H2** em memória, ideal para desenvolvimento
- ✅ **Flyway** para controle de versão do banco de dados
- ✅ **DTOs** para separação de responsabilidades
- ✅ **Swagger/OpenAPI** para documentação interativa
- ✅ **Spring Data JPA** para persistência ORM
- ✅ **Thymeleaf** para template engine

---

## 🔄 Roadmap

- [ ] Adicionar autenticação JWT
- [ ] Implementar Global Exception Handler
- [ ] Adicionar validações (@Valid)
- [ ] Implementar paginação (PageRequest)
- [ ] Adicionar filtros avançados
- [ ] Cobertura de testes (JaCoCo)
- [ ] CI/CD com GitHub Actions

---

## 📞 Suporte

Para dúvidas, sugestões ou problemas, abra uma [issue](https://github.com/seu-usuario/Cadastro1/issues) no repositório.

---



## 👤 Autor

**Giuliano M**

- GitHub: [@devgiuliano](https://github.com/devgiuliano)
- LinkedIn: [@giulianodev](https://www.linkedin.com/in/giulianodev/)
- Email: giuliano.m995@gmail.com

---

## ⭐ Agradecimentos

Obrigado por interesse neste projeto! Se achou útil, não esquece de deixar uma ⭐ no repositório!

---

**Última atualização:** 27 de Fevereiro, 2026

