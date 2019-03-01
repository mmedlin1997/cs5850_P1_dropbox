package cs5850.CS5850_P1_Dropbox;

import java.nio.file.Paths;
import java.util.Scanner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class App 
{
    public static void main( String[] args )
    {
       final String WATCHED_FOLDER_NAME = "Dropbox";
       final String S3_BUCKET_NAME = "mmedlin-dropbox-uswest1";
       final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox";
       
       FolderUtility util = new FolderUtility(WATCHED_FOLDER_NAME);
       util.setPath(Paths.get(DROPBOX_PATH));
       
       // Verify path to watched folder
       if(!util.getIsValidPath()) {
          System.out.println("Please use path to folder named Dropbox.");
          System.exit(0);
       }
       
       // Setup using credentials at ~/user/.aws
       AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
       IAwsS3Client s3 = new AwsS3Client(s3Client, S3_BUCKET_NAME);
       
       // Start watching folder on separate thread
       Thread fwt = new Thread(new FolderWatcher(util.getPath(), s3));
       fwt.start();
       System.out.println("Watching for changes in: " + util.getPath().toString());
       System.out.println("Syncing with AWS S3 bucket: " + S3_BUCKET_NAME + "\n");
       
       System.out.println("Press enter to stop watching folder and exit.");
       Scanner in = new Scanner(System.in);
       if(in.nextLine().isEmpty()) {
          fwt.interrupt();
       } else {
          fwt.interrupt();
       }
       in.close();
       
       System.out.println( "Program Done" );
    }
    
}
