package numbers;

import java.math.BigInteger;

/**
 * @author Jacob Doiron
 * @since 10/30/2015
 */
public enum Unit {

    HUNDRED(new BigInteger("100"), " hundred "),
    THOUSAND(new BigInteger("1000"), " thousand "),
    MILLION(new BigInteger("1000000"), " million "),
    BILLION(new BigInteger("1000000000"), " billion "),
    TRILLION(new BigInteger("1000000000000"), " trillion "),
    QUINTILLION(new BigInteger("1000000000000000"), " quintillion "),
    SEXTILLION(new BigInteger("1000000000000000000"), " sextillion ");

    private final BigInteger value;

    private final String representation;

    Unit(BigInteger value, String representation) {
        this.value = value;
        this.representation = representation;
    }

    public BigInteger getValue() {
        return value;
    }

    public String getRepresentation() {
        return representation;
    }
}
