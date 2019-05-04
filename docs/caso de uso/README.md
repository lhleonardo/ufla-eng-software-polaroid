# Caso de uso 

Imagem que descreve os casos de uso do sistema. 

Atores: Usuário

![alt text](https://github.com/lhleonardo/polaroid/blob/master/docs/caso%20de%20uso/source/caso%20de%20uso.jpg?raw=true "Diagrama de Caso de Uso - Polaroid")

# Caso de uso expandido/detalhado

Tabelas descritivas para cada um dos casos de uso da imagem acima

|Caso de Uso:     |RF001 - Envio de Mensagem                                                                                                                |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------|
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Ação de enviar uma mensagem entre usuários logados no sistema                                                                            |
|Pré-condição     |Estar autenticado e ambos possuírem uma ligação de amizade                                                                               |
|Pós-condição     |Emitir uma notificação para o usuário destinatário que uma nova mensagem foi recebida                                                    |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Digita a mensagem no campo de texto destinado a escrita de mensagem                                                         |
|                 |2. [SISTEMA] Processa o texto digitado                                                                                                   |
|                 |3. [SISTEMA] Envia a mensagem para o destinatário                                                                                        |
|                 |4. [SISTEMA] Espera pela confimação de envio                                                                                             |
|                 |5. [SISTEMA] Mostra a confirmação de envio para o usuário                                                                                |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF002 - Cadastro de nova conta                                                                                                           |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Criar uma nova conta no sistema para o usuário                                                                                           |
|Pré-condição     |O username não pode existir                                                                                                              |
|Pós-condição     |Enviar um e-mail para o usuário que acabou de criar a conta com mensagem de boas vindas a plataforma                                     |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Solicita a criação de uma nova conta                                                                                        |
|                 |2. [SISTEMA] Mostra a tela de cadastro e espera pelos dados                                                                              |
|                 |3. [USUARIO] Digita os dados: e-mail, nome completo, data de nascimento, username, senha e foto de perfil.                               |
|                 |4. [SISTEMA] Verifica os dados digitados                                                                                                 |
|                 |5. [SISTEMA] Conclui a criação da conta e redireciona para o início do programa                                                          |
|Fluxo Alternativo|                                                                                                                                         |
|                 |4. [SISTEMA] Caso o login e e-mail já existam, mostra a mensagem "Login já existe"                                                       |
|                 |4. [SISTEMA] Caso o login já exista, mostra a possibilidade  de recuperar a senha                                                        |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF003 - Atualizar dados da conta                                                                                                         |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |O usuário, em seu perfil, escolhe o dado que deseja alterar.                                                                             |
|Pré-condição     |O usuário deve ter um conta e estar autenticado                                                                                          |
|Pós-condição     |No caso de alteração do email deve-se verificar se não há duplicatas                                                                     |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Solicita a mudança de dados.                                                                                                |
|                 |2. [SISTEMA] Prepara a tela de alteração dos dados                                                                                       |
|                 |3. [USUARIO] Modifica os dados desejados                                                                                                 |
|                 |4. [SISTEMA] Recebe e verifica os dados.                                                                                                 |
|                 |5. [SISTEMA] Conclui a mudança de dados e notifica que houve êxito.                                                                      |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF004 - Adicionar um amigo                                                                                                               |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |O usuário adiciona um amigo em sua lista de amigos.                                                                                      |
|Pré-condição     |O usuário deve ter um conta e não ainda não possuir esse amigo.                                                                          |
|Pós-condição     |Enviar uma solitação para o outro usuário de amizade.                                                                                    |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Solicita a tela de busca de usuários pelo username                                                                          |
|                 |2. [SISTEMA] Mostra a tela de busca e espera pelo nickname                                                                               |
|                 |3. [USUARIO] Insere o usuário que deseja adicionar.                                                                                      |
|                 |4. [SISTEMA] Recebe e valida os dados.                                                                                                   |
|                 |5. [SISTEMA] Torna os usuário amigos.                                                                                                    |
|Fluxo Alternativo|                                                                                                                                         |
|                 |4. [SISTEMA] Caso o usuário não exista, mostrar a mensagem "Usuário inexistente".                                                        |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF005 - Excluir um amigo                                                                                                                 |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Desfazer o vínculo de amizade entre dois usuários                                                                                        |
|Pré-condição     |Ambos estarem autenticados e amigos                                                                                                      |
|Pós-condição     |Apagar as conversas entre os usuários e remover o vínculo de amizade                                                                     |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Entra no perfil de quem deseja excluir                                                                                      |
|                 |2. [SISTEMA] Mostra o perfil do usuário                                                                                                  |
|                 |3. [USUARIO] Seleciona a opção "Desfazer amizade"                                                                                        |
|                 |4. [SISTEMA] Desfaz amizade entre os usuários                                                                                            |
|                 |5. [SISTEMA] Apaga as conversas com o usuário excluído                                                                                   |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF006 - Iniciar conversa com um amigo                                                                                                    |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Inicia uma nova conversa com um amigo ja adicionado                                                                                      |
|Pré-condição     |Os usuário devem ser amigos.                                                                                                             |
|Pós-condição     |Abrir a janela de conversa para o usuário que iniciou                                                                                    |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Abre o perfil do amigo.                                                                                                     |
|                 |2. [SISTEMA] Mostra o perfil do usuário.                                                                                                 |
|                 |3. [USUARIO] Selecione a opção "Iniciar conversa"                                                                                        |
|                 |4. [SISTEMA] Cria uma janela específica da conversa com o usuário                                                                        |
|Fluxo Alternativo|                                                                                                                                         |
|                 |3.  [SISTEMA] Caso o usuário escolhido não seja amigo, mostrar a mensagem "Adicionar amigo, antes de inciar uma conversa".               |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF007 - Excluir conversa com um amigo                                                                                                    |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Apagar conversa entre dois usuários                                                                                                      |
|Pré-condição     |Ambos serem amigos e devidamente autenticados                                                                                            |
|Pós-condição     |--------------------                                                                                                                     |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Solicita exclusão da conversa                                                                                               |
|                 |2. [SISTEMA] Apaga a conversa para o usuário                                                                                             |
|                 |3. [SISTEMA] Exclui a janela de conversa com usuário para o usuário que solicitou a exclusão                                             |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF008 - Excluir conversa de grupo                                                                                                        |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Apaga uma conversa de um grupo que o usuário participa.                                                                                  |
|Pré-condição     |O usuário deve participar do grupo.                                                                                                      |
|Pós-condição     |---                                                                                                                                      |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Escolhe o grupo e seleciona o opção "Excluir conversa".                                                                     |
|                 |2. [SISTEMA] Apaga as mensagens do grupo somente para o usuário que a solicitou.                                                         |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF009 - Criar grupo de conversa com amigos                                                                                               |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |Cria um grupo destinado a mensagens entre um conjunto de usuários                                                                        |
|Pré-condição     |Usuário que está criando o grupo esteja autenticado                                                                                      |
|Pós-condição     |---                                                                                                                                      |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Solicita a opção de criar grupo                                                                                             |
|                 |2. [SISTEMA] Solicita o nome do grupo, a imagem de perfil e os integrantes                                                               |
|                 |3. [USUARIO] Informa o nome do grupo, a imagem de perfil e os integrantes, a partir de seu username                                      |
|                 |4. [SISTEMA] Verifica se o username dos integrantes de grupo são existentes                                                              |
|                 |5. [SISTEMA] Cria grupo com todos os integrantes                                                                                         |
|                 |6. [SISTEMA] Notifica os integrantes que foi adicionado ao grupo e cria uma janela de conversa específica para o grupo                   |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF010 - Convidar um amigo para grupo                                                                                                     |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |O usuário com a tag "administrador" em um grupo convida um amigo para o grupo.                                                           |
|Pré-condição     |O usuário deve possuir a tag "administrador" do grupo e estar autenticado                                                                |
|Pós-condição     |Notifica o usuário que ele foi adicionado ao grupo                                                                                       |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Clica com o direto na conversa do grupo e seleciona "Adicionar amigo".                                                      |
|                 |2. [SISTEMA] Mostra os possíveis amigos para adicionar no grupo.                                                                         |
|                 |3. [USUARIO] Escolhe qual amigo deseja adicionar.                                                                                        |
|                 |4. [SISTEMA] Adiciona o usuário desejado ao grupo                                                                                        |
|                 |5. [SISTEMA] Cria a janela do grupo para o usuário adicionado                                                                            |
|                 |6. [SISTEMA] Notifica os demais membros que o usuário foi adicionado                                                                     |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF011 - Sair de um grupo de conversa                                                                                                     |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |O usuário escolhe um grupo que deseja sair .                                                                                             |
|Pré-condição     |O usuário deve participar do grupo antes de sair dele.                                                                                   |
|Pós-condição     |O usuário é notificado que saiu do grupo e as conversas do grupo devem ser excluídas.                                                    |
|Fluxo Principal  |                                                                                                                                         |
|                 |1.[USUARIO] Clica com o direito no grupo desejado e seleciona a opção "sair do grupo".                                                   |
|                 |2. [SISTEMA] Remove o usuário e exclui a convesa do grupo para o usuário em questão.                                                     |
|                 |3. [SISTEMA] Exclui a janela de conversa do grupo para o usuário que saiu.                                                               |
|                 |3. [SISTEMA] Notifica os demais membros do grupo o username do usuário que saiu do grupo.                                                |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
|                 |                                                                                                                                         |
|Caso de Uso:     |RF012 - Remover um amigo do grupo                                                                                                        |
|Atores:          |Usuário                                                                                                                                  |
|Descrição        |O usuário com a tag "administrador" escolhe um membro do grupo para remover.                                                             |
|Pré-condição     |O usuário que fez a ação de remover deve possuir a tag "administrador" e o usuário escolhido para ser removido deve fazer parte do grupo.|
|Pós-condição     |O usuário é notificado da sua remoção do grupo e tem a conversa apagada para ele.                                                        |
|Fluxo Principal  |                                                                                                                                         |
|                 |1. [USUARIO] Com a tag "administrador", o lider clica com o direito na converas do grupo escolhe a opção "Remover membro".               |
|                 |2. [SISTEMA] Mostra os membros do grupo.                                                                                                 |
|                 |3.[USUARIO] Escolhe o usuário que será removido.                                                                                         |
|                 |4. [SISTEMA] Remove o usuário selecionado do grupo e apaga a janela de mensagem do grupo para o usuário em questão.                      |
|                 |5. [SISTEMA] Notifica os integrantes que o usuário foi removido do grupo                                                                 |
|Fluxo Alternativo|                                                                                                                                         |
|                 |---                                                                                                                                      |
