package Model;

public class PostClass {
	private String name;
	private int price;
	private int postClassId;
	
	public int getPostClassId() {
		return postClassId;
	}
	public void setPostClassId(int postClassId) {
		this.postClassId = postClassId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String toString(){
		return this.name;
	}
	
}
