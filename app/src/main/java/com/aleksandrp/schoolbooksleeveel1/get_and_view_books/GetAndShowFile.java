package com.aleksandrp.schoolbooksleeveel1.get_and_view_books;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import com.aleksandrp.schoolbooksleeveel1.reader_pdf.WorkingClass;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by Aleksandr on 14.11.2015.
 */
public class GetAndShowFile implements StaticValues {

    private static Context mContext;
    private static GetAndShowFile mGerAndShowFile;

    public GetAndShowFile(Context context) {
        this.mContext = context;
    }


    public void downloadFileFromReppositoria(String fileUri) {
//        fileUri = "https://docs.google.com/uc?authuser=0&id=0B3SV6nfdIWiyU0w5SXRtaGVyUzg&export=download";
//        fileUri = "https://mega.nz/#!L9AyQZwK!FddpOLI6IkbexifdWpSeV4S61vGhRV7yy4XZ57-eNN8";
        fileUri = "http://maven.apache.org/maven-1.x/maven.pdf";
//        fileUri = "http://pidruchnyk.com.ua/uploads/book/Matematyka_3klas_Rivkind.pdf";
        String fileName = fileUri.substring(fileUri.lastIndexOf("/") + 1);
//        String fileName = "qqq.pdf";
        new DownloadFile().execute(fileUri, fileName);
    }

    public void viewFile(String nameFile) {
        nameFile = "maven.pdf";
//        nameFile = "Французька мова (Клименко) 1 клас.pdf";
        String path = Environment.getExternalStorageDirectory() + "/" +
                NAME_FOLDER_SAVED + "/" + nameFile;
        File pdfFile = new File(path);  // -> filename = maven.pdf
        if (pdfFile.exists()) {
            try {
                final Intent intent = new Intent(mContext, WorkingClass.class);
                intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, path);
                mContext.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
            mAlertDialog.setTitle("Такого файла нет в библеотеке");
            mAlertDialog.setPositiveButton(mContext.getResources().getString(android.R.string.ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            mAlertDialog.show();
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, NAME_FOLDER_SAVED);
            folder.mkdir();

            File pdfFile = new File(folder, fileName);
            File pdfFileСрусл = new File(Environment.getExternalStorageDirectory() + "/" +
                    NAME_FOLDER_SAVED + "/" + fileName);
            if (pdfFileСрусл.exists()) return null;
            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }
}
