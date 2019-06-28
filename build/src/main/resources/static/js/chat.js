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
            adicionarMensagem(resultado.content)
        });
    });
}

formEnviarMensagem.addEventListener("submit", event => {
	event.preventDefault()
	stompClient.send('/chat', {}, JSON.stringify({'to': chatArea.dataset.conversa_participant, 'from': selfID, 'content': document.getElementById("mensagem").value}))
	adicionarMensagem(document.getElementById("mensagem").value)
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
				adicionarMensagem(message.content)
			})
		})
		.catch(erro => console.log(erro))
}

connect();
