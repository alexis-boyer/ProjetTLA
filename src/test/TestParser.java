package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import ex.Lexer;
import ex.Parser;
import ex.SourceReader;
import ex.Token;

public class TestParser {

	@Test
	public void test() {
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

		assertTrue("Le parser détécte une erreur dans un code correct", testParser(reader));

		reader = new SourceReader("forward 15" + "\n"
				+"procedure procedure1 [ " + "\n"
				+"	right 15" + "\n"
				+"	left 12" + "\n"
				+"	reapeat 15[" + "\n"
				+"		color 3" + "\n"
				+"		forward forward"  + "\n"
				+"		forward 12"  + "\n"
				+"		forward 12"  + "\n"
				+"	]"  + "\n"
				+"]"  + "\n"
				+"call procedure1"  + "\n" );

		assertTrue("Le parser n'a pas détécter l'érreur dans un code faux", !testParser(reader));

		reader = new SourceReader("forward 15" + "\n"
				+"procedure procedure1 [ " + "\n"
				+"	right 15" + "\n"
				+"	left 12" + "\n"
				+"	reapeat 15" + "\n"
				+"		color 3" + "\n"
				+"		forward 12"  + "\n"
				+"		forward 12"  + "\n"
				+"		forward 12"  + "\n"
				+"	]"  + "\n"
				+"]"  + "\n"
				+"call procedure1"  + "\n" );

		assertTrue("Le parser détécte une erreur dans un code correct", !testParser(reader));
	}

	public static boolean testParser(SourceReader sr) {
		ArrayList<Token> tokens = new Lexer().lexer(sr);
		try {
			new Parser().parser(tokens);
		} catch (Exception e) {
			return false;
		}
		System.out.println("------------------");
		return true;
	}

}
