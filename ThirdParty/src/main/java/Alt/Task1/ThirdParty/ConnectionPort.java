package Alt.Task1.ThirdParty;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ConnectionPort {

	private ConnectionPort() {
        throw new AssertionError();
    }
	
	public static int LoadPort()
	{
		int port;
        
        File filePort = new File("port.txt");
        if(!filePort.exists())
        {
        	port = 4567; //hardcode
        }
        else
        {	
        	try {
				FileInputStream fis = new FileInputStream(filePort);
				DataInputStream din = new DataInputStream(fis);
				BufferedReader br = new BufferedReader(new InputStreamReader(din));
				port = Integer.parseInt(br.readLine());
				din.close();
				fis.close();
			} catch (Exception e) {
				System.err.println("Connection port wasn't be loaded(( Server not started!");
				return 4567; //hardcode;
			}
        }
        System.out.println("Connection port = "+ port);
        return port;
	}
	
}
