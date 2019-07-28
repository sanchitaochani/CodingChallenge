package axxess.appdev.example.android.codingchallenge;

import android.app.LauncherActivity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    final String LOG_TAG = "Adapter log tag";
    private Context mContext;
    ArrayList<Items> mItems;

    public ImageAdapter(Context context, ArrayList<Items> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if container for GridView exists
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_items, null);
            ImageView imageView = convertView.findViewById(R.id.image);
            ViewHolder viewHolder = new ViewHolder(imageView);
            convertView.setTag(viewHolder);
        }
        Items viewData = (Items)getItem(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag(); //gets the tag of VH

        //Build string using ID
        String imageURL = "https://i.imgur.com/" + viewData.getImageID() + ".jpg";
        Log.i(LOG_TAG, imageURL);
        //Load image using Picasso
        Picasso.get().load(imageURL).into(viewHolder.mImageView);

        return convertView;
    }

    public static class ViewHolder {
        ImageView mImageView;

        ViewHolder(ImageView imageView) {
            mImageView = imageView;
        }
    }
}
