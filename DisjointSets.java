import java.io.*;
import java.util.*;


/****************************
 *
 * COMP251 template file
 *
 * Assignment 2, Question 1
 *
 *****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;

    /* constructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n > 0) {
            par = new int[n];
            rank = new int[n];
            for (int i = 0; i < this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString() {
        int pari, countsets = 0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i = 0; i < this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari] == null) {
                setstrings[pari] = String.valueOf(i);
                countsets += 1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i = 0; i < this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find representative of element i */
    public int find(int i) {

        int[] parTemp = new int[par.length];    //temp array created, can be modified harmlessly

        System.arraycopy(par, 0, parTemp, 0, par.length);

        if (parTemp[i] == i)
            return i;

        parTemp[i] = find(parTemp[i]);          //recursively find parent
        return parTemp[i];

    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {

        int setI = find(i);                     //parent of i and j
        int setJ = find(j);

        if (setI == setJ) {                     //if elements have the same parent
            return setI;                        //do nothing, return parent of i

        } else if (rank[setI] <= rank[setJ]) {  //if equal ranks or i less than

            for (int k = 0; k < par.length; k++) {
                if (par[k] == setI) {           //if in set j, now in set i
                    par[k] = setJ;
                }
            }
                if (rank[setI] == rank[setJ]) { //if ranks are equal
                    rank[setJ]++;               //new rank increments
                }
                return rank[setJ];

        } else {                                //rank set j > rank set i

            for (int k = 0; k < par.length; k++) {
                if (par[k] == setJ) {           //if in set j, now in set i
                    par[k] = setI;
                }
            }
            return rank[setI];
        }
    }

    public static void main(String[] args) {

        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2, 1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4, 5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3, 1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2, 4);
        System.out.println(myset);

    }

}
