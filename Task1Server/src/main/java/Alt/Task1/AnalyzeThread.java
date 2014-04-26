package Alt.Task1;

import Geometry.Ray;

public class AnalyzeThread extends Thread {
	
	  Ray ClientRay;
	  String Output;
	  Brain MainBrain;
	  
	  
	  public AnalyzeThread(Ray ray, Brain brain)
	  {
		  this.ClientRay = ray;
		  this.Output = "";
		  this.MainBrain = brain;
	  }
	
	  @Override
	  public void run(){
		  	try {
				Thread.sleep(5000);
				this.Output = "Hello, Crazy Max!!!";
			} catch (InterruptedException e) {
				System.err.println("Error occured at Analyzing Thread"+e.getMessage());
			}
	  }
	
}
