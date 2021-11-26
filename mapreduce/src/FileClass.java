import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <b>Class FileClass</b>
 * <p>
 * Global file management
 * </p>
 */
public class FileClass {
	private String path;
	private File file;
	
	/**
	 * @param path (<i>type: String</i>): the path to the file to which we want to perform various actions
	 * 
	 * <p>
	 * Constructor
	 * </p>
	 */
	public FileClass(String path) {
		this.path = path;
		this.file = new File(path);
	}
	
	/**
	 * @return path
	 * 
	 * <i>Path getter</i>
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @param path
	 * 
	 * <p>Path setter</p>
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @param text (<i>type: String</i>): the text content to write into the current file
	 * 
	 * <p>This method write a text passed in parameter into the current file</p>
	 */
	public void FileWriter(String text) {
        try {
              file.createNewFile();
              FileWriter myWriter = new FileWriter(file);
              myWriter.write(text);
              myWriter.close();
              
              Desktop d = Desktop.getDesktop();
              d.open(file);
              
            } catch (IOException e) {
              System.out.println("Une erreur c'est produit");
              e.printStackTrace();
            }
    }
	

	/**
	 * @param text (<i>type: String</i>): the text content we want to split
	 * 
	 * <p>This method split words in a text</p>
	 */
	public ArrayList<String> splitWord(String text){
		String[] arrayStringWords = text.split(" ");
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(arrayStringWords));
		return words;
	}
	
	@Override
	public String toString() {
		return "path : " + path;
	}
}
