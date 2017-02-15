package phase_2;

import java.io.*;
import java.util.*;

public class SearchTree {
	private static List<Node> items;
	// private static Queue<Node> currentLeafNodes;
	public static Node root;

	public SearchTree(){
		this.root = null;
	}

	public void insert(List<Node> node){

		Node newNode = new Node(node.name, node.cost, node.value);
		
		if(root == null){
			root = new Node("root", 0, 0);
			return;
		}

		items.add()

		// while(true){
		// 	parent = current;
		// 		current = current.left;
		// 		if(current == null){
		// 			parent.left = newNode;
		// 			return;
		// 		}

		// 		current = current.right;
		// 		if(current == null){
		// 			parent.right = newNode;
		// 			return;
		// 		}
		// 	}
		// }
	}

	public void display(Node root){

		if(root!=null){
			display(root.left);
			System.out.println("Name: " + root.name + " Cost: " + root.cost + " Value: " + root.value);
			display(root.right);
		}

	}

	public static void main(String arg[]){
		SearchTree node = new SearchTree();
		// node.insert("A", 5, 5);
		// node.insert("B", 10, 5);
		// node.display(node.root);
		// System.out.println("");
	}


	public static void csvParser(String file)
	{
		String csvFile = new File(file).getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        List<Node> list = new ArrayList<Node>();

        try {

            br = new BufferedReader(new FileReader(csvFile));

            wLimit = Integer.parseInt(br.readLine());
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String hold = "";
                String[] data = line.split(",");
                
                Node temp = new Node();
                temp.name = data[0];
                temp.cost = Integer.parseInt(data[1]);
                temp.value = Integer.parseInt(data[2]);

                list.add(temp);
                // items = list;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}