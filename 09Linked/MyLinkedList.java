import java.util.Iterator;

public class MyLinkedList implements Iterable<Integer> {
	private Node head;
	private Node tail;
	private int size;
	
	
	private class Node {
		int data;
		Node next;
		Node prev;
		
		public Node(int d, Node n) {
			data = d;
			next = n;
		}
		
		public Node(int d) {
			data = d;
		}
		
		public String toString() {
			String res = "(";
			if (prev==null) res +="null)";
			else res += prev.data+")";
			res+=""+data;
			if (next==null) res +="(null)";
			else res += "("+next.data+")";
			return res;
		}
	}
	
	public MyLinkedList() {}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			Node ptr = head;
			@Override
			public boolean hasNext() {
				return ptr != null;
			}
			
			@Override
			public Integer next() {
				int temp = ptr.data;
				ptr = ptr.next;
				return temp;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	private boolean addHead(int data) {
		Node t = new Node(data);
		if (head != null) {
			t.next = head;
			head.prev = t;
		}
		else tail = t;
		
		head = t;
		
		size++;
		return true;
	}
	
	//adds to end of list
	public boolean add(int val) {
		if (size == 0) {
			addHead(val);
			return true;
		}
		Node t = new Node(val);
		t.prev = tail;
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
	
	public void add(int index, int val) {
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
		t.prev = prev;
		prev.next.prev = t;
		prev.next = t;
		
		size++;
	}
	
	public int remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		int temp = -1;
		if (index == 0) {
			temp = head.data;
			head = head.next;
			head.prev = null;
			
			size--;
			return temp;
		}
		Node t = getRef(index);
		temp = t.data;
		
		if (index == size-1) {
			tail = tail.prev;
			tail.next = null;
		}
		else {
			t.next.prev = t.prev;
			t.prev.next = t.next;
		}
		
		size--;
		return temp;
	}
	
	public int get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		return getRef(index).data;
	}
	
	public int set(int index, int newVal) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(""+index);
		
		Node t = getRef(index);
		int old = t.data;
		t.data = newVal;
		return old;
	}
	
	public int indexOf(int val) {
		Node ptr = head;
		int counter = 0;
		
		while(ptr != null || counter < size) {
			if (ptr.data == val) return counter;
			
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
			System.out.println(ptr);
			if (ptr.next == null){
				res += ptr.data;
			}
			else res += ptr.data +", ";
			ptr = ptr.next;
		}
		return res+"]";
	}
	
	public static void main(String args[]) {
		MyLinkedList l = new MyLinkedList();
		
		System.out.println(l);
		l.add(3);
		System.out.println(l);
		l.add(7);
		l.add(11);
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
		for (Integer i : l) {
			System.out.println(i);
		}
	}
}
