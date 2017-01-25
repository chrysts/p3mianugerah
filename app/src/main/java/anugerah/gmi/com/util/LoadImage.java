package anugerah.gmi.com.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by christian.simon on 04/11/2016.
 */
public class LoadImage extends AsyncTask<String, String, Bitmap> {


    private Bitmap bitmap;
    private ImageView imageView;

    public LoadImage( ImageView imageView){

        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    protected Bitmap doInBackground(String... args) {
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {

        if(image != null){
            imageView.setImageBitmap(image);
        }else{

        }
    }
}
