import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


//Classe qui appelle la map
/**
 * <b>Class ThreadClient</b>
 * <p>
 * Class used to create a map for each client
 * </p>
 */
public class ThreadClient extends Thread{
	private int id;
	private int port;
	
	private Socket socket;
	
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * @param socket (<i>type: Socket</i>): the client's socket
	 * @param port (<i>type: int</i>): the threadClient id
	 * 
	 * <p>
	 * Constructor
	 * </p>
	 */
	public ThreadClient(Socket socket, int id) {
			this.id = id;
			this.socket = socket;
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
     * @param map (<i>type: Map String, Integer</i>): the map to send
     * 
	 * <p>
	 * Send a map output with the socket 
	 * </p>
	 */
	public void sendOutputStream(Map<String, Integer> map) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
	    out.println(map);
	    out.flush();
	}
	
	
	/** 
	 * <p>
	 * Method to map the text
	 * </p>
	 */
	public void run(){
		try {
			
			String str = getInputStream();
			System.out.println("Message du client : " + str);
			str = str.replace("[", " ");
			str = str.replace("]", " ");
			str = str.replace(",", " ");
			str = str.replace(".", "");
			str = str.replace(System.getProperty("line.separator"), "");
			str = str.toLowerCase();
			String[] arrayStringWords = str.split(" ");
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(arrayStringWords));
			
			MapClass mapper = new MapClass(words);
			mapper.getMap().remove("");			
			
			sendOutputStream(mapper.getMap());
			
		    socket.close();
		    
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		
	}

}