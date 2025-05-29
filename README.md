# Encurtador de URL - JAVA Spring Boot + Docker

Este projeto é uma API de encurtador de URLs desenvolvida com **Spring Boot**, utilizando banco de dados **MySQL 8.0.40**, com suporte a **Swagger**, **Spring Actuator**, **rate limiting**, **testes unitários e de integração**, e pronta para execução via Docker.

---

## ✅ Funcionalidades

- Encurtar uma URL longa em uma URL curta única.
- Redirecionar uma URL curta para a original.
- Consultar estatísticas de acesso de uma URL encurtada.
- Limite de requisições: **máx. 100 por minuto por cliente**.
- Monitoramento e métricas com Spring Actuator.
- Documentação interativa via Swagger.

---

## 🔗 Endpoints Disponíveis

### Principais Endpoints

| Método | Endpoint                      | Descrição                                                       |
|--------|-------------------------------|-----------------------------------------------------------------|
| GET    | `/shorten`                    | Gera e retorna uma URL encurtada a partir da URL original.     |
| GET    | `/{shortCode}`                | Redireciona para a URL original baseada no código curto.       |
| GET    | `/stats/{shortCode}`          | Retorna estatísticas da URL encurtada.                         |

### Exemplo de Resposta de `/stats/{shortCode}`

```json
{
  "shortCode": "abc123",
  "originalUrl": "https://www.youtube.com/watch?v=QOxWGRWNmZM",
  "shortUrl": "http://localhost:8080/abc123",
  "clicks": 27,
  "createdAt": "2025-05-25T10:30:00Z",
  "expiresAt": "2025-06-25T10:30:00Z"
}
```
Endpoints do Spring Actuator
Endpoint	Descrição
/actuator/health	Verifica se a aplicação está no ar.
/actuator/info	Informações da aplicação (customizável).
/actuator/metrics	Métricas da aplicação.
/actuator	Lista todos os endpoints habilitados.

📦 Requisitos para rodar a aplicação
Antes de iniciar, certifique-se de ter os seguintes softwares instalados:

Docker — versão 20.x ou superior

Docker Compose — versão 1.29 ou superior

(Opcional) Postman — para testar os endpoints

🚀 Executando o projeto
bash
Copiar
Editar
# Clone o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

Ou faça download do projeto e abra em uma IDE de sua escolha

# Suba os containers
Antes de subir o conteiner docker execute o comando "mvn clean package" em um cmd dentro da pasta do projeto para gerar a o arquivo .Jar

Após ter o build concluído execute o comando "docker-compose up --build" com o docker desktop em execução

A API será exposta em: http://localhost:8080

📘 Acessar via Swagger
Documentação da API disponível em:
👉 http://localhost:8080/swagger-ui/index.html

📮 Exemplo de uso com Postman
Abra o Postman.

Crie uma nova requisição GET para http://localhost:8080/shorten

OBS: colocar uma data de expiração é OPCIONAL, sem ela o prazo de expiração será padrão de 1 dia, e se for fonecida, o máximo será 7 dias.
```json 
{
  "expirationDate": "2025-05-25T21:23:41.996Z",
  "originalUrl": "string"
} 
```
Observe o retorno com a URL encurtada.

Teste o redirecionamento usando o código gerado em /{shortCode}.

Consulte estatísticas com GET /stats/{shortCode}.

👤 Autor

Nome do Autor : Guilherme Brito Souza Santos

Email: [guilhermereal1911@gmail.com]

LinkedIn: [linkedin.com/in/seu-usuario](https://www.linkedin.com/in/guilherme-brito-souza-santos-49766329b/)

GitHub: github.com/guidev1911
