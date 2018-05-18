package bootcamp.android.presenters;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import bootcamp.android.R;
import bootcamp.android.adapters.ShoppingItemsListAdapter;
import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import bootcamp.android.views.ShoppingItemsListView;
import bootcamp.android.views.ShoppingItemsListingActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingItemsListPresenter {

    private final ShoppingItemsListView view;
    private final ProductRepository repository;
    private  List<Product> products;

    public ShoppingItemsListPresenter(ShoppingItemsListView view, ProductRepository repository) {
        this.view = view;
        this.repository = repository;
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
                view.renderProducts(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                view.showNetworkError();
            }
        };
    }

}
