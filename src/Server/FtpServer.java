package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class FtpServer {
	
	private ServerSocket firstSocket;
	private int port = 1075;
	OutputStream out;
	
	public FtpServer()
	{
		try{
			firstSocket = new ServerSocket(port);
			Socket con = firstSocket.accept();
			out=con.getOutputStream() ;
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("220 Service ready\r\n");
			InputStream is = con.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String str = br.readLine();
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new FtpServer();

	}

}
