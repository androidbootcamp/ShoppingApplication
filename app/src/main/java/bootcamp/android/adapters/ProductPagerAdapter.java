package bootcamp.android.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import bootcamp.android.fragments.ProductFragment;
import bootcamp.android.models.Product;

public class ProductPagerAdapter extends FragmentStatePagerAdapter {

  private final ArrayList<Product> products;

  public ProductPagerAdapter(FragmentManager fm, ArrayList<Product> products) {
    super(fm);
    this.products = products;
  }

  @Override
  public Fragment getItem(int position) {
    return ProductFragment.newInstance(products.get(position));
  }

  @Override
  public int getCount() {
    return products.size();
  }
}
