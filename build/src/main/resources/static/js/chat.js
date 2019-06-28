botoesAbrirConversas = document.querySelectorAll("a.conversa[data-conversa_id]")
formEnviarMensagem = document.querySelector("#form-mensagem")

chatArea = document.querySelector(".chat")

var stompClient = null;

selfID = document.getElementById("selfNick").innerHTML

function connect() {
    var socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/user/' + selfID + '/msg', function (response) {
            resultado = JSON.parse(response.body);
            tela = document.querySelector("a[data-conversa_participant='"+resultado.from+"']")
            if (tela == null) {
            	document.location.reload(true)
            }
            adicionarMensagem("[" + resultado.from+ "] "+ resultado.content)
        });
    });
}

formEnviarMensagem.addEventListener("submit", event => {
	event.preventDefault()
	stompClient.send('/chat', {}, JSON.stringify({'to': chatArea.dataset.conversa_participant, 'from': selfID, 'content': document.getElementById("mensagem").value}))
	adicionarMensagem("[" + selfID + "] "+ document.getElementById("mensagem").value)
	document.getElementById("mensagem").value = ""
})

botoesAbrirConversas.forEach(botao => {
	botao.addEventListener("click", evento => {
		abrirConversa(botao.dataset.conversa_id, botao.dataset.conversa_participant)
	})
})

function adicionarMensagem(texto) {
	var node = document.createElement("p");                 
	var textnode = document.createTextNode(texto);         
	node.appendChild(textnode);   
	messageArea = document.getElementById("mensagens")
	messageArea.appendChild(node);
	
	messageArea.scrollTop = messageArea.scrollHeight - messageArea.clientHeight;
}

function abrirConversa(idConversa, idParticipante) {
	chatArea.classList.remove("invisible")
	chatArea.setAttribute("data-chat_id", idConversa)
	chatArea.setAttribute("data-conversa_participant", idParticipante)
	document.getElementById("mensagens").innerHTML = '';
	 
	console.log("/chat/" + idConversa)
	fetch("/chat/" + idConversa)
		.then(result => result.json())
		.then(data => {
			document.getElementById("nomeParticipante").innerHTML = data.participant
			console.log(data)
			data.messages.forEach(message => {
				adicionarMensagem("[" + message.author.username+ "] "+ message.content)
			})
		})
		.catch(erro => console.log(erro))
}

connect();

function novaConversa() {	
	fetch("/friends/" + selfID)
		.then(response => response.json())
		.then(amigos => {
			console.log(amigos)
			texto = "<select id='my-select-2'>"
			amigos.forEach(amigo => {
				texto += "<option value='" + amigo.username + "'>" + amigo.ownerName + "</option>"
			})
			texto += "</select>"
				
			Swal.fire({
				title: 'Selecione um amigo para conversar',
				html: texto,
					onOpen: () => {
						$('#my-select-2').select2()
					},
					preConfirm: () => {
						return $('#my-select-2').val()
					}
			}).then(result => {
				Swal.fire('You selected: ' + result.value)
				
				fetch("/chat/create/" + selfID + "/" + result.value)
					.then(result => document.location.reload(true));
			})
		})
}