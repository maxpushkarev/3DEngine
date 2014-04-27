package Alt.Task1;

import Alt.Task1.ThirdParty.ConnectionPort;


public class App 
{
    public static void main( String[] args )
    {
        int port = ConnectionPort.LoadPort();
        System.out.println("Engine-Server is starting.... Please, wait!");
        
        /* Create brain with initialize */
        Brain brain = new Brain();
        brain.Initialize();
        
        System.out.println("Engine-Server is ready!");
        /* create communicator between server and client */
        Communicator communicator = new Communicator(port, brain);
        communicator.Initialize();
        
      
    }
}
