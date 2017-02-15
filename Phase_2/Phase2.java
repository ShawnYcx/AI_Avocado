package Phase_2;

import java.io.*;
import java.util.*;

public class Phase2 {

	private static List<String> tree = new ArrayList<String>();
    private static List<Node> items = new ArrayList<Node>();
	private static int treeHeight = 1;
	private static int costLimit;

	public static void main(String[] args) throws Exception{
		make_tree("Phase_2/k05.csv");
		System.out.println(tree);

        getOptimaltotal();
	}

    public static List<Integer> getNodeContents(String content){
        List<Integer> holder = new ArrayList<Integer>();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        int cost = 0, value = 0;
        String temp;

        while (tokenizer.hasMoreTokens()) {
            temp = tokenizer.nextToken();
            for (int i = 0; i < items.size(); i++) {
                if (temp == items.get(i).name){
                    cost += items.get(i).cost;
                    value += items.get(i).value;
                }
            }
        }
        holder.add(cost);
        holder.add(value);
        return holder;
    }

    public static void getOptimaltotal() {
        treeHeight--;
        Node newNode = new Node("", 0, 0, 0);
        List<Integer> holder;
        
        for(int i = (int)Math.pow(2,treeHeight)-1; i <= (int)Math.pow(2,treeHeight+1)-2; i++){
            
            // Error here
            System.out.println(tree.get(i));
            if(tree.get(i) != ""){
            	holder = getNodeContents(tree.get(i));
            
            // Error here
            
                if (holder.get(0) <= costLimit && holder.get(1) > newNode.value){
                newNode.name = tree.get(i);
                newNode.cost = holder.get(0);
                newNode.value = holder.get(1);
            	}
            }
        }

        System.out.print("The Optimal solution is [" + newNode.name + "] with the cost of ");
        System.out.print(newNode.cost + " and the value of " + newNode.value);

    }   

	public static void make_tree(String file) throws Exception {

		tree.add("");

		String csvFile = new File(file).getAbsolutePath();
        BufferedReader br = null;
        String line = "";

        br = new BufferedReader(new FileReader(csvFile));

        costLimit = Integer.parseInt(br.readLine());
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String hold = "";
            String[] data = line.split(",");

            String name = data[0];
            int cost = Integer.parseInt(data[1]);
            int value = Integer.parseInt(data[2]);

            Node newNode = new Node(name, cost, value, cost/value);
            
            items.add(newNode);

            // for (int i = 0;i < items.size(); i++) {
            //     System.out.println(i + ": " + items.get(i).name);
            //     System.out.println(i + ": " + items.get(i).cost);
            //     System.out.println(i + ": " + items.get(i).value);
            // }
            
            for(int i = (int)Math.pow(2,treeHeight)-1; i <= (int)Math.pow(2,treeHeight+1)-2; i++){

            	if(i % 2 == 1){
            		tree.add(tree.get((i-1)/2));
            	}
            	else{
            		tree.add(tree.get((i-1)/2) + "," + data[0]);
            	}
            }

            treeHeight++;
		}
	}
}