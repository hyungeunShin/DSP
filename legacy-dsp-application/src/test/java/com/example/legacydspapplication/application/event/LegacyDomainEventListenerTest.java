package com.example.legacydspapplication.application.event;

import com.example.legacydspapplication.application.adgroup.LegacyAdGroupService;
import com.example.legacydspapplication.application.campaign.LegacyCampaignService;
import com.example.legacydspapplication.application.keyword.LegacyKeywordService;
import com.example.legacydspapplication.application.user.LegacyUserService;
import com.example.legacydspapplication.domain.DomainEvent;
import com.example.legacydspapplication.domain.adgroup.LegacyAdGroup;
import com.example.legacydspapplication.domain.adgroup.event.*;
import com.example.legacydspapplication.domain.campaign.LegacyCampaign;
import com.example.legacydspapplication.domain.campaign.event.*;
import com.example.legacydspapplication.domain.keyword.LegacyKeyword;
import com.example.legacydspapplication.domain.keyword.event.LegacyKeywordCreatedEvent;
import com.example.legacydspapplication.domain.keyword.event.LegacyKeywordDeletedEvent;
import com.example.legacydspapplication.domain.keyword.event.LegacyKeywordEvent;
import com.example.legacydspapplication.domain.user.LegacyUser;
import com.example.legacydspapplication.domain.user.event.LegacyUserCreatedEvent;
import com.example.legacydspapplication.domain.user.event.LegacyUserDeletedEvent;
import com.example.legacydspapplication.domain.user.event.LegacyUserEvent;
import com.example.legacydspapplication.domain.user.event.LegacyUserNameUpdatedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class LegacyDomainEventListenerTest {
    @Autowired
    LegacyUserService userService;

    @Autowired
    LegacyCampaignService campaignService;

    @Autowired
    LegacyAdGroupService adGroupService;

    @Autowired
    LegacyKeywordService keywordService;

    @MockitoBean
    LegacyDomainEventListener eventListener;

    @Test
    void userEvent() {
        LegacyUser user = userService.create("이름1");
        userService.updateName(user.getId(), "이름2");
        userService.delete(user.getId());

        Assertions.assertAll(
                () -> verify(eventListener, times(3)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(3)).handleEvent(any(LegacyUserEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyUserDeletedEvent.class))
        );
    }

    @Test
    void campaignEvent() {
        LegacyCampaign campaign = campaignService.create("이름1", 1L, 1000L);
        campaignService.updateName(campaign.getId(), "이름2");
        campaignService.updateBudget(campaign.getId(), 2000L);
        campaignService.delete(campaign.getId());

        Assertions.assertAll(
                () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(4)).handleEvent(any(LegacyCampaignEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignBudgetUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyCampaignDeletedEvent.class))
        );
    }

    @Test
    void adGroupEvent() {
        LegacyAdGroup adGroup = adGroupService.create("이름1", 1L, 1L, "https://naver.com");
        adGroupService.updateName(adGroup.getId(), "이름2");
        adGroupService.updateLinkUrl(adGroup.getId(), "https://daum.net");
        adGroupService.delete(adGroup.getId());

        Assertions.assertAll(
                () -> verify(eventListener, times(4)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(4)).handleEvent(any(LegacyAdGroupEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupNameUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupLinkUrlUpdatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyAdGroupDeletedEvent.class))
        );
    }

    @Test
    void keywordEvent() {
        LegacyKeyword keyword = keywordService.create("키워드", 1L, 1L);
        keywordService.delete(keyword.getId());

        Assertions.assertAll(
                () -> verify(eventListener, times(2)).handleEvent(any(DomainEvent.class)),
                () -> verify(eventListener, times(2)).handleEvent(any(LegacyKeywordEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordCreatedEvent.class)),
                () -> verify(eventListener, times(1)).handleEvent(any(LegacyKeywordDeletedEvent.class))
        );
    }
}