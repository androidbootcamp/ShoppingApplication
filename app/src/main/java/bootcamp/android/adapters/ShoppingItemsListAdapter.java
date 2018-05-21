package bootcamp.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bootcamp.android.R;
import bootcamp.android.presenters.ShoppingItemsListPresenter;
import bootcamp.android.viewmodel.ProductViewModel;
import bootcamp.android.viewmodel.ShoppingItemsViewModel;
import bootcamp.android.views.ProductDetailsActivity;
import bootcamp.android.constants.Constants;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.IMAGE_URL_KEY;
import static bootcamp.android.views.ShoppingItemsListingActivity.PRODUCT_DETAILS_REQUEST_CODE;

public class ShoppingItemsListAdapter extends RecyclerView.Adapter<ShoppingItemsListAdapter.ShoppingItemViewHolder>{
    private final ShoppingItemsListPresenter presenter;
    private ShoppingItemsViewModel shoppingItemsViewModel;

  public ShoppingItemsListAdapter(ShoppingItemsViewModel shoppingItemsViewModel, ShoppingItemsListPresenter presenter) {
    this.shoppingItemsViewModel = shoppingItemsViewModel;
    this.presenter= presenter;
  }

  @Override
  public ShoppingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View productItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
    return new ShoppingItemViewHolder(productItemView);
  }

  @Override
  public void onBindViewHolder(ShoppingItemViewHolder holder, int position) {
    ProductViewModel product = shoppingItemsViewModel.getItemsList().get(position);
      if(product.getInCart())
          holder.itemView.setBackgroundColor(0xFF00FF00);
      else
          holder.itemView.setBackgroundColor(0x00000000);
    holder.titleView.setText(product.getTitle());
    Picasso.get().load(product.getImageUrl()).into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return shoppingItemsViewModel.getItemsList().size();
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
      ProductViewModel productViewModel = shoppingItemsViewModel.getItemsList().get(getAdapterPosition());
      String title = productViewModel.getTitle();
      intent.putExtra(Constants.TITLE_KEY, title);
      //assuming product title is unique
      Product productFromTitle = presenter.getProductFromTitle(title);
      intent.putExtra(Constants.DESCRIPTION_KEY, productFromTitle.getDescription());
      intent.putExtra(IMAGE_URL_KEY, productFromTitle.getImageUrl());
      ((Activity)context).startActivityForResult(intent, PRODUCT_DETAILS_REQUEST_CODE);

    }
  }
}
