package oy.tol.tra;

import java.util.function.ObjDoubleConsumer;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private Object []itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      this(DEFAULT_STACK_SIZE);
      // TODO: call the constructor with size parameter with default size of 10.
         
      
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity<=2) {
         throw new StackAllocationException("cannot allocate room for the internal array");
      }
      try{
         itemArray = new Object[capacity];
         this.capacity=capacity;
      }catch (OutOfMemoryError e){
         throw new StackAllocationException("Fail to allocate more room for the stack");
      }
      
   }

   @Override
   public int capacity() {
      // TODO: Implement this
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
       if (element == null){ 
         throw new NullPointerException("the element to push cannot be null ");
       }
       if(currentIndex >= capacity-1){
         try{
            int newCAPACITY = 2*capacity;
            Object [] newArray = new Object[newCAPACITY];
            for (int i=0; i<capacity; i++){
               newArray[i] = itemArray[i];
            }
            itemArray = newArray;
            capacity = newCAPACITY;
         }catch(OutOfMemoryError e){
            throw new StackAllocationException("Fail to allocate more room for the stack");
         }
       }
       itemArray[currentIndex+1] = element;
       currentIndex++;       
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (currentIndex == -1){
         throw new StackIsEmptyException("the stack cannnot be empty") ;
      }
      Object popElement = itemArray[currentIndex];
      currentIndex--;
      return (E)popElement;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (currentIndex == -1){
         throw new StackIsEmptyException("the stack cannnot be empty");
      }
      Object peekElement=itemArray[currentIndex];
      return (E)peekElement;
   }

   @Override
   public int size() {
      // TODO: Implement this
      return currentIndex+1;
   }

   @Override
   public void clear() {
      // TODO: Implement this
        currentIndex = -1;
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      if (currentIndex == -1 ){
         return (true);
      }
      else{
         return(false);
      }
      
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}