package tm1pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sala {
	int n = 0;
	int m;
	ArrayList<Integer> v;
	ArrayList<Integer> w;

	int suma(ArrayList<Integer> v, ArrayList<Integer> w) {
		int nr = 0;
		ArrayList<Integer> z1 = new ArrayList<>();
		// ArrayList<Integer> z2 = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			// z1.add(v.get(i));
			// z2.add(v.get(i));
			nr = nr + w.get(i);
		}
		return Collections.min(v) * nr;

	}

	private int sumamax(ArrayList<Integer> v, ArrayList<Integer> w) {
		int max = 0;
		ArrayList<Integer> z2 = new ArrayList<>();
		ArrayList<Integer> z1 = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			z1.add(v.get(i));
			z2.add(w.get(i));

		}
		return max;

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File myObj = new File("0-sala.in");
		Scanner scan = new Scanner(myObj);
		int n = scan.nextInt();
		int m = scan.nextInt();
		Sala x = new Sala();
		x.v = new ArrayList<>();
		x.w = new ArrayList<>();

		x.m = m;
		int max = 0;
		while (n > 0) {
			if (m > 0) {
				x.v.add(scan.nextInt());
				x.w.add(scan.nextInt());
				m--;
			} else {
				for (int i = 0; i < x.m; i++) {
					int nr1 = x.v.get(i);
					int nr2 = x.w.get(i);
					x.v.set(i, m);
					x.w.set(i, m);

					if (x.suma(x.v, x.w) > max)
						max = x.suma(x.v, x.w);
					else {
						x.v.set(i, nr1);
						x.w.set(i, nr2);
					}

				}

			}
			// x.sumamax(v,w);
			n--;
		}
		scan.close();

		for (int i = 0; i < v.size() - 1; i++)
			for (int j = i + 1; j < v.size(); j++) {

				if (v.get(i) + w.get(i) < v.get(j) + w.get(j)) {
					int asc = v.get(i);
					v.set(i, v.get(j));
					v.set(j, asc);

					asc = w.get(i);
					w.set(i, w.get(j));
					w.set(j, asc);

				}
			}

		int nr = 0;

		FileWriter myWriter = new FileWriter("sala.out");

		String s = Long.toString(Collections.min(v) * nr);
		System.out.println(s);
		myWriter.write(s);
		myWriter.close();

	}

}
