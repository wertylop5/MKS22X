public class RunningMedian {
	private MyHeap lower;
	private MyHeap higher;
	
	public RunningMedian() {
		lower = new MyHeap();
		higher = new MyHeap(false);
	}
	
	private void balance() {
		if (lower.getSize() > higher.getSize()) {
			while (Math.abs(lower.getSize() - higher.getSize()) > 1) {
				higher.add(lower.remove());
			}
		}
		else {
			while (Math.abs(lower.getSize() - higher.getSize()) > 1) {
				lower.add(higher.remove());
			}
		}
	}
	
	public void add(int i) {
		if (getSize() == 0) {
			lower.add(i);
		}
		else {
			if (i > getMedian()) {
				higher.add(i);
			}
			else {
				lower.add(i);
			}
			
			balance();
		}
	}
	
	/*
	//Don't know what to do, so just removing the median
	public Double remove() {
		Double res;
		if (lower.getSize() == higher.getSize()) {
			res = (lower.peek() + higher.peek()) / 2.0;
			//Don't know what to remove here
		}
		else if (lower.getSize() > higher.getSize()) {
			res = lower.peek()*1.0;
			lower.remove();
		}
		else {
			res = higher.peek()*1.0;
			higher.remove();
		}
		
		balance();
		return res;
	}
	*/
	
	public double getMedian() {
		if (lower.getSize() == higher.getSize()) {
			return (lower.peek() + higher.peek()) / 2.0;
		}
		else if (lower.getSize() > higher.getSize()) {
			return lower.peek()*1.0;
		}
		return higher.peek()*1.0;
	}
	
	public int getSize() { return lower.getSize() + higher.getSize(); }
	
	public static void main(String[] args) {
		RunningMedian m = new RunningMedian();
		/*
		m.add(3);
		System.out.println(m.getMedian());
		
		m.add(3);
		System.out.println(m.getMedian());
		
		m.add(0);
		System.out.println(m.getMedian());
		
		m.add(-1);
		System.out.println(m.getMedian());
		
		m = new RunningMedian();
		m.add(0);
		System.out.println(m.getMedian());
		
		m.add(2);
		System.out.println(m.getMedian());
		
		m.add(1);
		System.out.println(m.getMedian());
		*/
		if (args.length < 1) System.exit(1);
		for (String s : args) {
			m.add(Integer.parseInt(s));
		}
		System.out.println(m.getMedian());
		/*m.add(-1);
		System.out.println(m.getMedian());*/
	}
}
