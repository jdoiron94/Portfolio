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
    QUADRILLION(new BigInteger("1000000000000000"), " quadrillion "),
    QUINTILLION(new BigInteger("1000000000000000000"), " quintillion "),
    SEPTILLION(new BigInteger("1000000000000000000000"), " septillion ");

    private final BigInteger value;

    private final String representation;

    /**
     * Constructs a mathematical unit with its value and String representation.
     *
     * @param value The value the enum represents.
     * @param representation The value's string equivalence.
     */
    Unit(BigInteger value, String representation) {
        this.value = value;
        this.representation = representation;
    }

    /**
     * @return The enum's integer value.
     */
    public BigInteger getValue() {
        return value;
    }

    /**
     * @return The enum's integer String representation.
     */
    public String getRepresentation() {
        return representation;
    }
}
