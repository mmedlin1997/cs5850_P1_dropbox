package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsS3ClientTest
{
   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();
   // Note: File is guaranteed to be deleted after the test finishes.
   
   @Test
   public void test() {
      final String BUCKET_NAME = "mmedlin-test";
//    final String BUCKET_NAME = "mmedlin-dropbox-uswest1";
      final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox\\";
      final String FILE1 = "test1.txt";
      final String FILE2 = "test1_copy.txt";
      final String FILE3 = "test3.txt";
      final String FILE4 = "test3_modify.txt";
      
      // Setup using credentials at ~/user/.aws
      AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
      IAwsS3Client s3 = new AwsS3Client(s3Client, BUCKET_NAME);
      
      System.out.println("Bucket list:");
      s3.getListFromBucket();
      
      // Put files to S3
      System.out.println("After adding file:");
      s3.putFile(new File(DROPBOX_PATH + FILE1));
      s3.getListFromBucket();
      
      System.out.println("After copying file:");
      s3.copyFile(FILE1, FILE2);
      s3.getListFromBucket();
      
      System.out.println("After deleting file:");
      s3.deleteFile(FILE1);
      s3.getListFromBucket();
      
      System.out.println("After renaming file:");
      s3.putFile(new File(DROPBOX_PATH + FILE3));
      s3.renameFile(FILE3, FILE4);
      s3.getListFromBucket();
   }
   
   @Test
   public void testAwsS3Client_should_getListFromBucket() throws IOException, InterruptedException {
      
      // Create a new empty temporary folder.
      final File testTempFolder = tempFolder.newFolder("Dropbox");
      
      final String S3_BUCKET_NAME = "mmedlin-test";
      
      // Setup using credentials at ~/user/.aws
      AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
      IAwsS3Client s3 = new AwsS3Client(s3Client, S3_BUCKET_NAME);
      
      // Create some filenames, then real files
      Path path = Paths.get(testTempFolder.getAbsolutePath());
      String[] fileNames = {
            path.toAbsolutePath().toString() + "\\" + "test1_",
            path.toAbsolutePath().toString() + "\\" + "test2_",
      };
      File[] expectedFiles = new File[fileNames.length];
      for(int i=0; i< fileNames.length; i++) {
         expectedFiles[i] = File.createTempFile(fileNames[i], null, path.toFile());
      }
      
      // Put up to S3
      for(File f : expectedFiles) {
         s3.putFile(f);
      }
      Thread.sleep(1000);
      
      System.out.println("Actual list");
      List<String> actualFileList = Arrays.asList(s3.getListFromBucket());
      
      List<String> expectedList = new ArrayList<String>();
      for(File f : expectedFiles) {
         expectedList.add(f.getName());
      }
      
      boolean match = true;
      for(File expectedFile : expectedFiles) {
         if(!actualFileList.contains(expectedFile.getName())) {
            match = false;
            break;
         };
      }
      
      assertEquals(match, true);
      
      //Clean up S3 bucket
      for(String s : actualFileList){
         s3.deleteFile(s);
      }
   }

}
