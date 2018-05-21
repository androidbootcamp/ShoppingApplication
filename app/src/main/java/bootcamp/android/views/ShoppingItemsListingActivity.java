package bootcamp.android.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.presenters.ShoppingItemsListPresenter;
import bootcamp.android.repositories.ProductRepository;
import bootcamp.android.viewmodel.ShoppingItemsViewModel;
import retrofit2.Callback;

import static bootcamp.android.constants.Constants.DESCRIPTION_KEY;
import static bootcamp.android.constants.Constants.IMAGE_URL_KEY;
import static bootcamp.android.constants.Constants.TITLE_KEY;

public class ShoppingItemsListingActivity extends Activity implements ShoppingItemsListView {

  public static final int PRODUCT_DETAILS_REQUEST_CODE = 9283;
  private ShoppingItemsListPresenter presenter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    presenter = new ShoppingItemsListPresenter(this, new ProductRepository());
    presenter.init();
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode == RESULT_OK){
      Product cartProduct = new Product(data.getStringExtra(TITLE_KEY),
              data.getStringExtra(DESCRIPTION_KEY),
              data.getStringExtra(IMAGE_URL_KEY));
      presenter.addItemInCart(cartProduct);
    }
  }

  @Override
  public void renderProducts(ShoppingItemsViewModel products) {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setAdapter(new ShoppingItemsListAdapter(products,presenter));
    recyclerView.setLayoutManager(new GridLayoutManager(ShoppingItemsListingActivity.this, 3));
  }

  @Override
  public void showNetworkError() {
    Toast.makeText(ShoppingItemsListingActivity.this, "Failed to get products", Toast.LENGTH_SHORT).show();

  }
}
