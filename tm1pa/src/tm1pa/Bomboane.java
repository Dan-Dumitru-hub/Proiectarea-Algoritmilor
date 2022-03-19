package tm1pa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Bomboane {
	ArrayList<Integer> v=new ArrayList<>();
	ArrayList<Integer> w=new ArrayList<>();
	int nr;
	int n;
	//int m;

	int numara(int i,int m) {
	
		if(m==0 && i==n) {
		
			nr++;
		return 0;}
		
		if(n==i) {
			return 0;
		}
		
		int j=v.get(i);
		while(j<=w.get(i)) {
			int m1=m;
		
			if(m-j>=0)
			numara(i+1,m-j);
			j++;
		    m=m1;
		
		}
		
		
		return i;
		
	}

	public static void main(String[] args) throws IOException {
	
		BufferedReader reader =new BufferedReader(new FileReader("5-bomboane.in"));
		String thisLine = null;
	      int n=0;
	      int m=0;
	      Bomboane g =new Bomboane();
	      thisLine=reader.readLine();
	      String s1[] = thisLine.split(" "); 
	      g.n=Integer.parseInt(s1[0]);
	      m=Integer.parseInt(s1[1]);
	      int i=0;
	      while(i<g.n) {
	    	  thisLine=reader.readLine();
		       s1 = thisLine.split(" "); 
		       //System.out.println(thisLine);
	    	  g.v.add(Integer.parseInt(s1[0]));
	    	  g.w.add(Integer.parseInt(s1[1]));
	    	  
	    	  i++;
	      }
	      //System.out.println(g.v);
		
		g.numara(0, m);

		//System.out.println(s1[1]);
		try{
			FileWriter myWriter = new FileWriter("bomboane.out");

			String s = Long.toString(g.nr);
			  //System.out.println(s);
			myWriter.write(s);
			myWriter.close();
		}
		 catch (IOException e) {
			}
	}

}
