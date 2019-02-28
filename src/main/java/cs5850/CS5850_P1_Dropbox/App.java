package cs5850.CS5850_P1_Dropbox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
       final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox";
       Path path = Paths.get(DROPBOX_PATH);
       
       Thread fwt = new Thread(new FolderWatcher(path));
       fwt.start();
       
       Scanner in = new Scanner(System.in);
       if(in.nextLine().isEmpty()) {
          fwt.interrupt();
       } else {
          fwt.interrupt();
       }
       in.close();
       
//       Thread fw = new Thread(new FolderWatcher());
//       System.out.println("Starting folder watcher.");
//       fw.start();
//       
//       Scanner in = new Scanner(System.in);
//       if(in.nextLine().isEmpty()) {
//          fw.interrupt();
//       } else {
//          fw.interrupt();
//       }
//       in.close();
       
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
