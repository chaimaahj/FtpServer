package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class FtpServer {
	
	private ServerSocket firstSocket;
	private int port = 1011;
	
	public FtpServer()
	{
		try{
			firstSocket = new ServerSocket(port);
			Socket clt = firstSocket.accept();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		

	}

}
