package com.rg;

import com.rg.huffmancoding.CompressFile;

public class Test {
    public static void main(String[] args) {
        String file = "H:\\QualiteDev\\tp_code_propre\\test.txt";
        String compressed = "H:\\QualiteDev\\tp_code_propre\\compressed.txt";
        CompressFile t = new CompressFile();
        if (t.compression(file, compressed)) {
            System.out.println("Compression done !");
        }
    }
}
