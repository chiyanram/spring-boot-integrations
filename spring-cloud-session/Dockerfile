FROM java:8
VOLUME /tmp
ADD build/libs/spring-cloud-session.jar spring-cloud-session.jar
RUN bash -c 'touch /spring-cloud-session.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-cloud-session.jar"]