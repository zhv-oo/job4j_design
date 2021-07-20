package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(80)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
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
        } catch (Exception e) {
            LOG.error("Error start server!", e);
        }
    }
}