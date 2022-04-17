package com.rg.huffmancoding;

import com.rg.binarytree.Tree;
import com.rg.filemanager.ReadFile;
import com.rg.filemanager.WriteFile;

import java.util.HashMap;
import java.util.HashSet;

public class CompressFile {

    public boolean compression(String file_path, String compressed_file_path) {
        boolean compression_done = false;

        ReadFile readFile = new ReadFile(file_path);
        String fileContent = extractFileContent(readFile);
        readFile.close();
        HashMap<Character, Integer> occurrences = countOccurrences(fileContent);


        Tree binary_tree = new GenerateBinaryTree()
                .TreeFromOccurrences(occurrences);


        HashMap<Character, String> characterCode = new HashMap<>();
        boolean onlyOneCharacter = onlyOneCharacter(fileContent);
        if (onlyOneCharacter) {
            new GenerateBinaryTree().generateCharacterCode(characterCode, binary_tree.getRoot(), "0");
        }
        if (!onlyOneCharacter) {
            new GenerateBinaryTree().generateCharacterCode(characterCode, binary_tree.getRoot(), "");
        }


        WriteFile writeFile = new WriteFile(compressed_file_path);

        String bytes = convertFileContentToBytes(fileContent, characterCode);

        String bytesToCharacters = convertBytesToCharacters(bytes);
        String missingBytes = bytes.substring(bytes.length() - bytes.length()%8);

        // The replace function is here to escape the encoding table to avoid problems when decompressing
        writeFile.writeText(characterCode.toString()
                .replace("\n", "\\n")
        );
        writeFile.writeText("\n" + missingBytes + "\n");
        writeFile.writeText(bytesToCharacters);


        writeFile.close();
        return compression_done;
    }

    private String extractFileContent(ReadFile file) {
        String extracted = "";
        String extracting = file.getLine();

        while (extracting != null) {
            extracted = extracted.concat(extracting + "\n");
            extracting = file.getLine();
        }

        // Delete the last unwanted "\n"
        extracted = extracted.substring(0, extracted.length() - 1);
        return extracted;
    }

    private HashMap<Character, Integer> countOccurrences(String text) {
        HashMap<Character, Integer> occurrences = new HashMap<>();
        Character character;
        boolean contains;

        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            contains = occurrences.containsKey(character);
            if (!contains) {
                occurrences.put(character, 1);
            }
            if (contains) {
                occurrences.put(character, occurrences.get(character) + 1);
            }
        }

        return occurrences;
    }

    private boolean onlyOneCharacter(String input) {
        boolean result = true;
        HashSet<Character> set = new HashSet<>();
        for (int i =0 ;i < input.length();i++){
            set.add(input.charAt(i));
            if (set.size() > 1) {
                result = false;
                break;
            }
        }
        return result;
    }

    private String convertFileContentToBytes(String fileContent, HashMap<Character, String> characterCode) {
        String bytes = "";

        for (int i = 0; i < fileContent.length(); ++i) {
            bytes = bytes.concat(characterCode.get(fileContent.charAt(i)));
        }

        return bytes;
    }

    private String convertBytesToCharacters(String bytes) {
        String bytesToCharacters = "";
        int i;
        for (i = 0; i < bytes.length()/8; i++) {
            int a = Integer.parseInt(bytes.substring(8*i,(i+1)*8),2);
            bytesToCharacters += (char)(a);
        }

        return bytesToCharacters;
    }
}
