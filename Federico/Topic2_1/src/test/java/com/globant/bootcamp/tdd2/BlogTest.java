package com.globant.bootcamp.tdd2;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class BlogTest {

    public Blog blog;
    public Entry[] entry;

    @Before
    public void startup(){
        blog = new Blog("The Best Blog of the Universe");
        entry = new Entry[10];
        for(int i=0;i<10;i++){
            entry[i] = new Entry("Wow" + i, "Chino");
            entry[i].setBody("Something interesting..." + i);
        }
    }

    @Test
    public void WhenBlogIsCreatedThenItIsEmpty(){
        List<Entry> entries = blog.showEntries();
        Assert.assertTrue(entries.isEmpty());
    }

    @Test
    public void WhenNewEntryIsCreatedThenItIsAddedAtTop(){

        blog.addEntry(entry[0]);
        blog.addEntry(entry[1]);
        List<Entry> entries = blog.showEntries();
        Assert.assertEquals(entries.get(0), entry[1]);
    }

    @Test
    public void WhenExistingEntryIsRemovedThenItIsNoLongerInBlog(){

        blog.addEntry(entry[0]);
        blog.rmEntry(entry[0]);
        List<Entry> entries = blog.showEntries();
        Assert.assertFalse(entries.contains(entry[0]));
    }

    @Test
    public void WhenNoExistingEntryIsRemovedThenNothingHappens(){

        List<Entry> entries = blog.showEntries();
        if(!entries.contains(entry[0])) {
            blog.rmEntry(entry[0]);
            Assert.assertFalse(entries.contains(entry[0]));
        }
    }

    @Test
    public void WhenExistingEntryIsRemovedByStringThenItIsNoLongerInBlog(){

        blog.addEntry(entry[0]);
        blog.rmEntry(entry[0].getTitle(), entry[0].getAuthor());
        List<Entry> entries = blog.showEntries();
        Assert.assertFalse(entries.contains(entry[0]));
    }


}
