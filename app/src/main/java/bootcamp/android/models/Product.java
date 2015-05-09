package bootcamp.android.models;

public class Product {

  private int drawable;
  private String title;
  private String description;

  public Product(String title, String description, int drawable) {
    this.title = title;
    this.description = description;
    this.drawable = drawable;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public int getDrawable() {
    return drawable;
  }

  @Override
  public String toString() {
    return title;
  }
}