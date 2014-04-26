package Alt.Task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CommunicationThread extends Thread {
	
	 private Socket Socket;
	 private String ClientId;
	 private Brain MainBrain;

	    public CommunicationThread(Socket clientSocket, Brain brain) {
	        this.Socket = clientSocket;
	        this.MainBrain = brain;
	    }

	    public void run() {
		  
	    	try
	    	{
	    	
	    		InputStream sin = this.Socket.getInputStream();
	    		OutputStream sout = this.Socket.getOutputStream();
	    		DataInputStream in = new DataInputStream(sin);
	    		DataOutputStream out = new DataOutputStream(sout);
	    		
	        
	    		String line = null;
	    		
	    		while((line=in.readUTF()).contains("ClientId:"))
	    		{
	    			this.ClientId=line.replace("ClientId:", ""); //TODO: refactor this shit! Use proto-messages maybe
	    			break;
	    		}
	    		
	    		System.out.println("New EngineClient connected!!! Id: "+this.ClientId);
	    		
	    		while(true) {
	    			try
	    			{
	    				line = in.readUTF(); 
	    			}
	    			catch(Exception ex)
	    			{
	    				System.out.println("Disconnect client "+this.ClientId);
	    				break;
	    			}
	    			System.out.println("Input data from client "+this.ClientId+": " + line);
	    			
	    			
	    			if(!ProtocolValidator.CheckMessage(line))
	    			{
	    				out.writeUTF("Invalid message: "+ line);
	    				out.flush();
	    				continue;
	    			}
	    			
	    			out.writeUTF("GOOD!");
	    			out.flush();
	    			
	    		}
	    		
	    		
	    	} catch (Exception e) {
	    		System.err.println("Error occured at Communication Thread (client "+this.ClientId+"): "+e.getMessage());
	    	}
	    	
	    }
	
}
