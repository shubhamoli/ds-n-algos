/**
 * Linked list is one of the most common Linear DS. 
 * 
 *  - In LL, the successive elements are connected by pointers
 *  - Last Element points to NULL
 *  - Can grow or Shrink during execution of program
 *  - Unlike arrays, LL doesn't waste space (no pre-allocation of space) but extra space is requried to store pointers
 *  
 * Pros:
 *  - can expand in constant time (no copying and re-allocating therefore faster than dynamic arays or ArrayList)
 * 
 * Cons:
 *  - Slower access time to individual element O(n) in worst case
 *  - LL don't have contiguous blocks of memory hence no caching in modern CPUs
 *  - Sometimes hard to manipulate
 * 
 * T.C.:
 *      indexing                          -  O(n)
 *      insertion/deletion at beginning   -  O(1)
 *      insertion/deletion at middle      -  O(n)
 *      insertion/deletion at end         -  O(n)
 *      
 * S.C.: 
 *      pointers                          -  O(n)
 */


class ListNode {
    private int data;
    private ListNode next;

    // constructor
    public ListNode(int data){
        this.data = data;
    }
    public void setData(int data){
        this.data = data;
    }
    public int getData(){
        return this.data;
    }
    public void setNext(ListNode next){
        this.next = next;
    }
    public ListNode getNext(){
        return this.next;
    }
}

/**
 * linked list ADT implementation
 */
class SinglyLinkedListADT{

    // holds head of LL
    ListNode head;

    private int length = 0;
    
    // default constructor
    public SinglyLinkedListADT(){
        length = 0;
    }

    public ListNode getHead() {
        return head;
    }

    public void insertAtBegin(ListNode node){
        // set node's next pointer to current head of LL
        node.setNext(head);
        // replace head of LL to new node inserted at beginning
        head = node;
        // increment length of LL
        length++;
    }

    public void insertAtEnd(ListNode node){
        // if no element is in LL
        if(head == null){
            head = node;
        }
        // else traverse whole LL and insert at last
        else{
            ListNode temp;

            // find last element 
            for(temp = head; (temp.getNext()) != null; temp = temp.getNext());
            
            // now temp is last node
            // set its next pointing to "node"
            temp.setNext(node);
        }
        // finally increment length
        length++;
    }

    public void insert(int data, int position){
        if(position < 0){
            position = 0;
        }
        if(position > length){
            position = length;
        }

        // if head is empty, LL has no element
        if(head == null){
            // so set head to this node
            head = new ListNode(data);
        }
        // if position is 0, insertAtBegin
        else if(position == 0){
            ListNode newNode =  new ListNode(data);
            this.insertAtBegin(newNode);
        }
        else {
            // traverse till "position"
            ListNode temp = head;
            for(int i=1; i< position;i++){
                temp = temp.getNext();
            }
            // finally we get a node @ position
            ListNode newNode = new ListNode(data);
            // and set New Node's next to next of this current node
            newNode.setNext(temp.getNext());
            // set next of this node to new Node
            temp.setNext(newNode);
        }
        length++;
    }

    public void removeFromBegin(){
        // save current HEAD in temp;
        ListNode temp = head;

        // set new 'HEAD' to second node
        if(temp != null){
            head = temp.getNext();
            temp.setNext(null);
            length--;
            return;
        }
        
        return;
    
    }

    public void removeFromEnd(){

        if(head == null){
            return;
        }
        // find that node after traversing the LL
        // whose next node's next pointer is NULL

        // temp 1 is head
        // temp 2 is null
        ListNode temp1 = head;
        ListNode temp2 = null;
        
        while(temp1.getNext() != null){
            temp2 = temp1;
            temp1 = temp1.getNext();
        }

        temp2.setNext(null);

        // decrement length
        length--;

        return;
    }

    public void removeMatched(ListNode node){
        if(head == null){
            return;
        }

        if(node.equals(head)){
            head = head.getNext();
            length--;
            return;
        }
        
        ListNode temp1 = head;
        ListNode temp2 = null;

        while(temp1.getNext() != null){
            temp2 = temp1.getNext();
            if(node.equals(temp2)){
                temp1.setNext(temp2.getNext());
                length--;
                return;
            }
            temp1 = temp2;
        }
    }

    public void remove(int position){

        if(position <= 0){
            position = 0;
        }
        if(position >= length){
            position = length;
        }

        if(head == null){
            return;
        }
        if(position == 0){
            this.removeFromBegin();
        }
        else{
            ListNode temp = head;

            for(int i=1; i < position; i++){
                temp = temp.getNext();
            }
            // now temp's next points to node next to its current next
            temp.setNext(temp.getNext().getNext());

            // decrement length
            length--;
        }
    }

    public String toString(){
        String delimiter_1 = "[";
        String delimiter_2 = "]";

        if(head == null){
            return delimiter_1 + delimiter_2;
        }
        // get head data
        String result = String.valueOf(head.getData());
        
        // create a temp variable to traverse through LL
        ListNode temp = head.getNext();

        while(temp != null){
            result = result + "," + String.valueOf(temp.getData());
            temp = temp.getNext();
        }

        return delimiter_1 +  result + delimiter_2;
    }

    public int length(){
        return length;
    }

    public int getPosition(int data){
        int position = 0;

        ListNode temp = head;

        while(temp != null){
            if(temp.getData() == data){
                return position;    
            }
            temp = temp.getNext();
            position++;
        }
        return -1;

    }

    // remove everything from the list
    public void clearList(){
        head = null;
        length = 0;
    }
}


public class SinglyLinkedListDemo {

    public static void main(String[] args){
        SinglyLinkedListADT LL = new SinglyLinkedListADT();
        
        System.out.println("Initially length of LL is: "+LL.length());
        
        ListNode for_match = new ListNode(90);

        // insert some nodes in LL
        LL.insertAtBegin(new ListNode(20));
        LL.insertAtBegin(new ListNode(10));
        LL.insertAtEnd(new ListNode(30));
        LL.insertAtEnd(new ListNode(40));
        LL.insertAtEnd(new ListNode(70));
        LL.insertAtEnd(new ListNode(80));
        LL.insertAtEnd(for_match);
        LL.insertAtEnd(new ListNode(50));
        LL.insertAtBegin(new ListNode(60));

        System.out.println("Now LL after some inserting nodes is: "+LL.toString());
        System.out.println("Current length is: "+LL.length());

        // now remove some nodes
        LL.removeFromBegin();
        LL.remove(4);    // remove node at index 4        
        LL.removeFromEnd();
        LL.removeMatched(for_match);   // remove node with data 90
        LL.remove(4);
        
        System.out.println("LL after removing some nodes is: "+LL.toString());
        System.out.println("Length of LL is: "+LL.length());            
        System.out.println("Position of 30 in LL is: "+LL.getPosition(30));
        System.out.println("Position of 130 in LL is: "+LL.getPosition(130));

        // finally clear the LL
        LL.clearList();

        System.out.println("Finally LL is cleared: "+LL.toString());
        
    }
}