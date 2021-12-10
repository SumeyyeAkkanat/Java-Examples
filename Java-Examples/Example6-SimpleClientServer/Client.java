import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Client extends JFrame {
	
  private JTextArea display;
  private JScrollPane sp;

  public Client() {
    super("Client");
    display = new JTextArea();
    sp = new JScrollPane(display);
    getContentPane().add(sp, BorderLayout.CENTER);
    setSize(350, 250);
    setVisible(true);
  }

  public void runClient() {
    Socket client;
    DataInputStream input;
    DataOutputStream output;
    String line;

    try {
      // Adim 1: Server'a bir Socket ile baglanmaya calis
      client = new Socket(InetAddress.getLocalHost(), 6000);
      display.append("Connected to: " + client.getInetAddress().getHostName());

      // Adim 2: Baglanti yapildi, I/O stream'leri olustur.
      input = new DataInputStream(client.getInputStream());
      output = new DataOutputStream(client.getOutputStream());

      // Adim 3: Server ile etkilesim...
      //output.writeUTF("Merhaba");
      output.writeUTF("Selam");
      line = input.readUTF();

      try {
        if (line.compareTo("OK") == 0) {
          display.append("\nServer message: " + input.readUTF());
          display.append("\nSending message \"Tesekkur ederim.\"\n");
          output.writeUTF("Tesekkur ederim.");
        }
        else {
          display.append("\nServer message: " + line);
        }
      }
      catch (IOException e) {
        System.out.println(e);
	display.append("\nERROR\n"+e);
      }

      // Adim 4: Baglantiyi kapat.
      display.append("\nTransmission complete. " + "Closing connection.\n");
      client.close();
    }
    catch (IOException e) {
      e.printStackTrace();
      display.append("\nERROR\n"+e);
    }
  }

  public static void main(String[] args) {
    Client c = new Client();
    c.setDefaultCloseOperation(EXIT_ON_CLOSE);
    c.runClient();
  }
}

