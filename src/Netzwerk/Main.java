package Netzwerk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hallo, hier ist das Netzwerk-Projekt");
        // Wir arbeiten auf localhost
        // Wir haben Port 9090 für unseren ServerSocket Port konfiguriert
        ServerSocket serverSocket = new ServerSocket(8787);
        // Wartet auf einen Verbindungsaufbau zu diesem Socket und nimmt ihn an.
        // Die Methode blockiert, bis eine Verbindung hergestellt ist.
        Socket socket = serverSocket.accept();

        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("Hallo Client!");
        System.out.println("Ein Client hat sich verbunden");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String command;
        while ((command = bufferedReader.readLine()) != null) {
            System.out.println(command);

            if ("h".equalsIgnoreCase(command)) {
                bufferedWriter.write("Das ist die Hilfe");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else if ("q".equalsIgnoreCase(command)) {
                bufferedWriter.write("Auf Wiedersehen");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
        System.out.println("Ende des Netzwerk-Projekts");
    }
}