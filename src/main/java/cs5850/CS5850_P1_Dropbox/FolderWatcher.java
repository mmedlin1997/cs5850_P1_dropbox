package cs5850.CS5850_P1_Dropbox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FolderWatcher implements Runnable
{
   private WatchService watchService;
   
   public FolderWatcher(Path path) {
      try {
         // Create a new Watch Service
         watchService = path.getFileSystem().newWatchService();
          
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
   
   private void handleEvent(WatchEvent<?> event) {
      Kind<?> kind = event.kind();
      if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
         System.out.println("Event kind:" + kind + ", file: " + event.context());
      }
      else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
         System.out.println("Event kind:" + kind + ", file: " + event.context());
      }
      else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
         System.out.println("Event kind:" + kind + ", file: " + event.context());
      }
      else {
         System.out.println("Event kind:" + kind + ", file: " + event.context());
      }
   }
}
