package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsS3ClientTest
{

   @Test
   public void testAwsS3Client_should_getListFromBucket()
   {
      AmazonS3 s3testClient = AmazonS3ClientBuilder.defaultClient();
      s3testClient.getRegion();
      final String BUCKET_NAME = "mmedlin-dropbox";
      final String BUCKET_TEST_FILE = 
            "C:\\Users\\vanessa\\Desktop\\Java\\example\\s3_test.txt";
      
//      AwsS3Client client = new AwsS3Client(s3testClient, BUCKET_NAME);
//      client.putFileToBucket(BUCKET_TEST_FILE);
//      fail("Not yet implemented");
   }
   
   @Test
   public void testAwsS3Client_should_createBucket() {
      final String S3_BUCKET_NAME = "mmedlin-test";
      final String LOCAL_PATH1 = "C:\\Users\\Vanessa\\Desktop\\Java\\example\\test1.txt";
      final String LOCAL_PATH2 = "C:\\Users\\Vanessa\\Desktop\\Java\\mmedlin-test\\";
      final String LOCAL_PATH3 = "C:\\Users\\Vanessa\\Desktop\\Java\\mmedlin-test\\test1.txt";
      
      Path path = Paths.get(LOCAL_PATH1);
      System.out.println(path.getFileName());
      System.out.println(path.getParent().getFileName());
      if (Files.notExists(path)) {
         System.out.println("Does NOT exist");
      }
      else {
         System.out.println("Does exist");
      }
      
      path = Paths.get(LOCAL_PATH2);
      System.out.println(path.getFileName());
      System.out.println(path.getParent().getFileName());
      if (Files.notExists(path)) {
         System.out.println("Does NOT exist");
      }
      else {
         System.out.println("Does exist");
      }
      
       path = Paths.get(LOCAL_PATH3);
      System.out.println(path.getFileName());
      System.out.println(path.getParent().getFileName());
      if (Files.notExists(path)) {
         System.out.println("Does NOT exist");
      }
      else {
         System.out.println("Does exist");
      }
      

//      AmazonS3 s3testClient = AmazonS3ClientBuilder.defaultClient();
      
      
   }

}
