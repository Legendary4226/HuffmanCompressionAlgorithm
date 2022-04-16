package com.rg;

import com.rg.huffmancoding.CompressFile;

public class Test {
    public static void main(String[] args) {
        CompressFile t = new CompressFile();
        t.compression("F:\\QualiteDev\\tp_code_propre\\test.txt", "F:\\QualiteDev\\tp_code_propre\\compressed.txt");
    }
}
