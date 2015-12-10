package com.aleksandrp.schoolbooksleeveel1.get_and_view_books;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.StartActivity;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;
import com.aleksandrp.schoolbooksleeveel1.reader_pdf.PdfActivity;
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
public class GetAndShowFile implements StaticValues {

    private static Context mContext;
    private String name;
    private DBImpl db;


    public GetAndShowFile(Context context) {
        this.mContext = context;
        db = DBImpl.getInstanceDB(mContext);
    }


    public void downloadFileFromReppositoria(String fileUri, String nameBook) {
        String addedPart = LINK_DOWNLOAD;
        fileUri = addedPart + fileUri.substring(fileUri.indexOf("=") + 1);
        this.name = nameBook;
        String fileName = nameBook + ".pdf";
        if (isNetworkOnline()) {
            new DownloadFile().execute(fileUri, fileName);
        } else {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
            mBuilder.setTitle(" ")
                    .setMessage(R.string.can_network_connection)
                    .setIcon(R.drawable.wifi_not)
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = mBuilder.create();
            alert.show();
        }
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    public void viewFile(String nameFile) {
        nameFile += ".pdf";
        String path = Environment.getExternalStorageDirectory() + "/" +
                NAME_FOLDER_SAVED + "/" + nameFile;
        File pdfFile = new File(path);  // -> filename = maven.pdf
        if (pdfFile.exists()) {
            try {
                Intent intent = new Intent(mContext, PdfActivity.class);
                intent.putExtra(PATH_FILE, path);
                mContext.startActivity(intent);
//                BooksFragment.getInstance().updateList();
//                GDZFragment.getInstance().update();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
            mAlertDialog.setTitle(R.string.book_not_found);
            final String finalNameFile = nameFile.substring(0, nameFile.lastIndexOf("."));
            mAlertDialog.setPositiveButton(mContext.getResources().getString(android.R.string.ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.putFlagLoader("0", finalNameFile);
                            dialog.cancel();
                        }
                    });
            mAlertDialog.show();
        }
    }

    protected class DownloadFile extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            StartActivity.mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, NAME_FOLDER_SAVED);
            folder.mkdir();

            File pdfFile = new File(folder, fileName);
            File pdfFileIsExit = new File(Environment.getExternalStorageDirectory() + "/" +
                    NAME_FOLDER_SAVED + "/" + fileName);
            if (pdfFileIsExit.exists()) return null;
            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int intProgress = 0;
            try {

                URL url = new URL(fileUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.setRequestMethod("GET");
                //urlConnection.setDoOutput(true);
                urlConnection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar

                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
                long totalSize = urlConnection.getContentLength();

                byte[] buffer = new byte[MEGABYTE];
                int bufferLength;
                long total = 0;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    total += bufferLength;
                    intProgress = (int) ((total * 100) / totalSize);
                    publishProgress(intProgress);
                    fileOutputStream.write(buffer, 0, bufferLength);
                }
                fileOutputStream.close();
            } catch (FileNotFoundException | MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            db.putFlagLoader(name, name);
            StartActivity.mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
