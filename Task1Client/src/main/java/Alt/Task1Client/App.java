package Alt.Task1Client;


public class App 
{
    public static void main( String[] args )
    {
    	int port=4567; //hardcode socket port
    	Client client = new Client(port);
    	client.Initialize();
    }
}
