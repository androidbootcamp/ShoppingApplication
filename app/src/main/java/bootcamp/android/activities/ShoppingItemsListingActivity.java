package bootcamp.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import androidplugins.Callback;
import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import static bootcamp.android.constants.Constants.*;

public class ShoppingItemsListingActivity extends Activity {

  private ArrayList<Product> products;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    if(savedInstanceState == null || savedInstanceState.getParcelableArrayList(PRODUCTS_KEY) == null) {
      final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Loading...", true, true);
      ProductRepository productRepository = new ProductRepository();
      productRepository.getProducts(productsCallback(recyclerView, progressDialog));
    } else {
      this.products = savedInstanceState.getParcelableArrayList(PRODUCTS_KEY);
      renderProducts(recyclerView, products);
    }
  }


  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if(products != null) outState.putParcelableArrayList(PRODUCTS_KEY, products);
  }

  private Callback<ArrayList<Product>> productsCallback(final RecyclerView recyclerView, final ProgressDialog progressDialog) {
    return new Callback<ArrayList<Product>>() {
      public void execute(ArrayList<Product> products) {
        ShoppingItemsListingActivity.this.products = products;
        renderProducts(recyclerView, products);
        progressDialog.dismiss();
      }
    };
  }
  private void renderProducts(RecyclerView recyclerView, ArrayList<Product> products) {
    recyclerView.setAdapter(new ShoppingItemsListAdapter(products));
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
  }

}
