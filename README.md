# Sistema de Login API

API REST de autenticação e gerenciamento de usuários, desenvolvida como projeto de portfólio.

## Tecnologias
- Java 17
- Spring Boot 3.5.6
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Maven
- Docker / Docker Compose

## Funcionalidades
- Cadastro de usuários
- Login com JWT
- Senhas armazenadas com BCrypt
- Controle de acesso por perfil
- Endpoint protegido para usuário autenticado
- Consulta de usuários
- Persistência em PostgreSQL

## Endpoints

### Autenticação
- POST /api/auth/register
- POST /api/auth/login

### Usuários
- GET /api/users
- GET /api/users/me

O endpoint `/api/users/me` exige um token JWT no header:

`Authorization: Bearer SEU_TOKEN`

## Exemplo de cadastro

```json
{
  "name": "Luis",
  "email": "luis@example.com",
  "password": "senha123"
}
```

## Exemplo de login

```json
{
  "email": "luis@example.com",
  "password": "senha123"
}
```

## Execução

```bash
docker compose up --build
```

API: `http://localhost:8080`

Projeto educacional/portfólio.

**Autor:** Luis Fillipe Backer Faria  
**GitHub:** lfillipebf-ai
