package risky.connect;
import java.io.*;
import java.net.*;

public class MplayClient {

	private static Socket clientSocket;

	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Object client_cmd;

	private MplayClient() throws IOException {
		System.out.println("Client: Attempting to connect to server...");
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		clientSocket = new Socket(MplayProtocol.SERVER_ADDRESS, MplayProtocol.SERVER_PORT);
		setupStreams(clientSocket);
		System.out.println("Client: Successfully connected to server");
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(inputStream));
		outputStream.writeObject(client_cmd);
		
		while(true) {
			
		}
	}

	private void setupStreams(Socket s) throws IOException {
		outputStream = new ObjectOutputStream(s.getOutputStream());
		inputStream = new ObjectInputStream(s.getInputStream());
	}

	public static void main(String[] args) {
		try{
			new MplayClient();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
