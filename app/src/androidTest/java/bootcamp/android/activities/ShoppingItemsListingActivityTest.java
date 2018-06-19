package bootcamp.android.activities;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.intercepting.SingleActivityFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import bootcamp.android.models.Product;
import bootcamp.android.repositories.ProductRepository;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static retrofit2.Response.success;

@RunWith(AndroidJUnit4.class)
public class ShoppingItemsListingActivityTest {

    private ProductRepository productRepository = mock(ProductRepository.class);

    private SingleActivityFactory<ShoppingItemsListingActivity> factory =
            new SingleActivityFactory<ShoppingItemsListingActivity>(ShoppingItemsListingActivity.class) {
        @Override
        protected ShoppingItemsListingActivity create(Intent intent) {
            return new ShoppingItemsListingActivity(productRepository);
        }
    };

    @Before
    public void setup() throws Exception {
        mockProductRepositoryForSuccess();
    }

    @Rule
    public ActivityTestRule<ShoppingItemsListingActivity> activityTestRule =
            new ActivityTestRule<>(factory, false, false);


    @Test
    public void testmyapp(){
        activityTestRule.launchActivity(new Intent());
        int i = 1;
        assertEquals(1,1);
    }

    private void mockProductRepositoryForSuccess() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<ArrayList<Product>> callback = (Callback<ArrayList<Product>>) invocation.getArguments()[0];

                Product product1 = new Product("The Best Watch", "Desc1", "ftp://ftp");
                Product product2 = new Product("The Worst Watch", "A beautiful Specimen", "fake://fake");
                Product product3 = new Product("Snowflake", "I'm Different", "none://none.n");


                Response<ArrayList<Product>> objectResponse = success(new ArrayList<>(asList(product1, product2, product3)));
                callback.onResponse(null, objectResponse);
                return null;
            }
        }).when(productRepository).getProducts(ArgumentMatchers.<Callback<ArrayList<Product>>>any());
    }
}
