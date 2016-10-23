package nb.bean.dao.impl;

import nb.bean.dao.NoteBookDAO;
import nb.bean.dao.exeption.DAOException;
import nb.bean.dao.impl.pool.ConnectionPool;
import nb.bean.entity.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NoteBookDaoImpl implements NoteBookDAO {

    @Override
    public void addNote(Note note) throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO NOTES(user_id, note, date) VALUES("
                        + note.getId() + ",'" + note.getNote() + "', '" + note.getDate().getTime() + "');");
            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void clearNoteBook(int id) throws DAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM notes WHERE user_id=" + id + ";");
            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public List<Note> findNotesByContent(String content, int id) throws DAOException {
        Connection connection = null;
        List<Note> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            connection = ConnectionPool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT note, date FROM notes WHERE user_id="
                        + id + " AND note LIKE '%" + content + "%';");
                while (resultSet.next()) {
                    resultList.add(new Note(resultSet.getString(1), new Date(Long.parseLong(resultSet.getString(2))), id));
                }
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return resultList;
    }

    @Override
    public List<Note> findByDate(int day, int month, int year, int id) throws DAOException {
        Connection connection = null;
        List<Note> allNotes = new ArrayList<>();
        List<Note> notesByDate = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            connection = ConnectionPool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT note, date FROM notes WHERE user_id=" + id + ";");
                while (resultSet.next()) {
                    allNotes.add(new Note(resultSet.getString(1), new Date(Long.parseLong(resultSet.getString(2))), id));
                }
            }
        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }

        for (Note note : allNotes) {
            Calendar calendar = Calendar.getInstance();
            Date date = (Date) note.getDate();
            calendar.setTime(date);
            int dayNote = calendar.get(Calendar.DAY_OF_MONTH);
            int monthNote = calendar.get(Calendar.MONTH);
            int yearNote = calendar.get(Calendar.YEAR);
            if (day != 0 && month != 0 && year != 0) {
                if (day == dayNote && month == monthNote && year == yearNote) {
                    notesByDate.add(note);
                }
            } else if (day != 0 && month == 0 && year == 0) {
                if (day == dayNote) {
                    notesByDate.add(note);
                }
            } else if (day == 0 && month != 0 && year == 0) {
                if (month == monthNote) {
                    notesByDate.add(note);
                }
            } else if (day == 0 && month == 0 && year != 0) {
                if (year == yearNote) {
                    notesByDate.add(note);
                }
            } else if (day != 0 && month != 0 && year == 0) {
                if (day == dayNote && month == monthNote) {
                    notesByDate.add(note);
                }
            } else if (day != 0 && month == 0 && year != 0) {
                if (day == dayNote && year == yearNote) {
                    notesByDate.add(note);
                }
            } else if (day == 0 && month != 0 && year != 0) {
                if (month == monthNote && year == yearNote) {
                    notesByDate.add(note);
                }
            }
        }
        return notesByDate;
    }

    @Override
    public List<Note> showAllNotes(int id) throws DAOException {
        Connection connection = null;
        List<Note> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            connection = ConnectionPool.getInstance().getConnection();
            try(Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT note, date FROM notes WHERE user_id=" + id + ";");
                while (resultSet.next()) {
                    resultList.add(new Note(resultSet.getString(1), new Date(Long.parseLong(resultSet.getString(2))), id));
                }
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            try {
                ConnectionPool.getInstance().returnConnection(connection);
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return resultList;
    }
}
