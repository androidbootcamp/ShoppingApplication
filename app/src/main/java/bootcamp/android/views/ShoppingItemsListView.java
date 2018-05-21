package bootcamp.android.views;

import bootcamp.android.viewmodel.ShoppingItemsViewModel;

public interface ShoppingItemsListView {
    void renderProducts(ShoppingItemsViewModel list) ;
    void showNetworkError();
}
