<p style="font-style: italic">
    Projeto Final da Disciplina de POO
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
apenas para resgate inicial dos dados salvos no arquivo.

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

Ainda, existe uma interface que produz relatórios do usuário,
de workspaces e jobs. A interface foi utilizada para abstrair
a implementação.