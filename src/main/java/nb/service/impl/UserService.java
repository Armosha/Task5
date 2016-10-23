package nb.service.impl;


import nb.bean.entity.User;
import nb.service.exeption.ServiceException;

public interface UserService {

    User logination(String login, String password) throws ServiceException;
    boolean registration(String login, String password) throws ServiceException;
}
