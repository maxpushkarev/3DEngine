package Alt.Task1;

import Alt.Task1.ThirdParty.ConnectionPort;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	
    	if(args.length == 0)
    	{
    		System.err.println("No scene avaible..GoodBye ;-(");
    		return;
    	}
        int port = ConnectionPort.LoadPort();
        
        System.out.println("Engine-Server is starting.... Please, wait!"); 
        
        /* Create brain and initialize it */
        Brain brain = new Brain();
        int init = brain.Initialize(args[0]);
        //int init = brain.Initialize("C:\\Users\\Platon\\workspace\\Task1Server\\target\\example2.xml");

        if(init == 0)
        {
        	System.out.println("3DEngine-Server is ready for raytracing!");
        }
        else
        {
        	System.out.println("3DEngine-Server failed!");
        	return;
        }
        /* create communicator between server and client */
       
        Communicator communicator = new Communicator(port, brain);
        communicator.Initialize(); 
        
      
    }
}
