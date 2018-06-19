package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bootcamp.android.constants.Constants.*;

public class ShoppingItemsListingActivity extends Activity {

  private ArrayList<Product> products;
  private ProductRepository productRepository;

  public ShoppingItemsListingActivity() {
    super();
    productRepository = new ProductRepository();
  }

  ShoppingItemsListingActivity(ProductRepository productRepository) {
    super();
    this.productRepository = productRepository;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    if(savedInstanceState == null || savedInstanceState.getParcelableArrayList(PRODUCTS_KEY) == null){
      final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);
      Callback<ArrayList<Product>> callback = productsCallback(progressDialog);
      productRepository.getProducts(callback);
    } else {
      products = savedInstanceState.getParcelableArrayList(PRODUCTS_KEY);
      renderProducts();
    }
  }

  @NonNull
  private Callback<ArrayList<Product>> productsCallback(final ProgressDialog progressDialog) {
    return new Callback<ArrayList<Product>>() {
      @Override
      public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
        products = response.body();
        renderProducts();
        progressDialog.dismiss();
      }

      @Override
      public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
        Toast.makeText(ShoppingItemsListingActivity.this, "Failed to get products", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
      }
    };
  }

  private void renderProducts() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setAdapter(new ShoppingItemsListAdapter(products));
    recyclerView.setLayoutManager(new GridLayoutManager(ShoppingItemsListingActivity.this, 3));
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if(products != null) outState.putParcelableArrayList(PRODUCTS_KEY, products);
  }
}
