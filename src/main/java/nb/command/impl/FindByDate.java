package nb.command.impl;

import nb.bean.FindByDateRequest;
import nb.bean.FindByDateResponse;
import nb.bean.Request;
import nb.bean.Response;
import nb.bean.entity.Note;
import nb.command.Command;
import nb.command.exception.CommandException;
import nb.service.NoteBookService;
import nb.service.ServiceFactory;
import nb.service.exeption.ServiceException;

import java.util.List;

/**
 * Created by Iryna Filipava on 06.10.2016.
 */
public class FindByDate implements Command {

    @Override
    public Response execute(Request request) throws CommandException {

        FindByDateResponse response = new FindByDateResponse();
        FindByDateResponse res;
        if (response instanceof FindByDateResponse) {
            res = (FindByDateResponse) response;
        } else {
            throw new CommandException("Wrong response");
        }
        FindByDateRequest req;
        if (request instanceof FindByDateRequest) {
            req = (FindByDateRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }
        List<Note> list = null;
        int day = 0,  month = 0, year = 0;
        try {
            if (req.getDay() != null && !req.getDay().equals("")) {
                day = Integer.parseInt(req.getDay());
            }
            if (req.getMonth() != null && !req.getMonth().equals("")) {
                month = Integer.parseInt(req.getMonth());
            }
            if (req.getYear() != null && !req.getYear().equals("")) {
                year = Integer.parseInt(req.getYear());
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Incorrect date was entered!");
        }
        ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        try {
            list = nbService.findByDate(day, month, year, req.getUserId());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        res.setErrorStatus(true);
        if (list.isEmpty()) {
            res.setResultMessage("There is no notes matched your request");
        } else {
            res.setDateNotes(list);
            res.setResultMessage("All OK!");
        }

        return res;
    }
}
