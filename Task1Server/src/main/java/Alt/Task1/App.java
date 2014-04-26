package Alt.Task1;


public class App 
{
    public static void main( String[] args )
    {
        int port=4567; //hardcode socket port
        System.out.println("Engine-Server is started.... Please, wait!");
        
        /* Create brain with initialize */
        Brain brain = new Brain();
        brain.Initialize();
        /* create communicator between server and client */
        Communicator communicator = new Communicator(port, brain);
        communicator.Initialize();
        
        System.out.println("Engine-Server is ready!");
    }
}
