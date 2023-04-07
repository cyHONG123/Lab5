/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import static java.io.ObjectInputFilter.merge;
import static java.util.Collections.swap;

public class Sorting{
int a;
public int[] getb(int[] a) {
    int[] k = Arrays.copyOf(a, a.length);
    Arrays.sort(k);
    return k;
}
public int[] getc(int[] a) {
    int[] k = Arrays.copyOf(a, a.length);
    Arrays.sort(k);
        int[] c = new int[0];
        for (int o = k.length-1; o > -1; o--) {
            int[] c1 = new int[c.length+1];
            for(int q =0; q<c.length;q++ ) {
                c1[q] = c[q];
            }
            c1[a.length-o-1] = k[o];
            c=c1;
        }
        return c;
}
public int[] getd(int[] a) {
    int[] b = Arrays.copyOf(a, a.length);
    for (int y = 0; y< b.length*0.1; y++) {
        Random u = new Random();
        int d1 = u.nextInt(b.length-1);
        int d2 = u.nextInt(b.length-1);
        while (d1==d2) {
            d1 = u.nextInt(b.length-1);
            d2 = u.nextInt(b.length-1);
        }
        int k = b[d1];
        b[d1] = b[d2];
        b[d2] = k;
    }
    return b;
}
public int compareTo(int a, int b) {
    if (a>b) {
        return 1;
    } else if (b>a) {
        return -1;
    }
    return 0;
}
public static void swap(int[] a, int index1, int index2) {
    int k = a[index1];
    a[index1] = a[index2];
    a[index2] = k;
}
void bubblesort (int[] a)  {
    for (int i = 0; i< a.length-1; i++) {
        for (int j = 0; j<a.length-1; j++) {
            if (compareTo(a[j], a[j+1]) >0) {
                swap(a, j, j+1);
            }
        }
    }
}
void selectionsort (int[] a) {
    /*This Selectionsort method is cite from ppt in February 28, 2023*/
    for (int i = 0; i< a.length-1; i++) {
        int lowindex = i;
        for (int j = a.length-1; j>i; j--) {
            if (compareTo(a[j], a[lowindex]) <0) {
                lowindex = j;
            }
        }
        swap(a, i, lowindex);
    }
}
//Below quicksort is cite from below material which is post on blackboard CSC172 as lecture code
    /** Source code example for "A Practical Introduction to Data
     Structures and Algorithm Analysis, 3rd Edition (Java)"
     by Clifford A. Shaffer
     Copyright 2008-2011 by Clifford A. Shaffer
     */
    int THRESHOLD = 0;
    int ARRAYSIZE = 0;
     void quicksort(int[] A) {
        qsort(A, 0, A.length-1);
        THRESHOLD = 0;
        ARRAYSIZE = 0;
    }
    void qsort(int[] A, int i, int j) {      // Quicksort
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        swap(A, pivotindex, j);       // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i-1, j, A[j]);
        swap(A, k, j);              // Put pivot in place
        if ((k-i) > 1) qsort(A, i, k-1);   // Sort left partition
        if ((j-k) > 1) qsort(A, k+1, j);   // Sort right partition
    }
    int partition(int[] A, int l, int r, int pivot) {
        do {                 // Move bounds inward until they meet
            while ((compareTo(A[++l],pivot))<0);
            while ((r!=0) && (compareTo(A[--r], pivot)>0));
            swap(A, l, r);       // Swap out-of-place values
        } while (l < r);              // Stop when they cross
        swap(A, l, r);         // Reverse last, wasted swap
        return l;      // Return first position in right partition
    }
    int findpivot(int[] A, int i, int j)
    { return (i+j)/2; }
    //
//Below mergesort is cite from below material which is post on blackboard CSC172 as lecture code
    /** Source code example for "A Practical Introduction to Data
     Structures and Algorithm Analysis, 3rd Edition (Java)"
     by Clifford A. Shaffer
     Copyright 2008-2011 by Clifford A. Shaffer
     */
    @SuppressWarnings("unchecked") // Generic array allocation
    void mergesort(int[] A) {
        int[] temp = new int[A.length];
        merge(A, temp, 0, A.length-1);
        THRESHOLD = 0;
        ARRAYSIZE = 0;
    }
    void merge(int[] A, int[] temp, int l, int r) {
        int mid = (l+r)/2;                // Select midpoint
        if (l == r) return;               // List has one element
        merge(A, temp, l, mid);   // Mergesort first half
        merge(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++)          // Copy subarray to temp
            temp[i] = A[i];
        // Do the merge operation back to A
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1)              // Left sublist exhausted
                A[curr] = temp[i2++];
            else if (i2 > r)              // Right sublist exhausted
                A[curr] = temp[i1++];
            else if (compareTo(temp[i1], temp[i2])<0) // Get smaller
                A[curr] = temp[i1++];
            else A[curr] = temp[i2++];
        }
    }
    //
void insertionsort (int[] a) {
    /*This Insertionsort method is cite from ppt in February 28, 2023*/
    for (int t = 1; t<a.length; t++) {
        for (int i = t; (i>0)&&compareTo(a[i], a[i-1])<0; i--) {
            swap(a, i, i-1);
        }
    }
}
 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        Sorting s = new Sorting();
        try {
            FileWriter v = new FileWriter("src/a.txt");
            v = new FileWriter("src/b.txt");
            v = new FileWriter("src/c.txt");
            v = new FileWriter("src/d.txt");
        }catch (
                IOException e) {
            System.out.println("IOException");
        }
        try {
            FileWriter out;
            while (t.hasNextLine()) {
                String ai = t.nextLine();
                String[] sp = ai.split(" ");
                In in = new In("src/" + sp[0]);
                // Storing file input in an array
                int[] a = in.readAllInts();
                int[] b = s.getb(a);
                int[] c = s.getc(a);
                int[] d = s.getd(b);
                // TODO: Generate 3 other arrays, b, c, d where
                // b contains sorted integers from a (You can use Java Arrays.sort() method)
                // c contains all integers stored in reverse order
                // (you can have your own O(n) solution to get c from b
                // d contains almost sorted array
                //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.
                //TODO:
                // Read the second argument and based on input select the sorting algorithm
                //  * 0 = Arrays.sort() (Java Default)
                //  * 1 = Bubble Sort
                //  * 2 = Selection Sort
                //  * 3 = Insertion Sort
                //  * 4 = Mergesort
                //  * 5 = Quicksort
                //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those.
                // For runtime and priting, you can use the same code from Lab 4 as follows:
                // TODO: For each array, a, b, c, d:
                String algorithmUsed = "";
                for (int k = 0; k < 4; k++) {
                    int[] sov = a;
                    String arrayUsed = "a";
                    out = new FileWriter("src/non.txt");
                    if (k==0) {
                        sov = a;
                        arrayUsed = "a";
                        out = new FileWriter("src/a.txt", true);
                    } else if (k == 1) {
                        sov = b;
                        arrayUsed = "b";
                        out = new FileWriter("src/b.txt", true);
                    } else if (k == 2) {
                        sov = c;
                        arrayUsed = "c";
                        out = new FileWriter("src/c.txt", true);
                    } else if (k == 3) {
                        sov = d;
                        arrayUsed = "d";
                        out = new FileWriter("src/d.txt", true);
                    }
                /*for (int o : sov) {
                    System.out.print(o+",");
                }*/
                    Stopwatch timer = new Stopwatch();
                    if (sp[1].equals("0")) {
                        Arrays.sort(sov);
                        algorithmUsed = "Arrays.sort() (Java Default)";
                    } else if (sp[1].equals("1")) {
                        s.bubblesort(sov);
                        algorithmUsed = "Bubble Sort";
                    } else if (sp[1].equals("2")) {
                        s.selectionsort(sov);
                        algorithmUsed = "Selection Sort";
                    } else if (sp[1].equals("3")) {
                        s.insertionsort(sov);
                        algorithmUsed = "insertion sort";
                    } else if (sp[1].equals("4")) {
                        s.mergesort(sov);
                        algorithmUsed = "Mergesort";
                    } else if (sp[1].equals("5")) {
                        s.quicksort(sov);
                        algorithmUsed = "Quicksort";
                    }
                    // TODO: Perform Sorting and store the result in an  array
                    double time = timer.elapsedTimeMillis();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    //TODO: Replace with your own netid
                    String netID = "chong10";
                    //TODO: Replace with the algorithm used
                    //TODO: Replace with the  array used
                /*for (int o : sov) {
                    System.out.print(o+",");
                }*/
                    PrintWriter writer = new PrintWriter(out);
                    StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, sp[0]);
                    // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
                    writer.print(algorithmUsed+"  ");
                    writer.print(arrayUsed+"  ");
                    writer.print(time+"  ");
                    writer.print(timeStamp+"  ");
                    writer.print(netID+"  ");
                    writer.print(sp[0]);
                    writer.println("");
                    writer.flush();
                    writer.close();
                }
            }
        } catch (
                IOException e) {
            System.out.println("IOException");
        }
    }

} 


