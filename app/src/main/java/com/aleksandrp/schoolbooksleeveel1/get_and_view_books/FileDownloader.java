package com.aleksandrp.schoolbooksleeveel1.get_and_view_books;


import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aleksandr on 14.11.2015.
 */
public class FileDownloader implements StaticValues {


    public static int intProgress;

    public static void downloadFile(String fileUrl, File directory, GetAndShowFile.DownloadFile downloadFile) {
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();
            // this will be useful so that you can show a tipical 0-100% progress bar

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            long total = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                total += bufferLength;
                intProgress =((int) (total * 100) / totalSize);
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
