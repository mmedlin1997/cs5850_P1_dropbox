package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsS3ClientTest
{

   @Test
   public void test()
   {
      AmazonS3 s3testClient = AmazonS3ClientBuilder.defaultClient();
      final String BUCKET_NAME = "mmedlin-dropbox";
      final String BUCKET_TEST_FILE = 
            "C:\\Users\\vanessa\\Desktop\\Java\\example\\s3_test.txt";
      
      AwsS3Client client = new AwsS3Client(s3testClient, BUCKET_NAME);
      client.putFileToBucket(BUCKET_TEST_FILE);
//      fail("Not yet implemented");
   }

}
