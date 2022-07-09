var nameInput = document.getElementById('message');
let id = id => document.getElementById(id);

let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/ws");
ws.onmessage = msg => updateChat(msg);
//ws.onclose = () => alert("WebSocket connection closed");


document.querySelector('form').addEventListener('submit', function (e) {
    //prevent the normal submission of the form
    e.preventDefault();
    if (nameInput.value !== "") {
        ws.send(nameInput.value);
    }
    nameInput.value = '';
    

});

function updateChat(msg) {
    let data = JSON.parse(msg.data);
    id("chat").insertAdjacentHTML("beforeend", data.userMessage);
    id("chat").scrollTop = id("chat").scrollHeight;
    console.log(data.userMessage);
};


const btn = document.querySelector(".btn-toggle");
const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");

btn.addEventListener("click", function () {
  if (prefersDarkScheme.matches) {
    document.body.classList.toggle("light-theme");
  } else {
    document.body.classList.toggle("dark-theme");
  }
});

