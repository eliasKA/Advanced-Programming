package programs;

import java.io.PrintStream;
import java.util.Scanner;

import implementations.List;
import implementations.Number;
import implementations.Set;
import specifications.NumberInterface;

public class MainTest {

	Scanner in;
	PrintStream out;

	MainTest() {
		in = new Scanner(System.in);
		out = new PrintStream(System.out);
	}

	void start() {
		List<NumberInterface> list = new List<NumberInterface>();
		if(list.isEmpty()){out.println(list.size());}
		
		list.insert(new Number("1"));
		list.insert(new Number("0"));
		list.insert(new Number("6"));
		list.insert(new Number("100"));
		list.insert(new Number("20000"));

		List<NumberInterface> list1=(List<NumberInterface>) list.clone();
		list1.goToFirst();
		do {
			out.print(list1.retrieve().toString()+" ");
		} while (list1.goToNext());
		
		if(!list1.isEmpty()){out.println(list1.size());}
		
		Set<NumberInterface> set = new Set<NumberInterface>();
		set.add(new Number("1"));
		set.add(new Number("2"));
		set.add(new Number("4"));
		set.add(new Number("8"));
		set.add(new Number("1"));
		
		out.println(set.toString());
		out.println(set.clone().toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainTest().start();
	}

}