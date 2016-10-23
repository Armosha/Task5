package nb.command.impl;

import nb.bean.AddNoteRequest;
import nb.bean.Request;
import nb.bean.Response;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.NoteBookService;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;

import java.util.Date;

public class AddNewNote implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        AddNoteRequest req = null;

        if(request instanceof AddNoteRequest){
            req = (AddNoteRequest)request;
        }else{
            throw new CommandException("Wrong request");
        }
        String note = req.getNote();
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();

        try {
            nbService.addNote(note, req.getUserId());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        Response response = new Response();
        response.setErrorStatus(true);
        response.setResultMessage("Note \"" + note + "\" is added!");

        return response;
    }

}