import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
    	long debut = System.currentTimeMillis();
        ArrayList<Client> clients= new ArrayList<Client>();
        FileClass f = new FileClass("big.txt");
        Reduce reduce = new Reduce();
        //System.out.println(f);
        String text = f.readFile();
        String[] stringword = text.split("\\P{L}+");
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(stringword));
        words.remove(" ");
        //System.out.println(words);
        
        System.out.println(words.size());
        long nbMap = 0;
        
        if(words.size() <= 5000) {
            nbMap = Math.round(Math.log10(words.size()));
        }
        else {
            nbMap = Math.round(Math.log(words.size()));
        }
        System.out.println(nbMap);
		
        
        
        for(int i = 0; i<nbMap; i++) {
            Client cli = new Client(1600);
            int size = words.size()/Math.round(nbMap);
            System.out.println(i);
            
            cli.openStream();
            if(i+1 == nbMap) {
            	System.out.println(words.size()-i*size);
                cli.sendOutputStream(words.subList(i*size, words.size()));
            }
            else {
                System.out.println(size);
                cli.sendOutputStream(words.subList(i*size, (i+1)*size));
            }
            
            clients.add(cli);
        }
        
        for(Client c:clients) {
        	String data = c.getInputStream();
        	System.out.println(data);
        	reduce.addMap(data);
            
        }
        System.out.println(reduce.generateMap());
        System.out.println(reduce.getSumTotal());

        System.out.println(words.size());
        System.out.println(System.currentTimeMillis()-debut);
        
        
        FileClass fw = new FileClass("res.txt");

    }
}