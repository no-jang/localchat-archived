# localchat

A local chat for the localhost.

## Demo:

A demo can be found at [localchat.felixfischer.tech](https://localchat.felixfischer.tech), however it may not work out
of the box.

This is the case on Google Chrome, but may also occur on other browsers.

The problem is, that the webui uses a websocket to communicate with the backend, which is currently not using an
encrypted connection (TLS). In practice this should not be a problem since the webui will only ever be accessed from the
localhost.

In order to circumvent this you will have to allow "mixed content" in your browser, instructions to do this can be
found [here](https://experienceleague.adobe.com/docs/target/using/experiences/vec/troubleshoot-composer/mixed-content.html?lang=en).

### DO NOT SEND ANY PERSONAL INFORMATION IN THE CHAT, IT IS VISIBLE TO ANYONE ON THE WEB!
