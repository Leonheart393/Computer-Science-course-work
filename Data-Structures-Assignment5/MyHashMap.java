package cop3530;

import java.util.Map;
import java.util.Iterator;

//MyHashMap class for creating a HashMap
public class MyHashMap<KeyType,ValueType> implements Iterable<Map.Entry<KeyType,ValueType>>
{
    //instance variables
    private HashFunction<? super KeyType> hash1;
    private HashFunction<? super KeyType> hash2;
    //MyHashMap constructor
    public MyHashMap( HashFunction<? super KeyType> h1, HashFunction<? super KeyType> h2 )
    {
       doClear();
       hash1 = h1;
       hash2 = h2;
    }
    //size method returns the size
    public int size( )
    { 
        return theSize;
    }
    //public method for clearing
    public void clear( )
    { 
        doClear(); 
    }
    //private method to be called by public ckear
    private void doClear()
    {
        theSize = 0;
        arr = new Node[ DEFAULT_ARRAY_SIZE ];
        sizeOfLists = new int[20];
    }
    //put method to add keys and values
    public ValueType put( KeyType k, ValueType v )
    {
        if( arr.length < size())
            rehash();
        
        int whichList = myHash(k);
        int whichList2 = myHash2(k);
        int i = 0;
        int j = 0;
        
        for( Node<KeyType , ValueType> p = arr[ whichList ]; p != null; p = p.next )
        {
            if( p.key.equals( k ) ) 
            {
                ValueType old = p.value;
                p.value = v;
                return old;
            }
            i++;
        }
        
        for( Node<KeyType , ValueType> p = arr[ whichList2 ]; p != null; p = p.next )
        {
            if( p.key.equals( k ) ) 
            {
                ValueType old = p.value;
                p.value = v;
                return old;
            }
            j++;
        }
        
        if(j > i)
        {
            arr[whichList] = new Node<>(k, v, arr[whichList]);
        }
        else
        {
            arr[whichList2] = new Node<>(k, v, arr[whichList2]);
        }
        ++theSize;
        
        return null;
    }
    //remove method for removing keys and values
    public boolean remove( KeyType k )
    { 
        int whichList = myHash(k);
        int whichList2 = myHash2(k);
          
        if(arr[whichList] != null)
        {
            if(arr[whichList].key.equals(k))
            {
                arr[whichList] = arr[whichList].next;
                --theSize;
                return true;
            }
            for(Node<KeyType, ValueType> p = arr[whichList]; p.next != null; p = p.next)
            {
                if(p.next.key.equals(k))
                {
                    p.next = p.next.next;
                    --theSize;
                    return true;
                }
            }
        }
        
        if(arr[whichList2] != null)
        {
            if(arr[whichList2].key.equals(k))
            {
                arr[whichList2] = arr[whichList2].next;
                --theSize;
                return true;
            }        
            for(Node<KeyType, ValueType> p = arr[whichList2]; p.next != null; p = p.next)
            {
                if(p.next.key.equals(k))
                {
                    p.next = p.next.next;
                    --theSize;
                    return true;
                }
            }
        }
        
        return false;
    }
    //get method for returning a value if a match is found from the keys   
    public ValueType get( KeyType k )
    { 
        int whichList = myHash(k);
        int whichList2 = myHash2(k);
        
        for(Node<KeyType, ValueType> p = arr[whichList]; p != null; p = p.next)
        {
            if(p.key.equals(k))
            {
                return p.value;
            }
            else
            {

            }
        } 
        
        for(Node<KeyType, ValueType> p = arr[whichList2]; p != null; p = p.next)
        {
            if(p.key.equals(k))
            {
                return p.value;
            }
            else
            {

            }
        } 
               
        return null;
    }
    //myHash method for returning an int result of a hash computation
    private int myHash(KeyType k)
    {
        if(hash1 == null)
            return Math.abs( k.hashCode( ) % arr.length );
        else
            return Math.abs( hash1.hashCode( k ) % arr.length );
    }
    //second myHash method for second hash function
    private int myHash2(KeyType k)
    {
        if(hash2 == null)
            return Math.abs( k.hashCode( ) % arr.length );
        else
            return Math.abs( hash2.hashCode( k ) % arr.length );
    }
     //rehash method to be called by put method for hashing
    private void rehash()
    {
        MyHashMap<KeyType, ValueType> bigger = new MyHashMap<>(hash1, hash2);
        bigger.arr = new Node[ arr.length * 2 ];
        bigger.sizeOfLists = new int [arr.length * 2 ];
        
        for( Node<KeyType , ValueType> lst : arr )
            for( Node<KeyType , ValueType> p = lst; p != null; p = p.next )
            {
                bigger.put( p.key, p.value ); 
            }
                     
        arr = bigger.arr;
        sizeOfLists = bigger.sizeOfLists;
        bigger = null;
    }
    //toString method for MyHashMap
    public String toString( )
    {
        StringBuilder sb = new StringBuilder("[ ");
        
        for(Map.Entry<KeyType, ValueType> p : this)
        {
            System.out.println(p);
            sb.append(p + " ");
        }
        sb.append("]");
        
        return new String(sb);
    }
    //Iterator implemented for MyHashMap, returns a Map.Entry item and provides
    //an advancement method.
    public Iterator<Map.Entry<KeyType,ValueType>> iterator( )
    {
        return new Iterator<Map.Entry<KeyType,ValueType>>( )
        {
            //hasNext method for iterator, returns true as long as there is a node
            public boolean hasNext( )
            {
                return current != null; 
            }
            //next method for Iterator, accesses a copy of the current 
            public Map.Entry<KeyType,ValueType> next( )
            {
                final Node<KeyType,ValueType> theCurrent = current;
                current = current.next;
                if(current == null)
                {
                    listNum++;
                    advanceToNewList();
                }
                
                Map.Entry<KeyType,ValueType> nextItem = new Map.Entry<KeyType,ValueType>( )
                {
                    public KeyType getKey() 
                    {
                        return theCurrent.key;
                    }

                    public ValueType getValue() 
                    {
                        return theCurrent.value;
                    }

                    public ValueType setValue(ValueType v) 
                    {
                        throw new UnsupportedOperationException();
                    }
                   
                };
                return nextItem;
            } 
            
            private void advanceToNewList( )
            { 
                while(listNum < arr.length && arr[listNum] == null)
                    listNum++;

                if(listNum != arr.length)
                    current = arr[listNum];            
            }

            {  
                advanceToNewList();
            }
            
            Node<KeyType,ValueType> current;   // current node
            int listNum = 0;                       // current list #

        };  
    }
    //Node class to be used for creating and utilizing nodes in MyHashMap
    private static class Node<KeyType, ValueType>
    {
        Node( KeyType k, ValueType v, Node<KeyType, ValueType> n )
        { key = k; value = v; next = n; }

        public String toString( )
        { return key + "=" + value; }

        KeyType key;
        ValueType value; 
        Node<KeyType,ValueType> next;
    }
    //getLengths method for calculating the distribution of list lengths
    //in the array, the item in index i is the number of lists of length i
    public int [] getLengths( )
    {
        int count = 0;
        
        for(int i = 0; i < arr.length; i++)
        {
            for(Node<KeyType, ValueType> p = arr[i]; p != null; p = p.next)
            {
                count++;
            }
            
            sizeOfLists[count] = sizeOfLists[count] + 1;
            count = 0;
        }
        
        return sizeOfLists;
    }   
    //instance variables
    private static final int DEFAULT_ARRAY_SIZE = 11;
    private int [] sizeOfLists = new int[20];
    private Node<KeyType, ValueType> [ ] arr = null;
    private int theSize = 0;
      
}  
