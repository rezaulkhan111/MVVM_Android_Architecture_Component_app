package dev.machine.code.mvvm_application.common;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
/**
 * Author: Rezaul Khan
 * github: https://github.com/rezaulkhan111
 */
public class Constants {
    public static void LoadImage(String imageUrl, ImageView imageId) {
        if (!imageUrl.equals("")) {
            try {
                Picasso.get().load(imageUrl).networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE).into(imageId);
            } catch (Exception ex) {
                Log.e("message", ex.getMessage());
            }
        }
    }
}
