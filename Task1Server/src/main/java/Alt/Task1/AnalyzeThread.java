package Alt.Task1;

import java.io.DataOutputStream;

import Geometry.Ray;

public class AnalyzeThread extends Thread {
	
	  Ray ClientRay;
	  String Output;
	  Brain MainBrain;
	  DataOutputStream OutputStream;
	  
	  
	  public AnalyzeThread(Ray ray, Brain brain, DataOutputStream out)
	  {
		  this.ClientRay = ray;
		  this.Output = "";
		  this.MainBrain = brain;
		  this.OutputStream = out;
	  }
	
	  @Override
	  public void run(){
		  	try {
				Thread.sleep(5000);
				this.Output = "Hello, Crazy Max!!!";
				
				synchronized(this.OutputStream)
				{
					this.OutputStream.writeUTF(this.Output);
					this.OutputStream.flush();
				}
				
			} catch (Exception e) {
				System.err.println("Error occured at Analyzing Thread"+e.getMessage());
			}
	  }
	
}
