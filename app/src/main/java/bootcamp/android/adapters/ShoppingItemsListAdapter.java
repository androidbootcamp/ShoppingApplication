package bootcamp.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.activities.ProductDetailsActivity;
import bootcamp.android.constants.Constants;
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
    Picasso.with(holder.imageView.getContext()).load(product.getImageUrl()).into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ShoppingItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView titleView;
    private final ImageView imageView;

    public ShoppingItemViewHolder(View itemView) {
      super(itemView);
      titleView = (TextView) itemView.findViewById(R.id.title);
      imageView = (ImageView) itemView.findViewById(R.id.imageView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Context context = view.getContext();
      Intent intent = new Intent(context.getApplicationContext(), ProductDetailsActivity.class);
      Product product = products.get(getAdapterPosition());
      intent.putExtra(Constants.PRODUCT_KEY, product);
      context.startActivity(intent);

    }
  }
}
