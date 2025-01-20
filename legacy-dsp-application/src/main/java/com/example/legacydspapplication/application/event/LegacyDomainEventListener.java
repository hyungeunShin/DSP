package com.example.legacydspapplication.application.event;

import com.example.legacydspapplication.domain.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class LegacyDomainEventListener {
    private final StreamBridge streamBridge;

    static final String OUTPUT_BINDING = "legacy-rabbit-out";

    @TransactionalEventListener //TransactionPhase phase() default TransactionPhase.AFTER_COMMIT;
    public void handleEvent(DomainEvent event) {
        streamBridge.send(OUTPUT_BINDING, LegacyDomainMessage.from(event));
    }
}
