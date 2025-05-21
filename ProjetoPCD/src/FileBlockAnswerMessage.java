
import java.io.Serializable;

public class FileBlockAnswerMessage implements Serializable {

    private String fileName;
    private long offset;
    private byte[] data;

    public FileBlockAnswerMessage(String fileName, long offset, byte[] data) {
        this.fileName = fileName;
        this.offset = offset;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public long getOffset() {
        return offset;
    }

    public byte[] getData() {
        return data;
    }

}
