package cs5850.CS5850_P1_Dropbox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FolderWatcher implements Runnable
{
   private WatchService watchService;
   private IAwsS3Client s3;
   private Path path;
   private WatchEvent<?> prevWatch;
   
   public FolderWatcher(Path path, IAwsS3Client s3) {
      try {
         // Create a new Watch Service
         this.watchService = path.getFileSystem().newWatchService();
         
         // Set storage client
         this.s3 = s3;
         this.path = path;
         this.prevWatch = null;
         
         // Register events
         path.register(watchService,
               StandardWatchEventKinds.ENTRY_CREATE,
               StandardWatchEventKinds.ENTRY_DELETE,
               StandardWatchEventKinds.ENTRY_MODIFY);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   @Override
   public void run() {
      // Create a watch key and take the next one
      WatchKey watchKey;
      
      while(!Thread.interrupted()) {
         try {
            while((watchKey = watchService.take()) != null) {
               // Prevent receiving two separate ENTRY_MODIFY events.
               Thread.sleep(50);
               
               // Handle each event
               for(WatchEvent<?> event : watchKey.pollEvents()) {
                  this.handleEvent(event);
               }
               
               // Reset the watch key so it can receive another event.
               watchKey.reset();
            }
            
            watchService.close();
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
   
   // create: CREATE MODIFY
   // rename: DELETE CREATE
   // delete: DELETE
   private void handleEvent(WatchEvent<?> event) {
      Kind<?> kind = event.kind();
      if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
         System.out.println("Uploading file: " + event.context());
         
         // Create file in S3
         Path path = Paths.get(this.path.toString(), event.context().toString());
         this.s3.putFile(path.toFile());
         this.prevWatch = event;
      }
      else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
         System.out.println("Deleting file: " + event.context());
         
         // Delete file in S3
         this.s3.deleteFile(event.context().toString());
         this.prevWatch = event;
      }
      else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
         if (this.prevWatch != null) {
            if (this.prevWatch.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
//               System.out.println("Prev kind was: " + this.prevWatch.kind() + ". Assuming create." );
            }
            else {
               System.out.println("Modifying file: " + event.context());
               this.s3.deleteFile(event.context().toString());
               Path path = Paths.get(this.path.toString(), event.context().toString());
               this.s3.putFile(path.toFile());
            }
         }
         this.prevWatch = event;
      }
      else {
         System.out.println("Event kind:" + kind + ", file: " + event.context());
      }
   }
}
