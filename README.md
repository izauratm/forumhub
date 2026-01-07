# <h1 align="center">🚀 ForumHub - Challenge Alura/ONE</h1>

Projeto **Challenge FórumHub** desenvolvido como parte do curso Alura/ONE | TECH FOUNDATION - Especialização Back-End, Módulo Java e Spring Framework G9 - ONE, com o objetivo de criar uma API REST para gerenciamento de tópicos em um fórum de cursos.

---

## ✨ Funcionalidades
- Cadastro de usuários com senhas criptografadas usando **BCrypt**
- Autenticação via **Bearer Token (JWT)**
- CRUD de tópicos e respostas
- Documentação interativa com **Swagger**
- Integração com banco de dados **MySQL**
- Testes de requisições com **Postman**

---

## 🛠️ Tecnologias utilizadas

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-green?logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-API%20Testing-orange?logo=postman&logoColor=white)
![BCrypt](https://img.shields.io/badge/BCrypt-Password%20Hashing-lightgrey)
![Trello](https://img.shields.io/badge/Trello-Organization-blue?logo=trello&logoColor=white)

---

## 📂 Estrutura do Projeto

```bash
src/
 ├── main/
 │   ├── java/com/alura/forumhub
 │   │    ├── controller/
 │   │    │    ├── AutenticacaoController.java
 │   │    │    └── TopicoController.java
 │   │    ├── domain/
 │   │    │    ├── curso/
 │   │    │    │    ├── Curso.java
 │   │    │    │    └── CursoRepository.java
 │   │    │    ├── perfil/
 │   │    │    │    └── Perfil.java
 │   │    │    ├── resposta/
 │   │    │    │    └── Resposta.java
 │   │    │    ├── topico/
 │   │    │    │    ├── StatusTopico.java
 │   │    │    │    ├── Topico.java
 │   │    │    │    └── TopicoRepository.java
 │   │    │    └── usuario/
 │   │    │         └── AutenticacaoService.java
 │   │    ├── dto/
 │   │    │    ├── TopicoRequest.java
 │   │    │    ├── TopicoResponse.java
 │   │    │    └── TopicoUpdateRequest.java
 │   │    ├── infra/
 │   │    │    ├── exception/
 │   │    │    │    └── TratadorDeErros.java
 │   │    │    ├── security/
 │   │    │    │    ├── DadosTokenJWT.java
 │   │    │    │    ├── SecurityConfig.java
 │   │    │    │    ├── SecurityFilter.java
 │   │    │    │    └── TokenService.java
 │   │    │    └── springdoc/
 │   │    │         └── SpringDocConfigurations.java
 │   │    ├── service/
 │   │    │    └── TopicoService.java
 │   │    └── ForumHubApplication.java
 │   └── resources/
 │        └── application.properties
 ├── test/
 │   └── java/com/alura/forumhub
 └── target/   # arquivos gerados pelo build
```

---

## 🔑 Autenticação
- O acesso às rotas protegidas é feito via **Bearer Token**.
- Exemplo de uso no **Postman**: Authorization: Bearer <seu_token_aqui>

---

## 📖 Documentação da API
Após rodar o projeto, acesse: http://localhost:8080/swagger-ui.html

Para visualizar e testar os endpoints.

---

## ⚙️ Como executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/izauratm/forumhub.git

### Configure o banco de dados MySQL no application.properties.
Execute o projeto: ./mvnw spring-boot:run 

### Acess a API em: http://localhost:8080

---

## 📌 Organização
As tarefas e etapas do desenvolvimento foram gerenciadas com o Trello, garantindo melhor acompanhamento do progresso e organização do fluxo de trabalho.

## 👩‍💻 Autora
Desenvolvido por Izaura TM durante o curso Alura/ONE | TECH FOUNDATION.

---
📎 Link da plataforma: [Cursos Alura|ONE](https://cursos.alura.com.br/) 
 

## 📜Licença 
Este projeto está licenciado sob os termos da [Licença MIT](LICENSE).

<img alt="Static Badge" src="https://img.shields.io/badge/license-MIT-green">
