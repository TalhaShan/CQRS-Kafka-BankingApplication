package com.techbank.account.common.events;

import com.techbank.cqrs.core.events.BaseEvents;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AccountClosedEvent extends BaseEvents {
}
