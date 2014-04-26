package Alt.Task1;

import java.net.ServerSocket;
import java.net.Socket;

public class Communicator {

	int Port;
	ServerSocket EngineServerSocket;
	
	public Communicator(int port)
	{
		this.Port = port;
	}
	
	
	public void Initialize()
	{
		try {
			
			EngineServerSocket = new ServerSocket(this.Port);
			System.out.println("Waiting for a EngineClients...");

			while(true)
			{
				Socket socket = EngineServerSocket.accept(); 
				System.out.println("New EngineClient connected!!!");
				new CommunicationThread(socket).start();
			}
		    
		        
		} catch (Exception e) {
			System.err.println("Error occured at Communicator: "+e.getMessage());
		}
     
	}
	
}