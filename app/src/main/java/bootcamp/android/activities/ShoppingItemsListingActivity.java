package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

public class ShoppingItemsListingActivity extends Activity {

  private List<Product> products;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);

    ProductRepository productRepository = new ProductRepository();
    products = productRepository.getProducts();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setAdapter(new ShoppingItemsListAdapter(products));
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    progressDialog.dismiss();
  }
}
