package com.example.locktrust.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.locktrust.Activities.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static com.example.locktrust.Utils.FileUtils.getFilePath;

//import android.support.compat.BuildConfig;


public class Helper {
    private static ProgressDialog dialog;
    private static String encodedImage;
    public static String offset = "0";
    public static String device_id_live = "0";
    public static String device_type = "A";
    private static Dialog mDialog;
    private static RequestOptions requestOptions;
    public static String imageBaseUrl="https://beta.locktrust.com/";//bita
   // public static String imageBaseUrl="https://chimpandzinc.com/locktrust/";//chimp
    //public static String imageBaseUrl="https://locktrust.com/";
    public static String imageurl=imageBaseUrl+"uploads/subject/";
    public static String imagProfileUrl=imageBaseUrl;
    public static String imageurlCources=imageBaseUrl+"uploads/course/";
    public static String imageurlAppCources=imageBaseUrl+"uploads/appcourse/";
    public static String imageurlBanner=imageBaseUrl+"uploads/banner/";

//

   /* public static RequestOptions imageDefault()
    {
        requestOptions= new RequestOptions();

        requestOptions.placeholder(R.drawable.place_holder);
        requestOptions.error(R.drawable.place_holder);
        return requestOptions;
    }
    public static RequestOptions imageUserDefault()
    {
        requestOptions= new RequestOptions();

        requestOptions.placeholder(R.drawable.man);
        requestOptions.error(R.drawable.man);
        return requestOptions;
    }*/

    public static void hidekeybord(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showToast(CharSequence msg, Context ctx) {
        if (ctx != null) {
            //CustomDialogManager.showOkDialog(ctx, msg.toString());
            Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static void Log(String tag, String msg) {
        if (msg == null)
            return;

//        if (BuildConfig.DEBUG)
//            Log.v(tag, msg);

    }

    public static void setMarquee(TextView tv) {
        tv.setSelected(true);
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setSingleLine(true);
        tv.setMarqueeRepeatLimit(-1);
    }

    public static String getCurrentTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        return ts;
    }

    public static boolean isNetworkAvailble(Context ctx) {
        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    @SuppressWarnings("static-access")
    public static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getSystem().getDisplayMetrics().density);
    }

    @SuppressWarnings("static-access")
    public static int pxToDp(Context context, int px) {
        return (int) (px / context.getResources().getSystem()
                .getDisplayMetrics().density);
    }

    public static void showDropDown(View view, ListAdapter adapter, final AdapterView.OnItemClickListener clickListener) {
        final ListPopupWindow window = new ListPopupWindow(view.getContext());
        window.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                window.dismiss();
                clickListener.onItemClick(arg0, arg1, arg2, arg3);
            }
        });

        window.setAdapter(adapter);
        window.setModal(true);
        window.setAnchorView(view);
        window.setWidth(ListPopupWindow.WRAP_CONTENT);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        window.show();
        if (window.isShowing()) {
            window.getListView().setVerticalScrollBarEnabled(false);
            window.getListView().setFastScrollEnabled(true);
        }
    }

    public final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    public static boolean isValidMail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[&@!$%^#+]).{6,}$");

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidConfirmPassword(EditText newpassword, EditText confirmpassword) {
        String newpass = newpassword.getText().toString().trim();
        String confirmpass = confirmpassword.getText().toString().trim();
        if (TextUtils.isEmpty(confirmpass)) {

            return false;
        } else if (!newpass.equals(confirmpass)) {

            return false;
        }
        confirmpassword.setError(null);
        return true;
    }

    public static boolean isValidPasswordLength(EditText editText) {
        String pass = editText.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {

            return false;
        } else if (pass.length() < 6 || pass.length() > 8) {

            return false;
        }
        editText.setError(null);
        return true;
    }

    public final static Pattern WEBSITE_PATTERN = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");

    public static boolean isValidWebsite(String website) {
        return WEBSITE_PATTERN.matcher(website).matches();
    }

    public static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "mremploy");
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

    public static Uri getOutputVideoFileUri() {
        return Uri.fromFile(getOutputVideoFile());
    }

    private static File getOutputVideoFile() {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "smartGolfer");
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        timeStamp = null;
        mediaStorageDir = null;
        return mediaFile;
    }

    public static String showDate(Date date) {

        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        String newDate = "";
        try {
            if (date != null)
                newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String showDateReverse(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String showDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String showDatedash(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String showDatedashDDMMYY(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String showDateFullMonth(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 0);
            newDate = formatedDate.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String showDateFullMonthdetails(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, -1);
            newDate = formatedDate.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static ArrayList<String> sortDates(ArrayList<String> dates) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map<Date, String> dateFormatMap = new TreeMap<>();
        for (String date : dates)
            dateFormatMap.put(f.parse(date), date);
        return new ArrayList<>(dateFormatMap.values());
    }

    public static String formatDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMM yyyy | hh:mm a", Locale.US); //29 Nov 2018 02:42 PM
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return newDate;
    }
    public static String formatDateTransaction(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return newDate;
    }



    public static String formatTime(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("hh:mm a", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return newDate;
    }

    public static Date getTimeInDateFormat(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("HH:mm", Locale.US);
        String newDate = "";
        Date date;
        try {
            date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }




    public static String showDateFullMonthdetailsdash(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, -1);
            newDate = formatedDate.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static Date getTimeFromString(String dateInString) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
            Date date = formatter.parse(dateInString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDateMMMDDYYYY(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        String newDate = "";
        try {
            Date date = formatter.parse(dateInString);
            newDate = formatedDate.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    public static String getCurrentDate() {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String currentDate = formatter.format(c.getTime());
            return currentDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getCurrentTime() {
        try {
            String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

            return currentTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date getDateFromString(String dateInString) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            Date date = formatter.parse(dateInString);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createStringDate(int year, int monthOfYear, int dayOfMonth) {
        String day = "" + dayOfMonth;
        if (day.length() == 1)
            day = "0" + day;
        String month = "" + (monthOfYear + 1);
        if (month.length() == 1)
            month = "0" + month;

        return day + "" + month + "" + year;
    }

    public static String getFileName(String path) {
        File file = new File(path);
        return file.getName();
    }

    public static String getPath(Uri uri, Activity activity) {

        Cursor cursor = null;
        int column_index = 0;
        try {

            String[] projection = {MediaStore.Images.Media.DATA};
            cursor = activity.managedQuery(uri, projection, null, null, null);
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
        return cursor.getString(column_index);
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getImagePathFromGalleryAboveKitkat(Context cntxt, Uri uri) {
        if (uri == null) {
            return null;
        }

        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = null;
        if (Build.VERSION.SDK_INT > 19) {
            try {
                // Will return "image:x*"
                String wholeID = DocumentsContract.getDocumentId(uri);
                // Split at colon, use second item in the array
                if (!wholeID.contains(":"))
                    return null;

                String id = wholeID.split(":")[1];
                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";
                cursor = cntxt.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection, sel, new String[]{id}, null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            cursor = cntxt.getContentResolver().query(uri, projection, null, null, null);
        }
        String path = null;
        try {
            int column_index = cursor
                    .getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index).toString();
            cursor.close();
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return path;
    }


    public static String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA};
    public static String[] mediaColumns = {MediaStore.Video.Media._ID};

    public static String getThumbnailPathForLocalFile(Activity context,
                                                      Uri fileUri) {

        long fileId = getFileId(context, fileUri);

        MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);

        Cursor thumbCursor = null;
        try {

            thumbCursor = context.managedQuery(
                    MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                    thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + " = "
                            + fileId, null, null);

            if (thumbCursor.moveToFirst()) {
                String thumbPath = thumbCursor.getString(thumbCursor
                        .getColumnIndex(MediaStore.Video.Thumbnails.DATA));

                return thumbPath;
            }

        } finally {
        }

        return null;
    }

    public static long getFileId(Activity context, Uri fileUri) {

        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null,
                null);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int id = cursor.getInt(columnIndex);

            return id;
        }

        return 0;
    }





    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

  /*  public static void pd(String msg, Context context) {

        dialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyleRegister);

        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.show();


    }*/


    public static void pd(String title, String msg, Context context) {


        dialog = new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(msg);

        dialog.show();
    }

    public static void pd(String title, String msg, int icon, Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(msg);
        dialog.setIcon(icon);
        dialog.show();
    }

    public static void dismiss_pd() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public static float getDifferenceInSecs(Date startDate, Date endDate) {
        //milliseconds
        float different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        float secondsInMilli = 1000;


        float elapsedSeconds = different / secondsInMilli;

        return elapsedSeconds;
    }


    //encrypts the password with SHA_512 algorithm
    public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String setImageToImageView(String photoPath) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);
        if (imageBitmap != null) {
            try {
                encodedImage = Helper.encodeImage(imageBitmap);
            } catch (Exception e) {
                e.printStackTrace();
                encodedImage = "";
            }
        } else {
            encodedImage = "";
        }
        return encodedImage;
    }


    public static void setImageToImageView(Context context, ImageView profile_imagePic, String photoPath)
    {
        Bitmap imageBitmap = BitmapFactory.decodeFile(photoPath);
        Glide.with(context)
                .asBitmap()
                .load(bitmapToByte(imageBitmap))
                .into(profile_imagePic);

        // profile_imagePic.setImageBitmap(imageBitmap);
    }
    private static byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static double convertLiterToGallons(double literQuentity) {
        double unitGallon = literQuentity * 0.264172;
        return unitGallon;
    }

    public static double convertKmtoMiles(double kmReading) {
        double unitMiles = kmReading * 0.621371;
        return unitMiles;
    }

    public static String urlToImageName(String Url) {
        String filename = Url.substring(Url.lastIndexOf("/") + 1);
        return filename;
    }

    //show date format in dd/MM/yyyy
    public static String DateChangeFormatDisplay(String inputDate) {
        DateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }

//    public static void t(String txt) {
//        Toast.makeText(MyApplication.getAppContext(), txt, Toast.LENGTH_SHORT).show();
//
//    }


/*    //login screen redirection
    public static void functionRedirectToLoginScreen(Activity context)
    {
        context.startActivity(new Intent(context, LoginActivity.class));
        context.finishAffinity();
    }*/

    public static String getPath(final Context context, final Uri uri) {

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (FileUtils.isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);

                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else {
                    Toast.makeText(context, "Could not get file path. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                /*final String id = DocumentsContract.getDocumentId(uri);
                if (!TextUtils.isEmpty(id)) {
                    if (id.startsWith("raw:")) {
                        return id.replaceFirst("raw:", "");
                    }
                    try {
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                        return getDataColumn(context, contentUri, null, null);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);*/
                String fileName = getFilePath(context, uri);
                if (fileName != null) {
                    return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
                }

                String id = DocumentsContract.getDocumentId(uri);
                if (id.startsWith("raw:")) {
                    id = id.replaceFirst("raw:", "");
                    File file = new File(id);
                    if (file.exists())
                        return id;
                }

                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                String id = docId.split(":")[1];

                String[] column = {MediaStore.Images.Media.DATA};
                String sel = MediaStore.Images.Media._ID + "=?";

                Cursor cursor = context.getContentResolver().
                        query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                column, sel, new String[]{id}, null);

                String filePath = "";

                int columnIndex = cursor.getColumnIndex(column[0]);

                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }

                cursor.close();

                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    contentUri = MediaStore.Files.getContentUri("external");
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }


        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static void  functionShare(Context context, String strShareEpisodeLink) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "FBK reality show");
        share.putExtra(Intent.EXTRA_TEXT, strShareEpisodeLink);

        context.startActivity(Intent.createChooser(share, "FBK reality"));
    }

    public static void setLocale(Context context, String lang) {

            Locale myLocale = new Locale(lang);
            Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
//            Intent refresh = new Intent(context, activity.class);
//            context.startActivity(refresh);
//            finish();
        }

        public static String convertRupeeToPound(String strRupee)
        {
            double rupeeValue=Double.parseDouble(strRupee);
            double poundValue=rupeeValue/110;

            return String.valueOf(poundValue);
        }

    //check is string empty of null
    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.trim().isEmpty())
            return false;
        return true;
    }

    //day add function
    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, i);
        return cal.getTime();
    }

    public static String getDisplayDateTime(String orgDate) {
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM ");

        try {
            Date datestartdate = inputFormat1.parse(orgDate);
            return inputFormat.format(datestartdate);
        } catch (Exception e) {
            return orgDate;
        }
    }
    public static String getDateBookTutorialTime(String orgDate) {
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMMM, yyyy");

        try {
            Date datestartdate = inputFormat1.parse(orgDate);
            return inputFormat.format(datestartdate);
        } catch (Exception e) {
            return orgDate;
        }
    }

    public static void functionDeleteCourseCart(Context context) {

     /*   class GetDeleteAllCoursesFromCart extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .courseDao()
                        .deleteCartCourses();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        }

        GetDeleteAllCoursesFromCart gt = new GetDeleteAllCoursesFromCart();
        gt.execute();*/
    }

    public static void removeUserDetails(Activity context) {
        AppDataHolder.getSession(context).setAccessToken("");
        AppDataHolder.getSession(context).setUserId("");
        AppDataHolder.getSession(context).setUserName("");
        AppDataHolder.getSession(context).setUserContact("");
        AppDataHolder.getSession(context).setUserEmail("");
        AppDataHolder.getSession(context).setUserImage("");
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Helper.functionDeleteCourseCart(context);//remove all the cart courses
        context.finishAffinity();
       // context.startActivity(new Intent(context, SelectionLoginActivity.class));
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    }

