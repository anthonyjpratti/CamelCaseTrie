package com.ant.trie;

import java.io.Serializable;
import java.util.HashMap;


public class Node implements Serializable{

    private boolean terminal = false;
    private boolean leaf = true;
    private HashMap<Character,Node> map = new HashMap<Character,Node>();
    private char data;

    public Node(){}

    public Node(boolean terminal){ this.terminal = terminal; }

    public Node(char data){
        this.data = data;
    }

    public void setData( char data){
        this.data = data;
    }
    public char getData(){
        return data;
    }

    public boolean isTerminal(){
        return terminal;
    }

    public void setTerminal(boolean terminal){
        this.terminal = this.terminal || terminal;
    }

    public boolean isLeaf(){
        return leaf;
    }


    public Node addChild( char data, boolean terminal){
        leaf = false;
        Node child = map.computeIfAbsent(data, Node::new);
        child.setTerminal( terminal );
        return child;
    }

    public Node getChild(char data){
        return map.get(data);
    }

    public String toString(){
        return "data: " + data + ", terminal: " + terminal;
    }

}
