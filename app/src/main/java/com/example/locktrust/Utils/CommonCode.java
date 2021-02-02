package com.example.locktrust.Utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//TypeToken<SettingData> token = new TypeToken<SettingData>() {};
//settingData = new Gson().fromJson(strSession, token.getType());
@SuppressLint("SimpleDateFormat")
public class CommonCode {

    private Context context;
    static ProgressDialog progressBar;

    public CommonCode(Context context) {
        this.context = context;
    }

    /**
     * This is used to check weather Internet is on or off
     *
     * @return true if internet is on else return false
     * @author Swapnil 6:08:55 PM
     */




    public static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    public static File getOutputMediaFile() {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "durban");
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return mediaFile;
    }


}
