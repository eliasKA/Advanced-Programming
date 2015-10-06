package programs;

import java.io.PrintStream;
import java.util.Scanner;

import implementations.List;
import implementations.Number;
import specifications.NumberInterface;

public class MainTest {

	Scanner in;
	PrintStream out;
	
	MainTest(){
		in = new Scanner(System.in);
		out = new PrintStream(System.out);
	}
	
	void start(){
	List<NumberInterface> list =new List<NumberInterface>();
	list.insert(new Number("1"));
	list.insert(new Number("3"));
	list.insert(new Number("2"));
	
	list.goToLast();
	out.println(list.retrieve().toString());
	list.goToFirst();
	out.println(list.retrieve().toString());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainTest().start();
	}

}