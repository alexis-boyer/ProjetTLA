package ex;

import java.util.ArrayList;

public class Lexer {

    static Integer transitions[][] = {
            //               a		c		d		e		f		g		h		i		l		o		p		r		t		u		w	  blanc	  lettre chiffre	[		]	   autre
            /*  0 */    {     47,    27,     47,     47,     1,      47,     47,     47,     9,      47,     37,     14,     47,     47,     47,      0,     47,     48,    109,    110,   null },
            /*  1 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,       2,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /*  2 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,      3,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /*  3 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,      4,    101,     47,     47,    101,   null,   null },
            /*  4 */    {      5,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /*  5 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,      6,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /*  6 */    {     47,    47,      7,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /*  7 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    102,     47,     47,   null,   null,   null },
            /*  8 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    102,     47,     47,   null,   null,   null },
            /*  9 */    {     47,    47,     47,     10,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 10 */    {     47,    47,     47,     47,    11,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 11 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     12,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 12 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    103,     47,     47,   null,   null,   null },
            /* 13 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 14 */    {     47,    47,     47,     20,    47,      47,     47,     15,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 15 */    {     47,    47,     47,     47,    47,      16,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 16 */    {     47,    47,     47,     47,    47,      47,     17,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 17 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     18,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 18 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    103,     47,     47,   null,   null,   null },
            /* 19 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 20 */    {     21,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 21 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     22,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 22 */    {     47,    47,     47,     23,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 23 */    {     24,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 24 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     25,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 25 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    105,     47,     47,   null,   null,   null },
            /* 26 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 27 */    {     28,    47,     47,     47,    47,      47,     47,     47,    47,      32,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 28 */    {     47,    47,     47,     47,    47,      47,     47,     47,    29,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 29 */    {     47,    47,     47,     47,    47,      47,     47,     47,    30,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 30 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    106,     47,     47,   null,   null,   null },
            /* 31 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 32 */    {     47,    47,     47,     47,    47,      47,     47,     47,    33,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 33 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      34,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 34 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     35,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 35 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    107,     47,     47,   null,   null,   null },
            /* 36 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 37 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     38,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 38 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      39,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 39 */    {     47,    40,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 40 */    {     47,    47,     47,     41,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 41 */    {     47,    47,     42,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 42 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     43,     47,    101,     47,     47,    101,   null,   null },
            /* 43 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     44,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 44 */    {     47,    47,     47,     45,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 45 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    108,     47,     47,   null,   null,   null },
            /* 46 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 47 */    {     47,    47,     47,     47,    47,      47,     47,     47,    47,      47,     47,     47,     47,     47,     47,    101,     47,     47,    101,   null,   null },
            /* 48 */    {   null,  null,   null,   null,   null,   null,   null,   null,   null,   null,   null,   null,   null,   null,   null,    111,   null,     48,    111,   null,   null }
            //          	a		c		d		e		f		g		h		i		l		o		p		r		t		u		w	  blanc	  lettre chiffre	[		]	   autre
            // 101 accepte ident                        (goBack : oui)
            // 102 accepte forward                      (goBack : non)
            // 103 accepte left                         (goBack : non)
            // 104 accepte right                        (goBack : non)
            // 105 accepte reapeat                      (goBack : non)
            // 106 accepte call                         (goBack : non)
            // 107 accepte color                        (goBack : non)
            // 108 accepte procedure                    (goBack : non)
            // 109 accepte ouvreBlock                   (goBack : non)
            // 110 accepte fermeBlock                   (goBack : non)
            // 111 accepte entier                       (goBack : oui)

    };

    static final int ETAT_INITIAL = 0;

    private int indiceSymbole(Character c) {
        if ( c == null || Character.isWhitespace(c)) return 15;
        if (c == 'a') return 0;
        if (c == 'c') return 1;
        if (c == 'd') return 2;
        if (c == 'e') return 3;
        if (c == 'f') return 4;
        if (c == 'g') return 5;
        if (c == 'h') return 6;
        if (c == 'i') return 7;
        if (c == 'l') return 8;
        if (c == 'o') return 9;
        if (c == 'p') return 10;
        if (c == 'r') return 11;
        if (c == 't') return 12;
        if (c == 'u') return 13;
        if (c == 'w') return 14;
        if (c == '[') return 18;
        if (c == ']') return 19;
        if (Character.isLetter(c)) return 16;
        if (Character.isDigit(c)) return 17;
        return 20;
    }

    public ArrayList<Token> lexer(SourceReader sr) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        String buf="";
        int etat = ETAT_INITIAL;
        while (true) {
            Character c = sr.lectureSymbole();
            Integer e = transitions[etat][indiceSymbole(c)];
            if (e == null) {
                System.out.println(" pas de transition depuis état " + etat + " avec symbole " + c);
                return new ArrayList<Token>(); // renvoie une liste vide
            }
            if (e >= 100) {
                if (e == 101) {
                    tokens.add(new Token(TokenClass.ident, buf));
                    sr.goBack();
                } else if (e == 102) {
                    tokens.add(new Token(TokenClass.forward));
                } else if (e == 103) {
                    tokens.add(new Token(TokenClass.left));
                } else if (e == 104) {
                    tokens.add(new Token(TokenClass.right));
                } else if (e == 105) {
                    tokens.add(new Token(TokenClass.reapeat));
                } else if (e == 106) {
                    tokens.add(new Token(TokenClass.call));
                } else if (e == 107) {
                    tokens.add(new Token(TokenClass.color));
                } else if (e == 108) {
                    tokens.add(new Token(TokenClass.procedure));
                } else if (e == 109) {
                    tokens.add(new Token(TokenClass.ouvreBlock));
                } else if (e == 110) {
                    tokens.add(new Token(TokenClass.fermeBlock));
                } else if (e == 111) {
                    tokens.add(new Token(TokenClass.entier, buf));
                    sr.goBack();
                }
                etat = 0;
                buf = "";
            } else {
                etat = e;
                if (etat>0) buf = buf + c;
            }
            if (c==null) break;
        }
        return tokens;
    }
}
