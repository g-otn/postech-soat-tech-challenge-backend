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

### Executando o projeto em `dev`
Requisitos: Java 17

1. Abra o projeto Maven localizado na pasta `techchallenge/` com sua IDE.
2. Por padrão, ele:
   - Utilizará o perfil do Maven `dev`, que configura o perfil ativo
     do Spring Boot para `dev`, que habilita a integração com o `spring-boot-docker-compose`.
   - Ele irá utilizar o compose em `techchallenge/compose-dev.yaml` para iniciar o banco de dados e também executar os scripts da pasta `bd/`

### Testes

### Compilando e publicando a imagem Docker

#### Empacotando o projeto Maven
1. Utilizando sua IDE, altere o profile Maven para `prod`.
   - Verifique se `dev` foi desselecionado (`!dev`)
2. Execute os scripts de lifecycle do Maven para gerar o .jar, nessa ordem:
   - `clean`, `compile` e `package`
3. Um arquivo deve ter sido gerado na pasta `target/`. Exemplo: `target/techchallenge-fase-1.jar`

#### Fazendo build da imagem
1. Na raiz do repositório, execute esse comando para criar uma imagem local:
   - `docker buildx build -t g0tn/soat-tech-challenge-backend:<tag> techchallenge`
   - Substitua <tag> por algo como `fase-1`
2. Aplique também a tag `latest` a imagem:
   - `docker tag g0tn/soat-tech-challenge-backend:<tag> g0tn/soat-tech-challenge-backend:latest`
   - Essa tag é importante para a imagem correta ser baixada pelo [`docker-compose.yml`](docker-compose.yml)

#### Enviando imagem ao Docker Hub
3. Envie a imagem com as duas novas tags ao Docker Hub:
   - `docker push g0tn/soat-tech-challenge-backend:latest`
   - `docker push g0tn/soat-tech-challenge-backend:<tag>`

#### Validando o `docker-compose.yaml` com a nova imagem
Execute os passos em "Executando (para professores)" no inicio desse documento.
