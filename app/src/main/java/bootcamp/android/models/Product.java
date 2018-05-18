package bootcamp.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Product {

  private String title;
  private String description;
  @SerializedName(value="image_url") private String imageUrl;

  public Product(String title, String description, String imageUrl) {
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  @Override
  public String toString() {
    return title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(title, product.title) &&
            Objects.equals(description, product.description) &&
            Objects.equals(imageUrl, product.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, imageUrl);
  }
}