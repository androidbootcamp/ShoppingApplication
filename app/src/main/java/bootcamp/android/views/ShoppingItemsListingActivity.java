package bootcamp.android.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingItemsListingActivity extends Activity {

  private List<Product> products;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);

    Callback<List<Product>> callback = productsCallback(progressDialog);

    ProductRepository productRepository = new ProductRepository();
    productRepository.getProducts(callback);
  }

  @NonNull
  private Callback<List<Product>> productsCallback(final ProgressDialog progressDialog) {
    return new Callback<List<Product>>() {
      @Override
      public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
        products = response.body();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ShoppingItemsListAdapter(products));
        recyclerView.setLayoutManager(new GridLayoutManager(ShoppingItemsListingActivity.this, 3));
        progressDialog.dismiss();
      }

      @Override
      public void onFailure(Call<List<Product>> call, Throwable t) {
        Toast.makeText(ShoppingItemsListingActivity.this, "Failed to get products", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
      }
    };
  }
}
