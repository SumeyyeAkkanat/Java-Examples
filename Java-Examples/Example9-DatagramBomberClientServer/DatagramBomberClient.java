import java.io.*;
import java.net.*;

class DatagramBomberClient {

	public static void main(String args[]) throws Exception {

		DatagramSocket clientSocket = new DatagramSocket();
		String host = "localhost";

		if (args.length > 0) {
			host = args[0];
		}

		InetAddress IPAddress = InetAddress.getByName(host);
		System.out.print("Lutfen isteginizi yaziniz:");
		BufferedReader inFromUser = new BufferedReader(
		                                    new InputStreamReader(System.in));
		String sentence = inFromUser.readLine();
		byte[] sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
		                            IPAddress, 13579);
		clientSocket.send(sendPacket);
		System.out.println("Packet Sent.");
		int counter = 0;
		byte[] receiveData = new byte[65508];

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
			                               receiveData.length);
			clientSocket.receive(receivePacket);
			String recStr = new String(receivePacket.getData(), 0,
			                           receivePacket.getLength());
			if (!recStr.equalsIgnoreCase(new String("" + counter))) {
				System.out.print("Sirasi bozulmus paket. Gelen:" + recStr);
				System.out.println(" Beklenen:" + counter);
			}
			else {
				System.out.print(recStr + " ");
			}
			counter++;
		}
	}
}

