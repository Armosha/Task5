package nb.controller;

import nb.command.Command;
import nb.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private Map<String, Command> commands = new HashMap<String, Command>();

    public CommandHelper() {
        commands.put("ADD_NEW_NOTE", new AddNewNote());
        commands.put("FIND_BY_NOTE", new FindNotes());
        commands.put("SHOW_NOTEBOOK", new ShowNoteBook());
        commands.put("FIND_BY_DATE", new FindByDate());
    }

    public Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);
        return command;
    }
}