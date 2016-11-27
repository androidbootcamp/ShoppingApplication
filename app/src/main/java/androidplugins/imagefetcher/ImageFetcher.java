package androidplugins.imagefetcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import androidplugins.Callback;

public class ImageFetcher extends AsyncTask<String, Void, Bitmap> {
  private Callback<Bitmap> bitmapCallback;
  private Context context;

  public ImageFetcher(Callback<Bitmap> bitmapCallback, Context context) {
    this.bitmapCallback = bitmapCallback;
    this.context = context;
  }

  @Override
  protected Bitmap doInBackground(String... urls) {
    String imageUrl = urls[0];
    Bitmap imageBitmap = null;
    imageBitmap = download(imageUrl);
    return imageBitmap;
  }

  private Bitmap download(String imageUrl) {
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
    return imageBitmap;
  }

  @Override
  protected void onPostExecute(Bitmap bitmap) {
    super.onPostExecute(bitmap);
    bitmapCallback.execute(bitmap);
  }
}

