package bootcamp.android.repositories;

import java.util.ArrayList;

import androidplugins.Callback;
import bootcamp.android.models.Product;
import bootcamp.android.services.ProductsParser;

public class ProductRepository {

  public void getProducts(Callback<ArrayList<Product>> productsCallback) {
    new androidplugins.contentfetcher.ContentFetcher(responseCallback(productsCallback), "GET").execute("https://androidbootcamp.github.io/staticcontent/shoppingapplication/products_json.json");
  }

  private Callback<String> responseCallback(final Callback<ArrayList<Product>> productsCallback) {
    return new Callback<String>() {
      @Override
      public void execute(String strJSONData) {
        productsCallback.execute(new ProductsParser().parseProducts(strJSONData));
      }
    };
  }

}