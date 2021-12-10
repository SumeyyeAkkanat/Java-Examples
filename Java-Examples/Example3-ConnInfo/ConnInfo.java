import java.net.*;
import java.io.*;

public class ConnInfo {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("kullanim: java ConnInfo hostname");
      System.exit(0);
    }

    InetAddress host = null;

    try {
      host = InetAddress.getByName(args[0]);
    }
    catch (UnknownHostException e) {
      System.err.println(e);
      System.exit(0);
    }

    try {
      Socket theSocket = new Socket(host, 80);
      System.out.println("\ngetInetAddress() " + theSocket.getInetAddress());
      System.out.println("getPort() " + theSocket.getPort());
      System.out.println("getLocalAddress() " + theSocket.getLocalAddress());
      System.out.println("getLocalPort() " + theSocket.getLocalPort());
      System.out.println("getRemoteSocketAddress() " +
                         theSocket.getRemoteSocketAddress());
      System.out.println("getLocalSocketAddress() " +
                         theSocket.getLocalSocketAddress());
      theSocket.close();
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }
}


