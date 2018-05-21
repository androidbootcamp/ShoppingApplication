package bootcamp.android.viewmodel;

import java.util.List;

import bootcamp.android.models.Product;

public class ProductViewModel {
    private String title;
    private String imageUrl;
    private Boolean isInCart;

    public ProductViewModel(Product product) {
        this.title = product.getTitle();
        this.imageUrl = product.getImageUrl();
        this.isInCart = false;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getInCart() {
        return isInCart;
    }

    public void setInCart(Boolean inCart) {
        isInCart = inCart;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductViewModel)) {
            return false;
        }
        ProductViewModel c = (ProductViewModel) o;
        return c.getTitle().equals(this.getTitle()) && c.getImageUrl().equals(this.getImageUrl());
    }

}
