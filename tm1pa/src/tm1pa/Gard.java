package tm1pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Gard {

//int x ()
	public static void main(String[] args) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("2-gard.in"));
			String thisLine = null;
			thisLine = reader.readLine();
			String s1[] = thisLine.split(" ");
			ArrayList<Integer> v;
			ArrayList<Integer> w;
			int nr = 0;

			int n = Integer.parseInt(thisLine);
			// System.out.println(n);
			thisLine = reader.readLine();
			s1 = thisLine.split(" ");
			int i = 0;
			// Gard g = new Gard();
			v = new ArrayList<>();
			w = new ArrayList<>();

			v.add(Integer.parseInt(s1[0]));
			// System.out.println(v);
			w.add(Integer.parseInt(s1[1]));
			int ok = 1;
			// System.out.println(v);
			while (n != i + 1) {
			//	System.out.println(v);

				// v.add(scan.nextInt());
				// w.add(scan.nextInt());
				thisLine = reader.readLine();
				s1 = thisLine.split(" ");

				int a = Integer.parseInt(s1[0]);
				int b = Integer.parseInt(s1[1]);

				v.add(a);
				w.add(b);

				i++;

			}
			for ( i = 0; i < v.size() - 1; i++)
				for (int j = i + 1; j < v.size(); j++) {

					if (v.get(i)  > v.get(j) ) {
						int asc = v.get(i);
						v.set(i, v.get(j));
						v.set(j, asc);

						asc = w.get(i);
						w.set(i, w.get(j));
						w.set(j, asc);

					}
				}
			
			//System.out.println(v);
			for (i = 0; i < v.size(); i++)
				for (int j = 0; j < v.size() ; j++) {
					if(j==i)
						continue;
					if(v.get(j)== 0&&w.get(j)==0)
						continue;

					if (v.get(j) < v.get(i) && w.get(i) < w.get(j)) {

						v.set(i,0);
						w.set(i,0);
						nr++;
						// break;
					}

				}

			System.out.println(nr);
			FileWriter myWriter = new FileWriter("gard.out");

			String s = Long.toString(nr);

			myWriter.write(s);
			myWriter.close();

		} catch (FileNotFoundException e) {
		}

	}

}
