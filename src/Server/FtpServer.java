package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;





public class FtpServer {
	
	private ServerSocket firstSocket;
	private BufferedReader br;
	private PrintWriter p;
	private int port = 1076;
	private OutputStream out;
	private Socket con;
	
	
	
	
	public FtpServer()
	{
		try{
			firstSocket = new ServerSocket(port);
			con = firstSocket.accept();
			out=con.getOutputStream() ;
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("220 Service ready\r\n");
			//écouter sur le port 1075
			InputStream is = con.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(is);
	         br = new BufferedReader(isr);
	        String str = br.readLine();
	        InputStream in = con.getInputStream();
	        DataInputStream dis= new DataInputStream(in);
	        while(true)
	        {
	        	if(str.equals("chaimaa"))
	        	{
	        		dos.writeBytes("331 Username OK\r\n");
	        	}
	        	if(str.equals("azerty"))
	        	{
	        		dos.writeBytes("230 User logged in\r\n");
	        	}
	        }
		
		}catch(IOException e){
		e.printStackTrace();
					}
	}
	
	
	
		
	}
	

	


