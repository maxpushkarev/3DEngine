package Alt.Task1Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	String IpAddress = "127.0.0.1";
	int Port;
	Socket ClientSocket;
	String ClientId;
	
	public Client(int port)
	{
		this.Port = port;
		this.ClientId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
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
            
            out.writeUTF("ClientId:"+this.ClientId); //TODO: refactor this shit! Use proto-messages maybe
            
            System.out.println("Hello! I'm simple 3D-Engine client. My id: "+this.ClientId);
           
            ServerListener serverListener = new ServerListener(in); 
            serverListener.start();
            
            while (true) {
            	
            	System.out.println("Enter input data:");
                line = keyboard.readLine();
                
                System.out.println("Sending input to Engine...");
                out.writeUTF(line); 
                out.flush(); 
                
            }
            
		} catch (Exception e) {
			System.err.println("Error occured in client: "+e.getMessage());
		} 
		
	}
	
}
