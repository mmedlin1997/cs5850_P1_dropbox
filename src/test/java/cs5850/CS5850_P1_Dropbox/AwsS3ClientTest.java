package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsS3ClientTest
{

   @Test
   public void testAwsS3Client_should_getListFromBucket() {
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
   public void testAwsS3Client_should_createBucket() {
      
   }

}
