var nameInput = document.getElementById('message');

document.querySelector('form').addEventListener('submit', function (e) {

    //prevent the normal submission of the form
    e.preventDefault();

    console.log(nameInput.value);
    httpGetAsync("http://localhost:4567/send?message=" + nameInput.value, function (response) {
        console.log(response);
    });

    message.value = '';

    function httpGetAsync(theUrl, callback) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                callback(xmlHttp.responseText);
        }
        xmlHttp.open("GET", theUrl, true); // true for asynchronous 
        xmlHttp.send(null);
    }
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

