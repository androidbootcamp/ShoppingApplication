package bootcamp.android.repositories;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.models.Product;
import bootcamp.android.networking.ContentDownloader;
import bootcamp.android.networking.ProductsParser;
import bootcamp.android.networking.RetrofitCore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public class ProductRepository {

  ProductRequester productRequester = new RetrofitCore().create(ProductRequester.class);

  public List<Product> getProductsSync() {
    String strJSONData = new ContentDownloader().fetchResponse("https://androidbootcamp.github.io/staticcontent/shoppingapplication/products_json.json");
    return new ProductsParser().parseProducts(strJSONData);
  }

  public void getProducts(Callback<ArrayList<Product>> callback){
    Call<ArrayList<Product>> productsCall = productRequester.products();
    productsCall.enqueue(callback);
  }


  interface ProductRequester{
    @GET("products_json.json") Call<ArrayList<Product>> products();
  }
}