package Alt.Task1Client;

import java.io.DataInputStream;

public class ServerListener extends Thread {

	DataInputStream InputStream;
	
	public ServerListener(DataInputStream in)
	{
		this.InputStream = in;
	}
	
	 @Override
	  public void run(){
		 
		 String line=null;
		 
		 try {
			 
			 while(true)
			 {
				 line = this.InputStream.readUTF();
				 System.out.println("================");
				 System.out.println("RESULT: " + line);
				 System.out.println("================");
			 }
			 
		} catch (Exception e) {
			System.err.println("Error occured in ServerListener: "+e.getMessage());
		} 
	 }
	 
}
