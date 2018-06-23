package bootcamp.android.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import bootcamp.android.R;

import static bootcamp.android.constants.Constants.DESCRIPTION_KEY;
import static bootcamp.android.constants.Constants.DRAWABLE_KEY;
import static bootcamp.android.constants.Constants.TITLE_KEY;

public class ProductDetailsActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.product_details);
    Bundle extras = getIntent().getExtras();
    String title = extras.getString(TITLE_KEY);
    String description = extras.getString(DESCRIPTION_KEY);
    int drawableResourceId = extras.getInt(DRAWABLE_KEY);

    TextView imageTitle = (TextView) findViewById(R.id.product_title);
    imageTitle.setText(title);

    TextView issueDescription = (TextView) findViewById(R.id.product_description);
    issueDescription.setText(description);

    ImageView imageView = (ImageView) findViewById(R.id.product_image);
    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), drawableResourceId);
    imageView.setImageBitmap(bitmap);
  }

}
