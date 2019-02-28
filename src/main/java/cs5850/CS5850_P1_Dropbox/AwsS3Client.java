package cs5850.CS5850_P1_Dropbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AwsS3Client
{
   private final AmazonS3 s3client;
   private final String bucketName;
   
   public AwsS3Client(AmazonS3 s3client, String bucketName) {
      this.s3client = s3client;
      this.bucketName = bucketName;
   }
   
   public void putFileToBucket(String file_path) {
      try {
         String key_name = Paths.get(file_path).getFileName().toString();
         this.s3client.putObject(this.bucketName, key_name, new File(file_path));
      } catch (AmazonS3Exception e) {
         e.getErrorMessage();
      }
   }
   
   public static void getListFromBucket(String bucket_name) {
      System.out.format("List from S3 bucket %s contents...\n", bucket_name);
      final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
      ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
      List<S3ObjectSummary> objects = result.getObjectSummaries();
      for (S3ObjectSummary os: objects) {
          System.out.println("* " + os.getKey());
      }
   }
   
   public static void downloadFileFromBucket(String bucket_name, String file_path) {
      String key_name = Paths.get(file_path).getFileName().toString();
      
      final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
      try {
         System.out.format("S3 bucket %s downloading file %s...\n", bucket_name, key_name);
         S3Object o = s3.getObject(bucket_name, key_name);
         S3ObjectInputStream s3is = o.getObjectContent();
         FileOutputStream fos = new FileOutputStream(new File(file_path));
         byte[] read_buf = new byte[1024];
         int read_len = 0;
         while ((read_len = s3is.read(read_buf)) > 0) {
            fos.write(read_buf, 0, read_len);
         }
         s3is.close();
         fos.close();
      } catch (AmazonServiceException e) {
         System.err.println(e.getErrorMessage());
         System.exit(1);
      } catch (FileNotFoundException e) {
         System.err.println(e.getMessage());
         System.exit(1);
      } catch (IOException e) {
         System.err.println(e.getMessage());
         System.exit(1);
      }
   }
   public static void copyObject(String bucket_name, String orig_object, String new_object) {
      final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
      try {
         s3.copyObject(bucket_name, orig_object, bucket_name, new_object);
      } catch (AmazonServiceException e) {
         System.err.println(e.getErrorMessage());
         System.exit(1);
      }
   }
   
   public static void deleteObject(String bucket_name, String object_key) {
      final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
      try {
          s3.deleteObject(bucket_name, object_key);
      } catch (AmazonServiceException e) {
          System.err.println(e.getErrorMessage());
          System.exit(1);
      }
   }

}
