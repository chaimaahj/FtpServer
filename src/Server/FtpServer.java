package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
	public Status status = Status.NOT_LOGGED;
	public enum Status {
		NOT_LOGGED, ENTERED_LOGIN, LOGGED
	}
	
	
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
	       
		
		}catch(IOException e){
		e.printStackTrace();
					}
	}
	
	public void executeCommand(String c) throws IOException {
		int index = c.indexOf(" ");
		String command = ((index == -1) ? c.toUpperCase() : c.substring(0, index).toUpperCase());
		String args = (( index == -1) ? null : c.substring(index+1, c.length()));
		
		System.out.println("Command: "+command + " Args: "+ args);
		
		switch(command) {
			case "USER": 
				handleUSER(args);
				break;
			case "PASS": 
				handlePASS(args);
				break;
			case "QUIT":
				handleQUIT();
				break;
			default: 
				System.out.println("501 Unkown command");
				break;
		}
	}
	
	private void handleUSER(String username) throws IOException {
		
		if(username.toLowerCase().equals("chaimaa")) {
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("331 Username ok\r\n");
		    this.status = Status.ENTERED_LOGIN;
		}
		else if(this.status == Status.LOGGED) {
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("User already logged in\r\n");
		}
		else {
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("USER Not logged in\r\n");
		
		}
	}
	
	public void handlePASS(String password) throws IOException {
		if(this.status  == Status.ENTERED_LOGIN && password.equals("1234")) {
			this.status = Status.LOGGED;
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("230 User logged in\r\n");
			
		}
		else if(this.status == Status.LOGGED) {
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes(" User already logged in\r\n");
		}
		else {
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("USER Not logged in\r\n");
		}
	}
	
	private void handleQUIT() throws IOException {
		try {
			br.close();
			this.p.close();
			this.firstSocket.close();
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeBytes("disconnecting from SERVER\r\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
}

		
	}
	

	


