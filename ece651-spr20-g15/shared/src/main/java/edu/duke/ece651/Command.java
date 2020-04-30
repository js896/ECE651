package edu.duke.ece651;

public enum Command {
    Move(1, "Move"),
    Attack(2, "Attack"),
    Done(3, "Done");
    private int key;
    private String value;

    private Command(int key, String value) {
        this.key = key;
        this.value = value;
    }

    int getKey() {
        return key;
    }

    String getValue() {
        // TODO Auto-generated method stub
        return value;
    }

}
