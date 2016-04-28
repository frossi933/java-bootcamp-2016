package com.globant.bootcamp.tdd;

/**
 * Created by root on 4/27/16.
 */
public class RFLClient {
    static public void main(String[] args){

        MyFile.RecentFileList recentFiles = new MyFile.RecentFileList();

        MyFile[] files = new MyFile[10];
        for(int i = 0 ; i<10 ; i++){
            files[i]=new MyFile("file" + i);
            files[i].open();
        }

        for(MyFile f : recentFiles.getFiles())
        System.out.println(f.getName());
    }
}
