# Encurtador de URLs

O seu desafio será implementar um serviço que permite encurtar URLs longas para torná-las mais compactas e fáceis de compartilhar.

## Requisitos
- O encurtador de URLs recebe uma URL longa como parâmetro inicial.
- O encurtamento será composto por um mínimo de 05 e um máximo de 10 caracteres.
- Apenas letras e números são permitidos no encurtamento.
- A URL encurtada será salva no banco de dados com um prazo de validade (você pode escolher a duração desejada).
- Ao receber uma chamada para a URL encurtada https://xxx.com/DXB6V, você deve fazer o redirecionamento para a URL original salva no banco de dados. Caso a URL não seja encontrada no banco, retorne o código de status HTTP 404 (Not Found).

## Exemplo

O seu serviço recebe uma chamada para encurtar uma URL.

### [POST] {{host}}/shorten-url
```json
{
"url": "https://backendbrasil.com.br"
}
```

**E retorna um JSON com a URL encurtada:**

HTTP/1.1 200 OK
```json
{
"url": "https://xxx.com/DXB6V"
}
```


## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Data MongoDB
* Docker
* MongoDB

## Instruções para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/Joaopsguimaraes/urlshortener.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
    cd urlshortener
    ```
3. Certifique-se de que o Docker está instalado e em execução.
4. Inicie o MongoDB usando Docker:
   ```bash
   cd docker
   ```
   ```bash
    docker-compose up -d
   ```
5. Execute o projeto Spring Boot
6. Acesse a API em `http://localhost:8080/shorten-url` para encurtar URLs.
7. Use a URL encurtada retornada para redirecionar para a URL original.
8. Para testar o redirecionamento, acesse a URL encurtada no navegador ou use uma ferramenta como Postman.
