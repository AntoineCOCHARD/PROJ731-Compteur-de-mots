import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
    	// ************************************* Initialisation ************************************* //
    	ArrayList<Client> clients= new ArrayList<Client>();
    	Reduce reduce = new Reduce();
    	
    	FileClass fres = new FileClass("res.txt");
    	FileClass flog = new FileClass("info.txt");
    	
    	long nbMap = 0;
    	long time;
    	long debut = System.currentTimeMillis();
    	
    	String log = "";
    	// R�cup�ration du texte � traiter
    	time = System.currentTimeMillis()-debut;
        log += "D�but lecture fichier. Temps : " + time + "\n";
        String text = new String(Files.readAllBytes(Paths.get("big.txt")),
                StandardCharsets.UTF_8);
        time = System.currentTimeMillis()-debut;
        log += "Fin lecture fichier. Temps : " + time + "\n";
        //S�paration des mots
        String[] stringword = text.split("\\P{L}+");
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(stringword));
        words.remove(" ");
        
    	// ************************************* Mapping ************************************* //

        // D�t�ction du nombre de map � effectuer en fonction du nombre de mot
        if(words.size() <= 5000) {
            nbMap = Math.round(Math.log10(words.size()));
        }
        else {
            nbMap = Math.round(Math.log(words.size()));
        }

        
        // Cr�ation des maps
        time = System.currentTimeMillis()-debut;
        log += "D�but cr�ation threads map. Temps : " + time + "\n";
        
        for(int i = 0; i<nbMap; i++) {
            Client cli = new Client(1600);
            int size = words.size()/Math.round(nbMap);
            System.out.println("Map " + i);
            
            cli.openStream();
            if(i+1 == nbMap) {
                cli.sendOutputStream(words.subList(i*size, words.size()));
            }
            else {
                cli.sendOutputStream(words.subList(i*size, (i+1)*size));
            }
            
            clients.add(cli);
        }
        time = System.currentTimeMillis()-debut;
        log += "Threads map cr��s. Temps : " + time + "\n";

        
        //R�cup�ration des r�sultats des maps
        for(Client c:clients) {
        	String data = c.getInputStream();
        	reduce.addMap(data);
            
        }
        time = System.currentTimeMillis()-debut;
        log += "Fin r�cup�ration des maps. Temps : " + time + "\n";
        
        
    	// ************************************* Reduce ************************************* //
        
        // Generation de la somme des maps
        log += "D�but reduce. Temps : " + time + "\n";
        Map<String, Integer> mapreduce = reduce.generateMap();
        int nbWord = reduce.getSumTotal();
        
        time = System.currentTimeMillis()-debut;
        log += "Fin reduce. Temps : " + time + "\n";
        
        
        // Ecriture des diff�rentes information 
        log += "Nombre de map : " + nbMap + "\n";
        log += "MapReduce g�n�r� : " + mapreduce + "\n";
        log += "Nombre de mot initial : " + words.size() + "\n";
        log += "Nombre de mot dans le MapReduce : " + nbWord + "\n";

        
        // Enregistrement du r�sultat dans un fichier
        fres.FileWriter(mapreduce.toString());
        flog.FileWriter(log);

    }
}