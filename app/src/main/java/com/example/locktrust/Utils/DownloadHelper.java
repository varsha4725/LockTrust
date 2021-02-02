package com.example.locktrust.Utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownloadHelper {

    private DownloadManager downloadManager;
    private long downloadReference;


    public DownloadHelper(Context context, String url, String imageName) {

        context.registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        context.registerReceiver(onNotificationClick,
                new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
        downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);

        //Restrict the types of networks over which this download may proceed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(true);
        //Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle(imageName);
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, imageName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Enqueue a new download and same the referenceId
        downloadReference = downloadManager.enqueue(request);
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            Toast.makeText(ctxt, "download complete!", Toast.LENGTH_LONG).show();
            ctxt.unregisterReceiver(onComplete);
        }
    };

    BroadcastReceiver onNotificationClick = new BroadcastReceiver() {
        public void onReceive(Context ctxt, Intent intent) {
            ctxt.unregisterReceiver(onNotificationClick);
        }
    };
}
