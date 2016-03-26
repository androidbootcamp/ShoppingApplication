package bootcamp.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.R;
import bootcamp.android.models.Product;

public class ShoppingItemsListAdapter extends BaseAdapter {
  private final Context context;
  public List<Product> products = new ArrayList<>();

  public ShoppingItemsListAdapter(Context context, List<Product> products){
    this.context = context;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public Object getItem(int position) {
    return products.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
    }
    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
    TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
    Product product = products.get(position);
    titleTextView.setText(product.getTitle());
    imageView.setImageResource(product.getDrawable());

    return convertView;
  }

}
