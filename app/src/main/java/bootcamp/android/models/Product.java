package bootcamp.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable{

  private String title;
  private String description;
  @SerializedName(value="image_url") private String imageUrl;

  public Product(String title, String description, String imageUrl) {
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  protected Product(Parcel in) {
    title = in.readString();
    description = in.readString();
    imageUrl = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(description);
    dest.writeString(imageUrl);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Product> CREATOR = new Creator<Product>() {
    @Override
    public Product createFromParcel(Parcel in) {
      return new Product(in);
    }

    @Override
    public Product[] newArray(int size) {
      return new Product[size];
    }
  };

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