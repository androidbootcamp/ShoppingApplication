package bootcamp.android.models;

import com.google.gson.annotations.SerializedName;

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
}