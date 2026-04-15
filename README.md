# Product Store - Cadastro de Produtos

Este é um aplicativo Android desenvolvido para o gerenciamento de estoque de uma loja de produtos eletrônicos. O aplicativo permite o cadastro de novos produtos e a visualização de uma listagem dos itens cadastrados.

## Funcionalidades

- **Cadastro de Produto:** Permite inserir Nome, Código (alfanumérico), Preço e Quantidade.
- **Validação de Dados:** 
    - Todos os campos são obrigatórios.
    - O preço deve ser um valor numérico positivo.
    - A quantidade deve ser um número inteiro positivo.
- **Persistência de Dados:** Utiliza **Room Database** para armazenar as informações localmente.
- **Listagem de Produtos:** Uma tela dedicada para listar todos os produtos cadastrados com seus respectivos nomes, códigos e preços.
- **Interface Moderna:** Desenvolvida seguindo padrões de Material Design com `TextInputLayout`, `RecyclerView` e `CardView`.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Banco de Dados:** Room Persistence Library
- **UI Components:** RecyclerView, CardView, Material Components
- **Arquitetura:** DAO (Data Access Object) e Database Singleton pattern

## Estrutura do Projeto

- `MainActivity.java`: Tela de cadastro e validação.
- `ProductListActivity.java`: Tela de exibição da lista de produtos.
- `Product.java`: Entidade que representa o produto no banco de dados.
- `ProductDao.java`: Interface com as operações de banco de dados.
- `ProductDatabase.java`: Classe de configuração do Room.
- `ProductAdapter.java`: Adaptador para exibição dos itens no RecyclerView.

---
*Projeto desenvolvido como parte de uma avaliação prática de desenvolvimento Android.*
