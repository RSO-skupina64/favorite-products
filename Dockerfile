FROM maven:3.8.6-openjdk-18 AS build
COPY ./ /app
WORKDIR /app
RUN mvn --show-version --update-snapshots --batch-mode clean package

FROM amazoncorretto:18
RUN mkdir /app
WORKDIR /app
COPY --from=build ./app/target/favorite-products.jar /app
EXPOSE 8080
CMD ["java", "-jar", "favorite-products.jar"]
