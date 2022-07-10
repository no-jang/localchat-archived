var nameInput = document.getElementById('message');
let id = id => document.getElementById(id);

let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/ws");
ws.onmessage = msg => updateChat(msg);
//ws.onclose = () => alert("WebSocket connection closed");


id("send").addEventListener('click', () => sendAndClear(id("message").value));
id("message").addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        sendAndClear(id("message").value);
    }
});

function sendAndClear(message) {
    if (message !== "") {
        console.log(message);
        ws.send(message);
        id("message").value = "";
    }
}

function updateChat(msg) {
    let data = JSON.parse(msg.data);
    id("chat").insertAdjacentHTML("beforeend", data.userMessage);
    id("chat").scrollTop = id("chat").scrollHeight;
    console.log(data.userMessage);
}

const btn = document.querySelector(".btn-colorscheme");
const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");


window.onload = function () {
    if (prefersDarkScheme.matches) {
        console.log("Dark mode");
        id("themeicon").classList.add("fa-sun");
    } else {
        console.log("Light mode");
        id("themeicon").classList.add("fa-moon");
    }
};

btn.addEventListener("click", function () {
    if (prefersDarkScheme.matches) {
        document.body.classList.toggle("light-theme");
        id("themeicon").classList.toggle("fa-moon");
        id("themeicon").classList.toggle("fa-sun");
    } else {
        document.body.classList.toggle("dark-theme");
        id("themeicon").classList.toggle("fa-sun");
        id("themeicon").classList.toggle("fa-moon");
    }
});

