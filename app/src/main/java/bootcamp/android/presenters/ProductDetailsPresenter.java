package bootcamp.android.presenters;

import bootcamp.android.models.Product;
import bootcamp.android.views.ProductDetailsView;

public class ProductDetailsPresenter {
    private ProductDetailsView view;
    private Product product;

    public ProductDetailsPresenter(ProductDetailsView view) {
        this.view = view;
    }

    public void render(String title, String description, String imageUrl) {
        product = new Product(title, description, imageUrl);
        view.displayProduct(product);
    }

    public void addToCart() {
        view.sendAddToCartResultAndNavigateToPreviousPage(product);
    }
}
