package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T [] array) {
        int i = array.length-1;
        if (array  != null && array.length != 0){
        for (int j=0; j<i; j++){
           for (int k=0; k<i; k++){
              if(array[k].compareTo(array[k+1])>0){
                 T tmp = array[k];
                 array[k] = array[k+1];
                 array[k+1] = tmp;
              }
           }
        }
     }
   }
   public static <T> void reverse(T [] array) {
    if (array != null && array.length != 0)
      {
      for ( int i=0; i < array.length/2; i++) {
         T temp = array[i];
         array[i] = array[array.length-i-1];
         array[array.length-i-1] = temp;
     }
   }


   }
}