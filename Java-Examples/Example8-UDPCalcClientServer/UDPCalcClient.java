import java.io.*;
import java.net.*;

class UDPCalcClient {
	public static void main(String args[]) throws Exception {
		String host = "localhost";

		if (args.length > 0) {
			host = args[0];
		}

		System.out.print("Lutfen iki adet sayi giriniz:");
		System.out.flush();
		BufferedReader readNumbers =
		        new BufferedReader(new InputStreamReader(System.in));
		String sentence = readNumbers.readLine();
		byte[] sendData = sentence.getBytes();
		InetAddress IPAddress = InetAddress.getByName(host);
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
		                            IPAddress, 2468);
		DatagramSocket calcClientSocket = new DatagramSocket();
		calcClientSocket.setSoTimeout(5000);
		int counter = 1;
		byte[] receiveData = new byte[65508];
		DatagramPacket resultPacket;
		resultPacket = new DatagramPacket(receiveData, receiveData.length);

		while (counter < 4) {
			try {
				calcClientSocket.send(sendPacket);
				System.out.print("Paket " + counter + ".kez Gonderildi.");
				System.out.println("Cevap Bekleniyor...");
				calcClientSocket.receive(resultPacket);
				String result = new String(resultPacket.getData(), 0,
				                           resultPacket.getLength());
				System.out.print("Paket alindi. Cevap: " + result);
				break;
			}
			catch (Exception e) {
				counter++;
				continue;
			}
		} // while son

		if (counter == 4) {
			System.out.println("Cevap alinamadi.");
		}
	}
}

