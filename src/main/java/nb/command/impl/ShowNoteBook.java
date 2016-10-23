package nb.command.impl;


import nb.bean.Request;
import nb.bean.Response;
import nb.bean.ShowAllNotesRequest;
import nb.bean.ShowAllNotesResponse;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.NoteBookService;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;

public class ShowNoteBook implements Command {

    @Override
    public Response execute(Request request) throws CommandException {

        ShowAllNotesRequest req;
        if (request instanceof ShowAllNotesRequest) {
            req = (ShowAllNotesRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        Response response = new ShowAllNotesResponse();
        ShowAllNotesResponse res;
        if (response instanceof ShowAllNotesResponse) {
            res = (ShowAllNotesResponse) response;

        } else {
            throw new CommandException("Wrong response");
        }

        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            res.setAllBook(nbService.showNotebook(req.getUserId()));
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        res.setErrorStatus(true);
        if (res.getAllBook().isEmpty()) {
            res.setResultMessage("Notebook is empty!");
        } else {
            res.setResultMessage("All OK!");
        }
        return res;
    }
}
