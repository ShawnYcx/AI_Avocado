package phase_2;

public class Node{
	public String name;
	public int cost;
	public int value;
	Node left;
	Node right;

	public Node(String name, int cost, int value){
		this.name = name;
		this.cost = cost;
		this.value = value;
		left = null;
		right = null;
	}
}