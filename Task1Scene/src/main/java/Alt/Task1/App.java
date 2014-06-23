package Alt.Task1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(7654), 0);
        server.createContext("/scene", new SceneHandler(args[0]));
        server.setExecutor(null); 
        server.start();
    }

    

}
