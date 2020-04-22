# Spring Boot Practice

Learning for create REST API using Spring Boot with Animal Crossing data.

## Requirement

1. Java 8
2. Docker (for using PostgresSQL)

## How to run on localhost

1. Run PostgresSQL using Docker

```shell
$ docker container run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=admin -d postgres
```

PostgresSQL running on port 5432 with username is **postgres** and password is **admin**

2. Run Spring Boot

```shell
$ mvn spring-boot:run
```

Spring Boot running on <http://localhost:9000>
