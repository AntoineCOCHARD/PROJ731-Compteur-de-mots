import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Class Reduce</b>
 * <p>
 * Class used to reduce all maps in a other one
 * </p>
 */
public class Reduce {
	private ArrayList<String> maps = new ArrayList<String>();
	private Map<String,Integer> map = new HashMap<String,Integer>();
	
	/**
	 * <p>
	 * Constructor
	 * </p>
	 */
	public Reduce() {
	}
	
	/**
	 * @param map (<i>type: String</i>): a map cast into a string
	 * 
	 * <p>
	 * add a map into a list
	 * </p>
	 */
	public void addMap(String map) {
		maps.add(map);
	}
	
	/**
	 * <p>
	 * Generate a map based on the other maps
	 * </p>
	 */
	public Map<String,Integer> generateMap(){
		
		for (String s:maps) {
			s = s.replace("{", "");
			s = s.replace("}", "");
			String[] pairs = s.split(",");
			for (int i=0;i<pairs.length;i++) {
			    String pair = pairs[i];
			    String[] keyValue = pair.split("=");
			    if(map.containsKey(keyValue[0])) {
                    map.put(keyValue[0], map.get(keyValue[0]) + Integer.valueOf(keyValue[1]));
                }else {
                    map.put(keyValue[0], Integer.valueOf(keyValue[1]));
                }
			}
		}
		return map;
	}
	
	/**
	 * <p>
	 * Get the total of word in the map
	 * </p>
	 */
	public int getSumTotal() {
		int sum = 0;
		for (String key: map.keySet())
	        sum += map.get(key);
	    return sum;
    }
	
}
