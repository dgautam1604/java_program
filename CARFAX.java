import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;


public class CARFAX implements Comparable<CARFAX> {
	public static int keyLength;
	public static int threshold;
	static int n = 0;
	public static HashSet<String> vinNumber = new HashSet<String>();
	//public HashSet<String> vinNumberSorted = new HashSet<String>();
	//public Collection<String> vinNumberList;
	public static HashMap<String, Stack<String>> Records=new HashMap<String, Stack<String>>();
	static Comparator<CARFAX> nameSorter = Comparator.comparing(CARFAX::getName);
	public  static PriorityQueue<CARFAX> priorityQueue = new PriorityQueue<>(nameSorter);
	public String vin;
	 class Node { 
			String key; 
			Node left, right; 

			public Node(String item) { 
				key = item; 
				left = right = null; 
			} 
		} 

		Node root; 
		CARFAX() { 
			root = null; 
		} 
		public CARFAX(String vin) {
	        super();
	       
	        this.vin = vin;
	       
	    }

		void insert(String key) { 
		root = insertRecursion(root, key); 
		//calls a recursive fuction
		} 
		
		Node insertRecursion(Node root, String key) { 
			
			if (root == null) { 
				root = new Node(key); 
				return root; 
			} 

			if (key.compareTo(root.key)<0) 
				root.left = insertRecursion(root.left, key); 
			else if (key.compareTo(root.key)>0) 
				root.right = insertRecursion(root.right, key); 

			return root; 
		} 

		
	    void deleteKey(String key) 
	    { 
	        root = deleteRecursion(root, key); 
	    } 
	  
	    Node deleteRecursion(Node root, String key) 
	    { 
	        /*If the tree is empty */
	        if (root == null)  return root; 
	  
	        
	        if (key.compareTo(root.key)<0) 
	            root.left = deleteRecursion(root.left, key); 
	        else if (key.compareTo(root.key)>0) 
	            root.right = deleteRecursion(root.right, key); 
	  
	        else
	        { 
	            if (root.left == null) 
	                return root.right; 
	            else if (root.right == null) 
	                return root.left; 
	  
	            root.key = minimumValue(root.right); 
	  
	            root.right = deleteRecursion(root.right, root.key); 
	        } 
	  
	        return root; 
	    } 
	    String minimumValue(Node root) 
	    { 
	        String minv = root.key; 
	        while (root.left != null) 
	        { 
	            minv = root.left.key; 
	            root = root.left; 
	        } 
	        return minv; 
	    } 
	    
		void inorder() { 
		inorderRecursion(root); 
		} 

		void inorderRecursion(Node root) { 
			if (root != null) { 
				inorderRecursion(root.left); 
				System.out.println(root.key); 
				inorderRecursion(root.right); 
			} 
		} 
		static int count=0;
		void nextElement(String key1) { 
			nextElementRec(root,key1); 
			} 
		
		void nextElementRec(Node root,String key1) { 
			
			if (root != null) { 
				nextElementRec(root.left,key1); 
				if(count==1){
					System.out.println(root.key); 
					count=2;
				}
				if(root.key.equalsIgnoreCase(key1))
					count++;
				
				nextElementRec(root.right,key1); 
			} 
		} 
		void prevElement(String key1) { 
			prevElementRec(root,key1); 
			} 
		public static Stack<String> s = new Stack<String> ();  
		void prevElementRec(Node root,String key1) { 
			
			if (root != null) { 
				prevElementRec(root.left,key1); 
				if(key1.equalsIgnoreCase(root.key)){
					if(!s.isEmpty())
						System.out.println(s.pop());
					else
						System.out.println("This is the first element");
				}
				s.push(root.key);	
					
				prevElementRec(root.right,key1); 
			} 
		} 
		void prevElement(PriorityQueue<CARFAX> priorityQueue1,String key1) { 
			while(true) 
			{
				CARFAX e = priorityQueue1.poll();
				 if(e == null) break;
				/* if(count==1)
					 System.out.println(e.getName());*/
				 if(!e.getName().equalsIgnoreCase(key1))
				 {	CARFAX e1 = priorityQueue1.peek();
				 	if(e1 == null) break;
					 if(e1.getName().equalsIgnoreCase(key1))
						 System.out.println(e.getName());
				 }
			}
		} 
	 void nextElement(PriorityQueue<CARFAX> priorityQueue1,String key1) { 
			while(true) 
			{
				CARFAX e = priorityQueue1.poll();
				 if(e == null) break;
				/* if(count==1)
					 System.out.println(e.getName());*/
				 if(e.getName().equalsIgnoreCase(key1))
				 {	CARFAX e1 = priorityQueue1.peek();
				 	if(e1 == null) break;
					
						 System.out.println(e1.getName());
				 }
			}
		} 
	 public void setThreshold(int threshold)  {
			//if (threshold < 100 || threshold > 900000){
		 if (threshold < 10 || threshold > 100){
				System.out.println("Threshold value not correct");
				System.exit(0);}
			else
				this.threshold = threshold;
		}

		public void setKeyLength(int keyLength)  {
			if (keyLength < 10 || keyLength > 17){
				System.out.println("key should be between 10 to 17 characters");
				System.exit(0);}
			else
				this.keyLength = keyLength;
		}

	public void generate(int k) {

		// chose a random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		/* + "abcdefghijklmnopqrstuvxyz"; */

		if (keyLength == k) {
			StringBuilder sb = new StringBuilder(k);

			for (int i = 0; i < k; i++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
				int index = (int) (AlphaNumericString.length() * Math.random());

				// add Character one by one in end of sb
				sb.append(AlphaNumericString.charAt(index));
			}
			String newVin = sb.toString();
			// System.out.println(newVin);
			// vinNumber.add(newVin);
			if (vinNumber.size() == 0)
				vinNumber.add(newVin);
			else if (!vinNumber.contains(newVin))
				vinNumber.add(newVin);

		} else
			System.out.println("KeyLength not equal to the length mentioned");

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CARFAX c = new CARFAX();
		System.out.println("Enter key Length, threshold ");
		Scanner sc = new Scanner(System.in);
		// key and threshold values
		int k = sc.nextInt();
		int t = sc.nextInt();
		c.setKeyLength(k);
		c.setThreshold(t);
		while(true){
			System.out.println("What would you like to do today?");
			System.out.println("1.generate"+"\n"+
					"2.Display allKeys"+"\n"+
					"3.add(key and value)"+"\n"+
					"4.remove a key"+"\n"+
					"5.getValues for a key"+"\n"+
					"6.nextKey"+"\n"+
					"7.prevKey"+"\n"+
					"8.prevAccids"+"\n"+
					"9.Exit");
			Scanner br=new Scanner(System.in);
			int m=br.nextInt();
			
			
			
			if(m==1){
				System.out.println("Enter the number of elements to be generated");
				Scanner scin=new Scanner(System.in);
				n=scin.nextInt();
				while (c.vinNumber.size() != n)
					c.generate(k);
				//System.out.println(c.vinNumber);
				System.out.println("Generated");
				String[] alphaNumericStringArray = new String[2*n];
				Object[] arr = c.vinNumber.toArray();
				
			//	System.out.println("The array is:");
				for (int j = 0; j < arr.length; j++)
					alphaNumericStringArray[j] = arr[j].toString();
				
				if(n>t){
					System.out.println("Since number of elements are greater than threshold. Data Structure chosen is a Binary Tree");
					for (int i = 0; i < n; i++) {
						if(!alphaNumericStringArray[i].isEmpty())
							c.insert(alphaNumericStringArray[i]);
					}
				} else{
					System.out.println("Since number of elements are less than threshold. Data Structure chosen is a Priority Queue");
					for (int i = 0; i < n; i++) {
						
						if(!alphaNumericStringArray[i].isEmpty())
						c.priorityQueue.add(new CARFAX(alphaNumericStringArray[i]));
					}
				}
			} 
			else if(m==2){
				System.out.println("Display all keys");
				if(n>t){
					System.out.println("in Binary tree");
					c.inorder(); 
				}else{
					System.out.println("in Priority Queue");
					PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>(priorityQueue);
					while(true) 
					{
						CARFAX e = priorityQueue1.poll();
						 if(e == null) break;
					    System.out.println(e.getName()); 
					}
				}
					
				
			} else if(m==3){
				System.out.println("Add a key of the set Keylength and a value of the order 2020-04-19");
				Scanner scin=new Scanner(System.in);
				 String key=scin.next();
				 String Value=scin.next();
				 if(n>t){
					 c.insert(key);
					
					 if (Records.get(key) != null) {
						 Stack<String> st=new Stack<String>();
							st=Records.get(key);
							st.push(Value);
							Records.replace(key, st);
							
						} else {
							 Stack<String> st=new Stack<String>();
							 st.push(Value);
							Records.put(key, st);
						}
					
					 c.vinNumber.add(key);
					 System.out.println("Added");
				 }else{
					 c.priorityQueue.add(new CARFAX(key));
					 if (Records.get(key) != null) {
						 Stack<String> st=new Stack<String>();
							st=Records.get(key);
							st.push(Value);
							Records.replace(key, st);
							
						} else {
							 Stack<String> st=new Stack<String>();
							 st.push(Value);
							Records.put(key, st);
						}
					 c.vinNumber.add(key);
					 System.out.println("Added");
				 }
						 
			} else if(m==4){
				System.out.println("Enter the key to be Removed");
				Scanner scin=new Scanner(System.in);
				String key=scin.next();
				if(c.vinNumber.contains(key)){
				 if(n>t){
					 
					 c.deleteKey(key);
					 if (Records.get(key) != null) {
						 Stack<String> st1=new Stack<String>();
							st1=Records.get(key);
							 LocalDate myObj = LocalDate.now(); // Create a date object
							 st1.push(myObj.toString());
							Records.replace(key, st1);
							
						} else {
							 Stack<String> st1=new Stack<String>();
							 LocalDate myObj = LocalDate.now(); // Create a date object
							 st1.push(myObj.toString());
							 c.Records.put(key,st1);
						}
					
					 System.out.println("Removed");
				 }else{
					 PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>();
						while(true) 
						{
							
							CARFAX e = c.priorityQueue.poll();
							 if(e == null) break;
							 if(!e.getName().equalsIgnoreCase(key))
							 priorityQueue1.add(e);
						  //  System.out.println(e.getName());
						     
						   
						}
						//System.out.println("After removal");
						c.priorityQueue=priorityQueue1;
						if (Records.get(key) != null) {
							 Stack<String> st1=new Stack<String>();
								st1=Records.get(key);
								 LocalDate myObj = LocalDate.now(); // Create a date object
								 st1.push(myObj.toString());
								Records.replace(key, st1);
								
							} else {
								 Stack<String> st1=new Stack<String>();
								 LocalDate myObj = LocalDate.now(); // Create a date object
								 st1.push(myObj.toString());
								 c.Records.put(key,st1);
							}
					 System.out.println("Removed");
				 }
				}else{
					System.out.println("The key doesn't exists");
				}
				
			} 
			else if(m==5){
				System.out.println("Enter the keys whose value needs to be found");
				Scanner scin=new Scanner(System.in);
				String key=scin.next();
				if(c.Records.containsKey(key)){
					Stack<String> s = new Stack<String> ();  
					s=c.Records.get(key);
				System.out.println(s);
				} else
				System.out.println("The value of this key doesn't exist");
			}
			
			else if(m==6){
				System.out.println("Enter the key");
				Scanner scin=new Scanner(System.in);
				String key=scin.next();
				if(n>t){
					c.nextElement(key);
				}else{
					/*PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>();
					priorityQueue1=c.priorityQueue;*/
					PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>(priorityQueue);
					PQtest p=new PQtest();
					c.nextElement(priorityQueue1, key);
				}
				
				
				
			} else if(m==7){
				System.out.println("Enter the key");
				Scanner scin=new Scanner(System.in);
				String key=scin.next();
				if(n>t){
					c.prevElement(key);
				}else{
					/*PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>();
					priorityQueue1=c.priorityQueue;*/
					PriorityQueue<CARFAX> priorityQueue1 = new PriorityQueue<CARFAX>(priorityQueue);
					PQtest p=new PQtest();
					c.prevElement(priorityQueue1, key);
				}
				
			} else if(m==8){
				System.out.println("Displaying all the accidetal records");

				c.Records.entrySet().forEach(entry -> {
					
					System.out.println("For the given VIN Number"+entry.getKey());
					System.out.println("Accidents occured on the date are: ");
					System.out.println(entry.getValue());
					
					});
			}
			else{
				System.out.println("Bye");
				System.exit(0);
			}
		}
		
	}
	
	public String getName() {
		return vin;
	}

	public void setName(String vin) {
		this.vin = vin;
	}

	@Override
	public int compareTo(CARFAX o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}

}
