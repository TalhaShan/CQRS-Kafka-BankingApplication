package com.techbank.account.common.events;

import com.techbank.cqrs.core.events.BaseEvents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class FundsWithDrawEvent extends BaseEvents {
    private double amount;
}
