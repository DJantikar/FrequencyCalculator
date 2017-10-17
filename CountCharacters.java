import java.util.Comparator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

class FrequencyComparator implements Comparator<Entry<Character, Integer>>{

	@Override
	public int compare(Entry<Character, Integer> entry1, Entry<Character, Integer> entry2) {
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
