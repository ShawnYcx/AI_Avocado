package phase_4;

import java.io.*;
import java.util.*;

public class Phase4 {

	private static List<String> tree = new ArrayList<String>();
    private static List<Node> items = new ArrayList<Node>();
    private static int[] itemValues = new int[4];
	private static int treeHeight = 1;
	private static int costLimit;
	private static int cCount = 0;
	private static List<String> ns;
	private static List<String> minBound;
	private static List<String> maxBound;
	private static String fileName = "k24.csv";
	private static long begin;
	private static long end;

	public static void main(String[] args) throws Exception{
		
        make_tree1();

        output_fileName();

        output_capacity();

        output_bounds();

        begin = System.currentTimeMillis();
        getOptimaltotal();
        end = System.currentTimeMillis();

        output_totalTime("dumb",end - begin);

        reinitialize_tree();

        make_tree2();

        begin = System.currentTimeMillis();
        getOptimaltotal();
        end = System.currentTimeMillis();

        output_totalTime("smart", end - begin);



	}

    public static List<Integer> getNodeContents(String content){
        List<Integer> holder = new ArrayList<Integer>();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        int cost = 0, value = 0;
        String temp;

        while (tokenizer.hasMoreTokens()) {
            temp = tokenizer.nextToken();
            for (int i = 0; i < items.size(); i++) {
                if (temp.equals(items.get(i).name)){
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
            
            // System.out.println(tree.get(i));
            if(tree.get(i) != ""){
            	holder = getNodeContents(tree.get(i));
            
            
                if (holder.get(0) <= costLimit && holder.get(1) > newNode.value){
                newNode.name = tree.get(i);
                newNode.cost = holder.get(0);
                newNode.value = holder.get(1);
            	}
            }
        }

    }   


    public static void make_tree1() throws Exception {

		tree.add("");

		String csvFile = new File("resources/"+fileName).getAbsolutePath();
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

            Node newNode = new Node(name, cost, value, value/cost);
            
            items.add(newNode);


            
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


	public static void make_tree2() throws Exception {

		tree.add("");
 
		String csvFile = new File("resources/"+fileName).getAbsolutePath();
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

            for(int i = (int)Math.pow(2,treeHeight)-1; i <= (int)Math.pow(2,treeHeight+1)-2; i++){

                int parentNode = getNodeContents(tree.get((i-1)/2)).get(0);
            	if(i % 2 == 1){
            		if(getNodeContents(tree.get((i-1)/2)).get(0) <= costLimit){
                        tree.add(tree.get((i-1)/2));
                    }
                    else{
                        tree.add("");
                    }
            	}
            	else{
            		if(getNodeContents(tree.get((i-1)/2) + "," + data[0]).get(0) <= costLimit){
                        tree.add(tree.get((i-1)/2) + "," + data[0]);
                    }
                    else{
                        tree.add("");
                    }
            	}
            }

            treeHeight++;
		}
	}


/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void highestValueFirst(){
		
		Collections.sort(items, (Node ob1, Node ob2) -> ob2.value - ob1.value);

		ns = new ArrayList<String>();
		int i = 0;

		while(costLimit >= cCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			cCount = cCount + items.get(i).cost;
			itemValues[0] += items.get(i).value;
			i++;
		}
		sort(ns);

		cCount = 0;
	}


/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void lowestCostFirst(){


		Collections.sort(items, (Node ob1, Node ob2) -> ob1.cost - ob2.cost);

		ns = new ArrayList<String>();
		int i = 0;

		while(costLimit >= cCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			cCount = cCount + items.get(i).cost;
			itemValues[1] += items.get(i).value;
			i++;
		}
		cCount = 0;
		sort(ns);     

	}



/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void highestRatioFirst(){

		Node highest;
		for(int i = 0; i < items.size(); i++)
		{
			highest = items.get(i);
			for(int j = i; j < items.size(); j++)
			{
				if(highest.ratio < items.get(j).ratio)
				{
					items.set(i, items.get(j));
					items.set(j, highest);
					highest = items.get(i);
				}
			}
		}


		ns = new ArrayList<String>();
		int i = 0;

		while(costLimit >= cCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			cCount = cCount + items.get(i).cost;
			itemValues[2] += items.get(i).value;
			i++;
		}
		cCount = 0;
		sort(ns);

	}

/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void partialKnapsack(){

		Node highest;
		for(int i = 0; i < items.size(); i++)
		{
			highest = items.get(i);
			for(int j = i; j < items.size(); j++)
			{
				if(highest.ratio < items.get(j).ratio)
				{
					items.set(i, items.get(j));
					items.set(j, highest);
					highest = items.get(i);
				}
			}
		}



		ns = new ArrayList<String>();
		int i = 0;
		while(costLimit >= cCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			cCount = cCount + items.get(i).cost;
			itemValues[3] +=items.get(i).value;
			i++;
		}
		ns.add(items.get(i).name);

		int remainingWeight = costLimit - cCount;
		double remainingValue = (items.get(i).ratio) * remainingWeight;

		itemValues[3] += remainingValue;
		cCount = 0;
		sort(ns);
	}

	public static void calculate_bound() {

		int min;

		highestValueFirst();
		minBound = ns;
		min = itemValues[0];


		lowestCostFirst();
		if(itemValues[1] < min){
			min = itemValues[1];
			minBound = ns;
		}

		highestRatioFirst();
		if(itemValues[2] < min){
			minBound = ns;
		}
		System.out.println(ns);

		partialKnapsack();
		maxBound = ns;
	}

	public static void sort(List<String> ls){

		String temp;
		for(int i = 0; i < ls.size(); i++)
		{
			temp = ls.get(i);
			for(int j = i; j < ls.size(); j++)
			{
				if((int)temp.charAt(0) > (int)ls.get(j).charAt(0))
				{
					ls.set(i, ls.get(j));
					ls.set(j, temp);
					temp = ls.get(i);
				}
			}
		}
	}


	public static void reinitialize_tree() {

		tree = new ArrayList<String>();
		items = new ArrayList<Node>();
		treeHeight = 1;
	}

	public static void output_fileName() {

		System.out.println("The filename is: \n" + fileName + "\n");
	}

	public static void output_capacity() {

		System.out.println("The capacity for the given problem is: \n" + costLimit + "\n");
	}

	public static void output_bounds() {

		calculate_bound();

		System.out.println("The best greedy min boundary is: \n" + minBound + "\n");

		System.out.println("The best greedy max boundary is: \n" + maxBound + "\n");
	}

	public static void output_totalTime(String type,long time) {

		System.out.println("\nTotal time for \""+type+"\" search: " + time*.001);
	}
}