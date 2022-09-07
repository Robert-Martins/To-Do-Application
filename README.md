<p style="font-style: italic">
    Projeto Final da Disciplina de POO
</p>

<p style="font-style: italic">
    Alunos:<br>
    Bruno Alves dos Reis <br>
    Robert Cardoso Cantares Martins
</p>



# Sistema de To-Do

O sistema foi desenvolvido utilizando Java em sua versão 17.

Habilidades desenvolvidas durante implementação da aplicação:

- Herança, abstração e polimorfismo
- Manipulação de estruturas de dados
- Save e consumo de dados em arquivos
- Interface de interação com usuário (JOptionPane)
- Lógica de programação

O usuário da aplicação possui uma classe que o define,
com nome, timestamp de criação e atualização e seus workspaces.

O usuário final possuirá workspaces no qual poderá definir
o escopo de cada um e os jobs a serem cadastrados.

No escopo de workspaces, usuários poderão cadastrar jobs.
Cada job possuirá um status, comentários, data limite para conclusão
e data de conclusão.

Status são enums. As enumerações são:

- To-Do
- Development
- Done
- Stand-by

Os comentários possuem conteúdo e o intuito é que sejam utilizados
para evolução dos jobs por parte dos usuários.

Foi também criada uma entidade base com atributos comuns
para grande parte das classes, no intuito de evitar redundâncias
através de herança. A classe se denomina BaseEntity,
classe abstrata por não necessitar de instância.

Cada entidade da aplicação possui um CRUD e método de listagem
de atributos filhos desenvolvidos nos arquivos Controllers.

Foi criada também uma exception para recursos não encontrados com
base no id.

Além disso, utilizando JUnit, os construtores da entidade foram
testados. Os métodos do CRUD não foram testados por conta da 
manipulação de dados.

Todos os arquivos e alterações são salvos em um arquivo .txt.

Ainda, existe uma interface que produz relatórios do usuário,
de workspaces e jobs. A interface foi utilizada para abstrair
a implementação.

### Como rodar

Para rodar a aplicação é necessário possuir o JDK 17 e rodar o
método main() no arquivo Application.java.
