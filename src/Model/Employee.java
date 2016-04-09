package Model;

public class Employee {
	private String name;
	private String surname;
	private String branch;
	private int branchId;
	private int id;
	private int positionId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String toString(){
		return this.name + " " + this.surname;
	}
	
	public String emptyString(){
		return "";
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	
	/*
	public int getBranchIdOfEmployee(Employee employee){
		Employee e = new Employee();
		
		return e.getBranchId();
	}*/
	
}
