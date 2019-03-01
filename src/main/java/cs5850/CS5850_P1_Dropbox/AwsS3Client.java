package cs5850.CS5850_P1_Dropbox;

import java.io.File;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AwsS3Client implements IAwsS3Client
{
   private final AmazonS3 s3client;
   private final String bucketName;
   
   public AwsS3Client(AmazonS3 s3client, String bucketName) {
      this.s3client = s3client;
      this.bucketName = bucketName;
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#putFile(java.io.File)
    */
   @Override
   public void putFile(File file) {
      try {
         String fileName = file.getName().toString();
         this.s3client.putObject(this.bucketName, fileName, file);
      } catch (AmazonS3Exception e) {
         e.getErrorMessage();
      }
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#copyFile(java.lang.String, java.lang.String)
    */
   @Override
   public void copyFile(String origFileName, String newFileName) {
      try {
         this.s3client.copyObject(this.bucketName, origFileName, 
               this.bucketName, newFileName);
      } catch (AmazonServiceException e) {
         e.getErrorMessage();
      }
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#deleteFile(java.lang.String)
    */
   @Override
   public void deleteFile(String fileName) {
      try {
         this.s3client.deleteObject(this.bucketName, fileName);
      } catch (AmazonServiceException e) {
          e.getErrorMessage();
      }
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#renameFile(java.lang.String, java.lang.String)
    */
   @Override
   public void renameFile(String origFileName, String newFileName) {
      this.copyFile(origFileName, newFileName);
      this.deleteFile(origFileName);
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#getListFromBucket()
    */
   @Override
   public String[] getListFromBucket() {
      ListObjectsV2Result result = this.s3client.listObjectsV2(this.bucketName);
      List<S3ObjectSummary> objects = result.getObjectSummaries();
      
      String[] keys = new String[objects.size()];
      for (int i=0; i<objects.size(); i++) {
         keys[i] = objects.get(i).getKey(); 
      }
      return keys;
   }
   
   /* (non-Javadoc)
    * @see cs5850.CS5850_P1_Dropbox.IAwsS3Client#printList()
    */
   @Override
   public void printList() {
      String[] keys = this.getListFromBucket();
      for(String s : keys){
         System.out.println(s);
      }
   }
   
//   public static void downloadFileFromBucket(String bucket_name, String file_path) {
//      String key_name = Paths.get(file_path).getFileName().toString();
//      
//      final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
//      try {
//         System.out.format("S3 bucket %s downloading file %s...\n", bucket_name, key_name);
//         S3Object o = s3.getObject(bucket_name, key_name);
//         S3ObjectInputStream s3is = o.getObjectContent();
//         FileOutputStream fos = new FileOutputStream(new File(file_path));
//         byte[] read_buf = new byte[1024];
//         int read_len = 0;
//         while ((read_len = s3is.read(read_buf)) > 0) {
//            fos.write(read_buf, 0, read_len);
//         }
//         s3is.close();
//         fos.close();
//      } catch (AmazonServiceException e) {
//         System.err.println(e.getErrorMessage());
//         System.exit(1);
//      } catch (FileNotFoundException e) {
//         System.err.println(e.getMessage());
//         System.exit(1);
//      } catch (IOException e) {
//         System.err.println(e.getMessage());
//         System.exit(1);
//      }
//   }
   
}
