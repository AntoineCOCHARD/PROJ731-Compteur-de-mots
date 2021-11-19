import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileClass {
	private String path;
	private File file;
	
	public FileClass(String path) {
		this.path = path;
		this.file = new File(path);
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void FileWriter(String text) {
        try {
              file.createNewFile();
              FileWriter myWriter = new FileWriter(file);
              myWriter.write(text);
              myWriter.close();
              System.out.println("Le texte a bien été écrit");
              
              Desktop d = Desktop.getDesktop();
              d.open(file);
              
            } catch (IOException e) {
              System.out.println("Une erreur c'est produit");
              e.printStackTrace();
            }
    }
	
	
	
	public String readFile() {
		String text = "";
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				text += myReader.nextLine();
			}
			myReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Une erreur c'est produit");
			e.printStackTrace();
		}
		return text;
	}
	
	public ArrayList<String> splitWord(String text){
		String[] arrayStringWords = text.split(" ");
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(arrayStringWords));
		return words;
	}
	/*
	//Reformater le texte : enlever str vide, mettre en minuscule...
	public String formateText(String str) {
		
		return str;
	}
	 */
	
	@Override
	public String toString() {
		return "path : " + path + "\ntext : " + this.readFile();
	}
}
