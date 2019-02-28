package cs5850.CS5850_P1_Dropbox;

import java.io.File;

public interface IAwsS3Client
{

   void putFile(File file);

   void copyFile(String origFileName, String newFileName);

   void deleteFile(String fileName);

   void renameFile(String origFileName, String newFileName);

   String[] getListFromBucket();

   void printList();

}