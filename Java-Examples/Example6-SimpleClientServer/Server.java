import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Server extends JFrame {
	
  private JTextArea display;
  private JScrollPane sp;

  public Server() {
    super("Server");
    display = new JTextArea();
    sp = new JScrollPane(display);
    getContentPane().add(sp, BorderLayout.CENTER);
    setSize(350, 250);
    setVisible(true);
  }

  public void runServer() {
    ServerSocket server;
    Socket connection;
    DataOutputStream output;
    DataInputStream input;
    String line;
    int counter = 1;

    try {
      // Adim 1: Bir ServerSocket olustur.
      server = new ServerSocket(6000, 100);

      while (true) {
        // Adim 2: Bir baglanti bekle........
        display.append("Server is waiting for a client connection...\n");
        connection = server.accept();
        display.append("Connection " + counter + " received from: " +
                       connection.getInetAddress().getHostName());

        // Adim 3: Giris ve cikis streamleri olustur
        input = new DataInputStream(connection.getInputStream());
        output = new DataOutputStream(connection.getOutputStream());

        // Adim 4: Istemci sunucu islemleri
        line = input.readUTF();

        try {
          if (line.compareTo("Selam") == 0) {
            output.writeUTF("OK");
            display.append("\nSending message \"Connection successful\"\n");
            output.writeUTF("Connection successful");
            display.append("Client message: " + input.readUTF());
          }
          else {
            output.writeUTF("Sorry, your password is wrong...");
          }
        }
        catch (IOException e) {
          System.out.println(e);
        }

        // Adim 5: Baglantiyi kapat.
        display.append("\nTransmission complete. " + "Closing socket.\n\n");
        connection.close();
        ++counter;

        // Adim 6: 2. Adima geri don.
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Server s = new Server();
    s.setDefaultCloseOperation(EXIT_ON_CLOSE);
    s.runServer();
  }
}

