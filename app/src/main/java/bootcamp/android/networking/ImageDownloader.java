package bootcamp.android.networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageDownloader {
  OkHttpClient client = new OkHttpClient();

  public Bitmap downloadImage(String url) {

    Request request = new Request.Builder()
            .url(url)
            .build();

    Bitmap bitmap = null;
    InputStream in = null;
    Response response = null;
    try {
      response = client.newCall(request).execute();
      in = response.body().byteStream();
      bitmap = BitmapFactory.decodeStream(in);
      in.close();
      return bitmap;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


