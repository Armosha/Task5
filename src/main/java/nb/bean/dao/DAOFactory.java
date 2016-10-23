package nb.bean.dao;


import nb.bean.dao.impl.NoteBookDaoImpl;
import nb.bean.dao.impl.UserDAOImpl;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private final NoteBookDAO noteBookDAO = new NoteBookDaoImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public NoteBookDAO getNoteBookDAO() {
        return noteBookDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}