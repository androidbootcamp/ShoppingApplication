package bootcamp.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import bootcamp.android.R;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

import java.util.List;

public class ShoppingItemsListingActivity extends Activity {

    private ProductRepository productRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        productRepository = new ProductRepository();
        LinearLayout layout = (LinearLayout) findViewById(R.id.listofitems);
        List<Product> products  = productRepository.getProducts();
        LayoutInflater layoutInflater = getLayoutInflater();
        for (Product product : products) {
            View view = layoutInflater.inflate(R.layout.product, null);
            TextView titleView = (TextView)view.findViewById(R.id.title);
            titleView.setText(product.getTitle());
            TextView descriptionView = (TextView)view.findViewById(R.id.description);
            descriptionView.setText(product.getDescription());
            layout.addView(view);
        }
    }
}
