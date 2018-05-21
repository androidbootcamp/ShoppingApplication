package bootcamp.android.presenters;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import bootcamp.android.viewmodel.ShoppingItemsViewModel;
import bootcamp.android.views.ShoppingItemsListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingItemsListPresenter {

    private final ShoppingItemsListView view;
    private final ProductRepository repository;
    private  List<Product> products;
    private List<Product> cart;


    public ShoppingItemsListPresenter(ShoppingItemsListView view, ProductRepository repository) {
        this.view = view;
        this.repository = repository;
        this.cart = new ArrayList<>();
    }

    public void init(){
        repository.getProducts(productsCallback());
    }

    @NonNull
    private Callback<List<Product>> productsCallback() {
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                renderViewModel();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                view.showNetworkError();
            }
        };
    }

    private void renderViewModel() {
        ShoppingItemsViewModel shoppingItemsViewModel = new ShoppingItemsViewModel(products,cart);
        view.renderProducts(shoppingItemsViewModel);
    }

    public void addItemInCart(Product cartProduct) {
        cart.add(cartProduct);
        renderViewModel();
    }

    public Product getProductFromTitle(String title) {
        for (Product product :products){
            if(product.getTitle().equals(title))
                return product;
        }
        return null;
    }
}
