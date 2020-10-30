public interface Lists<E>{
	E get(int position);
	boolean add(E value);
	E remove(int position);
	int getSize();
}
class ArrayList<E> implements Lists<E>{
	E[] arr;
	int size;

	public ArrayList(){
		arr = (E[])new Object[15];//array of size 15
		size = 0;
	}
	public int getSize(){
		return size;
	}
	public E get(int position){
		if(position<0||position>=size){
			System.out.println("Invalid position");
			return null;
		}
		return arr[position];
	}
	private void grow_array(){
		E [] new_arr=(E[])new Object[arr.length*2];
		for(int i=0; i< arr.length; i++){
			new_arr[i]= arr[i];
		}
		arr=new_arr;
	}
	public boolean add (E value){
		if(size == arr.length){
			grow_array();
		}
		arr[size++]=value;
		return true;
	}
	public void add(int position, E value){
		for(int i = size; i>position; i--){
			arr[i]=arr[i-1];
		}
		arr[position]=value;
		++size;
	}
	public E remove(int position){
		if(position<0 && position>=size){
			System.out.println("Invalid index");
		}
		E value = arr[position];
		for(int i=position+1; i<size; i++){
			arr[i-1]=arr[i];
		}
		size--;
		return value;
	}

}
class LinkedList<E> implements Lists<E>{
	Node<E> head;
	int size;
	private static class Node<E>{
		E data;
		Node<E> next;

		public Node(E value){
			data=value;
			next=null;
		}
	}
	
	public LinkedList(){
		head=null;
		size=0;
	}
	public int getSize(){
		return size;
	}
	//Get : This takes in an integer position which then returns the node at that position in the list. If the position is invalid, return null
	public E get(int position){
		//error check
		if(position<0||position>=size){
			System.out.println("Invalid index");
			return null;
		}

		Node<E> curr = head;
		for (int i = 0; i<position; i++){
			curr = curr.next;
		}
		return curr.data;
	}
	public boolean add(E item){
		if(head == null){
			head = new Node<E>(item);
			++size;
			return true;
		}
		else{
			Node<E> prev = head;
			while(prev.next!= null){
				prev=prev.next;
			}
			Node<E> node = new Node<E>(item);
			prev.next= node;
			++size;
			return true;
		}
	}
	
	public void add(E item, int position){
		if(position == 0){
			Node<E> node = new Node<E>(item);
			node.next = head;
			head = node;
			++size;
		}
		else{
			Node<E> prev = head;
			for(int i=0; i<position-1; i++){
				prev = prev.next;
			}
			Node<E> node = new Node<E>(item);
			node.next = prev.next;
			prev.next = node;
			++size;
		}
	}
	//Remove: The function takes in an integer position , which it then both removes and returns the ListNode at that position
	public E remove(int position){
		//Error check
		if(position<0 || position>=size){
			System.out.println("Invalid index");
			return null;
		}
		if (position == 0){
			Node<E> node = head;
			head = head.next;
			--size;
			return node.data;
		}
		else{
			Node<E> prev = head;
			for (int i=0; i<position-1; i++){
				prev = prev.next;
			}
			Node<E> node = prev.next;
			prev.next = node.next;
			--size;
			return node.data;
		}
	}
	//Reverse: The function reverses the whole linked list from head to tail. It should take in a parameter ListNode head. And return back the new head once the function is finished.
	public Node<E> reverse(Node<E> head){
		Node<E> prev=null;
		Node<E> curr= head;
		Node<E> next= null;
		while(curr!=null){
			next= curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}
		head=prev;
		return head;
	}
}