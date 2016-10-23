package nb.bean;


import nb.bean.entity.Note;

import java.util.List;

/**
 * Created by Iryna Filipava on 06.10.2016.
 */

public class ShowAllNotesResponse extends Response {
    private List<Note> allBook;

    public List<Note> getAllBook() {
        return allBook;
    }

    public void setAllBook(List<Note> allBook) {
        this.allBook = allBook;
    }
}
