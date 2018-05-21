package bootcamp.android.viewmodel;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.models.Product;

public class ShoppingItemsViewModel {
    private List<ProductViewModel> productViewModels;
    private int itemCount;
    private int cartCount;

    public ShoppingItemsViewModel(List<Product> products, List<Product> shoppingCart) {
        this.productViewModels = getShoppingItemsListViewModels(products,shoppingCart);
        this.itemCount = 0;
        this.cartCount = 0;
    }

    private List<ProductViewModel> getShoppingItemsListViewModels(List<Product> products, List<Product> shoppingCart){
        if(productViewModels==null)
            productViewModels= new ArrayList<>();
        for (Product product:products) {

            ProductViewModel productViewModel = new ProductViewModel(product);
            if(shoppingCart.contains(product))
                productViewModel.setInCart(true);
            productViewModels.add(productViewModel);
        }
       return productViewModels;
    }

    public List<ProductViewModel> getItemsList() {
        return productViewModels;
    }

    public int getItemCount() {
        return productViewModels.size();
    }

    public int getCartCount() {
        int cnt=0;
        for (ProductViewModel item: productViewModels) {
            if(item.getInCart())
                cnt++;
        }
        return cnt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ShoppingItemsViewModel)) {
            return false;
        }
        ShoppingItemsViewModel c = (ShoppingItemsViewModel) o;
        return c.getItemsList().equals(this.getItemsList());
    }




}
