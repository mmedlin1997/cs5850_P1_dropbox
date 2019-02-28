package cs5850.CS5850_P1_Dropbox;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import static org.mockito.Mockito.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderWatcherTest
{

   @BeforeClass
   public static void setUpBeforeClass() throws Exception
   {
   }

   @AfterClass
   public static void tearDownAfterClass() throws Exception
   {
   }

   @Before
   public void setUp() throws Exception
   {
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void test_FolderWatcher_should_handleCreateFileEvent()
   {
   // Setup using credentials at ~/user/.aws
      AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
      IAwsS3Client mockS3 = mock(AwsS3Client.class);
      
      IAwsS3Client s3 = mock(IAwsS3Client.class);
      final String DROPBOX_PATH = "C:\\Users\\vanessa\\Desktop\\Java\\Dropbox";
      Path path = Paths.get(DROPBOX_PATH);
      
      Thread fwt = new Thread(new FolderWatcher(path, s3));
      fwt.start();
      
      fwt.interrupt();
   }

}
