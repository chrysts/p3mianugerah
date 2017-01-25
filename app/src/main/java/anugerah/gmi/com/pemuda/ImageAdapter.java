package anugerah.gmi.com.pemuda;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import anugerah.gmi.com.model.LogosViewModel;
import anugerah.gmi.com.util.LoadImage;

/**
 * Created by christian.simon on 30/10/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private LogosFragment mContext;
    private List<LogosViewModel> logosViewModels;

    public ImageAdapter(LogosFragment c, List<LogosViewModel> logosViewModels) {
        mContext = c;
        this.logosViewModels = logosViewModels;
    }

    public int getCount() {
        return logosViewModels.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext.getContext());
            imageView.setLayoutParams(new GridView.LayoutParams(200, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);

        try {
            //You can further optimize the code, by storing the bitmap is some
            //cache, so when the user scrolls away, it doesn't throw the image
            //away but keeps it for reuse once they scroll back.
           // new LoadImage(imageView).execute(logosViewModels.get(position).getImageUrl());
            Picasso.with(mContext.getContext()) //
                    .load(logosViewModels.get(position).getImageUrl()) //
                    .placeholder(R.drawable.logos24) //
                    .error(R.drawable.like) //
                    .fit() //
                    .tag(mContext.getContext()) //
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageView;
    }

    // references to our images
}
