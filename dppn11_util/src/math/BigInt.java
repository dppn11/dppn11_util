
package math;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Daniel Plaza
 */
public class BigInt extends BigInteger {

    public static BigInteger TWO = BigInt.valueOf(2L);
    public static BigInteger THREE = BigInt.valueOf(3L);
    public static BigInteger FOUR = BigInt.valueOf(4L);
    public static BigInteger FIVE = BigInt.valueOf(5L);
    public static BigInteger SIX = BigInt.valueOf(6L);
    public static BigInteger SEVEN = BigInt.valueOf(7L);
    public static BigInteger EIGHT = BigInt.valueOf(8L);
    public static BigInteger NINE = BigInt.valueOf(9L);

    public BigInt(BigInteger bigint) {
        super(bigint.toByteArray());
    }
    
    public BigInt(byte[] val) {
        super(val);
    }

    public BigInt(int signum, byte[] magnitude) {
        super(signum, magnitude);
    }

    public BigInt(String val, int radix) {
        super(val, radix);
    }

    public BigInt(String val) {
        super(val);
    }

    public BigInt(int numBits, Random rnd) {
        super(numBits, rnd);
    }

    public BigInt(int bitLength, int certainty, Random rnd) {
        super(bitLength, certainty, rnd);
    }
    
    /**
     * Calculate if the BigInteger is a prime
     * @return true if prime, false if no prime
     */
    public boolean isPrime() {
        BigInt counter = BigInt.valueOf(3L);
        BigInteger target = sqrt();
        while (counter.compareTo(target) == -1) {
            if (this.mod(counter).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
            switch (counter.getLastDigit()) {
                case 1:
                case 7:
                case 9:
                    counter=counter.add(TWO);
                    break;
                case 3:
                    counter=counter.add(FOUR);
                    break;
                default:
                    counter=counter.add(ONE);
            }
        }
        return true;
    }
    
    /**
     * Efficiency O(1) 
     * @return the digit count
     */
    public int getDigitCount() {
        double factor = Math.log(2) / Math.log(10);
        int digitCount = (int) (factor * this.bitLength() + 1);
        if (BigInteger.TEN.pow(digitCount - 1).compareTo(this) > 0) {
            return digitCount - 1;
        }
        return digitCount;
    }
    
    /**
     * Indexes: 11 10 9 8 7 6 5 4 3 2 1 0 
     * @param index digit position
     * @return digit at the given position
     */
    public int intAtDigit(int index) {
        BigInteger[] resultAndRemainder=null;
        BigInteger n=new BigInt(this);
        for(int i=0;i<=index;i++){
            resultAndRemainder = n.divideAndRemainder(BigInteger.TEN);
            n = resultAndRemainder[0];
        }
        return resultAndRemainder[1].intValue();
    }
    
    /**
     * 
     * @return First digit of the number
     */
    public int getFirstDigit() {
        return intAtDigit(getDigitCount()-1);
    }
    
    /**
     * 
     * @return Last digit of the number
     */
    public int getLastDigit() {
        BigInteger[] resultAndRemainder;
        resultAndRemainder = divideAndRemainder(BigInteger.TEN);
        return Math.abs(resultAndRemainder[1].intValue());
    }
    
    /**
     * Search for a pattern in the number. May not work in big numbers
     * @param pattern
     * @return 
     */
    public int getFirstIndexOfPattern(String pattern){
        String s = this.toString();
        return s.indexOf(pattern);
    }

    /**
     * 
     * @return Square root of the number
     * @throws IllegalArgumentException if complex (negative)
     */
    public BigInt sqrt()
            throws IllegalArgumentException {
        BigInt x = new BigInt(this);
        if (x.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x.equals(ZERO) || x.equals(ONE)) {
            return x;
        } // end if
        BigInt two = BigInt.valueOf(2L);
        BigInt y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        return y;
    }
    
    //OTHER METHOD TO CALCULATE SQRT
    private BigInt bigIntSqRootCeil()
            throws IllegalArgumentException {
        BigInt x = new BigInt(this.toByteArray());
        if (x.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
    // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == ZERO || x == ONE) {
            return x;
        } // end if
        BigInteger two = BigInt.valueOf(2L);
        BigInt y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(ONE);
        }
    }


    public static BigInt valueOf(long val) {
        return new BigInt(BigInteger.valueOf(val));
    }
    
    @Override
    public BigInt max(BigInteger val) {
        return new BigInt(super.max(val));
    }

    @Override
    public BigInt min(BigInteger val) {
        return new BigInt(super.min(val));
    }

    @Override
    public BigInt flipBit(int n) {
        return new BigInt(super.flipBit(n));
    }

    @Override
    public BigInt clearBit(int n) {
        return new BigInt(super.clearBit(n)); 
    }

    @Override
    public BigInt setBit(int n) {
        return new BigInt(super.setBit(n));
    }

    @Override
    public BigInt andNot(BigInteger val) {
        return new BigInt(super.andNot(val));
    }

    @Override
    public BigInt not() {
        return new BigInt(super.not());
    }

    @Override
    public BigInt xor(BigInteger val) {
        return new BigInt(super.xor(val));
    }

    @Override
    public BigInt or(BigInteger val) {
        return new BigInt(super.or(val));
    }

    @Override
    public BigInt and(BigInteger val) {
        return new BigInt(super.and(val));
    }

    @Override
    public BigInt shiftRight(int n) {
        return new BigInt(super.shiftRight(n));
    }

    @Override
    public BigInt shiftLeft(int n) {
        return new BigInt(super.shiftLeft(n));
    }

    @Override
    public BigInt modInverse(BigInteger m) {
        return new BigInt(super.modInverse(m));
    }

    @Override
    public BigInt modPow(BigInteger exponent, BigInteger m) {
        return new BigInt(super.modPow(exponent, m));
    }

    @Override
    public BigInt mod(BigInteger m) {
        return new BigInt(super.mod(m));
    }

    @Override
    public BigInt negate() {
        return new BigInt(super.negate());
    }

    @Override
    public BigInt abs() {
        return new BigInt(super.abs());
    }

    @Override
    public BigInt gcd(BigInteger val) {
        return new BigInt(super.gcd(val));
    }

    @Override
    public BigInt pow(int exponent) {
        return new BigInt(super.pow(exponent));
    }

    @Override
    public BigInt remainder(BigInteger val) {
        return new BigInt(super.remainder(val));
    }
    
    @Override
    public BigInt divide(BigInteger val) {
        return new BigInt(super.divide(val));
    }

    @Override
    public BigInt multiply(BigInteger val) {
        return new BigInt(super.multiply(val)); 
    }

    @Override
    public BigInt subtract(BigInteger val) {
        return new BigInt(super.subtract(val));
    }

    @Override
    public BigInt add(BigInteger val) {
        return new BigInt(super.add(val));
    }

    @Override
    public BigInt nextProbablePrime() {
        return new BigInt(super.nextProbablePrime());
    }

}
