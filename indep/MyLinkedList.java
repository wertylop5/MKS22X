public class MyLinkedList <T> {
	private Node head;
	private Node tail;
	private int size;
	
	
	private class Node {
		T data;
		Node next;
		
		public Node(T d, Node n) {
			data = d;
			next = n;
		}
		
		public Node(T d) {
			data = d;
		}
	}
	
	public MyLinkedList() {}
	
	private boolean addHead(T data) {
		Node t = new Node(data);
		if (head != null) {
			t.next = head;
		}
		else tail = t;
		
		head = t;
		
		size++;
		return true;
	}
	
	//adds to end of list
	public boolean add(T val) {
		if (size == 0) {
			addHead(val);
			return true;
		}
		Node t = new Node(val);
		tail.next = t;
		tail = t;
		
		size++;
		return true;
	}
	
	private Node getRef(int index) {
		Node ptr = head;
		while (index > 0) {
			ptr = ptr.next;
			index--;
		}
		return ptr;
	}
	
	public void add(int index, T val) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException(""+index);
		
		if (index == 0) {
			addHead(val);
			return;
		}
		if (index == size) {
			add(val);
			return;
		}
		
		Node prev = getRef(--index);	//so we can change the pointers of the node before targ
		
		Node t = new Node(val, prev.next);
		prev.next = t;
		
		size++;
	}
	
	public T remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		T temp;
		if (index == 0) {
			temp = head.data;
			head = head.next;
			
			size--;
			return temp;
		}
		
		Node t = getRef(--index);
		temp = t.next.data;
		
		if (index == size-1) {
			tail = t;
			tail.next = null;
		}
		else {
			t.next = t.next.next;
		}
		
		size--;
		return temp;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		return getRef(index).data;
	}
	
	public T set(int index, T newVal) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		Node t = getRef(index);
		T old = t.data;
		t.data = newVal;
		return old;
	}
	
	public int indexOf(T val) {
		Node ptr = head;
		int counter = 0;
		
		while(ptr != null || counter < size) {
		    if (ptr.data == val || ptr.data.equals(val)) return counter;
			
			ptr = ptr.next;
			counter++;
		}
		return -1;
	}
	
	public int size() { return size; }
	
	@Override
	public String toString() {
		String res = "[";
		Node ptr = head;
		while (ptr != null) {
			if (ptr.next == null){
				res += ptr.data;
			}
			else res += ptr.data +", ";
			ptr = ptr.next;
		}
		return res+"]";
	}
	
	public static void main(String args[]) {
		MyLinkedList<Integer> l = new MyLinkedList<>();
		
		System.out.println(l);
		l.addHead(3);
		System.out.println(l);
		l.addHead(7);
		l.addHead(11);
		System.out.println(l);
		l.add(6);
		System.out.println(l);
		l.add(6);
		System.out.println(l);
		
		//System.out.println(l.indexOf(0));
		
		l.add(0, -8);
		System.out.println(l);
		
		l.add(3, -8);
		System.out.println(l);
		
		l.add(7, -7);
		System.out.println(l);
		
		System.out.println(l.get(0));
		System.out.println(l.get(7));
		System.out.println(l.get(4));
		
		System.out.println("Set");
		System.out.println(l.set(0, -1));
		System.out.println(l);
		System.out.println(l.set(7, 100));
		System.out.println(l);
		System.out.println(l.set(4, 25));
		System.out.println(l);
		//System.out.println(l.get(8));
		//System.out.println(l.get(-1));
		
		System.out.println("Rem");
		System.out.println(l.remove(0));
		System.out.println(l);
		
		//System.out.println(l.remove(7));
		System.out.println(l.remove(6));
		System.out.println(l);
		System.out.println(l.remove(3));
		System.out.println(l);
		
		System.out.println(l.remove(3));
		System.out.println(l);
		
		/*
		System.out.println(l.set(4, 99));
		System.out.println(l);
		*/
		
		MyLinkedList<String> lol = new MyLinkedList<>();
		lol.add("a");
		lol.add("ba");
		lol.add("a");
		lol.add("9");
		System.out.println(lol);
		System.out.println(lol.indexOf("u"));
		/*
		lol.remove(0);
		lol.remove(0);
		System.out.println(lol.remove(0));
		System.out.println(lol);
		*/
	}
}
