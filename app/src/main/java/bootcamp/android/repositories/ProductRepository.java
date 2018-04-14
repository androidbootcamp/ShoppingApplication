package bootcamp.android.repositories;

import java.util.List;

import bootcamp.android.models.Product;
import bootcamp.android.services.ContentDownloader;
import bootcamp.android.services.ProductsParser;

public class ProductRepository {

  public List<Product> getProducts() {
    String strJSONData = new ContentDownloader().fetchResponse("https://androidbootcamp.github.io/staticcontent/shoppingapplication/products_json.json");
    return new ProductsParser().parseProducts(strJSONData);
  }
}