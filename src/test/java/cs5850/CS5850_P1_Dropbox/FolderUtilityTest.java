package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

public class FolderUtilityTest
{
   @Test
   public void testGetIsValidPath() {
      String[] paths = {
           "C:\\Users\\Vanessa\\Desktop\\Java\\example\\",
           "C:\\Users\\Vanessa\\Desktop\\Java\\example\\test1.txt",
           "C:\\Users\\Vanessa\\Desktop\\Java\\Dropbox\\",
           "C:\\Users\\Vanessa\\Desktop\\Java\\Dropbox\\test1.txt",
      };
      
      boolean[] expectedIsValid = {
            false,
            false,
            true,
            true,
      };
      String[] expectedPath = {
            "C:\\Users\\Vanessa\\Desktop\\Java\\example",
            "C:\\Users\\Vanessa\\Desktop\\Java\\example\\test1.txt",
            "C:\\Users\\Vanessa\\Desktop\\Java\\Dropbox",
            "C:\\Users\\Vanessa\\Desktop\\Java\\Dropbox",
      };
      
      FolderUtility util = new FolderUtility("Dropbox");
      
      for (int i=0; i<paths.length; i++) {
         util.setPath(Paths.get(paths[i]));
//         System.out.println(util.getPath().toString() + " " + util.getIsValidPath());
         assertEquals("IsValid", expectedIsValid[i], util.getIsValidPath());
         assertEquals("Path Strings", expectedPath[i], util.getPath().toString());
      }
   }
}
