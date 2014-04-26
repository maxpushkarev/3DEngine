package Alt.Task1Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	String IpAddress = "127.0.0.1";
	int Port;
	Socket ClientSocket;
	
	
	public Client(int port)
	{
		this.Port = port;
	}
	
	
	
	public void Initialize()
	{
			
		try {
			
			InetAddress ipAddress = InetAddress.getByName(this.IpAddress);
			this.ClientSocket = new Socket(ipAddress, this.Port);
			
			InputStream sin = this.ClientSocket.getInputStream();
            OutputStream sout = this.ClientSocket.getOutputStream();
            
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            
            while (true) {
            	System.out.println("Enter input data:");
                line = keyboard.readLine();
                System.out.println("Sending input to Engine...");
                out.writeUTF(line); 
                out.flush(); 
                line = in.readUTF(); 
                System.out.println("Result: " + line);
            }
            
		} catch (Exception e) {
			System.err.println("Error occured: "+e.getMessage());
		} 
		
	}
	
}
