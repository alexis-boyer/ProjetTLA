package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import ex.Lexer;
import ex.Parser;
import ex.SourceReader;
import ex.Token;

public class Test {

	public static void main(String[] args) {
		SourceReader reader = new SourceReader("forward 15" + "\n"
												+"procedure procedure1 [ " + "\n"
												+"	right 15" + "\n"
												+"	left 12" + "\n"
												+"	reapeat 15[" + "\n"
												+"		color 3" + "\n"
												+"		forward 12"  + "\n"
												+"		forward 12"  + "\n"
												+"		forward 12"  + "\n"
												+"	]"  + "\n"
												+"]"  + "\n"
												+"call procedure1"  + "\n" );
		testParser(reader);

	}

	private static void test(SourceReader entree) {
		System.out.println();
		ArrayList<Token> tokens = new Lexer().lexer(entree);
		for(Token t: tokens) {
			System.out.println(t);
		}
	}

	public static void testParser(SourceReader sr) {
		ArrayList<Token> tokens = new Lexer().lexer(sr);
		try {
			new Parser().parser(tokens);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------");
	}

}
