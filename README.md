# Projet Microservice - Documentation

Ce projet contient plusieurs microservices développés avec Spring Boot, orchestrés via Eureka, Kafka, et sécurisés avec Keycloak.  
La configuration centralisée est gérée via un Config Server qui lit les fichiers YAML du dépôt `config-repo`.

---

## Structure du projet

- **ApiGateway/** : Passerelle API qui fait le routage vers les microservices et gère la sécurité OAuth2 avec Keycloak  
- **ConfigServer/** : Serveur centralisé de configuration Spring Cloud Config, qui pointe vers `config-repo`  
- **DiscoveryServer/** : Serveur Eureka pour la découverte des microservices  
- **MS_Client/** : Microservice Client utilisant MongoDB  
- **MS_Commande/** : Microservice Commande utilisant MySQL  
- **config-repo/** : Dépôt local contenant les fichiers de configuration YAML pour tous les microservices  
- **kafka-compose.yml** : Fichier Docker Compose pour lancer Kafka et ses services associés  
- **keycloak-compose.yml** : Fichier Docker Compose pour lancer Keycloak

---

## Démarrage des services

1. **Démarrer Kafka et Keycloak** via Docker Compose :


docker-compose -f kafka-compose.yml -f keycloak-compose.yml up --build
---


2. **Accès aux interfaces graphiques
| Service              | URL                                                                            | Description                                |
| -------------------- | ------------------------------------------------------------------------------ | ------------------------------------------ |
| Eureka Dashboard     | [http://localhost:8095/](http://localhost:8095/)                               | Tableau de bord de découverte              |
| Keycloak Admin       | [http://localhost:8093/](http://localhost:8093/)                               | Gestion des utilisateurs et clients OAuth2 |
| Config Server        | [http://localhost:8096/](http://localhost:8096/)                               | Interface config server (REST API)         |
| MongoDB Compass      | (Application locale)                                                           | GUI pour MongoDB                           |
| MySQL Workbench      | (Application locale)                                                           | GUI pour MySQL                             |
| Swagger MS\_Client   | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) | Documentation API du microservice Client   |
| Swagger MS\_Commande | [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html) | Documentation API du microservice Commande |
