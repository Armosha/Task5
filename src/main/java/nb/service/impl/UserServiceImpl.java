package nb.service.impl;


import nb.bean.dao.DAOFactory;
import nb.bean.dao.exeption.DAOException;
import nb.bean.entity.User;
import nb.service.exeption.ServiceException;

public class UserServiceImpl implements UserService {

    @Override
    public User logination(String login, String password) throws ServiceException {
        if(login == null || login.equals("") || password == null || password.equals("")) {
            throw new ServiceException("Illegal parameters");
        }

        try {
            return DAOFactory.getInstance().getUserDAO().logination(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if(login == null || login.equals("") || password == null || password.equals("")) {
            throw new ServiceException("Illegal parameters");
        }

        try {
            return DAOFactory.getInstance().getUserDAO().registration(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
