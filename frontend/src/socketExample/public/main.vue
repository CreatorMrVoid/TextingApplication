<script setup lang="ts">
// Import the Socket.IO client library
import { io } from
// Interface for message objects
interface Message {
  user: string;
  body: string;
}

// Create a socket from the Socket.IO module
const socket: Socket = io();

// Assign variables for elements of the page for easy access
const messageUser = document.getElementById("messageUser") as HTMLInputElement;
const messageBody = document.getElementById("messageBody") as HTMLInputElement;
const errorMessage = document.getElementById("errorMessage") as HTMLSpanElement;
const messageField = document.getElementById(
  "messageField"
) as HTMLUListElement;

// Define a function to handle inbound 'newmessage' communications on the socket; render an <li> element for each
socket.on("newmessage", (messageObject: Message) => {
  const messageElement = document.createElement("li");
  messageElement.innerHTML = `<strong>${messageObject.user}:</strong> ${messageObject.body}`;
  messageField.appendChild(messageElement);
});

// Define a function to handle inbound 'botmessage' communications; render these similarly, but with an additional class for visual distinction
socket.on("botmessage", (messageObject: Message) => {
  const messageElement = document.createElement("li");
  messageElement.classList.add("botMessage");
  messageElement.innerHTML = `<strong>${messageObject.user}:</strong> ${messageObject.body}`;
  messageField.appendChild(messageElement);
});

// Define a function for sending a message; this function gets called when the user presses the 'Send' button
const sendMessage = (): void => {
  if (messageUser.value !== "" && messageBody.value !== "") {
    errorMessage.innerHTML = "";
    socket.emit("newmessage", {
      user: messageUser.value,
      body: messageBody.value,
    });
    messageBody.value = "";
  } else {
    errorMessage.innerHTML = "Please complete the form to send a message";
  }
};

// Expose the sendMessage function to the global scope so it can be called from the HTML
(window as any).sendMessage = sendMessage;
</script>
