# Instruções para execução
## Backend
Dependências:
- Docker
- Java JDK 21
- Maven 3.9

1. Entrar no diretório do projeto backend
```shell
cd setec-backend/crud/
```
2. Inicializar o banco de dados
```shell
docker compose up --build -d
```
3. Compilar `.jar` do projeto
```shell
mvn clean install
```
4. Iniciar aplicação Spring
```shell
mvn spring-boot:run
```

## Frontend:
Dependências:
- Docker
<br/>**OU**
- NodeJS 25
- NPM 11

1. Entrar no diretório do projeto frontend
```shell
cd setec-frontend/
```
2. Iniciar aplicação
  - Utilizando Docker
```shell
docker compose up --build
```
  - Utilizando NPM
```shell
npm run dev
```

### Para acessar a documentação da API, navegue para [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)
### Para acessar a aplicação web, navegue para [http://localhost:5173](http://localhost:5173)
