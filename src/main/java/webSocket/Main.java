package webSocket;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Zhang.hq
 * @date 2023/2/6
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        int port = 8887;

        SocketServer s = new SocketServer(port);
        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            s.broadcast(in);
            if (in.equals("exit")) {
                s.stop(1000);
                break;
            }
        }
    }
}
