/*
    @author: Abraham Hernandez - TSOFT
*/
package com.tsoft.bot.frontend.utility;

import java.util.logging.Logger;

public class Sleeper {

    private Sleeper() {
    }

    private static final double ESCALA = 1.8;

    public static void sleep(int tiempo) {
        try {
            if (tiempo <= 0) return;
            Thread.sleep((long) (tiempo * ESCALA));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.getLogger("[LOG]-sleep: " + e.getMessage());
        }
    }
}