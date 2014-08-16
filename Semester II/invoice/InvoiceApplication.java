package semester_ii.invoice;

public class InvoiceApplication {

    public static void main(String... args) {
        Invoice invoice = new Invoice(1, 999.99, "04G-P4-2690-KR", "EVGA GeForce GTX 690 4GB 512-bit GDDR5 Video Card");
        System.out.println(invoice);
        invoice.setQuantityPurchased(2);
        System.out.println(invoice);
        invoice.setPartNumber("02G-P4-2689-K2");
        invoice.setPartDescription("EVGA GeForce GTX 680 Hydro Copper+ 2GB 256-bit GDDR5 Video Card");
        invoice.setQuantityPurchased(Integer.MIN_VALUE);
        invoice.setPricePerItem(Integer.MIN_VALUE);
        System.out.print(invoice);
    }
}