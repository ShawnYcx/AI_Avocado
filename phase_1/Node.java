package phase_1;


public class Node {

	public String name;
	public int cost;
	public int value;
	public double ratio;

	public void getRatio() {

		ratio = (double)value/cost;
	}
}