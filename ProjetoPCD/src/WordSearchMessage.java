
import java.io.Serializable;

public class WordSearchMessage implements Serializable {

    private String searchWord;

    public WordSearchMessage(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getSearchWord() {
        return searchWord;
    }

}
