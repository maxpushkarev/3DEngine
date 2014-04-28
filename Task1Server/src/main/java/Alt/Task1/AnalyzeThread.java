package Alt.Task1;

import java.io.DataOutputStream;

import Geometry.Ray;
import Geometry.SceneObject;

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
		  SceneObject obj;
		  	try {
		  		obj = this.MainBrain.RayTrace(this.ClientRay);
				this.Output = this.ClientRay.getId()+": "+ (obj == null ? "" : obj.Name) ;
				synchronized(this.OutputStream)
				{
					try
					{
						this.OutputStream.writeUTF(this.Output);
					}
					catch(Exception ex)
	    			{
	    				System.out.println("CONNECTION LOST IN ANALYZING THREAD");
	    			}
					finally
					{
						this.OutputStream.flush();
					}
				}
				
			} catch (Exception e) {
				System.err.println("Error occured at Analyzing Thread"+e.getMessage());
			}
	  }
	
}
