package in.co.discoveideas.presentsir;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by Belal on 12/22/2015.
 */
public class CustomAdapter extends BaseAdapter {

    //Imageloader to load images
    private ImageLoader imageLoader;

    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> images;
    private ArrayList<String> names;

    public CustomAdapter(Context context, ArrayList<String> images, ArrayList<String> names){
        //Getting all the values
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creating a linear layout
        ImageView imageView;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //NetworkImageView
        NetworkImageView networkImageView = new NetworkImageView(context);

        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.drawable.user_icon, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
        networkImageView.setImageUrl(images.get(position),imageLoader);
       //networkImageView.setBackgroundColor(Color.parseColor("#ffffff"));
        //Creating a textview to show the title
        TextView textView = new TextView(context);
        textView.setText(names.get(position));
        textView.setPadding(55,0,0,0);
        textView.setTextColor(Color.rgb(0,0,0));



       // textView.setGravity(Gravity.END);

        //Scaling the imageview
        //networkImageView.setScaleType(imageView.ScaleType.CENTER);
        networkImageView.setLayoutParams(new GridView.LayoutParams(120,120));
        //networkImageView.setScaleType(ImageView.ScaleType.CENTER);
        networkImageView.setPadding(50, 8, 0, 8);
        //Adding views to the layout

        linearLayout.addView(networkImageView);
        linearLayout.addView(textView);

        //Returnint the layout
        return linearLayout;
    }
}