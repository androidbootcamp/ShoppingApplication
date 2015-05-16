package bootcamp.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bootcamp.android.R;
import bootcamp.android.models.Product;

import static bootcamp.android.constants.Constants.*;


public class ProductDetailsActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.product_details_container);

    Bundle extraArguments = getIntent().getExtras();
    ViewGroup parent = (ViewGroup) findViewById(R.id.product_details_container);
    parent.addView(getPopulatedView(getLayoutInflater(), parent, extraArguments));
  }


  private View getPopulatedView(LayoutInflater layoutInflater, ViewGroup parent, Bundle extraArguments) {
    View productDetailsView = layoutInflater.inflate(R.layout.product_details, parent, false);
    Product product = extraArguments.getParcelable(PRODUCT_KEY);
    TextView imageTitle = (TextView) productDetailsView.findViewById(R.id.product_title);
    imageTitle.setText(product.getTitle());
    ImageView imageView = (ImageView) productDetailsView.findViewById(R.id.product_image);
    Picasso.with(this).load(product.getImageUrl()).into(imageView);
    TextView issueDescription = (TextView) productDetailsView.findViewById(R.id.product_description);
    issueDescription.setText(product.getDescription());
    return productDetailsView;
  }

}
