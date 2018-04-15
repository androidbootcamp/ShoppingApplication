package bootcamp.android.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import bootcamp.android.R;
import bootcamp.android.adapters.ProductPagerAdapter;
import bootcamp.android.constants.Constants;
import bootcamp.android.models.Product;

public class ProductDetailsActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.product_details_container);
    ViewPager viewPager = findViewById(R.id.viewpager);
    int currentIndex = getIntent().getIntExtra(Constants.INDEX, -1);
    ArrayList<Product> productsList = getIntent().getParcelableArrayListExtra(Constants.PRODUCTS_KEY);
    viewPager.setAdapter(new ProductPagerAdapter(getSupportFragmentManager(), productsList));
    viewPager.setCurrentItem(currentIndex);
  }

}
