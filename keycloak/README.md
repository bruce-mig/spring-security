# Keycloak

Java Spring boot application to demonstrate the implementation of Keycloak with Spring Security.

## Running Keycloak docker container

To run the Keycloak docker container, use the following command:

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.2 start-dev
```
