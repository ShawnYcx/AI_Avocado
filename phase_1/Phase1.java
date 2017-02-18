package phase_1;

import java.io.*;
import java.util.*;

public class Phase1 {
	
	private static List<Node> items;
	private static int[] itemValues = new int[4];
	private static int wLimit;
	private static int wCount = 0;

	public static void main(String[] args) throws Exception
	{
		// Uncomment this line for windows
		// csvParser("phase_1\\k05.csv");

		// This lins for MacOSX
		csvParser("Phase_1/k24.csv");

		highestValueFirst();
		lowestCostFirst();
		highestRatioFirst();
		partialKnapsack();
	}

/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void highestValueFirst(){
		
		Collections.sort(items, (Node ob1, Node ob2) -> ob2.value - ob1.value);

		List<String> ns = new ArrayList<String>();
		int i = 0;

		while(wLimit >= wCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			wCount = wCount + items.get(i).cost;
			itemValues[0] += items.get(i).value;
			i++;
		}

		wCount = 0;
		
		// These prints are only for visual tracking of information
		System.out.println(ns);
		System.out.println("highestValueFirst: " + itemValues[0]);
	}


/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	public static void lowestCostFirst(){


		Collections.sort(items, (Node ob1, Node ob2) -> ob1.cost - ob2.cost);

		List<String> ns = new ArrayList<String>();
		int i = 0;

		while(wLimit >= wCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			wCount = wCount + items.get(i).cost;
			itemValues[1] += items.get(i).value;
			i++;
		}
		wCount = 0;

		// These prints are only for visual tracking of information
		System.out.println(ns);
		System.out.println("lowestCostFirst: " + itemValues[1]);
		// System.out.println(items.size());
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


		List<String> ns = new ArrayList<String>();
		int i = 0;

		while(wLimit >= wCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			wCount = wCount + items.get(i).cost;
			itemValues[2] += items.get(i).value;
			i++;
		}
		wCount = 0;

		System.out.println(ns);
		System.out.println("highestRatioFirst: " + itemValues[2]);
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



		List<String> ns = new ArrayList<String>();
		int i = 0;
		while(wLimit >= wCount + items.get(i).cost)
		{
			ns.add(items.get(i).name);
			wCount = wCount + items.get(i).cost;
			itemValues[3] += items.get(i).value;
			i++;
		}
		ns.add(items.get(i).name);

		int remainingWeight = wLimit - wCount;
		double remainingValue = (items.get(i).ratio) * remainingWeight;

		itemValues[3] += remainingValue;
		wCount = 0;
		
		// These prints are only for visual tracking of information
		System.out.println(ns);
		System.out.print("PartialKnapsack: " + (itemValues[3]-remainingValue) + " + " + remainingValue);
		System.out.println(" = "+ itemValues[3]);
	}

/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
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
                temp.getRatio();

                list.add(temp);
                items = list;

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