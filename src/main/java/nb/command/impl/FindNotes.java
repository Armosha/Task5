package nb.command.impl;

import nb.bean.FindByNoteRequest;
import nb.bean.FindByNoteResponse;
import nb.bean.Request;
import nb.bean.Response;
import nb.bean.entity.Note;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.NoteBookService;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;

import java.util.List;

public class FindNotes implements Command {

    @Override
    public Response execute(Request request) throws CommandException {
        FindByNoteResponse res = new FindByNoteResponse();
        FindByNoteRequest req;
        if (request instanceof FindByNoteRequest) {
            req = (FindByNoteRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }
        List<Note> list = null;
        String noteRec = req.getFindString();
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        try {
            list = nbService.findByNote(noteRec, req.getUserId());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }

        res.setErrorStatus(true);
        if (list.isEmpty()) {
            res.setResultMessage("There is no notes matched your request");
        } else {
            res.setFindBook(list);
            res.setResultMessage("All OK!");
        }
        return res;
    }
}

