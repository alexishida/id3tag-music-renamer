/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexishida.utils;


import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author 94954658268
 */
public class Diretorios {

    public ArrayList<String> arquivosDiretorio(String caminho) {

        ArrayList<String> retorno = new ArrayList();

        String files;
        File folder = new File(caminho);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                if (files.endsWith(".mp3") || files.endsWith(".MP3")) {
                    retorno.add(files);
                }
            }
        }

         return retorno;
    }
}
