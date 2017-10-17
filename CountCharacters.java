import java.util.Comparator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * @Author : Deepa Gopalrao
 * 
 * Problem : Count characters' frequency in given string and also find highest frequency character lexicographically.
 * 
 * Solution : Parse the given string in linear and compute frequency using TreeMap .
 * 	      To find highest frequency character construct priority queue with comparator to check for same frequency case.
 * 
 * Time Complexity : O(n) because we are parsing string in linear time
 * Space Complexity : O(n) because we are storing entries in TreeMap and PriorityQueue which is 2*n .Hence O(n).
 * 
 * Example given at the end of the program .
 * 			  
 */
class FrequencyComparator implements Comparator<Entry<Character, Integer>>{

	@Override
	public int compare(Entry<Character, Integer> entry1, Entry<Character, Integer> entry2) {
		// Case : Two characters have same frequency 
		if(entry1.getValue() == entry2.getValue())
			return entry1.getKey().compareTo(entry2.getKey());
		else
			return entry2.getValue() - entry1.getValue();
	}
}
public class CountCharacters {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter string : ");
		String s = scan.next();
		scan.close();
		TreeMap<Character,Integer> frequencyTable = new TreeMap<>();
		countCharacters(s,frequencyTable);
		System.out.print("Frequency of each character lexicographically  : ");
		System.out.println(frequencyTable);
		System.out.println("-------------------------------------------------------------------------");
		Entry<Character,Integer> entry = findHighestFrequencyChar(frequencyTable);
		System.out.print("Highest frequency character lexicographically is '"+entry.getKey()+
						 "' with frequency "+entry.getValue()+ ".");
		
	}

	private static Entry<Character, Integer> findHighestFrequencyChar(TreeMap<Character, Integer> frequencyTable) {
		PriorityQueue<Entry<Character,Integer>> pq = new PriorityQueue<>(new FrequencyComparator());
		pq.addAll(frequencyTable.entrySet());		
		return pq.poll();
	}

	private static void countCharacters(String s, TreeMap<Character, Integer> frequencyTable) {
		for(char ch : s.toCharArray()){
			int frequency = frequencyTable.containsKey(ch) ? 
							1 + frequencyTable.get(ch) : 
							1;
			frequencyTable.put(ch,frequency);
		}
	}

}

/*

Please enter string : 
google
Frequency of each character lexicographically  : {e=1, g=2, l=1, o=2}
-------------------------------------------------------------------------
Highest frequency character lexicographically is 'g' with frequency 2.

*/
