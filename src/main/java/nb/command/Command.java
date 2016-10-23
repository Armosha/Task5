package nb.command;

import nb.bean.Request;
import nb.bean.Response;
import nb.command.exception.CommandException;

import java.io.IOException;

public interface Command {
	Response execute(Request request) throws CommandException, IOException;
}
