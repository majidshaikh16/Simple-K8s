FROM maven:alpine
WORKDIR /var/apps/simpleweb/
COPY ./ ./
# COPY /home/webwerks/Documents/Docker/learn/Jenkins/test/repository/ /root/.m2/repository/
# RUN rm -r /root/.m2/repository/*
# COPY ./repository/* /root/.m2/repository/
# RUN mvn clean install
CMD [ "java", "-jar", "target/Simple-Web-exec.jar" ]
