Base URL:
http://localhost:8080/api/tarefas


Endpoints e Operações:

Método  Endpoint            Descrição                                   Status Esperado (Sucesso)               Status Esperado (Falha)
GET     /api/tarefas        Retorna a lista de todas as tarefas.        200 OK                                  -
POST    /api/tarefas        Salva uma nova tarefa no banco de dados.    201 Created                             400 Bad Request
PUT     /api/tarefas/{id}   Atualiza uma tarefa existente pelo ID.      200 OK                                  404 Not Found
DELETE  /api/tarefas/{id}   Remove uma tarefa do banco pelo ID.         24 No Content                           404 Not Found