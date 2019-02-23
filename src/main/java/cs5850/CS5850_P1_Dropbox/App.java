package cs5850.CS5850_P1_Dropbox;

public class App 
{
    public static void main( String[] args )
    {
       final String SAMPLE_FOLDER = "C:\\Users\\vanessa\\Desktop\\Java\\example";
       FolderWatcher.watchForEvent(SAMPLE_FOLDER);
     
       //final String BUCKET_NAME = "mmedlin-dropbox";
       
//       final String SAMPLE_BUCKET_FILE = "C:\\Users\\vanessa\\Desktop\\Java\\example\\s3_test.txt";
//       final String SAMPLE_BUCKET_FILE2 = "C:\\Users\\vanessa\\Desktop\\Java\\example\\s3_test2.txt";
       
//       AwsS3Client.putFileToBucket(SAMPLE_BUCKET_FILE, BUCKET_NAME);
//       AwsS3Client.putFileToBucket(SAMPLE_BUCKET_FILE2, BUCKET_NAME);
//       
//       AwsS3Client.getListFromBucket(BUCKET_NAME);
       
//       final String DOWNLOAD_FILE = "C:\\Users\\vanessa\\Desktop\\Java\\example_downloads\\s3_test.txt";
//       AwsS3Client.downloadFileFromBucket(BUCKET_NAME, DOWNLOAD_FILE);
       
       System.out.println( "Program Done" );
       
       
    }
}
