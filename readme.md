# MAPA - Programação I

### Nome: André Luís Fray Casanova

### RA: 20085878-5

---

Atividade MAPA de Programação I da Unicesumar. A aplicação é um sistema de controle de estoque/importações.

Não há interface gráfica, ou mesmo, persistência dos dados. O projeto tornou-se desafiador, pois até então não havíamos aprendido a tratar exceptions.

Entende-se que uma importadora realiza as seguintes operações básica:

- **Cadastro de Produtos:** com inclusão, alteração, consulta e exclusão de produtos.
- **Movimentação:** as entradas e saídas dos produtos em estoque.
- **Reajuste de Preços:** no caso, aumentar ou diminuir percentualmente um ou mais produtos do estoque.
- **Relatório:** listar o código e as informações de todos os produtos em uma tela.

Nesta prática, não foram utilizados os conceitos de herança e polimorfismo, apenas a abstração e o encapsulamento. Para ver esses outros conceitos consultar a [Atividade 1 de POO](https://github.com/allud1t/Atividade1POO).

## Classes

- **Principal**
  - Foi definido um ArrayList
- **Produto**
  - Declarado os atributos da classe e métodos construtores, bem como seus Getters e Setters.
- **Menu**
  - Para a realização dos menus, foi criada uma nova classe de modo a manter o main mais limpo possível. Aqui estão grande parte das estruturas condicionais e de repetição.

## Requisitos da Aplicação:

:white_check_mark: não existem produtos com nomes iguais;

:white_check_mark: preço unitário tem que ser maior que zero;

:white_check_mark: quantidade em estoque tem que ser igual ou maior que zero;

:white_check_mark: para subtrair ou adicionar do estoque são utilizados os métodos;

:white_check_mark: reajuste irá fazer o reajuste do preço unitário em porcentagem.

:white_check_mark: menus com outros sub-menus sempre utilizam a opção de retornar ao menu anterior

---

**Conceitos utilizados:**

- abstração
- encapsulamento
- comparação entre objetos
- javabeans
- lógica de programação

------

É possível baixar e executar o arquivo .jar dentro do projeto. Para isso, no diretório do arquivo abra o terminal e rode o seguinte comando:

`java -jar Importadora.jar`

------

##### :rocket:Github: https://github.com/allud1t/SistemaImportadora

##### :computer:Git: https://github.com/allud1t/SistemaImportadora.git

