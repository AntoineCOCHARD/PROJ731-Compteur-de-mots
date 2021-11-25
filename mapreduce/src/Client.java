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

/**
 * <b>Class Client</b>
 * <p>
 * Instantiated class for each map
 * </p>
 */
//
public class Client {
    private int port;

    private Socket socket;
    
    private BufferedReader in;
    private PrintWriter out;
    
    /**
	 * @param port (<i>type: int</i>): the client's port
	 * 
	 * <p>
	 * Constructor
	 * </p>
	 */
    public Client(int port) {
        this.port = port;
    }
    
    /** 
	 * <p>
	 * Socket creation
	 * </p>
	 */
    public void openStream() throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getLocalHost(),port);
    }
    
    /** 
	 * <p>
	 * Socket removal
	 * </p>
	 */
    public void closeStream() throws UnknownHostException, IOException {
        socket.close();
    }
    
    /** 
	 * <p>
	 * Get text input from the socket 
	 * </p>
	 */
    public String getInputStream() throws IOException {
        in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
        String str = in.readLine();
        return str;
    }
    
    /** 
     * @param text (<i>type: String</i>): the text to send
     * 
	 * <p>
	 * Send text output with the socket 
	 * </p>
	 */
    public void sendOutputStream(String text) throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.println(text);
        out.flush();
    }
    
    /** 
     * @param map (<i>type: List String</i>): A list of map to send
     * 
	 * <p>
	 * Get map output with the socket 
	 * </p>
	 */
    public void sendOutputStream(List<String> map) throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.println(map);
        out.flush();
    }
    
    
}