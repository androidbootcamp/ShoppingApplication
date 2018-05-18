package bootcamp.android.views;

import bootcamp.android.models.Product;

public interface ProductDetailsView {
    void displayProduct(Product product);
    void sendAddToCartResultAndNavigateToPreviousPage(Product product);
}
