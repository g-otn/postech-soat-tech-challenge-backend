# Imagem otimizada para executar Java 17
FROM bellsoft/liberica-runtime-container:jre-17-slim-musl

# Cria e troca para usuário non-root
ENV APP_USER=backend
# Sintaxe do BusyBox, disponível na imagem
RUN addgroup -S $APP_USER && adduser -D -g "" -G $APP_USER $APP_USER
USER backend

# Copiar .jar para o container
ARG JAR_FILE=target/app.jar
ENV APP_FOLDER=/opt/app
WORKDIR $APP_FOLDER
COPY $JAR_FILE app.jar

# Executar aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 80