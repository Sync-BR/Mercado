package Beans;

/**
 *
 * @author SYNC
 */
public class ProductsBean {

    public ProductsBean() {
    }
    
    private int productId;
    private String productName;
    private String productDescription;
    private double productValue;
    private int productStock;
    private String productSupplier;
    private String productBarcode;
    private String productImages;



    public ProductsBean(int productId, String productName, String productDescription, double productAmount, int productStock, String productSupplier, String productBarcode, String productImages) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productValue = productAmount;
        this.productStock = productStock;
        this.productSupplier = productSupplier;
        this.productBarcode = productBarcode;
        this.productImages = productImages;
    }

    public ProductsBean( String productName, String productDescription, double productAmount, int productStock, String productSupplier, String productBarcode, String productImages) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productValue = productAmount;
        this.productStock = productStock;
        this.productSupplier = productSupplier;
        this.productBarcode = productBarcode;
        this.productImages = productImages;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public double getProductValue() {
        return productValue;
    }

    public void setProductValue(double productValue) {
        this.productValue = productValue;
    }

}
