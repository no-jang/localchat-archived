var nameInput = document.getElementById('message');


let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/ws");
ws.onmessage = msg => console.log(msg);
ws.onclose = () => alert("WebSocket connection closed");


document.querySelector('form').addEventListener('submit', function (e) {

    //prevent the normal submission of the form
    e.preventDefault();

    console.log(nameInput.value);

    if (nameInput.value !== "") {
        ws.send(nameInput.value);
    }
    
    nameInput.value = '';

});

console.log('script.js loaded');

const btn = document.querySelector(".btn-toggle");
const prefersDarkScheme = window.matchMedia("(prefers-color-scheme: dark)");

btn.addEventListener("click", function () {
  if (prefersDarkScheme.matches) {
    document.body.classList.toggle("light-theme");
  } else {
    document.body.classList.toggle("dark-theme");
  }
});

