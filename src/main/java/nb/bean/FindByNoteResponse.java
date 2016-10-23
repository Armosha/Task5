package nb.bean;

import nb.bean.entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iryna Filipava on 06.10.2016.
 */
public class FindByNoteResponse extends Response {

    private List<Note> findBook = new ArrayList<Note>();

    public List<Note> getFindBook() {
        return findBook;
    }

    public void setFindBook(List<Note> findBook) {
        this.findBook = findBook;
    }
}

