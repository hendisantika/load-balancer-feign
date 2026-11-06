# Load Balancer Feign

[![CI](https://github.com/hendisantika/load-balancer-feign/actions/workflows/ci.yml/badge.svg)](https://github.com/hendisantika/load-balancer-feign/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.x-6DB33F?logo=spring)

Playground for **client-side load balancing** with **Spring Cloud OpenFeign**, **Spring Cloud LoadBalancer** and
**Netflix Eureka** service discovery.

Feign is a declarative web service client: you write an interface, annotate it, and Spring Cloud generates the HTTP
client. Combined with Eureka, every Feign call is resolved through the service registry and load-balanced
(round-robin by default) across all registered instances of the target service.

## Architecture

```
                        ┌──────────────────────────┐
           register     │      eureka-server       │     register
      ┌────────────────▶│   :8761  (registry)      │◀────────────────┐
      │                 └────────────▲─────────────┘                 │
      │                              │ fetch registry                │
┌─────┴────────────┐     ┌───────────┴──────────────┐     ┌──────────┴───────┐
│  hello-service   │◀────│ spring-cloud-eureka-     │────▶│ hello-service-2  │
│  :7111           │     │ feign-client :8080       │     │ :7112            │
└──────────────────┘     │ (Feign + LoadBalancer)   │     └──────────────────┘
                         └──────────────────────────┘
        both register as "hello-service" → requests are round-robined between them
```

## Modules

| Module                                              | Build  | Port | Description                                                                                   |
|-----------------------------------------------------|--------|------|-----------------------------------------------------------------------------------------------|
| `eureka-server`                                     | Gradle | 8761 | Eureka service registry (dashboard at http://localhost:8761)                                  |
| `hello-service`                                     | Gradle | 7111 | Instance #1 of `hello-service`, exposes `/` and `/greeting`                                   |
| `hello-service-2`                                   | Gradle | 7112 | Instance #2 of `hello-service`, exposes `/`                                                   |
| `spring-cloud-eureka-feign-client`                  | Maven  | 8080 | Feign client (`/get-greeting`) and a plain `RestTemplate` + `EurekaClient` variant (`/get-greeting-no-feign`) |
| `spring-cloud-eureka-feign-client-integration-test` | Maven  | –    | Integration tests for a Feign `BooksClient` using WireMock and Spring Cloud LoadBalancer      |

## Tech Stack

- Java **25** (Gradle toolchain / Maven `java.version`)
- Spring Boot **4.1.x**
- Spring Cloud **2025.1.x** (Netflix Eureka, OpenFeign, LoadBalancer)
- Gradle **9.9** wrapper & Maven **3.10** wrapper
- JUnit 6, WireMock, Testcontainers, Awaitility, Lombok

## Prerequisites

- JDK 25 (e.g. `sdk install java 25-tem`)
- No local Gradle/Maven needed — every module ships its own wrapper

## Build & Test

Each module is an independent project. Build them one by one:

```bash
# Gradle modules
(cd eureka-server   && ./gradlew build)
(cd hello-service   && ./gradlew build)
(cd hello-service-2 && ./gradlew build)

# Maven modules
(cd spring-cloud-eureka-feign-client                  && ./mvnw verify)
(cd spring-cloud-eureka-feign-client-integration-test && ./mvnw verify)
```

`ServiceDiscoveryBooksClientLiveTest` needs a real Eureka server on `localhost:8761`, so it is excluded from the
default build. Start `eureka-server` and run it explicitly:

```bash
cd spring-cloud-eureka-feign-client-integration-test
./mvnw test -Dtest=ServiceDiscoveryBooksClientLiveTest -Dsurefire.failIfNoSpecifiedTests=false
```

## Run Locally

Start the services in this order (each in its own terminal):

```bash
(cd eureka-server   && ./gradlew bootRun)                   # 1. registry  → http://localhost:8761
(cd hello-service   && ./gradlew bootRun)                   # 2. instance #1 → http://localhost:7111
(cd hello-service-2 && ./gradlew bootRun)                   # 3. instance #2 → http://localhost:7112
(cd spring-cloud-eureka-feign-client && ./mvnw spring-boot:run)  # 4. Feign client → http://localhost:8080
```

Set `EUREKA_URI` to point the clients at another registry (default `http://localhost:8761/eureka`).

Open the Eureka dashboard — `HELLO-SERVICE` should list **2 instances** (both apps share
`spring.application.name=hello-service`):

```bash
curl http://localhost:7111/          # This is Hello Service 1: ...
curl http://localhost:7112/          # This is Hello Service 2: ...
```

> **Note:** `GreetingClient` / `NoFeignClientController` in `spring-cloud-eureka-feign-client` currently target the
> service id `spring-cloud-eureka-client`. Point them at `hello-service` to see Feign round-robin between the two
> instances. Load balancing itself is verified by `LoadBalancerBooksClientIntegrationTest` in the integration-test
> module.

## CI/CD

| Workflow                                                    | Trigger                      | What it does                                                                                       |
|-------------------------------------------------------------|------------------------------|----------------------------------------------------------------------------------------------------|
| [`ci.yml`](.github/workflows/ci.yml)                        | push / PR to `master`        | Matrix build & test of all 5 modules on Temurin JDK 25, test reports on failure, Gradle dependency graph submission |
| [`maven-publish.yml`](.github/workflows/maven-publish.yml)  | release created / manual     | Builds the Maven modules and publishes them to GitHub Packages                                     |

## Dependency Updates

[Dependabot](.github/dependabot.yml) keeps everything current:

- **Gradle** & **Maven** modules — daily (05:00 Asia/Jakarta), one entry per ecosystem covering all module directories
- **GitHub Actions** — weekly, in a single grouped PR
- Updates are **grouped** (Spring Boot, Spring Cloud, testing libraries, Maven plugins, Gradle wrapper, other minor/patch)
  to keep the PR count low
- A **cooldown** (3 days, 7 days for majors) skips brand-new releases until they have settled
- PRs are labelled (`dependencies`, `java`, `gradle` / `maven` / `github_actions`) and use conventional commit prefixes

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
