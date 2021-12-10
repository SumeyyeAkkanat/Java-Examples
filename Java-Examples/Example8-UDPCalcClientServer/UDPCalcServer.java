import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

class UDPCalcServer {

	public static void main(String args[]) throws Exception {

		DatagramSocket calcServerSocket = new DatagramSocket(2468);
		byte[] receiveData = new byte[65508];
		byte[] sendData = new byte[1024];

		while (true) {
 
			// Istemciden gelecek bilgiyi tutmak icin bir DatagramPacket hazirlama
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
			                               receiveData.length);

			// Istemciden gelecek DatagramPacket'i beklemeye baslama
			calcServerSocket.receive(receivePacket);

			//Gelen paketin icerigini ve geldigi istemci hakkinda bilgi basma
			System.out.print("Packet received from " + receivePacket.getAddress());
			System.out.print(" port " + receivePacket.getPort());
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println(" Message:" + sentence);
			
			//Gelen paketteki iki sayiyi okuma ve sonucu hazirlama
			String newSentence;
			try {
				StringTokenizer st = new StringTokenizer(sentence, " x*");
				int d1 = Integer.parseInt(st.nextToken());
				int d2 = Integer.parseInt(st.nextToken());
				newSentence = d1 + "*" + d2 + "=" + (d1 * d2) + "\n";
			}
			catch (Exception e) {
				newSentence = "Lutfen bosluk ile ayrilmis iki sayi gonderiniz.\n";
			}
			
			//Gonderilecek byte[] 'i hazirlama
			sendData = newSentence.getBytes();
			
			// Paketin gonderilecegi adresi gelen paketin icinden okuma
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			
			//Cevabi iceren DatagramPacket'i hazirlama
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
			                            IPAddress, port);

			//Cevabi iceren DatagramPacket'i gonderme
			calcServerSocket.send(sendPacket);

		} // while bitti
	}
}

