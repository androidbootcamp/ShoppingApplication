package bootcamp.android.networking;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContentDownloader {

  OkHttpClient client = new OkHttpClient();

  public String fetchResponse(String endpoint) {

    Request request = new Request.Builder()
            .url(endpoint)
            .build();

    Response response = null;
    try {
      response = client.newCall(request).execute();
      return response.body().string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}

