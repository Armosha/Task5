package nb.command.impl;


import nb.bean.AuthenticationRequest;
import nb.bean.AuthenticationResponse;
import nb.bean.Request;
import nb.bean.Response;
import nb.bean.entity.User;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;
import nb.service.impl.UserService;

public class Authentication implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        AuthenticationRequest req = null;

        if(request instanceof AuthenticationRequest) {
            req = (AuthenticationRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        AuthenticationResponse response = new AuthenticationResponse();

        String login = req.getLogin();
        String password = req.getPassword();

        UserService userService = ServiceFactory.getInstance().getUserService();

        User currentUser = null;

        try {
            currentUser = userService.logination(login, password);
        } catch (ServiceException e) {
            response.setErrorStatus(true);
            response.setErrorMessage(e.getMessage());
            return response;
        }

        if(currentUser == null) {
            response.setErrorStatus(true);
            response.setErrorMessage("Authentication error, user does not exist!");
            return response;
        } else {
            response.setErrorStatus(false);
            response.setResultMessage("Success");
            response.setUser(currentUser);
            return response;
        }
    }
}