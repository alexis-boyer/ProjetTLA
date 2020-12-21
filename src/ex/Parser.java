package ex;

import java.util.ArrayList;

public class Parser {

	private int pos;
    private int profondeur;
    private ArrayList<Token> tokens;

    public void parser(ArrayList<Token> tokens) throws Exception {
        this.tokens = tokens;
        pos = 0;
        S();
        System.out.println("Fin atteinte = " + (pos == tokens.size()));
    }

    private void S() throws Exception {

    	if (getTokenClass() == TokenClass.forward ||
                getTokenClass() == TokenClass.left ||
                getTokenClass() == TokenClass.right ||
                getTokenClass() == TokenClass.color) {
    		// production S -> (forward|left|right|color)AS

    		getToken();
    		profondeur++;
    		A();
    		S();
    		profondeur--;
    		return;
        }
    	else if (getTokenClass() == TokenClass.reapeat) {
    		getToken();
    		profondeur++;
    		B();
    		S();
    		profondeur--;
    		return;
    		// production S -> reapeat BS
        }
    	else if (getTokenClass() == TokenClass.procedure) {
    		getToken();
    		profondeur++;
    		C();
    		S();
    		profondeur--;
    		return;
    		// production S -> procedure CS
        }
    	else if (getTokenClass() == TokenClass.call) {
    		getToken();
    		profondeur++;
    		D();
    		S();
    		profondeur--;
    		return;
    		// production S -> call DS
        }
    	else if (isEOF() ||
    			getTokenClass() == TokenClass.fermeBlock) {
    		return;
    		// production S -> epsilon
        }
        throw new Exception("forward|left|right|color|reapeat|procedure|call|epsilon attendue");
    }

    private void A() throws Exception {

    	if (getTokenClass() == TokenClass.entier) {
    		// production A -> entier

    		getToken();
            return;
        }
        throw new Exception("entier attendu");
    }

    private void B() throws Exception {

    	if (getTokenClass() == TokenClass.entier) {
    		// production B -> entier E

    		getToken();
    		profondeur++;
    		E();
    		profondeur--;
            return;
        }
        throw new Exception("entier attendu");
    }

    private void C() throws Exception {

    	if (getTokenClass() == TokenClass.ident) {
    		// production C -> ident E

    		getToken();
    		profondeur++;
    		E();
    		profondeur--;
            return;
        }
        throw new Exception("entier attendu");
    }

    private void D() throws Exception {

    	if (getTokenClass() == TokenClass.ident) {
    		// production C -> ident

    		getToken();
            return;
        }
        throw new Exception("entier attendu");
    }

    private void E() throws Exception {

    	if (getTokenClass() == TokenClass.ouvreBlock) {
    		// production E -> [S]

    		getToken();
    		profondeur++;
    		S();
    		profondeur--;

    		if(getTokenClass() == TokenClass.fermeBlock){
    			getToken();
                return;
    		}
        }
        throw new Exception("entier attendu");
    }

    private TokenClass getTokenClass() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            return tokens.get(pos).getCl();
        }
    }

    /*
     * Retourne le prochain token à lire
     * ET AVANCE au token suivant
     */
    private Token getToken() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            Token current = tokens.get(pos);
            pos++;
            return current;
        }
    }

    private boolean isEOF() {
        return pos >= tokens.size();
    }
}
