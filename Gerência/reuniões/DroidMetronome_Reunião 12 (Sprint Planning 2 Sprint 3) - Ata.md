Ata da Reunião 12 (Sprint Planning 2)
=====================================

Informações iniciais
--------------------
Data da reunião - `06/06/2015`  
Local da reunião - `Vídeo chamada Hangout`  
Inicio marcado da reunião - `20:30`  
Inicio real da reunião - `20:30`  
Término previsto da reunião - `22:00`  
Término real da reunião - `21:55`  

Participantes
-------------
Requiridos | Presentes
-----------|----------------
[Gabriela Aimée Guimarães](mailto:gabrielaimeeg@hotmail.com) | Ok!
[Gleibson Wemerson Silva Borges](mailto:gleibsongyn@gmail.com) | Ok!
[Gustavo Moraes dos Santos](mailto:gustavo_moraiss@hotmail.com) | Ok!
[Hiago Augusto Koziel Rahmig](mailto:hiagokoziel100@gmail.com) | Ok!
[Paulo de Oliveira Neto](mailto:pauloesgyn@gmail.com)| Ok!
[Pedro Henrique Silva Farias](mailto:pedrohenriquedrim@gmail.com)| Ok!
[Renan Ofugi Mikami](mailto:renangyn2010@hotmail.com) | Ok!


Assuntos da reunião
-------------------
1. Desmembrar as histórias de usuário (Casos de Uso) escolhidas na sprint planning 1 em tarefas para a Sprint 2;
2. Estimar esforço;
3. Delegar as tarefas para responsáveis.

Memória da reunião
------------------
* Todos os integrantes do grupo se reuniram aos fundos da sala do CTS no INF com o uso de um *notebook* para tirar dúvidas, discutir estratégias para a conclusão do trabalho e definir as tarefas ("*tasks*") do ***Backlog* da *Sprint* 2**.
* Entramos em discussão para destrinchar as histórias do Backlog de Produto (Casos de Uso) selecionadas em tarefas ("*tasks*") com um critério eficiente e viável para completar em menos de uma semana, para compensar o atraso na execução do projeto.
* A ***Sprint Planning 2*** foi feito de forma semelhante à *Sprint Planning 2* das outras *Sprints*, algo semelhante a um *Planning Poker*: o Moderador (*Scrum Master* da equipe) perguntava sobre a história de usuário (caso de uso) "X", o que consistia de ser feito para concluir aquela história e quanto tempo levaria para fazer cada uma das atividades, estimado de acordo com as experiências em projetos anteriores da equipe. No final, perguntava-se novamente se estava bom desse jeito ou se daria pra "quebrar" ainda mais tal tarefa, até a um ponto que fosse irredutível. Depois colocava-se a tarefa no *board* de tarefas da ferramenta de gerenciamento de projetos [**waffle.io**](https://waffle.io/) como uma nova *issue* do GitHub, com um tempo estimado no campo *size*, uma data de entrega definida em uma *Milestone* de *Sprint Diário*, na coluna "Backlog de Sprint".
* Para estimar o esforço, utilizou-se a estimativa de pontos de casos de uso. Um caso de uso é considerado complexo se este depende de mais de 2 casos de uso para ser implementado; médio, caso dependa de apenas um caso de uso para ser implementado; e simples, caso não dependa de nenhum. O peso em horas é baseado na sequência de fibonacci.

| Tipo de caso de Uso | Dependências | Peso em Horas |
|---------------------|--------------|------|
| Simples | Nenhum | 3 |
| Médio | 1 ou 2 | 5 |
| Complexo | maior que 2 | 8 |

O total de dependências é determinado somando a quantidade de casos de uso que precisam ser implementados para que aquele que está sendo avaliado seja implementado, mais ele mesmo. Ou seja, um caso de uso que depende da implementação de outros dois casos de uso receberá valor 2(duas dependências) mais 1(valor dele mesmo), resultando em um total de dependências igual a 3.
Para este sprint, foram escolhidos os seguintes casos de uso:
CSU7 - Escolher Figura Rítmica, CSU10 - Definir Modo Vibratório e CSU11 - Definir Modo LED.

| Caso de Uso | Depende de | Total de Dependências |
|-------------|------------|-------|
| CSU7 | CSU1 | 2 |
| CSU10 | CSU1 e CSU8 | 3 |
| CSU11 | CSU1 e CSU8 | 3 |

Apartir das tabelas, pode-se obter o esforço em horas multiplicando o peso do caso de uso, com a quantidade de dependências dele:

Caso de Uso | Esforço em Horas |
------------|------------------|
| CSU7 | 5 |
| CSU10 | 8 |
| CSU11 | 8 |

* As tarefas foram definidas, assim com os responsáveis por cada uma delas. Tais informações estão armazenadas na ferramenta waffle.io, que está integrada com o repositório do projeto. Cada atividade definida resultou em uma *issue*, associada da forma descrita no item anterior.

Conclusões finais
-----------------
* Divisão e definição das tarefas registradas na [pasta de registro de *screenshots* do waffle.io](https://github.com/gabrielaimeeg/DroidMetronome/tree/master/Gerência/waffle.io), com o esforço dos casos de uso estimados em horas.
![waffle.io sprint planning 2](https://raw.githubusercontent.com/gabrielaimeeg/DroidMetronome/f484660c9467fbfb94f57d129931fd854218ae7a/Ger%C3%AAncia/waffle.io/DroidMetronome_Quadro%20de%20Tarefas%20-%20waffle.io.png)
* ***Sprint Planning 2*** finalizada e ***Backlog* da *Sprint* 2** aprovada pelos membros, que se comprometeram a fazer as tarefas.

