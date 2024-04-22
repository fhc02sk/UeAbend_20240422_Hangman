package hangmangame;

import java.io.*;
import java.net.Socket;

public class HangmanClient implements Runnable {
    private Game game;
    private Socket client;
    private String name;

    public HangmanClient(Game game, Socket client) {
        this.game = game;
        this.client = client;
    }
    @Override
    public void run() {

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
            ) {

            bw.write("Welcome to Hangman. What's your name?");
            bw.newLine();
            bw.flush();

            String command;
            while ((command = br.readLine()) != null){
                System.out.println("received >> " + command);

                String[] commandParts = command.split(" ");
                if (commandParts[0].equals("EXIT")) {
                    break;
                }
                else if (commandParts[0].equals("NAME")) {
                    this.name = commandParts[1];
                    System.out.println("Name: " + name);
                }
                else if (commandParts[0].equals("TRY")) {
                    char c = commandParts[1].charAt(0);

                    String foundWord = game.tryCharacter(c);
                    bw.write(foundWord);
                    bw.newLine();
                    bw.flush();

                    if (game.isFinished()){
                        bw.write("Gratulation, das gesuchte Wort wurde gefunden. Neues Game startet");
                        bw.newLine();
                        bw.flush();
                    }

                }
                else {
                    System.out.println("unkown command >> " + command);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
