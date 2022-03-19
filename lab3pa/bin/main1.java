import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main1 {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, m;
        int[] dist;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                m = sc.nextInt();
                dist = new int[n];
                for (int i = 0; i < n; i++) {
                    dist[i] = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            // TODO: Aflati numarul minim de opriri necesare pentru
            // a ajunge la destinatie.
n=5;
m=10;
 int [] dist = new int[n];
dist[0]=1;
dist[1]=3;
dist[2]=1;
dist[3]=13;
dist[4]=20;
        	

        	

int i=0;
int j;
int w=m-dist[0];
int x=0;

/*
if(dist[n-1]<=m)
return 0;

s=0;
int cv=m;

for(i=0;i<n;i++){

for(j=i+1;j<n;j++){

if(dist[j]-dist[i]<cv){
cv=cv-dist[i];
break;}
if(dist[j]-dist[i]>=cv)
{
s++;
cv=m;
i=j-1;
break;}

}}**/

            int s=0;
            int k=0;
            int adj;
  for(i=0;i<n-1;i++) {
	  for(j=i+1;j<n;j++) 
		  if(Math.abs(dist[i]) < Math.abs(dist[j]) ) {
			  adj=dist[i];
			  dist[i]=dist[j];
			  dist[j]=adj;
			  
		  }
	    	  
	  }  
  
  
  
  
	  
            
            
            
            
            
            
			return s;
        }

        public void solve() {
         //   readInput();
           // writeOutput(getResult());
            System.out.print(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}