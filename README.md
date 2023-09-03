# FIAP - SOAT1 - Tech Challenge - Group 63

Group course project of a self service and kitchen management system for a fictional fast food restaurant.

## Executing via Kubernetes (for teachers)

Requirements: Kubernetes, kubectl

1. Clone and navigate to this repository:

```
git clone https://github.com/g-otn/soat-tech-challenge.git
cd soat-tech-challenge/
```

2. Make sure:
   - Port `30000` is available.
   - Kubernetes cluster is running.
       - If using minikube due to a limitation in how it works is necessary to run the following code snippet to forward correctly the application, it will automatically open a browser window with the correct IP and PORT to execute the tests, if using the Docker Desktop kubernates this step is not required.
       ```
       minikube service soat-tech-challenge-backend-service -n fiap-grupo-63
       ```
3. Apply configuration files in the `kubernetes` folder:

```
kubectl apply -f kubernetes/
```

This will create in the `fiap-grupo-63` namespace and all the
objects required to run the workload inside it.

4. Access http://localhost:30000/ or http://localhost:30000/swagger-ui/index.html to access a live OpenAPI spec of the available endpoints.

<hr>

## Contributing (for group members)

<details>

### Executing the project in `dev` mode

Requirements: Docker, Docker Compose, Java 17

1. Open the Spring Boot Maven project in the `techchallenge/` folder with your IDE.
2. By default it'll:
   - Use the `dev` Maven profile, which sets the Spring boot profile to `dev`,
     which enables the `spring-boot-docker-compose` integration.
   - `spring-boot-docker-compose` will use the compose file at `techchallenge/compose-dev.yaml`
     to start a dev database, executing the scripts in `db` folder.
3. Start the project, no environment variables are required.
4. Access http://localhost:8080 to open the Open API live docs (Swagger UI).

### Manually compiling and publishing the Docker image

#### Compiling and packaging the Maven project

1. With your IDE, **change the Maven profile to `prod`**. (see `application-prod.yml`)
   - Check if `dev` profile is not enabled at the same time (`!dev`)
   - If `dev` is enabled in some way, the application will try to use the `compose-dev.yaml`.
     (We don't want a container to try to create containers,
     We want the app to simply directly connect to the db instead)
2. Execute the Maven lifecycle commands to generate the final .jar file, in this order:
   - `clean`, `compile` and then `package`
3. A .jar file should be created in the `target/` folder. Example: `target/techchallenge-fase-1.jar`

#### Building the Docker image

1. Execute the build command in the `techchallenge/` folder, where the `Dockerfile` is located to create a local image:
   - `docker buildx build -t g0tn/soat-tech-challenge-backend:<tag> .`
   - Replace `<tag>` for something like `fase-1`
2. Apply the `latest` tag to the image too:
   - `docker tag g0tn/soat-tech-challenge-backend:<tag> g0tn/soat-tech-challenge-backend:latest`
   - **This tag is important so the correct version is downloaded from Docker Hub** by [`docker-compose.yml`](docker-compose.yml)

#### Pushing image to Docker Hub

3. Pushing the image to Docker Hub:
   - `docker push g0tn/soat-tech-challenge-backend:latest`
   - `docker push g0tn/soat-tech-challenge-backend:<tag>`

#### Validating the `docker-compose.yaml` with the new image

Execute the steps in "Executing (for teachers)" at the beginning of this doc.

### Kubernetes

#### Generating configmap volume for SQL scripts

```
kubectl create configmap db-config --from-file=db/
```

</details>

## Executing via docker-compose

<details>

Requirements: Docker, Docker Compose

1. Make sure port `80` is available.
2. With a copy of this repo (the `db` folder has some required SQL scripts), execute Docker Compose:

```bash
docker compose up
# or
docker-compose up
```

3. Access http://localhost/ or http://localhost/swagger-ui/index.html to access
   a live OpenAPI spec of the available endpoints.

</details>

## Acknowledgments

- Course project group members: [g-otn](https://github.com/g-otn), [HenriqueZaim](https://github.com/HenriqueZaim),
  [marcelovbcfilho](https://github.com/marcelovbcfilho), [thgosii](https://github.com/thgosii).
- Course teachers
