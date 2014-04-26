package Alt.Task1;


public class App 
{
    public static void main( String[] args )
    {
        int port=4567; //hardcode socket port
        //create brain for calculating
        Brain brain = new Brain();
        /* create communicator between server and client 
         * (copy reference for brain to communicator) */
        Communicator communicator = new Communicator(port, brain);
        communicator.Initialize();
    }
}
