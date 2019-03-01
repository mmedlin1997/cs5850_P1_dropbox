package cs5850.CS5850_P1_Dropbox;

import org.apache.commons.io.FileUtils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderWatcherTest
{
   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();
   // Note: File is guaranteed to be deleted after the test finishes.
   
   @Test
   public void test_FolderWatcher_should_handleCreateFileEvent() throws IOException, InterruptedException {
      
      // Create a new empty temporary folder.
      final File testTempFolder = tempFolder.newFolder("Dropbox");
      final File testFile1 = new File(testTempFolder.getAbsolutePath() + "\\" + "test1.txt");
      
      // Mock
      IAwsS3Client mockS3 = mock(IAwsS3Client.class);
      doNothing().when(mockS3).putFile(testFile1.getAbsoluteFile());
      
      // Start watching the tempFolder
      Path path = Paths.get(testTempFolder.getAbsolutePath());
      Thread fwt = new Thread(new FolderWatcher(path, mockS3));
      fwt.start();
      
      // Create a file
      FileUtils.writeStringToFile(testFile1, "I will be created", StandardCharsets.UTF_8);
      
      // Verify S3 create file is triggered
      Thread.sleep(100);
      verify(mockS3, times(2)).putFile(testFile1.getAbsoluteFile());
      
      // Stop watching the tempFolder
      fwt.interrupt();
   }

   @Test
   public void test_FolderWatcher_should_handleDeleteFileEvent() throws IOException, InterruptedException {
      
      // Create a new empty temporary folder.
      final File testTempFolder = tempFolder.newFolder("Dropbox");
      final File testFile1 = new File(testTempFolder.getAbsolutePath() + "\\" + "test1.txt");
      
      // Create a file
      FileUtils.writeStringToFile(testFile1, "I will be deleted", StandardCharsets.UTF_8);
      
      // Mock
      IAwsS3Client mockS3 = mock(IAwsS3Client.class);
      doNothing().when(mockS3).deleteFile(testFile1.getAbsoluteFile().toString());
      
      // Start watching the tempFolder
      Path path = Paths.get(testTempFolder.getAbsolutePath());
      Thread fwt = new Thread(new FolderWatcher(path, mockS3));
      fwt.start();
      
      // Delete a file
      testFile1.delete();
      Thread.sleep(100);
      
      // Verify S3 delete file is triggered
      verify(mockS3, times(1)).deleteFile(testFile1.getName().toString());
      
      // Stop watching the tempFolder
      fwt.interrupt();
   }
   
   @Test
   public void test_FolderWatcher_should_handleRenameEvent() throws IOException, InterruptedException {
      
      // Create a new empty temporary folder.
      final File testTempFolder = tempFolder.newFolder("Dropbox");
      final File testFile1 = new File(testTempFolder.getAbsolutePath() + "\\" + "test1.txt");
      final File newNameFile1 = new File(testTempFolder.getAbsolutePath() + "\\" + "renamed1.txt");

      // Create a file
      FileUtils.writeStringToFile(testFile1, "I will be renamed", "UTF-8");
      
      // Mock
      IAwsS3Client mockS3 = mock(IAwsS3Client.class);
      doNothing().when(mockS3).putFile(testFile1.getAbsoluteFile());
      doNothing().when(mockS3).deleteFile(testFile1.getAbsoluteFile().toString());
      
      // Start watching the tempFolder
      Path path = Paths.get(testTempFolder.getAbsolutePath());
      Thread fwt = new Thread(new FolderWatcher(path, mockS3));
      fwt.start();
      
      // Delete a file
      testFile1.renameTo(newNameFile1);
      Thread.sleep(100);
      
      // Verify S3 put, delete file is renamed (delete and put)
      verify(mockS3, times(1)).deleteFile(testFile1.getName().toString());
      verify(mockS3, times(1)).putFile(newNameFile1.getAbsoluteFile());
      
      // Stop watching the tempFolder
      fwt.interrupt();
   }
   
   @Test
   public void test_FolderWatcher_should_handleModifyEvent() throws IOException, InterruptedException {
   // Create a new empty temporary folder.
      final File testTempFolder = tempFolder.newFolder("Dropbox");
      final File testFile1 = new File(testTempFolder.getAbsolutePath() + "\\" + "test1.txt");
      
      // Create a file
      FileUtils.writeStringToFile(testFile1, "I will be modified and re-saved", StandardCharsets.UTF_8);
      
      // Mock
      IAwsS3Client mockS3 = mock(IAwsS3Client.class);
      doNothing().when(mockS3).putFile(testFile1.getAbsoluteFile());
      
      // Start watching the tempFolder
      Path path = Paths.get(testTempFolder.getAbsolutePath());
      Thread fwt = new Thread(new FolderWatcher(path, mockS3));
      fwt.start();
      
      // Modify a file
      FileUtils.writeStringToFile(testFile1, "\n...now with new line.", StandardCharsets.UTF_8, true);
      Thread.sleep(1000);
      
      // Verify S3 put, delete file is re-saved (delete an put)
      verify(mockS3, times(1)).putFile(testFile1.getAbsoluteFile());
      
      // Stop watching the tempFolder
      fwt.interrupt();
   }
}
