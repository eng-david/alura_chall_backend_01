# ALURAFLIX
API REST desenvolvida durante o challenge#01 de backend da Alura

Esta API implementa as seguintes especifícações:
1. Rotas no padrão REST
2. Validações feitas conforme as regras de negócio
3. Base de dados para persistência das informações
4. Serviço de autenticação para acesso às rotas GET, POST, PUT e DELETE
5. Paginação das requisições
6. Autenticação Stateless com JWT

## Regras de Negócio
1. Todos os campos de vídeos e categorias devem ser obrigatórios e validados
2. A categoria com ID = 1, deve chamar LIVRE e caso ela não seja especificada na criação do vídeo, deve-se atribuir o ID = 1
3. Endpoint "GET /videos/free" com um número fixo de filmes disponível, sem a necessidade de autenticação

## Recursos
- Rotas CRUD para videos

Retorna todos os videos:
```
GET /videos
```

Retorna video por id:
```
GET /videos/:id
```

- Rotas CRUD para categorias
- Rota GET relacionando categorias e videos:
```
GET /categorias/:id/videos/
```
- Rota que busca vídeos por nome:
```
GET /videos/?search=jogos
```

## Tecnologias Utilizadas
- `linguagem Java`
- `Spring Boot`
- `Maven`
- `Banco de Dados MySQL`

## Pendentes
- [ ] Caso a autenticação não seja válida, retornar uma mensagem informando Não autorizado ou Credenciais inválidas.
- [ ] Caso usuário e senha inválido, informar Usuário e senha inválido.
- [ ] Criar testes de unidade para os modelos e controller.
- [ ] Crie testes de integração.
- [ ] Colocar a API em produção, em algum provedor de cloud.