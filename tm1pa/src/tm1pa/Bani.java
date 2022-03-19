package tm1pa;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Bani {
	long nr = 0;
	int n1;
	
	
	

	int  t2(int ultim,int i) {
		//System.out.println(i);
		
		
		if(ultim==10) {
			
		t2(50,i+1);
		t2(100,i+1);}

		if(ultim==50) {
		t2(10,i+1);
		t2(200,i+1);}

		if(ultim==100) {
		t2(10,i+1);
		t2(100,i+1);}

		if(ultim==200) {
		t2(50,i+1);
		t2(100,i+1);
		t2(500,i+1);
		}

		if(ultim==500) {
		t2(200,i+1);
		t2(10,i+1);}
		if(n1==i+1) {
			nr++;
		return 0;}
		return 0;
		
	

			
		}
	 private long fastPow(long base, int exponent, int mod) {
         // TODO: Calculati (base^exponent) modulo mod in O(log exponent).

         // base^0 = 1
         if (exponent == 0)  {
             return 1;
         }

         long aux = 1;
         while (exponent != 1) {
             if (exponent % 2 == 0) {   // par
                 base = (1L * base * base) % (1L * mod);
                 exponent /= 2;
             } else {                   // impar
                 aux = (1L * aux * base) % (1L * mod);
                 exponent--;
             }
         }

         return (1L * aux * base*5 ) % (1L * mod);
     }
	 


	public static void main(String[] arg) throws IOException {
		Bani cv = new Bani();
		//cv.v = new ArrayList<Integer>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("20-bani.in"));
			String thisLine = null;
			thisLine = reader.readLine();
			String s1[] = thisLine.split(" ");
			
		//	File myObj = new File("20-bani.in");
		//	Scanner scan = new Scanner(myObj);

			int x = Integer.parseInt(s1[0]);
			int n = Integer.parseInt(s1[1]);
			//n=1000;
			//x=2;
     //  cv.n1=n;
			if (x == 1) {
				
				cv.nr=5;
				int i=0;
				
				
				cv.nr=cv.fastPow(2,n-1,1000000007);
			} else {System.out.println(x);
				
		
				cv.t2(10, 0);
				//System.out.println(cv.nr);
				cv.t2(50,0);
				cv.t2(100, 0);
				
				cv.t2(200,0);
				
				cv.t2(500, 0);
				
				
				
				
			}
			try {

				FileWriter myWriter = new FileWriter("bani.out");

				String s = Long.toString(cv.nr % 1000000007);
				System.out.println(s);
				myWriter.write(s);
				myWriter.close();
			} catch (IOException e) {
			}

		} catch (FileNotFoundException e) {
		}
		
	}

}
