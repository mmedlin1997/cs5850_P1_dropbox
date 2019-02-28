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
    
//    public static void s3client() {
//       final String BUCKET_NAME = "mmedlin-test";
////       final String BUCKET_NAME = "mmedlin-dropbox-uswest1";
//       final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox\\";
//       final String FILE1 = "test1.txt";
//       final String FILE2 = "test1_copy.txt";
//       final String FILE3 = "test3.txt";
//       final String FILE4 = "test3_modify.txt";
//       
//       // Setup using credentials at ~/user/.aws
//       AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
//       IAwsS3Client s3 = new AwsS3Client(s3Client, BUCKET_NAME);
//       
//       System.out.println("Bucket list:");
//       s3.printList();
//       
//       // Put files to S3
//       System.out.println("After adding file:");
//       s3.putFile(new File(DROPBOX_PATH + FILE1));
//       s3.printList();
//       
//       System.out.println("After copying file:");
//       s3.copyFile(FILE1, FILE2);
//       s3.printList();
//       
//       System.out.println("After deleting file:");
//       s3.deleteFile(FILE1);
//       s3.printList();
//       
//       System.out.println("After renaming file:");
//       s3.putFile(new File(DROPBOX_PATH + FILE3));
//       s3.renameFile(FILE3, FILE4);
//       s3.printList();
//    }
    
//    public static void watchFolder() {
//       final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox";
//       Path path = Paths.get(DROPBOX_PATH);
//       
//       // Setup using credentials at ~/user/.aws
//       AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
//       IAwsS3Client s3 = new AwsS3Client(s3Client, "mmedlin-test");
//       
//       Thread fwt = new Thread(new FolderWatcher(path, s3));
//       fwt.start();
//       
//       Scanner in = new Scanner(System.in);
//       if(in.nextLine().isEmpty()) {
//          fwt.interrupt();
//       } else {
//          fwt.interrupt();
//       }
//       in.close();
//    }
    
//    public static void folderUtility() {
//       final String WATCHED_FOLDER_NAME_1 = "Dropbox";
//       
//       final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox\\";
//       
//       FolderUtility util_1 = new FolderUtility(WATCHED_FOLDER_NAME_1);
//       util_1.setPath(Paths.get(DROPBOX_PATH));
//       System.out.println("Watching path: " + util_1.getPath().toString());
//       System.out.println("Is valid? " + util_1.getIsValidPath());
//       
//       final String DROPBOX_PATH_2 = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox\\test1.txt";
//       util_1.setPath(Paths.get(DROPBOX_PATH_2));
//       System.out.println("Watching path: " + util_1.getPath().toString());
//       System.out.println("Is valid? " + util_1.getIsValidPath());
//       
//       final String DROPBOX_PATH_3 = "C:\\Users\\vanessa\\Desktop\\Java\\junk\\";
//       util_1.setPath(Paths.get(DROPBOX_PATH_3));
//       System.out.println("Watching path: " + util_1.getPath().toString());
//       System.out.println("Is valid? " + util_1.getIsValidPath());
//    }
}
