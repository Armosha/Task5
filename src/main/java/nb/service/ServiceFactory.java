package nb.service;

import nb.service.impl.NoteBookServiceImpl;
import nb.service.impl.UserService;
import nb.service.impl.UserServiceImpl;


public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private NoteBookService nbService = new NoteBookServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public static ServiceFactory getInstance(){
        return instance;
    }

    public NoteBookService getNoteBookService(){
        return nbService;
    }

    public UserService getUserService() {
        return userService;
    }

}
