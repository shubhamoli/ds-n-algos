/**
 * Double LL is another LL implementation in which we have pointers to both prev as well as next node.
 * The benefit is that we don't have to keep track of previous node of the node we want to process 
 * 
 *  - head's prev points to NULL
 *  - Last Element next points to NULL
 *  
 * Pros:
 *  - Same as LL and don't have to maintain pointer of previous node.
 * 
 * Cons:
 *  - More complex as we have to maintain and manipulate more pointers
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


class DLLNode {
    private int data;
    private DLLNode next;
    private DLLNode prev;

    // constructor
    public DLLNode(int data){
        this.data = data;
    }
    public void setData(int data){
        this.data = data;
    }
    public int getData(){
        return this.data;
    }
    public void setNext(DLLNode next){
        this.next = next;
    }
    public DLLNode getNext(){
        return this.next;
    }
    public void setPrev(DLLNode prev){
        this.prev = prev;
    }
    public DLLNode getPrev(){
        return this.prev;
    }
}

/**
 * linked list ADT implementation
 */
class DLLADT{

    // holds head of LL
    DLLNode head;

    private int length = 0;
    
    // default constructor
    public DLLADT(){
        length = 0;
    }

    public DLLNode getHead() {
        return head;
    }

    public void insertAtBegin(DLLNode node){

        if(head == null){
            head = node;
        }
        else{
            // set node's next pointer to current head of DLL
            node.setNext(head);
            // set prev pointer of head to node
            head.setPrev(node);
            // make node new head
            head = node;
        }
        // increment length of LL
        length++;
    }

    public void insertAtEnd(DLLNode node){
        // if no element is in DLL
        if(head == null){
            head = node;
        }
        // else traverse whole DLL and insert at last
        else{
            DLLNode temp;

            // find last element 
            for(temp = head; (temp.getNext()) != null; temp = temp.getNext());
            
            // now temp is last node

            // set node prev pointer to temp
            node.setPrev(temp);
            // set temp next pointer to "node"
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
        // make new DLLNode
        DLLNode newNode = new DLLNode(data);

        // if head is null, DLL has no element
        if(head == null){
            // so set head to this node
            head = newNode;
            length++;
        
        }
        // if position is 0, insertAtBegin
        else if(position == 0){
            this.insertAtBegin(newNode);
        }
        else {
            // traverse till "position"
            DLLNode temp = head;
            for(int i=1; i< position;i++){
                temp = temp.getNext();
            }
            // and set New Node's next to next of this position node
            newNode.setNext(temp.getNext());
            // set prev to temp
            newNode.setPrev(temp);
            // set next of this position to new Node
            temp.setNext(newNode);

            length++;
        
        }
    }

    public void removeFromBegin(){
        // save current HEAD in temp;
        DLLNode temp = head;

        // set new 'HEAD' to second node
        if(temp != null){
            head = temp.getNext();
            head.setPrev(null);
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
        // find last node after traversing the DLL
        //
        // Note: here benefit of DLL comes, we don't have to maintain another
        //       pointer to (n-1) node as in case of LL

        DLLNode temp = head;
        
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        
        // set last node's prev pointer to NULL
        // and second last node's (temp) next pointer to NULL
        DLLNode secondLast = temp.getPrev();
        secondLast.setNext(null);
        temp.setPrev(null);

        // decrement length
        length--;

        return;
    }

    public void removeMatched(DLLNode node){
        if(head == null){
            return;
        }

        if(node.equals(head)){
            // set head to next element
            head = head.getNext();
            // and set its prev to null
            head.setPrev(null);
            // decrement length
            length--;
            return;
        }
        
        DLLNode temp = head;

        while(temp.getNext() != null){
            if(node.equals(temp.getNext())){
                // skip "temp.getNext()""
                temp.setNext(temp.getNext().getNext());
                // if new next is not null node
                // then set its prev to temp
                if(temp.getNext() != null){
                    temp.getNext().setPrev(temp);
                }
                length--;
                return;
            }
            temp = temp.getNext();
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
            DLLNode temp = head;

            for(int i=1; i < position; i++){
                temp = temp.getNext();
            }
            // now temp's next points to node next to its current next
            // and that node's prev points to temp
            // actually, we skipped position node
            temp.getNext().getNext().setPrev(temp);
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
        
        // create a temp variable to traverse through DLL
        DLLNode temp = head;

        while(temp.getNext() != null){
            result = result + "," + String.valueOf(temp.getNext().getData());
            temp = temp.getNext();
        }

        return delimiter_1 +  result + delimiter_2;
    }

    public int length(){
        return length;
    }

    public int getPosition(int data){
        int position = 0;

        DLLNode temp = head;

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


public class DoublyLinkedListDemo {

    public static void main(String[] args){
        DLLADT DLL = new DLLADT();

        DLLNode for_match = new DLLNode(80);
        
        // insert some nodes
        DLL.insertAtBegin(new DLLNode(30));
        DLL.insertAtBegin(new DLLNode(20));
        DLL.insertAtEnd(new DLLNode(40));
        DLL.insertAtEnd(new DLLNode(50));
        DLL.insert(60, 5);
        DLL.insert(70, 6);
        DLL.insertAtEnd(for_match);
        DLL.insertAtEnd(new DLLNode(110));
        DLL.insertAtEnd(new DLLNode(120));
        DLL.insertAtBegin(new DLLNode(10)); 
        DLL.insertAtBegin(new DLLNode(100));        
        DLL.insert(90, 0);

        System.out.println("DLL is: "+DLL.toString());
        System.out.println("Current length of DLL is: " + DLL.length());

        // now remove some nodes
        DLL.removeFromBegin();
        DLL.removeFromBegin();
        DLL.removeFromEnd();
        DLL.removeFromEnd();
        DLL.remove(0);
        DLL.removeMatched(for_match);

        System.out.println("DLL after removing some items is: "+DLL.toString());
        System.out.println("And Length is: " + DLL.length());
        
        // check position of some nodes
        System.out.println("Position of 20 is: "+DLL.getPosition(20));
        System.out.println("Position of 120 is: "+DLL.getPosition(120));

        // Clearing the DLL
        DLL.clearList();

        System.out.println("DLL is now cleared: " + DLL.toString());
    }
}