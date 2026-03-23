package com.sivareddy.utils;

import java.util.concurrent.ThreadLocalRandom;

public class EcommerceUtils {
    public static String generateOrderId() {
        return "ORD" + ThreadLocalRandom.current().nextLong(0, 1_00_00_00_0_00);
    }
}
