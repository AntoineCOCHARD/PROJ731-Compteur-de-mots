import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reduce {
	private ArrayList<String> maps = new ArrayList<String>();
	private Map<String,Integer> map = new HashMap<String,Integer>();
	
	public Reduce() {
		// TODO Auto-generated constructor stub
	}
	
	public void addMap(String map) {
		maps.add(map);
	}
	
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
	
	public int getSumTotal() {
		int sum = 0;
		for (String key: map.keySet())
	        sum += map.get(key);
	    return sum;
    }
	
}
