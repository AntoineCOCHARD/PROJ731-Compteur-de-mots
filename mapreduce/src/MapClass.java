import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Class MapClass</b>
 * <p>
 * Class used to map all the word in a list
 * </p>
 */
public class MapClass {
    private ArrayList<String> text;
    private Map<String, Integer> map = new HashMap<String, Integer>();
    
    /**
	 * @param text (<i>type: ArrayList String</i>): a list of word
	 * 
	 * <p>
	 * Constructor
	 * </p>
	 */
    public MapClass(ArrayList<String> text) {
        this.text = text;
        int i;
        for (i=0;i<text.size();i++) {
            if (map.containsKey(text.get(i))){
                map.put(text.get(i), map.get(text.get(i))+1);
            }
            else {
                map.put(text.get(i), 1);
            }
        }
    }
    
    /** 
	 * <p>
	 * Getter text
	 * </p>
	 */
	public ArrayList<String> getText() {
		return text;
	}
	
	/** 
	 * @param text (<i>type: ArrayList String</i>): A list of word to set
	 * 
	 * <p>
	 * Setter text
	 * </p>
	 */
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
	
	/** 
	 * <p>
	 * Getter map
	 * </p>
	 */
	public Map<String, Integer> getMap() {
		return map;
	}
	
	/** 
	 * @param map (<i>type: Map String, Integer</i>): the map to set
	 * 
	 * <p>
	 * Setter map
	 * </p>
	 */
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
}