FROM maven:alpine
WORKDIR /var/apps/simpleweb
COPY . .
RUN mvn clean install
CMD [ "java", "-jar", "Simple-Web-exec.jar" ]
