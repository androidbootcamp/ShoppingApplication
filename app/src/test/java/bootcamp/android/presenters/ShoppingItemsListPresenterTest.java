package bootcamp.android.presenters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import bootcamp.android.views.ShoppingItemsListView;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingItemsListPresenterTest {

    @Mock private ShoppingItemsListView view = mock(ShoppingItemsListView.class);
    @Mock private ProductRepository repository = mock(ProductRepository.class);

    @Test
    public void requestRepositoryForResponseOnInitialisationOfPresenter() {
        ShoppingItemsListPresenter presenter = new ShoppingItemsListPresenter(view, repository);
        presenter.init();
        verify(repository).getProducts(any(Callback.class));
    }

    @Test
    public void renderProductsOnUIOnSuccessfulResponseFromRepository(){
        final Product product = new Product("title", "des", "url");
        final List<Product> list = new ArrayList<Product>(){{add(product);}};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final Callback<List<Product>> callback = (Callback<List<Product>>) invocation.getArguments()[0];
                callback.onResponse(null, Response.success(list));
                return null;
            }
        }).when(repository).getProducts(any(Callback.class));

        ShoppingItemsListPresenter presenter = new ShoppingItemsListPresenter(view, repository);
        presenter.init();
        verify(view).renderProducts(list);
    }

    @Test
    public void showErrorOnRepositoryFailure(){
        final Product product = new Product("title", "des", "url");
        final List<Product> list = new ArrayList<Product>(){{add(product);}};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final Callback<List<Product>> callback = (Callback<List<Product>>) invocation.getArguments()[0];
                callback.onFailure(null, new RuntimeException());
                return null;
            }
        }).when(repository).getProducts(any(Callback.class));

        ShoppingItemsListPresenter presenter = new ShoppingItemsListPresenter(view, repository);
        presenter.init();
        verify(view).showNetworkError();
    }


}












