package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        String[] tmp = str.split("=|, | ");
                        if (tmp.length > 2) {
                            if (tmp[2].equals("Hello")) {
                                out.write("Hello, dear friend!\r\n".getBytes());
                            } else if (tmp[2].equals("Bye")) {
                                out.write("Server closed!\r\n".getBytes());
                                server.close();
                            } else {
                                out.write((tmp[2] + "\r\n").getBytes());
                            }
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}