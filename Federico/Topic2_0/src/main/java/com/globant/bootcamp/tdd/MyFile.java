package com.globant.bootcamp.tdd;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MyFile {

    private String name;
    private Date createdDate;
    private String content = "";
    private boolean isopen = false;

    public MyFile(String name){
        this.name = name;
        createdDate = new Date();
    }

    static class RecentFileList {

        static private LinkedList<MyFile> files;
        static final private int MAX_NUM_FILES = 15;

        public RecentFileList(){

            files = new LinkedList<MyFile>();
        }

        static public boolean isEmpty(){

            return files.isEmpty();
        }

        static public boolean isMember(MyFile file){
            return files.contains(file);
        }

        static public void addFile(MyFile file){
            if(files.size() >= MAX_NUM_FILES)
                files.removeLast();

            files.addFirst(file);
        }

        static public void rmFile(MyFile file){
            files.remove(file);
        }

        static public void deleteAll(){
            files=null;
        }

        static public List<MyFile> getFiles(){
            return files;
        }

        static public MyFile getTop() {
            return files.getFirst();
        }
    }

    public String getName(){
        return name;
    }

    public void open(){
        if(isopen)
            RecentFileList.rmFile(this);

        RecentFileList.addFile(this);
        isopen = true;
    }

    public void close(){
        isopen = false;
    }

    public boolean isopen(){
        return isopen;
    }
}
