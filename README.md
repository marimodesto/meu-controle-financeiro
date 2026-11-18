# Meu Controle Financeiro

## Sobre o projeto

O **Meu Controle Financeiro** é uma aplicação desktop desenvolvida em Java com interface gráfica, criada para auxiliar no controle de receitas e despesas de forma simples e organizada.

O projeto foi desenvolvido como trabalho final da disciplina de Programação I, com o objetivo de aplicar os conceitos de Programação Orientada a Objetos (POO).

## Funcionalidades

- Cadastrar receitas e despesas.
- Listar os lançamentos financeiros.
- Editar lançamentos existentes.
- Excluir lançamentos.
- Calcular e exibir o saldo atual.
- Validar os dados informados pelo usuário.
- Exibir mensagens de erro quando os dados são inválidos.

## Tecnologias utilizadas

- Java
- Java Swing para a interface gráfica
- NetBeans IDE
- `ArrayList` e `List` para armazenar os lançamentos em memória.

## Conceitos de Programação Orientada a Objetos

O projeto utiliza os seguintes conceitos:

- **Encapsulamento:** atributos privados e métodos getters e setters.
- **Herança:** as classes `Receita` e `Despesa` herdam da classe `Lancamento`.
- **Polimorfismo:** receitas e despesas implementam comportamentos diferentes para calcular seu impacto no saldo.
- **Abstração:** a classe abstrata `Lancamento` define características comuns aos lançamentos.
- **Interface:** a interface `OperacaoFinanceira` define o método para calcular o impacto financeiro.
- **Coleções:** utilização de listas para armazenar os lançamentos.

## Classes do projeto

- `ControleFinanceiro.java`: classe principal responsável por iniciar a aplicação.
- `TelaPrincipal.java`: interface gráfica e interação com o usuário.
- `Lancamento.java`: classe abstrata que representa um lançamento financeiro.
- `Receita.java`: representa uma receita.
- `Despesa.java`: representa uma despesa.
- `OperacaoFinanceira.java`: interface que define a operação de cálculo financeiro.
- `ControleLancamentos.java`: gerencia os lançamentos e calcula o saldo.

## Como executar o projeto

1. Instale o JDK e o NetBeans IDE.
2. Acesse o repositório no GitHub: https://github.com/marimodesto/meu-controle-financeiro
3. Clique em **Code** e depois em **Download ZIP**.
4. Extraia os arquivos baixados.
5. Abra o NetBeans e crie um projeto Java com o pacote `meucontrolefinanceiro`.
6. Adicione ao projeto os arquivos `.java` disponíveis na pasta `src/meucontrolefinanceiro`.
7. Execute o arquivo `ControleFinanceiro.java`.

## Armazenamento dos dados

Os lançamentos são armazenados temporariamente em memória. Portanto, os dados cadastrados são perdidos quando a aplicação é encerrada.

## Autora

Mariana

Desenvolvido como projeto acadêmico para a disciplina de Programação I.
