package bootcamp.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.models.Product;

public class ShoppingItemsListAdapter extends RecyclerView.Adapter<ShoppingItemsListAdapter.ShoppingItemViewHolder>{
  private List<Product> products;

  public ShoppingItemsListAdapter(List<Product> products) {
    this.products = products;
  }

  @Override
  public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View productItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
    return new ShoppingItemViewHolder(productItemView);
  }

  @Override
  public void onBindViewHolder(ShoppingItemViewHolder holder, int position) {
    Product product = products.get(position);
    holder.titleView.setText(product.getTitle());
    holder.imageView.setImageResource(product.getDrawable());
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ShoppingItemViewHolder extends RecyclerView.ViewHolder{

    private final TextView titleView;
    private final ImageView imageView;

    public ShoppingItemViewHolder(View itemView) {
      super(itemView);
      titleView = (TextView) itemView.findViewById(R.id.title);
      imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
  }
}
