package Alt.Task1;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SceneHandler implements HttpHandler {
	
	String name;
	
	SceneHandler(String _name)
	{
		name = _name; 
	
	}
	
    public void handle(HttpExchange t) throws IOException {
    	
    	 File xmlFile = new File(name);
         
         
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder;
		
			try {
				
				byte[] encoded = Files.readAllBytes(Paths.get(name));
		    	
		        String response = new String(encoded, Charset.defaultCharset());
		        t.sendResponseHeaders(200, response.length());
		        OutputStream os = t.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
}