
import java.util.ArrayList;
import java.util.List;

public class DownloadTaskManager {

    private static final int BLOCK_SIZE = 10240;        // Valor padrão do enunciado
    private List<FileBlockRequestMessage> blockRequests;

    public DownloadTaskManager() {
        this.blockRequests = new ArrayList<>();
    }

    public void createBlockRequests(String fileName, long fileSize) {
        blockRequests.clear();      // Limpar a lista antes de criar novos blocos
        long offset = 0;

        while (offset < fileSize) {
            int length = (int) Math.min(BLOCK_SIZE, fileSize - offset);     // Caso em que o tamanho do bloco é menor que o BLOCK_SIZE
            blockRequests.add(new FileBlockRequestMessage(fileName, offset, length));
            offset += length;
        }
    }

    public List<FileBlockRequestMessage> getBlockRequests() {
        return blockRequests;
    }

    // Testes
    public static void main(String[] args) {
        DownloadTaskManager manager = new DownloadTaskManager();
        String fileName = "example.mp3";
        long fileSize = 50000;

        manager.createBlockRequests(fileName, fileSize);

        for (FileBlockRequestMessage block : manager.getBlockRequests()) {
            System.out.println("Bloco: " + block.getFileName()
                    + ", Offset: " + block.getOffset()
                    + ", Length: " + block.getLength());
        }
    }

}
