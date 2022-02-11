# BOOTCAMP IT 13/12

### Requisito Final:

| Iohara |
| :---: |
|[<img src="https://avatars.githubusercontent.com/u/96189406?v=4?size=250" width=115><br>](https://github.com/iohara97) |

## Projeto Final -  Mercado Livre Frescos


O objetivo deste **projeto final** é implementar uma API REST no âmbito do slogan e aplicar
os conteúdos trabalhados durante o **BOOTCAMP MELI**. (Git, Java, Spring, Armazenamento,
Qualidade e Segurança).

Por meio dessa API, o usuário poderá:

c Ser capaz de inserir um lote de produtos no armazém de distribuição para registrar
essa existência no estoque.

● Ter as informações necessárias para entender em que setor deve ser armazenada a
mercadoria para que fique em bom estado enquanto estiver no almoxarifado e para
que se possa mostrar ao colaborador que vai procurar o produto (picking) onde está .

● Ser capaz de detectar se há produtos que estão prestes a expirar para tomar alguma
medida a esse respeito (pode ser devolvê-los ao Vendedor, jogá-los fora ou realizar
alguma ação comercial específica para liquidá-los).

● Para poder consultar o estoque, listar quais produtos estão em qual armazém e dado
um produto específico, entender também em qual armazém ele está armazenado.

● Poder cadastrar o pedido de compra para que os colaboradores dentro do Fullfilment
possam montar o (s) pedido (s) para despachá-los.

## Requisito 6

Pensando na necessidade de gestão de estoque, objetivo foi construir uma API para gerar relatórios com filtros.

● Relatório baseado em produtos próximos da data de validade (21 dias).

● Por meio de parâmetro, o cliente pode controlar o limite de estoque para conferência.

● Relatório por categoria, setor e armazem.

### Autenticação


Autenticação como representante passada por Bearer Token necessária: 


**[localhost:8080/api/v1/auth](localhost:8080/api/v1/stock-report?estoqueMinimo=100)**

- Payload


    {

        "user": "lucian",

        "senha": "12345"

    }

### Endpoints:

● **[Retorna relatório de estoque simples](localhost:8080/api/v1/stock-report?estoqueMinimo=100)**

● **[Retorna relatório de estoque por categoria](localhost:8080/api/v1/stock-report/filterCategory?categoria=congelado&estoqueMinimo=200)**

● **[Retorna relatório de estoque por setor](localhost:8080/api/v1/stock-report/filterSector?setorId=2&estoqueMinimo=200)**

● **[Retorna relatório de estoque por armazem](localhost:8080/api/v1/stock-report/filterStorage?armazemId=1&estoqueMinimo=200)**




## 🚀 Frameworks, Linguagens e Ferramentas

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![PostMan](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![IntelliJIDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

## 🔗 Links Úteis

#### * Para baixar as Coleções do Postman:

[![PostMan](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](https://www.getpostman.com/collections/06888636d5bae5c4fb62)

###
