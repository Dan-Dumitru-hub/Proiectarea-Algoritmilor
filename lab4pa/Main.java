package lab4pa;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        private final static int MOD = 1000000007;

        int n;
        char[] expr1;
      
        private void readInput() {
         
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                String s = sc.next().trim();
                s = " " + s;
                expr1 = s.toCharArray();
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
        	n=7;
        	char [] expr= new char[n];
        	expr[0]='T';
        	expr[1]='&';
        	expr[2]='F';
        	expr[3]='^';
        	expr[4]='F';
        	expr[5]='^';
        	expr[6]='T';
        			
int s=0;
int [][]t=new int[n][n];

int [][]f=new int[n][n];
char cv='|';
char cv1='^';
char cv2='&';

for(int i = 0;i<n;i++){
if(expr[i] == 'T')
    t[i][i]=1;
 
 else t[i][i]=0;

if(expr[i]== 'F')
    f[i][i]=1;
 else f[i][i]=0;
}
int d=0;

for(int i = 0;i<n-1;i++)
    for(int j=i+1;j<n;j++){
    	d=s;
        s=0;
        if(expr[i] == cv2){
    
for(int k=1;k<=j-1;k++)
    s=s +t[i][k] * t[k+1][j];
t[i][j]= s;
s=0;
for(int k=1;k<=j-1;k++)
 s=s +t[i][k] * f[k+1][j]+f[i][k] * t[k+1][j]  + f[i][k] * f[k+1][j] ;
f[i][j]= s;
 }



        if(expr[i]==cv){
for(int k=1;k<=j-1;k++)
     s=s +t[i][k] * f[k+1][j]+f[i][k] * t[k+1][j]  + t[i][k] * t[k+1][j] ;
t[i][j]= s;
s=0;
for(int k=1;k<=j-1;k++)
 s=s  + f[i][k] * f[k+1][j] ;
f[i][j]= s;

}


if(expr[i]==cv1){
for(int k=1;k<=j-1;k++)
   s=s +t[i][k] * f[k+1][j] + f[i][k] * t[k+1][j];
t[i][j]= s;
s=0;
for(int k=1;k<=j-1;k++)
 s=s + f[i][k] * f[k+1][j] +t[i][k] * t[k+1][j] ;
f[i][j]= s;}


}


            return t[0][n-1]%MOD;
        }

        public void solve() {
           // readInput();
           // writeOutput(
        	System.out.println(getResult());
        	
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
