package Alt.Task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import Geometry.Ray;

public class CommunicationThread extends Thread {
	
	 Socket Socket;
	 String ClientId;
	 Ray ClientRay;
	 Brain MainBrain;

	    public CommunicationThread(Socket clientSocket, Brain brain) {
	        this.Socket = clientSocket;
	        this.MainBrain = brain;
	    }

	    @Override
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
	    			
	    			this.ClientRay = new Ray(line);
	    			AnalyzeThread analyzeThread = new AnalyzeThread(this.ClientRay, this.MainBrain);
	    			
	    			analyzeThread.start();
	    			analyzeThread.join();
	    			
	    			
	    			out.writeUTF(analyzeThread.Output);
	    			out.flush();
	    			
	    		}
	    		
	    		
	    	} catch (Exception e) {
	    		System.err.println("Error occured at Communication Thread (client "+this.ClientId+"): "+e.getMessage());
	    	}
	    	
	    }
	
}
