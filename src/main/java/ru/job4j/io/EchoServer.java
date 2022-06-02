package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    Pattern valid = Pattern.compile("^?msg=.+$");
                    String str = in.readLine();
                    if (valid.matcher(str).find()) {
                        Scanner sc = new Scanner(str).useDelimiter("\\sHTTP.*$");
                        String answer = sc.next().split("=", 2)[1];
                        out.write((answer + "\r\n").getBytes());
                        if ("Exit".equals(answer)) {
                            out.write("Server is closed".getBytes());
                            server.close();
                        }
                    } else {
                        out.write("Unknown request".getBytes());
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("In/Out error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server socket error", e);
        }
    }
}