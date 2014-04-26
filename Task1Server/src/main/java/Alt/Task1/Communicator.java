package Alt.Task1;

import java.net.ServerSocket;
import java.net.Socket;

public class Communicator {

	int Port;
	ServerSocket EngineServerSocket;
	Brain MainBrain;
	
	public Communicator(int port, Brain brain)
	{
		this.Port = port;
		this.MainBrain = brain;
	}
	
	
	public void Initialize()
	{
		try {
			
			EngineServerSocket = new ServerSocket(this.Port);
			System.out.println("Waiting for a EngineClients...");

			while(true)
			{
				Socket socket = EngineServerSocket.accept(); 
				new CommunicationThread(socket, this.MainBrain).start();
			}
		    
		        
		} catch (Exception e) {
			System.err.println("Error occured at Central Communicator of Engine Server: "+e.getMessage());
		}
     
	}
	
}
