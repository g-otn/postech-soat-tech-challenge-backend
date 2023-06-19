# FIAP - SOAT1 - Tech Challenge - Grupo 63

## Executando (para professores)

1. Verifique se a porta `80` não está sendo utilizada.
2. Com uma cópia do projeto (a pasta `bd` possui scripts de inicialização do BD necessários), execute o Docker Compose:

```bash
docker compose up
# ou
docker-compose up
```

3. Acesse http://localhost/swagger-ui.html para acessar uma documentação viva dos endpoints.

## Contribuindo (para integrantes)

Requisitos: Java 17

1. Abra o projeto Maven localizado na pasta `techchallenge/` com sua IDE.
2. Por padrão, ele:
   - Utilizará o perfil do Maven `dev`, que configura o perfil ativo
     do Spring Boot para `dev`, que habilita a integração com o `spring-boot-docker-compose`.
   - Ele irá utilizar o compose em `techchallenge/compose-dev.yaml` para iniciar o banco de dados e também executar os scripts da pasta `bd/`

### Testes

### Compilando e publicando a imagem Docker

#### Empacotando o projeto Maven

#### Fazendo build da imagem

#### Enviando imagem ao Docker Hub

#### Validando `docker-compose.yaml` de `prod`
