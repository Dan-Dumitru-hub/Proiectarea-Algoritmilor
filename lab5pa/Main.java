package lab5pa;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
         ArrayList<ArrayList<Integer>> all = new ArrayList<>();
         //all.add();
      ArrayList<Integer>solutie= new ArrayList<Integer>();
  ArrayList<Integer> numere= new ArrayList<Integer>() ;
   boolean[] accept = null;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                accept = new boolean[n];
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
                for (ArrayList<Integer> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%d%c", arr.get(i), i + 1 == arr.size() ?
                                '\n' : ' ');
                    }
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<Integer> vector(int n){
 ArrayList<Integer> v = new ArrayList<Integer>();
 for(int i=1;i<n+1;i++)
    v.add(i);
return v;

        }
ArrayList<Integer> vector1(int n , int n1 , ArrayList<Integer> v ){
 ArrayList<Integer> v1 = new ArrayList<Integer>();
 for(int i=n;i<n1;i++)
    v1.add(v.get(i));
return v1;


}


        private ArrayList<ArrayList<Integer>> getResult1( ArrayList<Integer> v , int start , int end ) {
           

            // TODO: Construiti toate submultimele multimii {1, ..., N}.

            // Pentru a adauga o noua submultime:
            //   ArrayList<Integer> submultime;
            //   all.add(submultime);
     
    // Stop if we have reached the end of the array      
    if (end == v.size())  
        return all; 
      
    // Increment the end point and start from 0  
    else if (start > end)  
        getResult1(v, 0, end + 1); 
          
    // Print the subarray and increment the starting point  
    else
    { 
        ArrayList<Integer> v1 = new  ArrayList<Integer>();
        v1=vector1(start,end,v);
        
          all.add(v1);
 
        getResult1(v, start + 1, end); 
    } 
  
return all;}
        ArrayList<ArrayList<Integer>> sort ( ArrayList<ArrayList<Integer>>sol){
        	ArrayList<Integer> adj;
        	
        	for(int i=0;i<sol.size()-1;i++)
        		for(int j=i+1;j<sol.size();j++)
        		{if( sol.get(i).size() > sol.get(j).size() ) {
        			adj=sol.get(i);
        			sol.set(i, sol.get(j));
        			sol.set(j, adj);
        		}
        			
        		}
	
			return sol;
        	
        }
        
        
ArrayList<Integer> copie(ArrayList<Integer> sol,int contor){
    ArrayList<Integer> sol1=new ArrayList<Integer>();
    for(int i=0;i<contor;i++)
        sol1.add(sol.get(i));
    return sol1;
}

private  void backtrack(int level, int n, int contor) {
        if(level==n){
            if(contor==0){
                //System.out.println("Vida!");
                return;
            }
           // for(int i=0; i<contor; i++){
              //  System.out.print(solutie[i]+" ");
           // }
           ArrayList<Integer> sol=copie(solutie , contor);
            all.add(sol);
            System.out.println(all);
            return;
        }
        
        solutie.set(contor,numere.get(level));
        backtrack(level+1, n, contor+1);
        backtrack(level+1, n, contor);
    }


 private ArrayList<ArrayList<Integer>> getResult(){
        n=4;
        accept = new boolean[n];
        for(int i=0; i<n; i++){
            accept[i] = true;
            numere.add(i+1);
            solutie.add(i+1);
        }


        
        backtrack(0, n, 0);
        sort(all);
return all;
}




        public void solve() {
           // readInput();
          //  ArrayList<Integer> v = new  ArrayList<Integer> ();
           // v=vector(n);
           // writeOutput(getResult1(v,0,0));
//writeOutput(
		getResult();
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
