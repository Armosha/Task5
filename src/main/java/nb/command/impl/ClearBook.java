package nb.command.impl;


import nb.bean.ClearBookRequest;
import nb.bean.Request;
import nb.bean.Response;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.NoteBookService;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;

public class ClearBook implements Command {

    @Override
    public Response execute(Request request) throws CommandException {

        ClearBookRequest req = null;

        if(request instanceof ClearBookRequest){
            req = (ClearBookRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }

        Response response = new Response();

        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            nbService.clearNote(req.getUserId());
        } catch (ServiceException e) {
            response.setErrorStatus(true);
            response.setErrorMessage(e.getMessage());
            return response;
        }
        response.setErrorStatus(true);
        response.setResultMessage("User notebook is cleared!");

        return response;
    }
}