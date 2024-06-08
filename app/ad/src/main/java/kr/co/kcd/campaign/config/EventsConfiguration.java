package kr.co.kcd.campaign.config;

import kr.co.kcd.shared.spring.common.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventsConfiguration {
    private final ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventsInitializer() {
        return () ->
            Events.setPublisher(applicationContext);
    }
}
