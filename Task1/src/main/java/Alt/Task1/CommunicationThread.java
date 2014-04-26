package Alt.Task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CommunicationThread extends Thread {
	
	 private Socket Socket;
	 private String ClientId;

	    public CommunicationThread(Socket clientSocket) {
	        this.Socket = clientSocket;
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
	    			out.writeUTF(line+"-answer");
	    			out.flush();
	    			
	    		}
	    		
	    		
	    	} catch (Exception e) {
	    		System.err.println("Error occured at Communication Thread (client "+this.ClientId+"): "+e.getMessage());
	    	}
	    	
	    }
	
}
