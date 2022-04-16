package com.rg.binarytree;

public class Node implements Comparable {
    private final Character character;
    private int count;

    private Node left;
    private Node right;

    public Node(Character character, int count) {
        this.character = character;
        this.count = count;
        this.left = null;
        this.right = null;
    }

    public Character getCharacter() {
        return character;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int compareTo(Object o) {
        return Integer.compare(this.getCount(), ((Node) o).getCount());
    }

    @Override
    public String toString() {
        return "{'" + character + "' : " + count + "}";
    }
}
