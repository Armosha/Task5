package nb.bean.dao;


import nb.bean.dao.exeption.DAOException;
import nb.bean.entity.User;

public interface UserDAO {

    User logination(String login, String password) throws DAOException;
    boolean registration(String login, String password) throws DAOException;
}