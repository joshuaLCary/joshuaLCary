package sort;
/* Author: Joshua L. Cary
 * Date:Feb 6th, 2024
 * Description:
 * Implementation of the functions from Sorts.java.
 * Takes an a chocie of array sorting method and length of array from user,
 * and then (if n<= 20, prints sorted arrays. Otherwise, does not print).
 * */

import java.util.Random;
import java.util.Scanner;

public class SortsDriver {
    public static void main(String[] args) {
      Sorts s = new Sorts();
      Scanner feed = new Scanner(System.in);
      
      // Ask and get designated sorting mehtod(s)
      System.out.println("Choose one letter pertaining to sorting method (m, q, i, r, or a): ");
      String sortMethod = feed.nextLine();

      // Ask and get designated array length
      System.out.println("Now choose the length of the Array: ");
      String sizeString = feed.nextLine();
        // Parse string taken from nextLine(), for int
      int sizeInteger = Integer.parseInt(sizeString);

      Random mizer = new Random();
      int[] ray = new int[sizeInteger];
      for (int i = 0; i < ray.length; i++){
        ray[i] = mizer.nextInt();
      }

     
      //---------- Code for execution of sort (if statements)------------
      // All
      if (sortMethod.contains("a")){
        int mergeS[] = ray;
        int insertS[] = ray;
        int quickS[] = ray;
        int radixS[] = ray;

        s.mergeSort(mergeS, 0, ray.length);
        s.insertionSort(insertS, 0, ray.length);
        s.quickSort(quickS, 0, ray.length);
        s.radixSort(radixS);

        if (s.getComparisonCount() <= 20){
          System.out.println(ray);
          
          System.out.println(mergeS);
          System.out.println(insertS);
          System.out.println(quickS);
          System.out.println(radixS);
        }
        s.resetComparisonCount();
      }

      // Merge
      else if (sortMethod.contains("m")){
        int preSort[] = ray;

        s.mergeSort(ray, 0, ray.length);

        if (s.getComparisonCount() <= 20){
          System.out.println(preSort);
          System.out.println(ray);
        }
        s.resetComparisonCount();
      }

      // Insertion
      else if (sortMethod.contains("i")){
        int preSort[] = ray;

        s.insertionSort(ray, 0, ray.length);

        if (s.getComparisonCount() <= 20){
          System.out.println(preSort);
          System.out.println(ray);
        }
        s.resetComparisonCount();
      }

      // Quick
      else if (sortMethod.contains("q")){
        int preSort[] = ray;

        s.quickSort(ray, 0, ray.length);

        if (s.getComparisonCount() <= 20){
          System.out.println(preSort);
          System.out.println(ray);
        }
        s.resetComparisonCount();
      }

      // (LSD) Radix
      else if (sortMethod.contains("r")){
        int preSort[] = ray;

        s.radixSort(ray);

        if (s.getComparisonCount() <= 20){
          System.out.println(preSort);
          System.out.println(ray);
        }
        s.resetComparisonCount();
      }
    
    }
}
