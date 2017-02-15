package phase_2;

public class Node{
	public String name;
	public int cost;
	public int value;
	public int total;
	
	public Node(String name, int cost, int value, int total){
		this.name = name;
		this.cost = cost;
		this.value = value;
		this.total = total;
		
		left = null;
		right = null;
	}
}