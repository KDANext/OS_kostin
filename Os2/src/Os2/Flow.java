package Os2;

public class Flow {
	private String Name;
	private MyInt CurrentTime;
	private int NeedTime;
	private boolean Done = false;
	public Flow(String Name, MyInt CurrentTime ,int NeedTime) {
		this.Name = "Flow name: "+Name;
		this.CurrentTime =CurrentTime;
		this.NeedTime =NeedTime;		
	}
	public void DoIt() {
		System.out.println(Name + "Done ");
		CurrentTime.add(NeedTime);
		Done = true;
	}
	public boolean MaybeWork(int Time) {
		//System.out.println(NeedTime + " " + "<=" + Time + " " + CurrentTime );
		if(NeedTime <= Time) {
			return true;
		}
		return false;
	}
	public boolean Status() {	
		return Done;
	}
	public String PrintInfo() {
		return Name + " Need time: " + NeedTime;
	}
	public String EndWithError() {
		Done = true;
		return Name + "end with error";
	}
	public boolean ErrorTime(int time) {
		if(time < NeedTime) {
			Done = true;
			System.out.println(Name + "not enough Core time");
			return true;
		}
		return false;
	}
}
