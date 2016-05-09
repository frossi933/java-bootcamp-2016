package com.globant.bootcamp.tdd2;

import java.util.LinkedList;
import java.util.List;

public class Blog {

    private String name;
    private LinkedList<Entry> entries;
    private final int SHOW_MAX = 10;

    public Blog (String name){
        this.name = name;
        entries = new LinkedList<Entry>();
    }

    public void addEntry(Entry entry) {
        entries.addFirst(entry);
    }

    public void rmEntry(Entry entry) {
        entries.remove(entry);
    }

    public void rmEntry(String title, String author){

        Entry entry;
        for(int i=0;i<entries.size();i++){
            entry = entries.get(i);
            if(entry.getTitle() == title && entry.getAuthor() == author) {
                entries.remove(i);
                return;
            }
        }
    }

    public List<Entry> showEntries() {

        Entry entry;
        int size = entries.size();
        int min = (size <= SHOW_MAX) ? size : SHOW_MAX;
        List<Entry> res = new LinkedList<Entry>();
        for (int i = 0; i < min; i++)
            res.add(entries.get(i));

        return res;
    }
}
