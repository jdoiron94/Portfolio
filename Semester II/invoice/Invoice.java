package semester_ii.invoice;

import java.text.NumberFormat;
import java.util.Locale;

public class Invoice {

    private int quantityPurchased;
    private double pricePerItem;

    private String partNumber;
    private String partDescription;

    public Invoice(int quantityPurchased, double pricePerItem, String partNumber, String partDescription) {
        this.quantityPurchased = quantityPurchased;
        this.pricePerItem = pricePerItem;
        this.partNumber = partNumber;
        this.partDescription = partDescription;
    }

    public double getInvoiceAmount() {
        quantityPurchased = quantityPurchased > 0 ? quantityPurchased : 0;
        pricePerItem = pricePerItem > 0.0 ? pricePerItem : 0.0;
        return quantityPurchased * pricePerItem;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    @Override
    public String toString() {
        return String.format("Part Number: %s\nDescription: %s\nQuantity: %d\nPrice: %s\n", partNumber, partDescription, quantityPurchased, NumberFormat.getCurrencyInstance(Locale.US).format(getInvoiceAmount()));
    }
}