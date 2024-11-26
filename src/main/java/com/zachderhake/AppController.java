package com.zachderhake;

import io.javalin.http.Context;

public final class AppController {
    
    private AppController(){}

    public static void Test(Context context) {
        context.result("Hi");
    }

    public static void specialTest(Context context) {
        if ("yoski".contains(context.pathParam("special"))) {
            context.result("You Found the key");
        } else {
            context.result("You do not kow the key");
        }
    }
}
