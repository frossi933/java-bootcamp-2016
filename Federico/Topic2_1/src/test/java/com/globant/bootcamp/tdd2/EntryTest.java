package com.globant.bootcamp.tdd2;

import org.junit.*;

import javax.xml.ws.Action;

/**
 * Created by root on 4/28/16.
 */
public class EntryTest {

    private Entry entry;

    @Before
    public void startup(){
        entry = new Entry("Wow","Chino");
    }

    @Test
    public void WhenEntryIsCreatedThenCommentsIsEmpty(){
        Assert.assertTrue(entry.getComments().isEmpty());
    }

    @Test
    public void WhenEntryIsCreatedThenRatingIsZero(){
        Assert.assertTrue(entry.getRating() == 0.0);
    }

    @Test
    public void WhenEntryIsRatedCorrectlyThenRatingIsModified(){
        double old = entry.getRating();
        entry.rate(4.0);
        Assert.assertTrue(entry.getRating() != old);
    }

    @Test
    public void WhenEntryIsRatedIncorrectlyThenNothingHappens(){
        double old = entry.getRating();
        entry.rate(100);
        Assert.assertTrue(entry.getRating() == old);
    }

    @After
    public void teardown(){
        entry=null;
    }
}
