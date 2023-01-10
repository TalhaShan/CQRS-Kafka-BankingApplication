package com.techbank.account.cmd;

import com.techbank.account.cmd.api.commands.*;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private CommandHandler commandHandler;

    public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);

	}

    @PostConstruct //is used to tell spring execute  this method just after Bean initialization that's the only way autowired gonna work here
    public void registerHandlers(){

        commandDispatcher.registerHandler(OpenAccountCommand.class,commandHandler::handle);
        commandDispatcher.registerHandler(DepositFundsCommand.class,commandHandler::handle);
        commandDispatcher.registerHandler(WithDrawFundsCommand.class,commandHandler::handle);
        commandDispatcher.registerHandler(CloseAccountCommand.class,commandHandler::handle);
        commandDispatcher.registerHandler(RestoreReadDbCommand.class,command -> commandHandler.handle(command));

    }

}
