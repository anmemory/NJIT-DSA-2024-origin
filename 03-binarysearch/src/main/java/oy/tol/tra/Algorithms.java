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


    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        int middle;
        while(fromIndex<=toIndex){
            middle=fromIndex+(toIndex-fromIndex)/2;
            if( aValue.compareTo(fromArray[middle])>0 ){
                fromIndex=middle+1;
            }else if(aValue.compareTo(fromArray[middle])<0){
                toIndex=middle-1;
            }else{
                return middle;
            }
        }
        return -1;
    }

    
    public static <T>void swap(T[] array,int index1,int index2){
        T temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }//change: build a method for the swap function

    private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
        E benchmark=array[begin];
        int left=begin;
        int right=end;
        while (left!=right) {//change: I should not use if beause the loop should make a mistake and if I use while，the loop will run while the condition is true
           while((left<right)&&array[right].compareTo(benchmark)>0) {//change: while the right number is bigger than the pivot, minus the index to find a smaller one
                right--;
            }
           while((left<right)&&array[left].compareTo(benchmark)<=0) {//change: while the left number is smaller than the pivot, increase the index to find a bigger one
                left++;
            }
            if(left<right){//change: if the index does not cross, swap that two
                swap(array, left, right);
            }
            }
        array[begin]=array[left];//change: than restart a new one
        array[left]=benchmark ;
        return left;
    }


public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
    if(begin>=end){
        return;
    }
    int pivot=partition(array, begin, end);
    quickSort(array, begin, pivot-1);
    quickSort(array, pivot+1, end);
}

public static <E extends Comparable<E>> void fastSort(E [] array) {
    quickSort(array, 0, array.length - 1);
}
}
