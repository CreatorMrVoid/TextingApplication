import { io, Socket } from "socket.io-client";

let socket: Socket | null = null;

export const initializeSocket = (topicId: string): Socket => {
  if (!socket) {
    socket = io("http://localhost:8081", {
      // Use the Socket.IO server port
      transports: ["websocket"],
      query: { topicId },
    });

    socket.on("connect", () => {
      console.log("Connected to Socket.IO server");
    });

    socket.on("disconnect", () => {
      console.log("Disconnected from Socket.IO server");
    });
  }

  return socket;
};

export const getSocket = (): Socket | null => {
  return socket;
};

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
