Ata da Reunião 09 (Sprint Planning 2)
=====================================

Informações iniciais
--------------------
Data da reunião - `26/05/2015`  
Local da reunião - `Sala 257 INF/UFG - Alameda Palmeiras, Quadra D, Câmpus Samambaia`  
Inicio marcado da reunião - `20:30`  
Inicio real da reunião - `22:00`  
Término previsto da reunião - `20:50`  
Término real da reunião - `21:55`  

Participantes
-------------
Requiridos | Presentes
-----------|----------------
[Gabriela Aimée Guimarães](mailto:gabrielaimeeg@hotmail.com) | Ok!
[Gustavo Moraes dos Santos](mailto:gustavo_moraiss@hotmail.com) | Ok!
[Hiago Augusto Koziel Rahmig](mailto:hiagokoziel100@gmail.com) | Ok!
[Paulo de Oliveira Neto](mailto:pauloesgyn@gmail.com)| Ok!
[Pedro Henrique Silva Farias](mailto:pedrohenriquedrim@gmail.com)| Ok!
[Renan Ofugi Mikami](mailto:renangyn2010@hotmail.com) | Ok!
[Julianny Alves da Silva](mailto:julianny.alves@hotmail.com) | Ok!

Assuntos da reunião
-------------------
1. Desmembrar as histórias de usuário (Casos de Uso) escolhidas na sprint planning 1 em tarefas para a Sprint 2;
2. Estimar esforço;
3. Delegar as tarefas para responsáveis.

Memória da reunião
------------------
* Todos os integrantes do grupo se reuniram aos fundos da sala 257 do INF com o uso de um *notebook* para tirar dúvidas, discutir estratégias para a conclusão do trabalho e definir as tarefas ("*tasks*") do ***Backlog* da *Sprint* 2**.
* Dúvidas que haviam entre os integrantes sobre o conceitos de Teoria Musical e o funcionamento do metrônomo foram respondidas pelo *Scrum Master* [Gustavo Moraes]() e os que sabiam responder às dúvidas.
* Entramos em discussão para destrinchar as histórias do Backlog de Produto (Casos de Uso) selecionadas em tarefas ("*tasks*") com um critério eficiente e viável para completar em menos de uma semana, para compensar o atraso na execução do projeto.
* A ***Sprint Planning 2*** foi feito de forma semelhante à *Sprint Planning 2* da primeira *Sprint*, algo semelhante a um *Planning Poker*: o Moderador (*Scrum Master* da equipe) perguntava sobre a história de usuário (caso de uso) "X", o que consistia de ser feito para concluir aquela história e quanto tempo levaria para fazer cada uma das atividades, estimado de acordo com as experiências em projetos anteriores da equipe. No final, perguntava-se novamente se estava bom desse jeito ou se daria pra "quebrar" ainda mais tal tarefa, até a um ponto que fosse irredutível. Depois colocava-se a tarefa no *board* de tarefas da ferramenta de gerenciamento de projetos [**waffle.io**](https://waffle.io/) como uma nova *issue* do GitHub, com um tempo estimado no campo *size*, uma data de entrega definida em uma *Milestone* de *Sprint Diário*, na coluna "Backlog de Sprint".
* Para estimar o esforço, utilizou-se a estimativa de pontos de casos de uso. Um caso de uso é considerado complexo se este depende de mais de 2 casos de uso para ser implementado; médio, caso dependa de apenas um caso de uso para ser implementado; e simples, caso não dependa de nenhum.

| Tipo de caso de Uso | Dependências | Peso |
|---------------------|--------------|------|
| Simples | Nenhum | 3 |
| Médio | 1 | 6 |
| Complexo | igual ou maior que 2 | 9 |

Para este sprint, foram escolhidos os seguintes casos de uso:
CSU6 - Definir Timer, CSU7 - Escolher Figura Rítmica, CSU8 - Abrir Configurações, CSU9 - Visualizar Informações.

| Caso de Uso | Depende de | Total de Dependências |
|-------------|------------|-------|
| CSU6 | CSU1 | 1 |
| CSU7 | CSU1 | 1 |
| CSU8 | CSU1 | 1 |
| CSU9 | CSU1 e CSU8 | 2 |

Apartir das tabelas, pode-se obter o esforço em horas multiplicando o peso do caso de uso, com a quantidade de dependências dele:

Caso de Uso | Esforço em Horas |
------------|------------------|
| CSU6 | 3 |
| CSU7 | 3 |
| CSU8 | 3 |
| CSU9 | 9 |

* As tarefas foram definidas, assim com os responsáveis por cada uma delas. Tais informações estão armazenadas na ferramenta waffle.io, que está integrada com o repositório do projeto. Cada atividade definida resultou em uma *issue*, associada da forma descrita no item anterior. O ***Backlog* da *Sprint* 1** consistiu de **17** ***issues***, no total estimado de **79 horas**, tirando as Solicitações de Mudança, que necessitam de análise da **Manutenção de Software**.

Conclusões finais
-----------------
* Divisão e definição das tarefas registradas na [pasta de registro de *screenshots* do waffle.io](https://github.com/gabrielaimeeg/DroidMetronome/tree/master/Gerência/waffle.io), com o esforço dos casos de uso estimados em horas.
![waffle.io sprint planning 2](https://raw.githubusercontent.com/gabrielaimeeg/DroidMetronome/9167d6c786a9e466700671ba05d0961f67b9aa94/Ger%C3%AAncia/waffle.io/DroidMetronome_Quadro%20de%20Tarefas%20-%20waffle.io.png)
* ***Sprint Planning 2*** finalizada e ***Backlog* da *Sprint* 2** aprovada pelos membros, que se comprometeram a fazer as tarefas.

