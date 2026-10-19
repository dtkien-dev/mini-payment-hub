package dev.kien.paymenthub.bootstrap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLifecycleLogger {
    public StartupLifecycleLogger() {
        System.out.println("1. Bean constructor");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        System.out.println("2. Application ready");
    }
}
