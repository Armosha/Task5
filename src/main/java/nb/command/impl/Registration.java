package nb.command.impl;


import nb.bean.RegistrationRequest;
import nb.bean.Request;
import nb.bean.Response;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;
import nb.service.impl.UserService;

public class Registration implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        RegistrationRequest req = null;

        if(request instanceof RegistrationRequest) {
            req = (RegistrationRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        Response response = new Response();

        String login = req.getLogin();
        String password = req.getPassword();

        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            userService.registration(login, password);
        } catch (ServiceException e) {
            response.setErrorStatus(true);
            response.setErrorMessage(e.getMessage());
            return response;
        }

        response.setErrorStatus(false);
        response.setResultMessage("Success!");
        return response;
    }
}