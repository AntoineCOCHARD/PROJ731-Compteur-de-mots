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
public class ThreadClient extends Thread{
	private int id;
	private int port;
	
	private Socket socket;
	
	private BufferedReader in;
	private PrintWriter out;
	
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
	public void sendOutputStream(Map<String, Integer> map) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
	    out.println(map);
	    out.flush();
	}
	
    
	public ThreadClient(Socket socket, int id) {
			this.id = id;
			this.socket = socket;
	}
	
	
	public void run(){
		try {
			
			String str = getInputStream();
			System.out.println("Message du client : " + str);
			System.out.println("test");
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
			System.out.println(mapper.getMap().size());
			
			
			sendOutputStream(mapper.getMap());
			
		    socket.close();
		    
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		
	}

}