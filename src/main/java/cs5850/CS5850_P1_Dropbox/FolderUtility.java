package cs5850.CS5850_P1_Dropbox;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderUtility
{
   public final String FOLDER_NAME = "Dropbox";
   private Path path;
   private boolean isValidPath;
   
   public FolderUtility(String path) {
      this.path = Paths.get(path);
      this.isValidPath = validatePath();
   }
   
   private boolean validatePath() {
      // Check if path is a folder or file
      File f = new File(this.path.toString());
      if (f.isDirectory()) {
         System.out.println("in a directory");
         System.out.println("folder is: " + this.path.getFileName());
         if (FOLDER_NAME.equals(this.path.getFileName().toString())) {
            return true;
         }
      }
      else {
         if(FOLDER_NAME.equals(this.path.getParent().getFileName().toString())) {
            this.path = this.path.getParent();
            return true;
         }
      }
      
      return false;
   };
   
   public boolean getIsValidPath() {
      return this.isValidPath;
   }
   
   public String toString() {
      return this.path.toString();
   }
}
