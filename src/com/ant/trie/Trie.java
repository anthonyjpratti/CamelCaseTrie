package com.ant.trie;

import java.util.LinkedList;
import java.util.List;

public class Trie {

    private Node root = new Node( false );

    public void addWord(String str){
        if(str.length() == 0) return;
        Node curr = root;
        for(int i = 0; i < str.length() - 1; i++){
            char data = str.charAt(i);
            curr = curr.addChild(data, false);
        }
        curr.addChild(str.charAt(str.length() - 1), true);
    }

    public boolean contains(String str) {
        Node curr = root;
        for (int i = 0; i < str.length(); i++) {
            curr = curr.getChild(str.charAt(i));
            if( curr == null){
                return false;
            }
        }
        return curr.isTerminal();
    }

    public List<String> makeAllCamelCase(String str){
        return  makeAllCamelCase(str.toCharArray());
    }

    private List<String> makeAllCamelCase(char[] str) {
        List<String> list = new LinkedList<>();
        makeAllCamelCase(str,0,list);
        return list;
    }
    private void makeAllCamelCase(char[] str, int j, List<String> list){
        if( j >= str.length) return;
        Node curr = root.getChild(str[j]);
        str[j] = capitalize(str[j]);
        for (int i = j+1; i < str.length && curr != null; i++) {
            if( curr.isTerminal() ){
                makeAllCamelCase(str, i, list);
            }
            curr = curr.getChild(str[i]);
        }
        if(curr != null && curr.isTerminal()){
            list.add(new String(str));
        }
        str[j] = unCapitalize(str[j]);

    }

    private boolean isLower(char c){
        return  'a' <= c && c <= 'z';
    }

    private char capitalize(char c){
        return isLower(c) ? (char) (c + 'A' - 'a') : c;
    }

    private char unCapitalize(char c){
        return (char) (c + 'a' - 'A');
    }



}
