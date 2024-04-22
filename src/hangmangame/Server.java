package hangmangame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1111)){

            System.out.println("Server gestartet");



            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client Connected");

                Game g = new Game();
                HangmanClient hangmanClient = new HangmanClient(g, client);

                Thread thread = new Thread(hangmanClient);
                thread.start();

                //hangmanClient.run();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
