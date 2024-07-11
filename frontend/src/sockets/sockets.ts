import { io } from "socket.io-client";

const socket = io("http://localhost:8081");

const connect = (message: string, eventName: string) => {
  return new Promise<void>((resolve) => {
    socket.on("connect", () => {
      console.log("Connected to server");
      socket.emit(eventName, message);
      resolve();
    });
  });
};

const getSocket = () => {
  return socket;
};

export { connect, getSocket };

/*
export const socket = io(URL, {
  autoConnect: false,
});

export const connectSocket = () => {
  socket.connect();
};

export const disconnectSocket = () => {
  socket.disconnect();
};
*/
