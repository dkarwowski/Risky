package risky.connect;
import java.io.*;
import java.net.*;

public class MplayServer {
	
//update to include marshalling 
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Object serv_cmd;
	private static boolean serverRunning = false;

	private MplayServer() throws IOException, ClassNotFoundException {
		System.out.println("Server: Attemption to start...");
		serverSocket = new ServerSocket(MplayProtocol.SERVER_PORT);
		System.out.println("Server: Successfully started on port " + MplayProtocol.SERVER_PORT);
		serverRunning = true;
		
		while (serverRunning){
			clientSocket = serverSocket.accept();
			System.out.println("Server: Client connected from " + clientSocket.getInetAddress().getHostAddress());
			setupStreams(clientSocket);
			serv_cmd = inputStream.read();
            System.out.println("Received: " + serv_cmd);
			outputStream.writeObject("Echo: " + serv_cmd);
		}
	}

	private void setupStreams(Socket s) throws IOException {
		outputStream = new ObjectOutputStream(s.getOutputStream());
		inputStream = new ObjectInputStream(s.getInputStream());
	}

	public static void main(String[] args) {
		try{
			new MplayServer();
		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
