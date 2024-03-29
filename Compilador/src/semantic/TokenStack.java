/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantic;

import lex.Token;

/**
 *
 * @author helionagamachi
 */
public class TokenStack {

    private Element top;

    public TokenStack() {
    }

    public boolean isEmpty(){
        return top == null;
    }

    /**
     * Gets the top of the stack.
     * @return null if the stack is empty.
     */
    public Token pop(){
        if(top == null){
            return null;
        }else{
            Element result = top;
            top = result.getPrevious();
            return result.getToken();
        }
    }

    /**
     * Like pop, but doesn't remove the top from stack
     * @return
     */
    public Token peek(){
        if(top == null){
            return null;
        }
        return top.getToken();
    }


    @Override
    public String toString(){
        System.out.println("Called to string from the Token stack");
        String result = "";
        Element element;
        element = top;
        while(element != null){
            result = element.getToken() + " \n" + result;
            element = element.getPrevious();
        }
        return result;
    }

    /**
     * Adds a token to the stack.
     * @param token
     */
    public void push(Token token){
        Element element = new Element(token);
        element.setPrevious(top);
        top = element;
    }

    private class Element {

        private Element previous;
        private Token token;

        public Element(Token token) {
            this.token = token;
        }

        public Element getPrevious() {
            return previous;
        }

        public void setPrevious(Element previous) {
            this.previous = previous;
        }

        public Token getToken() {
            return token;
        }

        
    }
}
