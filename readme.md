# ALURAFLIX
API REST desenvolvida durante o challenge#01 de backend da Alura

Esta API implementa as seguintes especifícações:
1. Rotas no padrão REST
2. Validações feitas conforme as regras de negócio
3. Base de dados para persistência das informações
4. Serviço de autenticação para acesso às rotas GET, POST, PUT e DELETE
5. Paginação das requisições

## Regras de Negócio
1. Todos os campos de vídeos e categorias devem ser obrigatórios e validados
2. A categoria com ID = 1, deve chamar LIVRE e caso ela não seja especificada na criação do vídeo, deve-se atribuir o ID = 1

## Recursos
- Rotas CRUD para videos
- Rotas CRUD para categorias
- Rota GET relacionando categorias e videos:
```
GET categorias/:id/videos/
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