import java.net.*;
import java.io.*;

public class PortScanner {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("kullanim: java PortScanner server");
      System.exit(0);
    }

    InetAddress bilAdd = null;

    try {
      bilAdd = InetAddress.getByName(args[0]);
    }
    catch (UnknownHostException e) {
      System.err.println(e);
      System.exit(0);
    }
    
    
    
    for (int i = 0; i < 100; i++) {
      try {
        System.out.print(i + " ");
        InetSocketAddress socketaddress = new InetSocketAddress(bilAdd, i);
        Socket s = new Socket();
	s.connect(socketaddress, 500);
        System.out.println("\nPort " + i + " calismaktadir.");
        s.close();
      }
      catch (IOException e) {
        // Belirtilen portta hizmet bulunmamaktatir.
      }
    }
  }
}

