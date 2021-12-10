import java.io.*;
import java.net.*;

class DatagramBomberServer {

	public static void main(String args[]) throws Exception {

		// 13579 numarali UDP Portunda bir DatagramSocket acilmakta
		DatagramSocket serverSocket = new DatagramSocket(13579);
		byte[] receiveData = new byte[1024];

		while (true) {

			// Istemciden gelecek bilgiyi tutmak icin bir DatagramPacket hazirlama
			DatagramPacket receivePacket;
			receivePacket = new DatagramPacket(receiveData, receiveData.length);

			// Istemciden gelecek DatagramPacket'i beklemeye baslama
			serverSocket.receive(receivePacket);

			//Gelen paketin icerigini ve geldigi istemci hakkinda bilgi basma
			System.out.print("Packet received from " + receivePacket.getAddress());
			System.out.print(" port " + receivePacket.getPort());
			String sentence = new String(receivePacket.getData(), 0,receivePacket.getLength());
			System.out.println("" + sentence);

			// Paketlerin gonderilecegi adresi gelen paketin icinden okuma
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();

			String newSentence;

			// 500.000 defa icinde sirali rakamlar bulunan paket gonderme
			for (int x = 0; x < 500000; x++) {
				newSentence = "" + x;
				byte[] sendData = newSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}
			//for() son

		}
		//while son

	}
	//main() son
}

