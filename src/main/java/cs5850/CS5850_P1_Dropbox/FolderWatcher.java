package cs5850.CS5850_P1_Dropbox;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FolderWatcher implements Runnable
{
   
   public static void watchForEvent(String folder) {
      WatchService watcherService = null;
      try
      {
         watcherService = FileSystems.getDefault().newWatchService();
         
         //Create a path and register it with watch service
         Path path = Paths.get(folder);
         path.register(watcherService,
               StandardWatchEventKinds.ENTRY_CREATE,
               StandardWatchEventKinds.ENTRY_DELETE,
               StandardWatchEventKinds.ENTRY_MODIFY);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // Create a watch key and take the next one
      WatchKey watchKey;
      try
      {
         while((watchKey = watcherService.take()) != null) {
            // Prevent receiving two separate ENTRY_MODIFY events (file modified
            // and timestamp updated) by sleeping. Instead, receive one 
            // ENTRY_MODIFY event with two counts.
            Thread.sleep( 50 );
            
            // Do something on each event
            for(WatchEvent<?> event : watchKey.pollEvents()) {
               Kind<?> kind = event.kind();
               if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                  System.out.println("Event kind:" + kind 
                  + ", file: " + event.context());
               }
               else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                  System.out.println("Event kind:" + kind  
                  + ", file: " + event.context());
               }
               else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                  System.out.println("Event kind:" + kind 
                  + ", file: " + event.context());
               }
               else {
                  System.out.println("Event kind:" + kind 
                  + ", file: " + event.context());
               }
            }
            
            // Reset the watch key so it can receive another event.
            watchKey.reset();
         }
      } catch (InterruptedException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }  
   }
   
   @Override
   public void run()
   {
      System.out.println("Inside : " + Thread.currentThread().getName());
      
      while(!Thread.interrupted()) {
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }
      
   }
}
