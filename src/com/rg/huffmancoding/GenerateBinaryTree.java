package com.rg.huffmancoding;

import com.rg.binarytree.Node;
import com.rg.binarytree.Tree;

import java.util.*;

public class GenerateBinaryTree {

    public Tree TreeFromOccurrences(HashMap<Character, Integer> occurrences) {
        Tree tree = new Tree();

        ArrayList<Node> nodes = new ArrayList<>();
        Set<Character> iterateOccurrences = occurrences.keySet();
        for (Character character : iterateOccurrences) {
            nodes.add(new Node(character, occurrences.get(character)));
        }
        nodes.sort(Node::compareTo);

        tree.setRoot(recursivelyGenerateTree(nodes).get(0));

        return tree;
    }

    public ArrayList<Node> recursivelyGenerateTree(ArrayList<Node> nodes) {
        if (nodes.size() == 1) {
            return nodes;
        }

        Node firstNode = nodes.remove(0);
        Node secondNode = nodes.remove(0);

        Node newNode = new Node(null, firstNode.getCount() + secondNode.getCount());
        newNode.setLeft(firstNode);
        newNode.setRight(secondNode);

        nodes.add(newNode);
        nodes.sort(Node::compareTo);

        return recursivelyGenerateTree(nodes);
    }

    public void generateCharacterCode(HashMap<Character, String> characterCode, Node node, String code) {
        if (node.getCharacter() == null) {
            generateCharacterCode(characterCode, node.getLeft(), code + "0");
            generateCharacterCode(characterCode, node.getRight(), code + "1");
        }
        if (node.getCharacter() != null) {
            characterCode.put(node.getCharacter(), code);
        }
    }
}
