package oy.tol.tra;

import java.util.function.ObjDoubleConsumer;

public class QueueImplementation<E> implements QueueInterface<E>{
    private Object []itemArray;
    private int size= 0;
    private int head= 0;
    private int tail= 0;
    private int capacity;
    private static final int DEFAULT_STACK_SIZE = 10;
    

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_STACK_SIZE);
    }


    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity<=0) {
           throw new QueueAllocationException("cannot allocate room for the internal array");
        }
        try{
           itemArray = new Object[capacity];
           this.capacity=capacity;
           size=0;
           head=0;
           tail=0;
           // change: initialize the size, tail and head
        }catch (OutOfMemoryError e){
           throw new QueueAllocationException("Fail to allocate more room for the queue");
        }
        
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
      if (element == null){ 
         throw new NullPointerException("the element to enqueue cannot be null ");
       }
       if(tail >= capacity){
         try{
            int newCapacity = 2*capacity;
            Object [] newArray = new Object[newCapacity];
            int i = 0;
             //change: make a space for me to put the element into the new array
             while (i<size){
                //change: If the index is smaller than capacity, use it directly.
                if (head+i<capacity){
                    newArray[i] = itemArray[head+i];
                }else {
                    newArray[i] = itemArray[i-(capacity-head)];
                    //change: I find that the index could not be used and I recaculate it as (i-(capacity-head))
                }
                i++;
            }
            itemArray = newArray;
            capacity = newCapacity;
            //change: The header changes to 0
            head = 0;
            //change: Tail is size
            tail = size;
         }catch(OutOfMemoryError e){
            throw new QueueAllocationException("Fail to allocate more room for the queue");
         }
       }
       itemArray[tail] = element;
       if(head==capacity-1){
          tail = 0;}
          else{
             tail++;
          }
          size++;
    } 
    
     @SuppressWarnings("unchecked")
     @Override
     public E dequeue() throws QueueIsEmptyException {
      if (head == tail){
         throw new QueueIsEmptyException("the queue cannnot be empty") ;
      }
      Object dequeueElement = itemArray[head];
      itemArray[head] = null; //change: set the dequeue element null
      head++;
     if(head>=capacity){
          head = 0;
       }
        size--;
      return (E)dequeueElement;
   }
     
     public E element() throws QueueIsEmptyException{
      if (head == tail){
         throw new QueueIsEmptyException("the queue cannnot be empty") ;
      }
      Object Elemnt = itemArray[head];
      return (E)Elemnt;
     }

     @Override
     public int size() {
        return size;
     }
    
     @Override
     public void clear() {
       head = tail;
       head=0;
       tail=0;
       size=0;
   }
    
   @Override
   public boolean isEmpty() {
      if (head == tail && size == 0 ){
         return (true);
      }
      else{
         return(false);
      }
   }  


   
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= (size-1); index++) {
         if(index+head>=capacity){
         builder.append(itemArray[index+head-capacity].toString());
         }
         else{
         builder.append(itemArray[index+head].toString());   
         }
         if (index < size-1) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }



}