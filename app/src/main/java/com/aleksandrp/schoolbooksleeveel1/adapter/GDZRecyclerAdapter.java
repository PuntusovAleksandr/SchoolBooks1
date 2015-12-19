package com.aleksandrp.schoolbooksleeveel1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksandrp.schoolbooksleeveel1.R;
import com.aleksandrp.schoolbooksleeveel1.db.entity.Book;
import com.aleksandrp.schoolbooksleeveel1.db.functions_db.DBImpl;
import com.aleksandrp.schoolbooksleeveel1.dialods.ContextDialog;
import com.aleksandrp.schoolbooksleeveel1.get_and_view_books.GetAndShowFile;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Aleksandr on 26.11.2015.
 */
public class GDZRecyclerAdapter extends
        RecyclerView.Adapter<GDZRecyclerAdapter.TimeViewHolder>
        implements StaticValues {

    private ArrayList<Book> listItems;
    private Context context;
    private GetAndShowFile getFile;
    private AlertDialog.Builder ad;


    public GDZRecyclerAdapter(ArrayList<Book> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;

        TextView mTvName;
        ImageView mSmallIcon;
        ImageView mIconStatus;


        TimeViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_item);
            mTvName = (TextView) itemView.findViewById(R.id.tve_name);
            mSmallIcon = (ImageView) itemView.findViewById(R.id.iv_small_icon);
            mIconStatus = (ImageView) itemView.findViewById(R.id.iv_icon_status);
        }
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TimeViewHolder holder, int position) {
        final Book book = listItems.get(position);

        holder.mTvName.setText(book.getNameBook());
        final int resSmall = book.getSmallIcon();
        String resStatus = book.getIconStatus();

        if (resSmall != 0) {
            holder.mSmallIcon.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),
                    resSmall, 100, 100));
        }

        if (!resStatus.equals("0")) {
            holder.mIconStatus.setImageResource(R.drawable.pdf_500x500_down);
            holder.mIconStatus.setEnabled(false);
        } else {
            holder.mIconStatus.setImageResource(R.drawable.download_pdf_70x70);
            holder.mIconStatus.setEnabled(true);
        }

        holder.mSmallIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBook = book.getNameBook();
                ContextDialog contextDialog = new ContextDialog(context, nameBook, resSmall);
                contextDialog.show();
            }
        });

        holder.mIconStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = book.getLinkDownload();
                String nameBook = book.getNameBook();
                getFile = new GetAndShowFile(context);
                getFile.downloadFileFromReppositoria(link, nameBook);
            }
        });

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBook = book.getNameBook();
                getFile = new GetAndShowFile(context);
                getFile.viewFile(nameBook);
            }
        });

        holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!holder.mIconStatus.isEnabled()) {
                    ad = new AlertDialog.Builder(context);
                    ad.setTitle("Удаление файла книги!");  // заголовок
                    ad.setMessage(context.getString(R.string.want_like_delete_book) +
                            book.getNameBook() + "?"); // сообщение
                    ad.setIcon(android.R.drawable.ic_menu_info_details);
                    ad.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            deleteFileBook(book);
                        }
                    });
                    ad.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    ad.show();
                }
                return true;
            }
        });
    }

    private void deleteFileBook(Book book) {
        deleteFileFromSD(book.getNameBook());
        DBImpl.getInstanceDB(context).putFlagLoader("0", book.getNameBook());
    }

    private void deleteFileFromSD(String nameBook) {
        nameBook += ".pdf";
        String path = Environment.getExternalStorageDirectory() + "/" +
                NAME_FOLDER_SAVED + "/" + nameBook;
        File pdfFile = new File(path);  // -> filename = maven.pdf
        if (pdfFile.exists()) {
            try {
                pdfFile.delete();
                Toast.makeText(context, "Файл " + nameBook + " удалегн", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}