package nb.controller;

import nb.bean.Request;
import nb.bean.Response;
import nb.command.Command;
import nb.command.exception.CommandException;
import java.io.IOException;

public class Controller {
	private CommandHelper helper = new CommandHelper();

	public Controller(){}

	public Response doRequest(Request request){
		String commandName = request.getCommandName();
		Command command = helper.getCommand(commandName);
		Response response = null;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			response = new Response();
			response.setErrorStatus(false);
			response.setErrorMessage(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}