package com.erickogi14gmail.fdarad.mPicasso;

import android.content.Context;
import android.widget.ImageView;

import com.erickogi14gmail.fdarad.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * Created by BEN SALCIE on 2/4/2017.
 */
public class PicassoClient {
   static int []images={
            R.drawable.web,

    };
    public static void LoadImage(Context c, String url, ImageView img)
    {
        Random random=new Random();

        int r=0;
        if (url!=null && url.length()>0)
        {

            Picasso.with(c).load(url).placeholder(images[r]).into(img);
        }
        else {
            Picasso.with(c).load(images[r]).into(img);
        }
    }


}
