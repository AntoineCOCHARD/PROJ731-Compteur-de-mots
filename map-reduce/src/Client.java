import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Classe instanciée pour chaque map
public class Client {
    private int port;

    private Socket socket;
    
    private BufferedReader in;
    private PrintWriter out;
    
    public Client(int port) {
        this.port = port;
    }
    
    public void openStream() throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getLocalHost(),port);
    }
    public void closeStream() throws UnknownHostException, IOException {
        socket.close();
    }
    public String getInputStream() throws IOException {
        in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
        String str = in.readLine();
        return str;
    }

    public void sendOutputStream(String text) throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.println(text);
        out.flush();
    }
    public void sendOutputStream(List<String> map) throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.println(map);
        out.flush();
    }
    
    
}