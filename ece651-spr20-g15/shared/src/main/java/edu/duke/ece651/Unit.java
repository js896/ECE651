package edu.duke.ece651;

public class Unit {
    String owner;
    String type;
	int num;
	public Unit(int num) {
		this.num=num;
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

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
