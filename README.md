# 📋 Cadastro API

> API REST em Java 17 + Spring Boot 4 para gerenciamento de pessoas e tarefas com relacionamento bidirecional

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📋 Sobre o Projeto

A **Cadastro API** é um projeto educacional desenvolvido para praticar conceitos fundamentais do desenvolvimento backend com Java e Spring Boot:

1. **Arquitetura em Camadas** - Separação clara entre Controller, Service e Repository
2. **Relacionamento JPA** - Mapeamento bidirecional One-to-Many entre Tarefas e Pessoas
3. **Padrões de Design** - Implementação de DTOs, Mappers e Service Layer
4. **Versionamento de Banco** - Controle de schema com Flyway Migrations
5. **Documentação Interativa** - API documentada com OpenAPI/Swagger

Este projeto demonstra boas práticas de desenvolvimento backend, incluindo persistência com JPA/Hibernate, banco de dados H2 em memória e containerização com Docker.

---

## 🎯 Objetivos de Aprendizado

- ✅ Criar API RESTful completa com operações CRUD
- ✅ Implementar relacionamento bidirecional JPA (One-to-Many)
- ✅ Aplicar padrões de design (DTO, Mapper, Service Layer, Repository)
- ✅ Versionar schema de banco de dados com Flyway
- ✅ Documentar API com OpenAPI/Swagger
- ✅ Persistir dados com Spring Data JPA e Hibernate
- ✅ Containerizar aplicação com Docker
- ✅ Seguir arquitetura em camadas (Layered Architecture)

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|-----------|--------|----------|
| **Java** | 17 | Linguagem de programação |
| **Spring Boot** | 4.0.0 | Framework base |
| **Spring Web MVC** | 6.2.x | Criação de endpoints REST |
| **Spring Data JPA** | - | Persistência de dados e ORM |
| **H2 Database** | - | Banco de dados em memória |
| **Flyway** | - | Versionamento de schema do banco de dados |
| **Lombok** | - | Redução de código boilerplate |
| **SpringDoc OpenAPI** | 3.0.1 | Documentação interativa (Swagger) |
| **Thymeleaf** | - | Template engine |
| **Maven** | 3.8+ | Gerenciamento de dependências |
| **Docker** | - | Containerização da aplicação |

---

## 🏗️ Arquitetura do Projeto

```
cadastro-api/
├── api/                         # Camada de apresentação (Controllers e DTOs)
│   ├── Pessoas/
│   │   ├── PessoaController.java
│   │   ├── PessoaDTO.java
│   │   └── PessoaMapper.java
│   └── Tarefas/
│       ├── TarefasController.java
│       ├── TarefasDTO.java
│       └── TarefasMapper.java
├── domain/                      # Camada de negócio (Services e Models)
│   ├── Pessoas/
│   │   ├── PessoaService.java
│   │   └── PessoaModel.java
│   └── Tarefas/
│       ├── TarefasService.java
│       └── TarefasModel.java
└── repository/                  # Camada de persistência
    ├── PessoaRepository.java
    └── TarefasRepository.java
```

### 🔗 Relacionamento entre Entidades

O sistema utiliza um relacionamento **One-to-Many (Um-para-Muitos)**:

```
Tarefas (1) ──── (N) Pessoas
  └─ Uma tarefa pode ter várias pessoas associadas
  └─ Cada pessoa está associada a apenas uma tarefa
```

### 📐 Padrão de Camadas

- **Controller**: Recebe requisições HTTP e delega para o Service
- **Service**: Contém a lógica de negócio e validações
- **Repository**: Responsável pela persistência no banco de dados
- **Mapper**: Converte DTOs para Models e vice-versa

---

## 📁 Estrutura do Projeto

```
Cadastro1/
├── src/main/
│   ├── java/dev/pessoalprojects/cadastro/
│   │   ├── CadastroApplication.java          # Classe principal
│   │   ├── Pessoas/                          # Módulo de Pessoas
│   │   │   ├── PessoaModel.java              # Entidade JPA
│   │   │   ├── PessoaDTO.java                # Data Transfer Object
│   │   │   ├── PessoaMapper.java             # Conversão DTO ↔ Model
│   │   │   ├── PessoaController.java         # Endpoints REST
│   │   │   ├── PessoaService.java            # Lógica de negócio
│   │   │   └── PessoaRepository.java         # Interface JPA
│   │   └── Tarefas/                          # Módulo de Tarefas
│   │       └── (mesma estrutura de Pessoas)
│   └── resources/
│       ├── application.properties            # Configurações
│       └── db/migration/                     # Scripts Flyway
│           ├── V1__Add_tb_cadastro_de_pessoa.sql
│           └── V2__Add_cidade_tb_cadastro_de_pessoa.sql
├── pom.xml                                   # Dependências Maven
├── Dockerfile                                # Configuração Docker
└── README.md                                 # Este arquivo
```

---

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

---

## 🔌 Endpoints da API

> 📘 **Dica:** Todos os endpoints podem ser testados através do **Swagger UI** em `http://localhost:8080/swagger-ui/index.html` após iniciar a aplicação.

### Pessoas

#### ✅ Criar Pessoa

Cria uma nova pessoa no sistema associada a uma tarefa existente.

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

**Resposta de Sucesso (201 Created):**
```json
{
  "message": "Pessoa criada com sucesso: João Silva"
}
```

---

#### 📖 Listar Todas as Pessoas

Retorna uma lista com todas as pessoas cadastradas no sistema.

```http
GET /pessoas/listar
```

**Resposta de Sucesso (200 OK):**
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

---

#### 🔍 Buscar Pessoa por ID

Busca uma pessoa específica pelo seu ID.

```http
GET /pessoas/listar/{id}
```

**Resposta de Sucesso (200 OK):**
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

**Resposta de Erro (404 Not Found):**
```json
{
  "message": "ID não encontrado"
}
```

---

#### ✏️ Atualizar Pessoa

Atualiza os dados de uma pessoa existente.

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

**Resposta de Sucesso (200 OK):**
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

Remove uma pessoa do sistema.

```http
DELETE /pessoas/deletar/{id}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "message": "Usuario(a): João Silva deletada com sucesso"
}
```

**Resposta de Erro (404 Not Found):**
```json
{
  "message": "Id não encontrado para deleção"
}
```

---

### Tarefas

#### ✅ Criar Tarefa

Cria uma nova tarefa no sistema.

```http
POST /tarefas/criar
Content-Type: application/json

{
  "nome": "Desenvolvimento de API REST",
  "dificuldade": "Alta"
}
```

**Resposta de Sucesso (201 Created):**
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

Retorna todas as tarefas cadastradas com suas pessoas associadas.

```http
GET /tarefas/listar
```

**Resposta de Sucesso (200 OK):**
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
        "cidade": "São Paulo"
      }
    ]
  }
]
```

---

#### 🔍 Buscar Tarefa por ID

Busca uma tarefa específica pelo seu ID.

```http
GET /tarefas/listar/{id}
```

**Resposta de Sucesso (200 OK):**
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

Atualiza os dados de uma tarefa existente.

```http
PUT /tarefas/alterar/{id}
Content-Type: application/json

{
  "nome": "Desenvolvimento de API REST com Spring Boot",
  "dificuldade": "Média"
}
```

**Resposta de Sucesso (200 OK):**
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

Remove uma tarefa do sistema.

```http
DELETE /tarefas/deletar/{id}
```

**Resposta de Sucesso (204 No Content)**

---

## 📸 Documentação Interativa (Swagger)

A API possui documentação interativa gerada automaticamente com SpringDoc OpenAPI.

**Acesse:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

![Swagger UI](https://raw.githubusercontent.com/devgiuliano/Giulianom95/refs/heads/main/assets/Swagger.jpg)

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 17** ou superior instalado
- **Maven 3.8+** instalado
- Conexão com a internet (para download de dependências)

### Passos

1. **Clone o repositório**

```bash
git clone https://github.com/devgiuliano/Cadastro1.git
cd Cadastro1
```

2. **Compile o projeto**

```bash
mvn clean install
```

3. **Execute a aplicação**

```bash
mvn spring-boot:run
```

Ou execute o JAR gerado:

```bash
java -jar target/Cadastro-0.0.1-SNAPSHOT.jar
```

4. **Acesse a API**

- **Endpoint base:** `http://localhost:8080`
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **H2 Console:** `http://localhost:8080/h2-console`

> **Dica:** No H2 Console, use:
> - **JDBC URL:** `jdbc:h2:mem:cadastro`
> - **Username:** `sa`
> - **Password:** (deixe em branco)

---

## 🧪 Testando a API

### Com cURL

```bash
# Criar uma tarefa
curl -X POST http://localhost:8080/tarefas/criar \
  -H "Content-Type: application/json" \
  -d '{"nome":"Desenvolvimento","dificuldade":"Média"}'

# Criar uma pessoa
curl -X POST http://localhost:8080/pessoas/criar \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva","email":"joao@email.com","idade":30,"cidade":"São Paulo","tarefas":{"id":1}}'

# Listar todas as pessoas
curl http://localhost:8080/pessoas/listar

# Buscar pessoa por ID
curl http://localhost:8080/pessoas/listar/1
```

### Com HTTPie

```bash
http GET localhost:8080/pessoas/listar
```

### Com Postman ou Insomnia

Importe a coleção OpenAPI em: `http://localhost:8080/v3/api-docs`

---

## 🎓 Conceitos Implementados

### 1. Arquitetura em Camadas

```
Controller → Service → Repository → Database
```

Separação clara de responsabilidades seguindo o padrão MVC.

### 2. Relacionamento JPA Bidirecional

```java
// TarefasModel (lado One)
@OneToMany(mappedBy = "tarefas")
private List<PessoaModel> pessoas;

// PessoaModel (lado Many)
@ManyToOne
@JoinColumn(name = "tarefas_id")
private TarefasModel tarefas;
```

### 3. Padrão DTO (Data Transfer Object)

Separação entre entidades JPA e objetos expostos pela API, evitando exposição direta do modelo de dados.

### 4. Flyway Migrations

Versionamento automático do schema do banco de dados:
- `V1__Add_tb_cadastro_de_pessoa.sql` - Cria tabela de pessoas
- `V2__Add_cidade_tb_cadastro_de_pessoa.sql` - Adiciona coluna cidade

### 5. Documentação OpenAPI

Documentação automática gerada via anotações Spring.

---

## 🛠️ Troubleshooting

### Erro: "Porta 8080 já está em uso"

**Solução:** Altere a porta no `application.properties`:

```properties
server.port=8081
```

### Erro: "Failed to configure a DataSource"

**Causa:** Variáveis de ambiente não configuradas.

**Solução:** Configure as variáveis:

```bash
# Linux/macOS
export DATABASE_URL=jdbc:h2:mem:cadastro
export DATABASE_USERNAME=sa
export DATABASE_PASSWORD=

# Windows PowerShell
$env:DATABASE_URL="jdbc:h2:mem:cadastro"
$env:DATABASE_USERNAME="sa"
$env:DATABASE_PASSWORD=""
```

### H2 Console não abre

**Solução:** Verifique se a propriedade está habilitada no `application.properties`:

```properties
spring.h2.console.enable=true
```

---

## 📚 Recursos de Aprendizado

- [Spring Boot - Documentação Oficial](https://spring.io/projects/spring-boot)
- [Spring Data JPA - Guia](https://spring.io/guides/gs/accessing-data-jpa/)
- [Flyway - Database Migrations](https://flywaydb.org/documentation/)
- [OpenAPI/Swagger - SpringDoc](https://springdoc.org/)

---

## 💡 Contribuições

Este é um projeto educacional, mas sugestões são bem-vindas! Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abrir um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

**Giuliano**

- GitHub: [@devgiuliano](https://github.com/devgiuliano)
- LinkedIn: [@giulianodev](https://www.linkedin.com/in/giulianodev/)
- Email: giuliano.m995@gmail.com

---

## ⭐ Agradecimentos

- Spring Team - Pelo excelente framework
- Comunidade Java - Pela documentação e tutoriais
- H2 Database - Pelo banco em memória perfeito para desenvolvimento

---

<div align="center">

**Desenvolvido com ☕ e 💚 para aprender Spring Boot**

[↑ Voltar ao topo](#-cadastro-api)

</div>

