package me.freshtea.utilmod.util;

public class MathUtil {

    public static Pair<Double> intersection(double x11, double z11, double x12, double z12, double x21, double z21, double x22, double z22) {
        double dX1 = x12 - x11;
        double dX2 = x22 - x21;
        double dZ1 = z12 - z11;
        double dZ2 = z22 - z21;

        if (dX1 == 0 || dX2 == 0 || dZ1 == 0 || dZ2 == 0)
            return null;

        double tanA = dZ1 / dX1;
        double tanB = dZ2 / dX2;

        double ctgA = dX1 / dZ1;
        double ctgB = dX2 / dZ2;

        if (tanA == tanB || ctgA == ctgB)
            return null;

        double x = (z21 - z11 + x11 * tanA - x21 * tanB) / (tanA - tanB);
        double z = (x21 - x11 + z11 * ctgA - z21 * ctgB) / (ctgA - ctgB);

        return new Pair<>(x, z);
    }

}
