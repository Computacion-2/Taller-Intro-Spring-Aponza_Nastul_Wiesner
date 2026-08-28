package edu.icesi.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextManager {

    private static AnnotationConfigApplicationContext context;

    private SpringContextManager() {
        // Constructor privado para evitar instanciación externa
    }

    public static synchronized AnnotationConfigApplicationContext getContext() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext(AppConfig.class);
        }
        return context;
    }
}