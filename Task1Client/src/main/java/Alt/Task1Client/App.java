package Alt.Task1Client;

import Alt.Task1.ThirdParty.ConnectionPort;


public class App 
{
    public static void main( String[] args )
    {
    	int port = ConnectionPort.LoadPort();
    	Client client = new Client(port);
    	client.Initialize();
    }
}
