# Smart Mobility Trip Management Service

Ce projet est le microservice de gestion des trajets (Trip Management) pour l'architecture **Smart Mobility**. Il gère la logique liée aux voyages et itinéraires, et s'intègre avec d'autres services du système. Sa configuration est externalisée et il utilise des mécanismes de résilience pour communiquer avec les autres services.

## Caractéristiques

- **Nom de l'application :** `trip-management-service`
- **Version de Java :** 17
- **Bases de données :** MySQL (via Spring Data JPA)
- **Découverte de services :** Eureka Client (Netflix)
- **Communication inter-services :** OpenFeign
- **Résilience :** Resilience4j (Circuit Breaker)

## Principales Dépendances

Ce microservice utilise :
- **Spring Web** (`spring-boot-starter-webmvc`) pour exposer l'API REST.
- **Spring Data JPA** (`spring-boot-starter-data-jpa`) pour la couche de persistance.
- **MySQL Driver** (`mysql-connector-j`) pour la connexion à la base de données.
- **Spring Cloud Config Client** (`spring-cloud-starter-config`) pour récupérer la configuration centralisée.
- **Eureka Client** (`spring-cloud-starter-netflix-eureka-client`) pour l'enregistrement au service registry.
- **OpenFeign** (`spring-cloud-starter-openfeign`) pour les appels REST déclaratifs vers d'autres microservices.
- **Resilience4j** (`spring-cloud-starter-circuitbreaker-resilience4j`) pour implémenter le pattern Circuit Breaker et assurer la tolérance aux pannes.
- **Lombok** (`lombok`) pour réduire le code boilerplate.

## Configuration Externalisée

La configuration de ce service est gérée à distance par le Config Server. Au démarrage, l'application se connecte au serveur de configuration :
```text
http://localhost:8888
```

> **Attention (Fail-Fast) :** La propriété `spring.cloud.config.fail-fast=true` est activée. Cela signifie que **ce microservice refusera de démarrer si le serveur de configuration (Config Server) n'est pas joignable**.

Les propriétés clés récupérées depuis le Config Server (via Git) incluent :
- `server.port` (port d'exécution du service)
- `spring.datasource.url` (URL de la base MySQL)
- `spring.datasource.username` (utilisateur de la base)
- `spring.datasource.password` (mot de passe de la base)

## Comment lancer le projet ?

Assurez-vous que :
1. Votre base de données MySQL est accessible.
2. Le `spring-cloud-config-server` (sur le port 8888) est **impérativement démarré**.

Ensuite, vous pouvez compiler et lancer le service depuis la racine `smart-mobility-trip-management` :

```bash
./mvnw spring-boot:run
```

Sous Windows, utilisez :
```cmd
mvnw.cmd spring-boot:run
```
