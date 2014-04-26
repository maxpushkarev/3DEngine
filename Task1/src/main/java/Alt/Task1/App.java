package Alt.Task1;


public class App 
{
    public static void main( String[] args )
    {
        int port=4567; //hardcode socket port
        //create communicator between server and client
        Communicator communicator = new Communicator(port);
        communicator.Initialize();
    }
}
