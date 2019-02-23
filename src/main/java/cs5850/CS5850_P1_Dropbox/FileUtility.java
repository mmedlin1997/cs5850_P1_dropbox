package cs5850.CS5850_P1_Dropbox;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.LineIterator;

public class FileUtility
{
   private static final String FOLDER = "C:\\Users\\vanessa\\Desktop\\Java\\";
   private static final String FILE = "junk.txt";
   
   public static void run() throws IOException {
      System.out.println("Commons File Utility Example");
      String path = FOLDER + FILE;
      
      // FilenameUtils
      
      System.out.println("Full path of exampleTxt: " +
              FilenameUtils.getFullPath(path));
       
      System.out.println("Full name of exampleTxt: " +
              FilenameUtils.getName(path));
       
      System.out.println("Extension of exampleTxt: " +
              FilenameUtils.getExtension(path));
       
      System.out.println("Base name of exampleTxt: " +
              FilenameUtils.getBaseName(path));
      
      // FileUtils
      
      // We can create a new File object using FileUtils.getFile(String)
      // and then use this object to get information from the file.
      File exampleFile = FileUtils.getFile(path);
      LineIterator iter = FileUtils.lineIterator(exampleFile);
       
      System.out.println("Contents of exampleTxt...");
      while (iter.hasNext()) {
          System.out.println("\t" + iter.next());
      }
      iter.close();
       
      // We can check if a file exists somewhere inside a certain directory.
      File parent = FileUtils.getFile(FOLDER);
      System.out.println("Parent directory contains exampleTxt file: " +
              FileUtils.directoryContains(parent, exampleFile));
      
      // IOCase
      
      String str1 = "This is a new String.";
      String str2 = "This is another new String, yes!";
       
      System.out.println("Ends with string (case sensitive): " +
              IOCase.SENSITIVE.checkEndsWith(str1, "string."));
      System.out.println("Ends with string (case insensitive): " +
              IOCase.INSENSITIVE.checkEndsWith(str1, "string."));
       
      System.out.println("String equality: " +
              IOCase.SENSITIVE.checkEquals(str1, str2));
       
       
      // FileSystemUtils
//      System.out.println("Free disk space (in KB): " + FileSystemUtils.freeSpaceKb("C:"));
//      System.out.println("Free disk space (in MB): " + FileSystemUtils.freeSpaceKb("C:") / 1024);
   }
}
