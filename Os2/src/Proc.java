import java.util.*;;

public class Proc {
	private String name;
	private ArrayList<Flow> flows = new ArrayList<Flow>();
	
	public Proc(String name) {
		this.name = name;
		Random rnd = new Random();
		createFlows(rnd.nextInt(4));
	}

	private void createFlows(int countFlows) {
		Random rnd = new Random();
		for (int i = 0; i < countFlows; i++) {
			flows.add(new Flow(name + " " + String.valueOf(i)));
		}		
	}

	public String getName() {
		return name;
	}

	public ArrayList<Flow> getFlows() {
		return flows;
	}
}
