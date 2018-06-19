package bootcamp.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bootcamp.android.R;
import bootcamp.android.activities.ProductDetailsActivity;
import bootcamp.android.constants.Constants;
import bootcamp.android.databinding.ProductBinding;
import bootcamp.android.models.Product;

public class ShoppingItemsListAdapter extends RecyclerView.Adapter<ShoppingItemsListAdapter.ShoppingItemViewHolder> {
  private ArrayList<Product> products;

  public ShoppingItemsListAdapter(ArrayList<Product> products) {
    this.products = products;
  }

  @Override
  public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      ProductBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product, parent, false);
      return new ShoppingItemViewHolder(itemBinding);
  }

  @Override
  public void onBindViewHolder(ShoppingItemViewHolder holder, int position) {
    Product product = products.get(position);
    holder.productBinding.setProduct(product);
    holder.productBinding.executePendingBindings();
    Picasso.with(holder.productBinding.imageView.getContext()).load(product.getImageUrl()).into(holder.productBinding.imageView);
  }

  @Override
  public int getItemCount() {
    return products.size();
  }

  public class ShoppingItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ProductBinding productBinding;

    public ShoppingItemViewHolder(ProductBinding productBinding) {
      super(productBinding.getRoot());
      this.productBinding = productBinding;
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Context context = view.getContext();
      Intent intent = new Intent(context.getApplicationContext(), ProductDetailsActivity.class);
      intent.putExtra(Constants.INDEX, getAdapterPosition());
      intent.putExtra(Constants.PRODUCTS_KEY, products);
      context.startActivity(intent);
    }
  }
}
