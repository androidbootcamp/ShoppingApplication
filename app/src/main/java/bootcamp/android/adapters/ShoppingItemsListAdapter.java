package bootcamp.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidplugins.Callback;
import androidplugins.imagefetcher.ImageFetcher;
import bootcamp.android.R;
import bootcamp.android.activities.ProductDetailsActivity;
import bootcamp.android.constants.Constants;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.CURRENT_PRODUCT_KEY;
import static bootcamp.android.constants.Constants.IMAGE_URL_KEY;
import static bootcamp.android.constants.Constants.PRODUCTS_KEY;

public class ShoppingItemsListAdapter extends RecyclerView.Adapter<ShoppingItemsListAdapter.ShoppingItemViewHolder>{
  private ArrayList<Product> products;

  public ShoppingItemsListAdapter(ArrayList<Product> products) {
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
    ImageFetcher imageFetcher = new ImageFetcher(bitmapCallback(holder.imageView), holder.imageView.getContext());
    imageFetcher.execute(product.getImageUrl());
  }

  private Callback<Bitmap> bitmapCallback(final ImageView imageView) {
    return new Callback<Bitmap>() {
      @Override
      public void execute(Bitmap object) {
        imageView.setImageBitmap(object);
      }
    };
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
      intent.putParcelableArrayListExtra(PRODUCTS_KEY, products);
      intent.putExtra(CURRENT_PRODUCT_KEY, getAdapterPosition());
      context.startActivity(intent);

    }
  }
}
