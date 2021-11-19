import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapClass {
    private ArrayList<String> text;
    private Map<String, Integer> map = new HashMap<String, Integer>();

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

	public ArrayList<String> getText() {
		return text;
	}
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
}