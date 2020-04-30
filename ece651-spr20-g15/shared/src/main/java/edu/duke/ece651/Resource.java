package edu.duke.ece651;

public abstract class Resource {
	protected String type;
	protected int num;
	protected Resource() {
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
