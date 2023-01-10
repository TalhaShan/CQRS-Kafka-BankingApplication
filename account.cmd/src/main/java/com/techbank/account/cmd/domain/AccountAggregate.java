package com.techbank.account.cmd.domain;

import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundDepositEvent;
import com.techbank.account.common.events.FundsWithDrawEvent;
import com.techbank.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private Boolean active;
    private double balance;

    public double getBalance(){
        return this.balance;
    }

    public boolean getActive(){
        return this.active;
    }
    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder().
                id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance()).
                build());
    }

    public void apply(AccountOpenedEvent openedEvent) {

        this.id = openedEvent.getId();
        this.active = true;
        this.balance = openedEvent.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Fund cannot be deposited to closed Account");
        }
        if (amount <= 0) {
            throw new IllegalStateException("The deposit account must be greater than zero");
        }

        raiseEvent(FundDepositEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundDepositEvent depositEvent) {
        this.id = depositEvent.getId();
        this.balance += depositEvent.getAmount();
    }

    public void withdrawFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Fund cannot be wtihdrawn from closed Account");
        }
        raiseEvent(FundsWithDrawEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(FundsWithDrawEvent withDrawEvent) {
        this.id = withDrawEvent.getId();
        this.balance -= withDrawEvent.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("Account Already Closed");
        }
        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent closedEvent) {
        this.id = closedEvent.getId();
        this.active = false;
    }

}
