package bootcamp.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bootcamp.android.R;
import bootcamp.android.constants.Constants;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.*;
import static bootcamp.android.constants.Constants.PRODUCT_KEY;


public class ProductFragment extends Fragment {

  public static ProductFragment newInstance(Product product) {
    ProductFragment productFragment = new ProductFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(PRODUCT_KEY, product);
    productFragment.setArguments(bundle);
    return productFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View productDetailsView = inflater.inflate(R.layout.product_details, container, false);
    Product product = getArguments().getParcelable(PRODUCT_KEY);
    TextView imageTitle = productDetailsView.findViewById(R.id.product_title);
    imageTitle.setText(product.getTitle());
    ImageView imageView = productDetailsView.findViewById(R.id.product_image);
    Picasso.get().load(product.getImageUrl()).into(imageView);
    TextView issueDescription = productDetailsView.findViewById(R.id.product_description);
    issueDescription.setText(product.getDescription());
    return productDetailsView;
  }
}
