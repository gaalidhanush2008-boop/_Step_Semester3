import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 9 - Problem 2: Parcel Shipping Desk
 * Two separate questions, two separate places:
 *   - "how much does shipping cost?"  -> abstract class Parcel (every parcel answers)
 *   - "can it be insured?"            -> interface Insurable (only some parcels implement it)
 * Making another parcel type insurable later only means implementing Insurable on it.
 *
 * Input:  N, then N lines of "STANDARD|EXPRESS|FRAGILE weightKg declaredValue"
 */
public class P2_ParcelShippingDesk {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double declaredValue = sc.nextDouble();
            parcels.add(createParcel(type, weight, declaredValue));
        }

        double grandTotal = 0;
        for (Parcel p : parcels) {
            double charge = p.shippingCharge();
            double insurance = (p instanceof Insurable) ? ((Insurable) p).insurance() : 0;
            double total = charge + insurance;
            grandTotal += total;
            System.out.println(p.typeName() + ": Charge=" + fmt(charge)
                    + " Insurance=" + fmt(insurance) + " Total=" + fmt(total));
        }
        System.out.println("Grand Total: " + fmt(grandTotal));
    }

    static Parcel createParcel(String type, double weight, double declaredValue) {
        return switch (type) {
            case "STANDARD" -> new StandardParcel(weight, declaredValue);
            case "EXPRESS" -> new ExpressParcel(weight, declaredValue);
            case "FRAGILE" -> new FragileParcel(weight, declaredValue);
            default -> throw new IllegalArgumentException("Unknown parcel type: " + type);
        };
    }

    static String fmt(double value) {
        return String.format(Locale.US, "%.2f", value);
    }
}

interface Insurable {
    double INSURANCE_RATE = 0.02;      // 2% of declared value
    double insurance();
}

abstract class Parcel {
    protected final double weightKg;
    protected final double declaredValue;

    Parcel(double weightKg, double declaredValue) {
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    abstract String typeName();
    abstract double shippingCharge();
}

class StandardParcel extends Parcel {
    StandardParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
    String typeName() { return "STANDARD"; }
    double shippingCharge() { return 40 + 10 * weightKg; }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
    String typeName() { return "EXPRESS"; }
    double shippingCharge() { return 80 + 15 * weightKg; }
    public double insurance() { return declaredValue * INSURANCE_RATE; }
}

class FragileParcel extends Parcel implements Insurable {
    FragileParcel(double weightKg, double declaredValue) { super(weightKg, declaredValue); }
    String typeName() { return "FRAGILE"; }
    double shippingCharge() { return (40 + 10 * weightKg) + 50; }   // standard charge + Rs 50 handling
    public double insurance() { return declaredValue * INSURANCE_RATE; }
}
