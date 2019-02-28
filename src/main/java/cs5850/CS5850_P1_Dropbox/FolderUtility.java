package cs5850.CS5850_P1_Dropbox;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderUtility
{
   private String watchedFolderName;
   private Path path;
   private boolean isValidPath;
   
   public FolderUtility(String watchedFolderName) {
      this.watchedFolderName = watchedFolderName;
      this.path = Paths.get("");
      this.isValidPath = false;
   }
   
   
   public Path getPath() {
      return path;
   }


   public void setPath(Path path) {
      this.path = path;
      this.isValidPath = validatePath();
   }

   public boolean getIsValidPath() {
      return this.isValidPath;
   }
   
   private boolean validatePath() {
      // Check if path is a folder or file
      File f = new File(this.path.toString());
      if (f.isDirectory()) {
         if (this.watchedFolderName.equals(this.path.getFileName().toString())) {
            return true;
         }
      }
      else {
         if(this.watchedFolderName.equals(this.path.getParent().getFileName().toString())) {
            this.path = this.path.getParent();
            return true;
         }
      }
      
      return false;
   };
}
