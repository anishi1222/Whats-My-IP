ARG BUILD_IMAGE=maven:3.9.4-eclipse-temurin-17-focal
ARG RUNTIME_IMAGE=mcr.microsoft.com/openjdk/jdk:17-mariner

#ARG PROXY_SET=false
#ARG PROXY_HOST=
#ARG PROXY_PORT=

# ---------------------------------------------------
# Resolve all maven dependencies
# ---------------------------------------------------
FROM ${BUILD_IMAGE} as dependencies

COPY pom.xml ./

RUN mvn -B dependency:go-offline
#        -DproxySet=${PROXY_SET} \
#        -DproxyHost=${PROXY_HOST} \
#        -DproxyPort=${PROXY_PORT} \

# ---------------------------------------------------
# Build an artifact
# ---------------------------------------------------
FROM dependencies as build

COPY src ./src

RUN mvn -B clean package -Dmaven.test.skip
#        -DproxySet=${PROXY_SET} \
#        -DproxyHost=${PROXY_HOST} \
#        -DproxyPort=${PROXY_PORT} \

# ---------------------------------------------------
# Build container
# ---------------------------------------------------
FROM ${RUNTIME_IMAGE}

WORKDIR /opt/app
COPY --from=build /target/envchecker-0.1.jar envchecker.jar
# COPY --from=build /target/libs /opt/app/libs
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UseG1GC","-jar", "envchecker.jar"]
