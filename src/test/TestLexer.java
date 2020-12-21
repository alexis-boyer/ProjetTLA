package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ex.Lexer;
import ex.SourceReader;
import ex.Token;
import ex.TokenClass;

public class TestLexer {

	@Test
	public void test() {

		SourceReader reader = new SourceReader("forward 15" + "\n"
				+"procedure procedure1 [ " + "\n"
				+"	right 15" + "\n"
				+"	left 12" + "\n"
				+"	reapeat 15[" + "\n"
				+"		color 3" + "\n"
				+"	]"  + "\n"
				+"]"  + "\n"
				+"call procedure1"  + "\n" );
		Lexer lexer = new Lexer();

		ArrayList<Token> tokens = lexer.lexer(reader);

		if(tokens.get(0).getCl() != TokenClass.forward){
			fail("Le token forward et pris pour : "+ tokens.get(0).getCl());
		}
		if(tokens.get(1).getCl() != TokenClass.entier && tokens.get(1).getValue() != "15"){
			fail("Le token entier(15) et pris pour : "+ tokens.get(1));
		}
		if(tokens.get(2).getCl() != TokenClass.procedure){
			fail("Le token forward et pris pour : "+ tokens.get(2));
		}
		if(tokens.get(3).getCl() != TokenClass.ident && tokens.get(3).getValue() != "procedure1"){
			fail("Le token ident(procedure1) et pris pour : "+ tokens.get(3));
		}
		if(tokens.get(4).getCl() != TokenClass.right){
			fail("Le token right et pris pour : "+ tokens.get(4));
		}
		if(tokens.get(5).getCl() != TokenClass.entier && tokens.get(5).getValue() != "15"){
			fail("Le token entier(15) et pris pour : "+ tokens.get(5));
		}
		if(tokens.get(6).getCl() != TokenClass.left){
			fail("Le token left et pris pour : "+ tokens.get(6));
		}
		if(tokens.get(7).getCl() != TokenClass.entier && tokens.get(7).getValue() != "15"){
			fail("Le token entier(15) et pris pour : "+ tokens.get(7));
		}
		if(tokens.get(8).getCl() != TokenClass.reapeat){
			fail("Le token reapeat et pris pour : "+ tokens.get(8));
		}
		if(tokens.get(9).getCl() != TokenClass.entier && tokens.get(9).getValue() != "15"){
			fail("Le token entier(15) et pris pour : "+ tokens.get(9));
		}
		if(tokens.get(10).getCl() != TokenClass.ouvreBlock){
			fail("Le token ouvreBlock et pris pour : "+ tokens.get(10));
		}
	}

}
