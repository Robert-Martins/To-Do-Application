<p style="font-style: italic">
    Projeto Final da Disciplina de POO
</p>

# Sistema de To-Do

Foi desenvolvido um sistema de To-Do.

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
através de herança.