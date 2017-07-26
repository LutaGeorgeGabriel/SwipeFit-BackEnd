package swipefit;

// this class should be identical to the one in android studio
public class Product {

    private String name;

    private String url;

    private String site;

    private String retailer;

    private double price;

    private int ID;

    public Product(String url, String name, String site, String retailer, double price, int ID) {
        this.url = url;
        this.name = name;
        this.site = site;
        this.retailer = retailer;
        this.price = price;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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