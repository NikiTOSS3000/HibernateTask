package com.epam.hibernate.command;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public final class CommandFactory {private static CommandFactory instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private CommandFactory() {
        commands.put("list", new ListCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }
}
