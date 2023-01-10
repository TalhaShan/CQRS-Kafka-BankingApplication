package com.techbank.account.query.infrastructure.handlers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundDepositEvent;
import com.techbank.account.common.events.FundsWithDrawEvent;

public interface EventHandler {

    void on(AccountOpenedEvent event);

    void on(FundDepositEvent event);

    void on(FundsWithDrawEvent event);

    void on(AccountClosedEvent event);
}
