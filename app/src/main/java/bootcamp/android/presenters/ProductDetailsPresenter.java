package bootcamp.android.presenters;

import bootcamp.android.models.Product;
import bootcamp.android.views.ProductDetailsView;

public class ProductDetailsPresenter {
    private ProductDetailsView view;

    public ProductDetailsPresenter(ProductDetailsView view) {

        this.view = view;
    }

    public void render(String title, String description, String imageUrl) {
        view.displayProduct(new Product(title, description, imageUrl));
    }
}
