package Phase_2;

import java.io.*;
import java.util.*;

public class Phase2 {

	private static List<String> tree = new ArrayList<String>();
	private static int treeHeight = 1;
	private static int costLimit;
	


	public static void main(String[] args) throws Exception{
		make_tree("Phase_2/k05.csv");

		System.out.println(tree);
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

            for(int i = (int)Math.pow(2,treeHeight)-1; i <= (int)Math.pow(2,treeHeight+1)-2; i++){

            	if(i % 2 == 1){
            		tree.add(tree.get((i-1)/2));
            	}
            	else{
            		tree.add(tree.get((i-1)/2) + data[0]);
            	}
            }

            treeHeight++;
		}
	}

}