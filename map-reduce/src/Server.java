import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Classe executé en dehors (pour faire tourner l'app)
public class Server {
    private int port;

    private ServerSocket serverSocket;
    private Socket socket;

    public Server(int port) {
        this.port = port;
    }

    public void openServerStream() throws UnknownHostException, IOException, InterruptedException {
        serverSocket = new ServerSocket(port);
        System.out.println("Le serveur est à l'écoute du port "+serverSocket.getLocalPort());
        int idclient = 0;


        try {

            while (true){
                 try {
                     socket = serverSocket.accept();
                     idclient++;

                     //on le passe a un nouveau thread et on le demarre
                     ThreadClient cli= new ThreadClient(socket, idclient);
                     cli.run();
                 }catch (IOException e){
                     System.out.println( "Erreur : " + e );
                     break;
                 }
            }

            serverSocket.close();
        }catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        Server server = new Server(1600);
        server.openServerStream();

    }

}