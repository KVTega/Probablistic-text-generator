package model;
public class OurHashMap<K, V> implements OurMap<K, V> {
	private class HashNode {
		private K key;
		private V value;

		private HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@Override
		  public String toString() {
		    return this.key +" "+ this.value;
		  }
	}
	public static int TABLE_SIZE = 200000;
	private OurLinkedList<HashNode> [] lists;
	public int size;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OurHashMap() {
		lists = new OurLinkedList[OurHashMap.TABLE_SIZE];
		for (int i =0; i<TABLE_SIZE;i++){
			lists[i] = new OurLinkedList();
		}
	}
	@Override
	public String toString() {
		String result = "";
		for (int i=0;i<TABLE_SIZE;i++) {
			OurLinkedList<HashNode> list = lists[i];
			result+=i+" "+list+"\n";
		}
		return result;
	}

	@Override
	public V put(K key, V value) {
	    // Calculate the hash code of the key to determine the index
	    int index = Math.abs(key.hashCode()) % TABLE_SIZE;

	    // Create a HashNode with the provided key and value
	    HashNode newNode = new HashNode(key, value);

	    // Check if there is a linked list at the calculated index
	    if (lists[index] == null) {
	        lists[index] = new OurLinkedList<>();
	    }

	    // Search for an existing node with the same key in the linked list
	    OurLinkedList<HashNode> list = lists[index];
	    for (int i =0;i<list.size();i++) {
	    	HashNode node = list.get(i);
	    	if (node.key.equals(key)) {
	            // If the key already exists, update the value and return the old value
	            V oldValue = node.value;
	            node.value = value;
	            return oldValue;
	        }
	    }
	    

	    // If the key doesn't exist in the linked list, add the new HashNode
	    list.addFront(newNode);
	    size++;
	    // You may want to check if the number of elements in the linked list
	    // at this index exceeds a certain threshold and consider resizing
	    // and rehashing if needed.

	    return null; // Indicate that there was no previous value for this key
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		 int index = Math.abs(key.hashCode()) % TABLE_SIZE;

		    // Check if there is a linked list at the calculated index
		    if (lists[index] != null) {
		        // Search for the node with the given key in the linked list
		        OurLinkedList<HashNode> list = lists[index];
		        for (int i =0;i<list.size();i++)  {
		        	HashNode node = list.get(i);
		            if (node.key.equals(key)) {
		                // Return the value if the key is found
		                return node.value;
		            }
		        }
		    }

		    // Return null if the key is not found
		    return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		int index = Math.abs(key.hashCode()) % TABLE_SIZE;

	    if (lists[index] != null) {
	        OurLinkedList<HashNode> list = lists[index];
	        for (int i =0;i<list.size();i++)  {
	        	HashNode node = list.get(i);
	            if (node.key.equals(key)) {
	                
	                return true;
	            }
	        }
	    }
		return false;
	}

}
