package sort;
/* Author: Joshua L. Cary
 * Date: Feb 6th, 2024
 * Description: Hello \_0_
 *                      | \
 *                     / \
 * Sorts is a collection of functons to be used to sort an array
 * of integers in accending order.
 * Four methods are available (insertion, merge, quick and (LSD)radix sort),
 * and you can also excute them using SortsDriver.
 * */                   


import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.lang.Math;

public class Sorts {

   // maintains a count of comparisons performed by this Sorts object
  private int comparisonCount;

  public int getComparisonCount() {
    return comparisonCount;
  }

  public void resetComparisonCount() {
    comparisonCount = 0;
  }

  /** Sorts A[start..end] in place using insertion sort
    * Precondition: 0 <= start <= end <= A.length */
  public void insertionSort(int[] A, int start, int end) {
    // Code runs untill all bigger numbers move to the left side, and i reaches the end
    int i = start + 1;
    while (i < end){
      int j = i;
      while (j>0 && A[j-1]>A[j]){
        swap(A, j, j-1);
        comparisonCount++;
        j--;
      }
      i++;
    }
  }

  /** Partitions A[start..end] around the pivot A[pivIndex]; returns the
   *  pivot's new index.
   *  Precondition: start <= pivIndex < end
   *  Postcondition: If partition returns i, then
   *  A[start..i] <= A[i] <= A[i+1..end] 
   **/
  public int partition(int[] A, int start, int end, int pivIndex) {
    int i = start + 1 ;
    int j = end - 1;
    // Initial Swap, for good code
    swap(A, start, pivIndex);
    // Works through and exchanges i and i-1 when i < i-1, and exchanges i and j when i > i-1
    while (i <= j){
      if (A[i] > A[i-1]){
        swap(A, i, j);
        comparisonCount++;
        j--;
      }
      else{
        swap(A, i, i-1);
        comparisonCount++;
        i++;
      }
    }
    return i-1;
  }
  // /** use quicksort to sort the subarray A[start..end] */
  public void quickSort(int[] A, int start, int end) {
    if (end-start < 2){
      return;
    }
    int mid = partition(A, start, end, start);
    quickSort(A,start, mid);
    quickSort(A, mid+1, end);
  }

  /** merge the sorted subarrays A[start..mid] and A[mid..end] into
   *  a single sorted array in A. */
  public void merge(int[] A, int start, int mid, int end) {
    int i = start;
    int j = mid;
    int x = 0;
    int temp[] = new int [end-start];

    while (i < mid && j < end){
      // Compare i to j, if a smaller, add a to array and increment a
      if (A[i] < A[j]){
        temp[x] = A[i];
        comparisonCount++;
        i++;
        x++;
      }
      // if j smaller, add j to array and increment j
      else{
        temp[x] = A[j];
        comparisonCount++;
        j++;
        x++; 
      }
    }

    // if either array is empty, add other variable to array, increment that variable
    while (i < mid){
      temp[x] = A[i];
      comparisonCount++;
      i++;
      x++;
    }
    while (j < end){
      temp[x] = A[j];
      comparisonCount++;
      j++;
      x++;
    }
    
    // apply to A array
    for (int w = 0; w < end-start; w++ ){
      A[start + w] = temp[w];

    }
  }

  /** use mergesort to sort the subarray A[start..end] */
  public void mergeSort(int[] A, int start, int end) {
    if (end-start < 2){
      return;
    }
    int mid = (end + start)/2;
    mergeSort(A, start, mid);
    mergeSort(A, mid, end);
    merge (A, start, mid, end);
  }

  /** Sort A using LSD radix sort. */
  public void radixSort(int[] A) {
    int max = A[0];
    ArrayList<Queue<Integer>> rayLine = new ArrayList<Queue<Integer>>();

    // Finds largest numeber
    for (int i = 1; i < A.length; i++ ){
      if (A[i] > max){
        max = A[i];
      }
    }
    int funDigits = (int)Math.log10(max)+1;
    // System.out.println(funDigits);

    // Create buckets for negatives and positives
    for (int q = 0; q < 19; q++){
      rayLine.add(new LinkedList<Integer>());
    }
    // For each place till funDigits...
    for (int j = 1; j <= funDigits; j++){
      // All numbers in the array are sorted for their value in jth place
      for (int i = 0; i < A.length; i++){
        int quack = getDigit(A[i], j-1);
        Queue<Integer> basketCase =  rayLine.get(quack + 9);
        basketCase.add(A[i]);
      }

      // Place back into Array
      int total = 0;
      for (int q = 0; q < 19; q++){
        Queue<Integer> basketCase =  rayLine.get(q);
        while(!basketCase.isEmpty()){
          A[total] = basketCase.remove();
          total++;
        }
      }
    }
  }
 
  /* return the 10^d's place digit of n */
  private int getDigit(int n, int d) {
    return (n / ((int)Math.pow(10, d))) % 10;
  }

  /** swap a[i] and a[j]
   *  pre: 0 <= i, j < a.size
   *  post: values in a[i] and a[j] are swapped */
  public void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
 
}
