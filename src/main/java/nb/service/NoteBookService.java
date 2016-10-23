package nb.service;


import nb.bean.entity.Note;
import nb.service.exeption.ServiceException;

import java.util.List;

public interface NoteBookService {

    void addNote(String note, int userId) throws ServiceException;
    List<Note> findByDate(int day, int month, int year, int userId) throws ServiceException;
    List<Note> findByNote(String note, int userID) throws ServiceException;
    List<Note> showNotebook(int userID) throws ServiceException;
    void clearNote(int userId) throws ServiceException;
}
