import java.net.*;
import java.io.*;
import java.util.Date;

public class DayTimeServer {
	
  public static void main(String[] args) {
	  
    ServerSocket srvrSocket = null;
    Socket connSocket;
    OutputStream os;

    try {
      srvrSocket = new ServerSocket(13);
      System.out.println("Daytime Server is listening on port 13");
    }
    catch (IOException e) {
      System.err.println(e);
      System.exit(0);
    }

    while (true) {
	    
      try {
        connSocket = srvrSocket.accept();
        System.out.println("New User from " + connSocket.getInetAddress());
        os = connSocket.getOutputStream();
        Date now = new Date();
        String currentDate = now.toString() + "\r\n";
        os.write(currentDate.getBytes());
        os.close();
        connSocket.close();
      }
      catch (IOException e) {
        System.err.println(e);
      }
      
    } // end of while
  } // end of main
}

