package phase_2;

import java.io.*;
import java.util.*;

public class SearchTree {
	
	public static Node root;
	public SearchTree(){
		this.root = null;
	}

	public void insert(String name, int cost, int value){

		Node newNode = new Node(name, cost, value);
		if(root==null){
			root = newNode;
			return;
		}

		Node current = root;
		Node parent = null;
		double ratio = value/cost;
		while(true){
			parent = current;
			if(ratio < current.value/current.cost){
				current = current.left;
				if(current == null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current == null){
					parent.right = newNode;
					return;
				}
			}
		}
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
		node.insert("A", 10, 5);
		node.insert("A", 5, 5);
		node.display(node.root);
		System.out.println("");
	}

}