package bootcamp.android.views;

import java.util.List;

import bootcamp.android.models.Product;

public interface ShoppingItemsListView {
    void renderProducts(List<Product> list) ;
    void showNetworkError();
}
