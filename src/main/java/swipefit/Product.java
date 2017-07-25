package swipefit;

public class Product {

    private String name;

    private String imageUrl;

    private String siteURL;

    private String retailer;

    private double price;

    private int ID;

    public Product(int ID, String imageUrl,String name, String siteURL, String retailer, double price) {
        this.ID = ID;
        this.imageUrl = imageUrl;
        this.name = name;
        this.siteURL = siteURL;
        this.retailer = retailer;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}