/**
 * ArrayList (or dynamic arrays, or list) is available in Java standard library
 * 
 * It is similar to arrays but unlike arrays its size can grow automatically
 * as needed. 
 * 
 * In Java, the size gets 50% increase of the original size,
 * 
 * T.C. -
 *      Read             -  O(1) 
 *      Search by index  -  O(1)
 *      Search by value  -  O(n)
 *      Insert/Delete    -  O(1) at end and O(n) at arbitrary indices, due to shifting.
 */

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        
        // create an empty arraylist of integers,
        // without specifying the size
        ArrayList<Integer> arr = new ArrayList<Integer>();
        
        // you can also build an Arraylist with initialSize
        //
        // ArrayList<Integer> arr = new ArrayList<Integer>(5);
        // 
        // or can provide min. size using ensureCapacity(int minCapacity) method
        //
        // arr.ensureCapacity(5)
        
        
        // First, add as many elements as you want
        arr.add(20);
        arr.add(30);
        arr.add(45);
        arr.add(50);
        arr.add(60);

        System.out.println("Original ArrayList: "+arr);

        // now remove some elements
        // remove requires "index" of element to be removed
        arr.remove(4);

        System.out.println("ArrayList after deleting element at index 4: "+arr);
        
        // check if 50 is present or not
        System.out.println("Element 50 is present: "+arr.contains(50));

        // as 50 is present, we want to fetch its index
        System.out.println("Index of 50 is: "+arr.indexOf(50));

        // now get/read an element at index 2
        System.out.println("Element at Index 2 is: "+arr.get(2));

        // now add an elemen to an specified location
        arr.add(0, 10);

        System.out.println("ArrayList after adding element at 0: "+arr);

        // replace an element at specific index
        arr.set(3, 40);

        System.out.println("ArrayList after replacing an element at index 3: "+arr);

        // print size of array
        System.out.println("Current size of ArrayList: "+arr.size());
        
        // remove all items of ArrayList
        arr.clear();

        System.out.println("Size of ArrayList after clear: "+arr.size());
    }
}