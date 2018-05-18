package bootcamp.android.presenters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import bootcamp.android.models.Product;
import bootcamp.android.views.ProductDetailsView;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailsPresenterTest {

    @Mock private ProductDetailsView view;

    @Test
    public void renderProductsOnTheScreen(){

        ProductDetailsPresenter presenter = new ProductDetailsPresenter(view);
        Product testProduct = new Product("Pixel XL", "Best Android Phone Ever.", "dummy://image");
        presenter.render("Pixel XL", "Best Android Phone Ever.", "dummy://image");
        verify(view).displayProduct(testProduct);
    }
}
