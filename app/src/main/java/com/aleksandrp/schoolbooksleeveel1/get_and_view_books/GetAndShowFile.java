package com.aleksandrp.schoolbooksleeveel1.get_and_view_books;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;
import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;
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
    private  String name;
    DBImpl db;


    public GetAndShowFile(Context context) {
        this.mContext = context;
        db = new DBImpl(mContext);
    }


    public void downloadFileFromReppositoria(String fileUri, String nameBook) {
        String addedPart = "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=";
        fileUri = addedPart + fileUri.substring(fileUri.indexOf("=") + 1);
        this.name = nameBook;
        String fileName = nameBook + ".pdf";
        new DownloadFile().execute(fileUri, fileName);
    }

    public void viewFile(String nameFile) {
        nameFile += ".pdf";
//        nameFile = "maven.pdf";
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
        } else {
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
            mAlertDialog.setTitle("Такого файла нет в библеотеке");
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
            BooksFragment.getInstance().updateList();
            GDZFragment.getInstance().update();
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

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            db.putFlagLoader(name, name);
        }
    }
}
