package Os2;

public class MyInt {

	int value = 0;
	public String toString() {
		return value + "";
	}
	public void add(int arg) {
		value += arg;		
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
