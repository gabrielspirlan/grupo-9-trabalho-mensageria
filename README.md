# Trabalho Mensageria - Grupo 9

## 👥 Grupo 9 - Computação em Nuvem II | FATEC

- Felipe Aveline Pedaes
- Gabriel Resende Spirlandelli
- Henrique Almeiada Florentino
- Luiz Felipe Vieira Soares

Sistema de processamento de pedidos via mensageria com **Google Cloud Pub/Sub** e persistência em **PostgreSQL**, desenvolvido com **Spring Boot**.

---

## 📋 Sobre o Projeto

A aplicação consome mensagens de uma subscription do Google Cloud Pub/Sub contendo dados de pedidos (orders). Para cada mensagem recebida, o sistema:

1. Faz o parse do JSON contendo os dados do pedido
2. Persiste o pedido no banco de dados PostgreSQL, incluindo:
   - Dados do cliente (`Customer`)
   - Dados do vendedor (`Seller`)
   - Itens do pedido (`OrderItem`) com produtos e categorias
   - Informações de pagamento, envio e metadados
3. Expõe uma API REST para consulta dos pedidos processados

---

## ⚙️ Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL
- Conta no Google Cloud com Pub/Sub habilitado

---

## 🔐 Configuração das Credenciais

### 1. Google Cloud (Pub/Sub)

Para autenticar com o Google Cloud Pub/Sub, é necessário fornecer uma **Service Account Key** em formato JSON.

---

### 2. Banco de Dados PostgreSQL

As credenciais do banco de dados são carregadas via arquivo `.env` na raiz do projeto.

Crie um arquivo `.env` com o seguinte conteúdo:

```env
DB_URL=jdbc:postgresql://<HOST>:<PORTA>/<NOME_DO_BANCO>
DB_USERNAME=<SEU_USUARIO>
DB_PASSWORD=<SUA_SENHA>
```

**Exemplo:**
```env
DB_URL=jdbc:postgresql://localhost:5432/trabalho_mensageria
DB_USERNAME=postgres
DB_PASSWORD=minhasenha
```

---

## 🚀 Como Rodar

Com as credenciais configuradas, rode o projeto com Maven:

```bash
./mvnw spring-boot:run
```

Ou no Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação iniciará o listener do Pub/Sub automaticamente e ficará escutando novas mensagens.

---

## 🌐 API REST

A aplicação expõe os seguintes endpoints para consulta de pedidos:

### Buscar pedido por UUID
```
GET /orders/{uuid}
```

### Listar pedidos com filtros (paginado)
```
GET /orders?clientId=&productId=&status=&pageNumber=0&pageSize=10
```

| Parâmetro    | Tipo    | Obrigatório | Descrição                          |
|--------------|---------|-------------|------------------------------------|
| `clientId`   | Integer | Não         | Filtra por ID do cliente           |
| `productId`  | Integer | Não         | Filtra por ID do produto           |
| `status`     | String  | Não         | Filtra por status do pedido        |
| `pageNumber` | Integer | Não         | Número da página (padrão: 0)       |
| `pageSize`   | Integer | Não         | Itens por página (padrão: 10, máx: 50) |

A documentação completa da API pode ser acessada via Swagger em:
```
http://localhost:8080/swagger-ui.html
```

---

## 🏗️ Estrutura do Projeto

```
src/main/java/com/grupo9/trabalho_mensageria/
├── application/       # Services (regras de negócio)
├── domain/
│   ├── entities/      # Entidades JPA (Order, OrderItem, Product, ...)
│   ├── enums/         # Enums de domínio
│   └── converters/    # Conversores JPA
├── infrastructure/
│   ├── config/        # Configurações (Jackson, etc.)
│   └── repositories/  # Repositórios Spring Data JPA
├── mensageria/        # Subscriber do Google Cloud Pub/Sub
└── view/              # Controllers REST
```

---


