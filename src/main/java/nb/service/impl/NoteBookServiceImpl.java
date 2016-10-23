package nb.service.impl;

import nb.bean.dao.DAOFactory;
import nb.bean.dao.exeption.DAOException;
import nb.bean.entity.Note;
import nb.service.NoteBookService;
import nb.service.exeption.ServiceException;

import java.util.List;

public class NoteBookServiceImpl implements NoteBookService {

    @Override
    public void addNote(String note, int userId) throws ServiceException {
        if (note == null || "".equals(note)){
            throw new ServiceException("Wrong parameter!");
        }

        try {
            DAOFactory.getInstance().getNoteBookDAO().addNote(new Note(note, userId));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void clearNote(int userId) throws ServiceException {
        try {
            DAOFactory.getInstance().getNoteBookDAO().clearNoteBook(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Note> findByDate(int day, int month, int year, int userId) throws ServiceException {
        try {
            return DAOFactory.getInstance().getNoteBookDAO().findByDate(day, month, year, userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Note> findByNote(String note, int userId) throws ServiceException {
        if(note == null || note.equals("") || userId < 0) {
            throw new ServiceException("Illegal parameters");
        }

        try {
            return DAOFactory.getInstance().getNoteBookDAO().findNotesByContent(note, userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Note> showNotebook(int userID) throws ServiceException {
        try {
            return DAOFactory.getInstance().getNoteBookDAO().showAllNotes(userID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
