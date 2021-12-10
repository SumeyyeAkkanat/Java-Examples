import java.net.*;
import java.io.*;

public class DayTimeClient {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("kullanim: java DayTimeClient hostname");
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
      Socket theSocket = new Socket(host, 13);
      InputStream is = theSocket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String data = br.readLine();

      while (data != null) {
        System.out.println(data);
        data = br.readLine();
      }

      theSocket.close();
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }
}

