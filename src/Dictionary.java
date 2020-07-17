import java.util.LinkedList;

public class Dictionary implements DictionaryADT {

	private LinkedList<Record>[] hashtable;

	// Constructor
	@SuppressWarnings("unchecked")
	public Dictionary(int size) {
		hashtable = new LinkedList[size];

		// initializing the hashtable by filling with null to save memory
		for (int i = 0; i < size; i++) {
			hashtable[i] = null;
		}
	}

	// This is the hash function which converts a config string into an array index
	private int hashFunction(String config) {
		int val = 0;
		int M = hashtable.length;
		int x = 47;

		for (int i = 0; i < config.length(); i++) {
			val = (val * x + config.charAt(i)) % M;
		}

		return val % M;
	}

	// This method inserts a record into the dictionary.
	public int insert(Record pair) throws DictionaryException {
		// Linked list is initialized in this function from null to save memory.

		int tableIndex = hashFunction(pair.getConfig());

		// if the given record isn't in the table
		if (get(pair.getConfig()) != -1)
			throw new DictionaryException();

		// if there is already a record at the index (Collision)
		if (hashtable[tableIndex] != null) {
			// Add to end of linked list
			hashtable[tableIndex].addFirst(pair);
			return 1;
		}
		// if the index is still null/no list yet. Create list at index and insert pair
		// in list.
		else {
			LinkedList<Record> list = new LinkedList<Record>();
			hashtable[tableIndex] = list;
			list.addFirst(pair);
			return 0;
		}

	}

	// This method removes a configuration from the dicitonary
	public void remove(String config) throws DictionaryException {
		int tableIndex = hashFunction(config);

		// if the index doesn't contain any list
		if (hashtable[tableIndex] == null)
			throw new DictionaryException();

		// if there is a list at the index
		else {
			int e = 0;
			LinkedList<Record> curList = hashtable[tableIndex];
			Record curRec = curList.get(e);

			// if config wasn't found in list
			if (get(config) == -1)
				throw new DictionaryException();

			// Finding specific index (e) in the list (curList) that contains the specified
			// config within the Record (curRec)
			while (!curRec.getConfig().equals(config)) {
				e++;
				curRec = curList.get(e);
			}
			curList.remove(e);
		}

	}

	// returns score when given a config or -1 if not found
	public int get(String config) {
		int tableIndex = hashFunction(config);

		// if the index doesn't even have a list
		if (hashtable[tableIndex] == null)
			return -1;

		// if a linked list can be found at index
		else {
			LinkedList<Record> curList = hashtable[tableIndex];

			// looping through the records of the linked list
			for (Record rec : curList) {
				if (rec.getConfig().equals(config)) {
					return rec.getScore();
				}
			}

			// if not found in list
			return -1;
		}
	}

	// Returns the number of elements in the dictionary
	public int numElements() {
		int num = 0;

		// loops through all elements in array and adds lengths of lists to get num
		for (int i = 0; i < hashtable.length; i++) {
			if (hashtable[i] != null)
				num += hashtable[i].size();
		}

		return num;
	}

}
