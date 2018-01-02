package xyz.ncvt.ifs.entity;

public class Control {
	private int id;
	private String name;
	private String key;
	private boolean value;

	public Control(int id, String name, String key, boolean value) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
