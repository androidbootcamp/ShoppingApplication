package androidplugins.imagefetcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import androidplugins.Callback;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class ImageFetcher extends AsyncTask<String, Void, Bitmap> {
  private Callback<Bitmap> bitmapCallback;
  private Context context;
  private static final String IMAGE_CACHING_PREFERENCES_FILE_NAME = "ImageCachingPreferences";

  public ImageFetcher(Callback<Bitmap> bitmapCallback, Context context) {
    this.bitmapCallback = bitmapCallback;
    this.context = context;
  }

  @Override
  protected Bitmap doInBackground(String... urls) {
    String imageUrl = urls[0];
    SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
    String fileName = cachingSharedPreferences.getString(imageUrl, "");
    Bitmap imageBitmap = null;
    try {
      imageBitmap = !fileName.isEmpty()? BitmapFactory.decodeStream(inputFileStream(fileName)):downloadAndCacheImage(imageUrl);
    } catch (FileNotFoundException e) {
      imageBitmap = downloadAndCacheImage(imageUrl);
    }
    return imageBitmap;
  }

  private FileInputStream inputFileStream(String fileName) throws FileNotFoundException {
    if (isExternalStorageUsed()){
      return new FileInputStream(context.getExternalCacheDir() + "/" + fileName);
    }
    else {
      return new FileInputStream(context.getCacheDir() + "/" + fileName);
    }
  }

  private boolean isExternalStorageUsed(){
    SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
    String isExternalMediaUsedKey = "isExternalMediaUsed";
    if (!cachingSharedPreferences.contains(isExternalMediaUsedKey)) {
      boolean mExternalStorageAvailable = false;
      boolean mExternalStorageWriteable = false;
      String state = Environment.getExternalStorageState();

      if (Environment.MEDIA_MOUNTED.equals(state)) {
        // We can read and write the media
        mExternalStorageAvailable = mExternalStorageWriteable = true;
      } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        // We can only read the media
        mExternalStorageAvailable = true;
        mExternalStorageWriteable = false;
      } else {
        // Something else is wrong. It may be one of many other states, but all we need
        //  to know is we can neither read nor write
        mExternalStorageAvailable = mExternalStorageWriteable = false;
      }
      cachingSharedPreferences.edit().putBoolean(isExternalMediaUsedKey,mExternalStorageAvailable && mExternalStorageWriteable).commit();
    }
    return cachingSharedPreferences.getBoolean(isExternalMediaUsedKey, false);
  }

  private Bitmap downloadAndCacheImage(String imageUrl) {
    Bitmap imageBitmap = null;
    HttpClient httpClient = new DefaultHttpClient();
    try {
      URL url = new URL(imageUrl);
      URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
      HttpRequestBase httpRequest = new HttpGet(uri);
      HttpResponse httpResponse = httpClient.execute(httpRequest);
      StatusLine statusLine = httpResponse.getStatusLine();
      int statusCode = statusLine.getStatusCode();
      if (statusCode == HttpURLConnection.HTTP_OK) {
        HttpEntity httpEntity = httpResponse.getEntity();
        InputStream content = httpEntity.getContent();
        imageBitmap = BitmapFactory.decodeStream(content);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(imageBitmap != null) cacheImage(imageBitmap, imageUrl);
    return imageBitmap;
  }

  private void cacheImage(Bitmap result, String imageUrl) {
    String fileName = "image_" + System.currentTimeMillis() + ".png";
    try {
      ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
      result.compress(Bitmap.CompressFormat.PNG, 90, imageBytes);
      OutputStream imageOutputStream = outputFileStream(fileName);
      imageOutputStream.write(imageBytes.toByteArray());
      SharedPreferences cachingSharedPreferences = context.getSharedPreferences(IMAGE_CACHING_PREFERENCES_FILE_NAME, 0);
      SharedPreferences.Editor editor = cachingSharedPreferences.edit();

      editor.putString(imageUrl, fileName);
      editor.commit();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private FileOutputStream outputFileStream(String fileName) throws FileNotFoundException {
    if (isExternalStorageUsed())
      return new FileOutputStream(context.getExternalCacheDir() + "/" + fileName);
    else
      return new FileOutputStream(context.getCacheDir() + "/" + fileName);
  }

  @Override
  protected void onPostExecute(Bitmap bitmap) {
    super.onPostExecute(bitmap);
    bitmapCallback.execute(bitmap);
  }
}
