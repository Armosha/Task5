package nb.view;

import nb.bean.*;
import nb.bean.dao.impl.pool.ConnectionPool;
import nb.bean.entity.Note;
import nb.bean.entity.User;
import nb.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class View {

    private static String menu = "\"exit - exit from applicaion.\nin - authentication.\nup - registration.";

    private static String menu1 = "exit - exit from applicaion.\nadd - find note by content."
            + "\ndate - find note by date.\nshow - show all notes."
            + "\nclear - clear notebook.\nfind - find notes.";


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print(menu);
            Controller controller = new Controller();
            System.out.println("\nEnter your command, please");
            String string = reader.readLine();
            Response response;

            switch (string) {
                case "exit":
                    reader.close();
                    ConnectionPool.getInstance().closePool();
                    return;
                case "up":
                    System.out.println("Enter your login:");
                    String loginReg = reader.readLine();
                    System.out.println("Enter your password:");
                    String passwordReg = reader.readLine();
                    RegistrationRequest registrationRequest = new RegistrationRequest();
                    registrationRequest.setCommandName("REGISTRATION");
                    registrationRequest.setLogin(loginReg);
                    registrationRequest.setPassword(passwordReg);
                    response = controller.doRequest(registrationRequest);
                    if (response.isErrorStatus() == false) {
                        System.out.println(response.getResultMessage());
                    } else {
                        System.out.println(response.getErrorMessage());
                    }
                    break;
                case "in":
                    System.out.println("Enter your login:");
                    String loginIn = reader.readLine();
                    System.out.println("Enter your password:");
                    String passwordIn = reader.readLine();
                    AuthenticationRequest authenticationRequest = new AuthenticationRequest();
                    authenticationRequest.setCommandName("AUTHENTICATION");
                    authenticationRequest.setLogin(loginIn);
                    authenticationRequest.setPassword(passwordIn);
                    response = controller.doRequest(authenticationRequest);
                    if (!response.isErrorStatus() == false) {
                        System.out.println(response.getErrorMessage());
                    } else {
                        AuthenticationResponse resp = (AuthenticationResponse) response;
                        User currentUser = resp.getUser();
                        int sessionId = currentUser.getId();
                        System.out.println("Hello, " + currentUser.getLogin() + "!");

                        while (true) {
                            Response response2;
                            switch (menu1) {
                                case "exit":
                                    reader.close();
                                    ConnectionPool.getInstance().closePool();
                                    return;
                                case "add":
                                    AddNoteRequest addRequest = new AddNoteRequest();
                                    System.out.println("Enter your note:");
                                    String myNote = reader.readLine();
                                    addRequest.setNote(myNote);
                                    addRequest.setCommandName("ADD_NEW_NOTE");
                                    addRequest.setUserId(sessionId);
                                    response2 = controller.doRequest(addRequest);
                                    if (response2.isErrorStatus() == false) {
                                        System.out.println(response2.getErrorMessage());
                                    } else {
                                        System.out.println(response2.getResultMessage());
                                    }
                                    break;
                                case "show":
                                    ShowAllNotesRequest showRequest = new ShowAllNotesRequest();
                                    showRequest.setUserId(sessionId);
                                    showRequest.setCommandName("SHOW_NOTEBOOK");
                                    response2 = controller.doRequest(showRequest);
                                    if (response2.isErrorStatus() == false) {
                                        System.out.println(response2.getErrorMessage());
                                    } else {
                                        ShowAllNotesResponse res = (ShowAllNotesResponse) response2;
                                        List<Note> book = res.getAllBook();
                                        if (!book.isEmpty()) {
                                            for (Note note : book) {
                                                System.out.println(note);
                                            }
                                        }
                                        System.out.println(response2.getResultMessage());
                                    }
                                    break;
                                case "clear":
                                    ClearBookRequest clearRequest = new ClearBookRequest();
                                    System.out.println("Are you sure you want to clear user notes? All notes " +
                                            "will be lost !!! Y/N");
                                    String answer = reader.readLine();
                                    if (!(answer.equals("Y") || answer.equals("y"))) {
                                        break;
                                    }
                                    clearRequest.setUserId(sessionId);
                                    clearRequest.setCommandName("CLEAR_NOTEBOOK");
                                    response2 = controller.doRequest(clearRequest);
                                    if (response2.isErrorStatus() == false) {
                                        System.out.println(response2.getErrorMessage());
                                    } else {
                                        System.out.println(response2.getResultMessage());
                                    }
                                    break;
                                case "find":
                                    FindByNoteRequest findRequest = new FindByNoteRequest();
                                    System.out.println("Enter the search string:");
                                    String search = reader.readLine();
                                    findRequest.setFindString(search);
                                    findRequest.setUserId(sessionId);
                                    findRequest.setCommandName("FIND_BY_NOTE");
                                    response2 = controller.doRequest(findRequest);
                                    if (response2.isErrorStatus() == false) {
                                        System.out.println(response2.getErrorMessage());
                                    } else {
                                        FindByNoteResponse res = (FindByNoteResponse) response2;
                                        List<Note> noteFind = res.getFindBook();
                                        if (!noteFind.isEmpty()) {
                                            for (Note note : noteFind) {
                                                System.out.println(note);
                                            }
                                        }
                                        System.out.println(response2.getResultMessage());
                                    }
                                    break;
                                case "date":
                                    FindByDateRequest dateRequest = new FindByDateRequest();
                                    System.out.println("Enter day! Example 29");
                                    dateRequest.setDay(reader.readLine());
                                    System.out.println("Enter month! Example 8");
                                    dateRequest.setMonth(reader.readLine());
                                    System.out.println("Enter year! Example 2016");
                                    dateRequest.setYear(reader.readLine());
                                    dateRequest.setUserId(sessionId);
                                    dateRequest.setCommandName("FIND_BY_DATE");
                                    response2 = controller.doRequest(dateRequest);
                                    if (response2.isErrorStatus() == false) {
                                        System.out.println(response2.getErrorMessage());
                                    } else {
                                        FindByDateResponse res = (FindByDateResponse) response2;
                                        List<Note> noteFind = res.getDateNotes();
                                        if (!noteFind.isEmpty()) {
                                            for (Note note : noteFind) {
                                                System.out.println(note);
                                            }
                                        }
                                        System.out.println(response2.getResultMessage());
                                    }
                                    break;
                                default:
                                    System.out.println("Incorrect command!");
                                    break;
                            }
                        }
                    }
                    break;
            }
        }
    }
}