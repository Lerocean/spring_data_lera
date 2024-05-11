package ru.fedynko;

import ru.fedynko.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        MainService mainService = context.getBean(MainService.class);
        mainService.doTestOperations();
    }
}