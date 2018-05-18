package bootcamp.android.presenters;

import org.junit.Before;
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
    private ProductDetailsPresenter presenter;
    private Product testProduct;

    @Before
    public void setup(){
        presenter = new ProductDetailsPresenter(view);
        testProduct = new Product("Pixel XL", "Best Android Phone Ever.", "dummy://image");
        presenter.render("Pixel XL", "Best Android Phone Ever.", "dummy://image");
    }

    @Test
    public void renderProductsOnTheScreen(){
        verify(view).displayProduct(testProduct);
    }

    @Test
    public void addToCardAndCloseActivityOnClickingTheAddToCartButton(){
        presenter.addToCart();
        verify(view).sendAddToCartResultAndNavigateToPreviousPage(testProduct);
    }
}
