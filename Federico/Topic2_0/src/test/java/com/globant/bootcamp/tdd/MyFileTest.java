package com.globant.bootcamp.tdd;

import org.junit.*;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class MyFileTest {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    static MyFile.RecentFileList rfl;

    @Before
    public void startup(){
        MyFile.RecentFileList rfl = new MyFile.RecentFileList();
    }

    @Test
    public void WhenRFLisCreatedItStartsEmpty() {
        Assert.assertTrue(rfl.isEmpty());
    }

    @Test
    public void WhenCreatesFileThenItIsNotMember(){
        MyFile f1 = new MyFile("f1");
        Assert.assertFalse(rfl.isMember(f1));
    }

    @Test
    public void WhenOpensFileThenItIsMember(){
        MyFile f1 = new MyFile("f1");
        f1.open();
        Assert.assertTrue(rfl.isMember(f1));
    }

    @Test
    public void WhenReOpensFileThenItIsAtTop(){
        MyFile f1 = new MyFile("f1");
        MyFile f2 = new MyFile("f2");
        f1.open();
        f2.open();
        f2.close();
        f1.open();
        Assert.assertEquals(f1, rfl.getTop());
    }

    @Test
    public void WhenReOpensFileThenItIsNotDuplicated(){
        MyFile f1 = new MyFile("f1");
        MyFile f2 = new MyFile("f2");
        f1.open();
        f2.open();
        f2.close();
        f1.open();
        List<MyFile> files = rfl.getFiles();
        files.remove(f1);
        Assert.assertFalse(files.remove(f1)); // if f1 is no longer in files, then remove returns false
    }

    @Test
    public void AddsNewFileWhenListIsFullThenRemovesLast(){
        MyFile[] files = new MyFile[16];
        for(int i = 0 ; i<16 ; i++){
            files[i]=new MyFile("file" + i);
            files[i].open();
        }

        Assert.assertFalse(rfl.isMember(files[0]));
    }

    @Test
    public void AddsNewFileWhenListIsFullThenAddsItAtTop(){
        MyFile[] files = new MyFile[16];
        for(int i = 0 ; i<16 ; i++){
            files[i]=new MyFile("file" + i);
            files[i].open();
        }

        Assert.assertEquals(rfl.getTop(), files[15]);
    }

    @Test
    public void AddsNewFileWhenListIsFullThenDoesNotRemoveNonLastFiles(){
        MyFile[] files = new MyFile[16];
        for(int i = 0 ; i<16 ; i++){
            files[i]=new MyFile("file" + i);
            files[i].open();
        }

        for(int i = 1 ; i<14 ; i++)
            Assert.assertTrue(rfl.isMember(files[i]));
    }

    @After
    public void teardown(){
        rfl.deleteAll();
        rfl = null;
    }

    public void testMyFile()
    {

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
