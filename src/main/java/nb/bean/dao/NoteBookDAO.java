package nb.bean.dao;


import nb.bean.dao.exeption.DAOException;
import nb.bean.entity.Note;

import java.util.List;

public interface NoteBookDAO {

    void addNote(Note note) throws DAOException;
    void clearNoteBook(int id) throws DAOException;
    List<Note> findNotesByContent(String content, int id) throws DAOException;
    List<Note> findByDate(int day, int month, int year, int userId) throws DAOException;
    List<Note> showAllNotes(int id) throws DAOException;
}
