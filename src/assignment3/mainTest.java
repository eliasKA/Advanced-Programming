package assignment3;

import java.io.PrintStream;
import java.util.Iterator;

public class mainTest {

	PrintStream out;
	mainTest(){
		out = new PrintStream(System.out);
	}
	
	void test(){
		BSTree<IdentifierInterface> tree= new BSTree<IdentifierInterface>();
		
		Identifier a = new Identifier("a");
		Identifier b = new Identifier("b");
		Identifier c = new Identifier("c");
		Identifier d = new Identifier("d");
		Identifier x = new Identifier("x");
		Identifier y = new Identifier("y");
		Identifier z = new Identifier("z");
		
		tree.add(y);
		tree.add(d);
		tree.add(b);
		tree.add(x);
		tree.add(c);
		tree.add(a);
		tree.add(z);
		tree.add(a);
	

		out.println(tree.toString());
		
		Iterator<IdentifierInterface> up = tree.clone().ascendingIterator();
		Iterator<IdentifierInterface> down = tree.clone().descendingIterator();
		
		while(up.hasNext()){
			out.print(up.next() + "-");
		}
		
		out.println();
		
		while(down.hasNext()){
			out.print(down.next() + "-");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainTest().test();
	}

}
