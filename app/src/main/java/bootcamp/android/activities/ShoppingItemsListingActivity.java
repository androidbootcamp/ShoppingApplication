package bootcamp.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;

public class ShoppingItemsListingActivity extends Activity {

    private ProductRepository productRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        productRepository = new ProductRepository();
        List<Product> products  = productRepository.getProducts();
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new ShoppingItemsListAdapter(this, products));
    }
}
