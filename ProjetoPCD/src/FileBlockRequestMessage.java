// Identifica um bloco de um ficheiro

import java.io.Serializable;

public class FileBlockRequestMessage implements Serializable {

    private String fileName;    // Nome do ficheiro
    private long offset;        // Indice do byte do inÍcio do bloco
    private int length;         // Tamanho do bloco

    public FileBlockRequestMessage(String fileName, long offset, int length) {
        this.fileName = fileName;
        this.offset = offset;
        this.length = length;
    }

    public String getFileName() {
        return fileName;
    }

    public long getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }
}
