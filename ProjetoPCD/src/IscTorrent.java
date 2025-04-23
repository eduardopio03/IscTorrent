
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class IscTorrent {

    // Componentes principais da interface
    private final JFrame frame;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel label;
    private JTextField textField;
    private JButton searchButton;
    private JPanel rightPanel;
    private JButton downloadButton;
    private JButton connectButton;

    private Node node;

    public IscTorrent(int port, String workDir) throws IOException {
        File workDirFile = new File(workDir);

        // Se for um caminho relativo, tenta ajustá-lo à raiz do projeto
        if (!workDirFile.isAbsolute()) {
            // Sobe da pasta onde o Java está a correr para a raiz do projeto
            File projectRoot = new File(System.getProperty("user.dir")).getParentFile();
            workDirFile = new File(projectRoot, workDir);
        }

        String absoluteWorkDir = workDirFile.getCanonicalPath();

        if (!workDirFile.exists() || !workDirFile.isDirectory()) {
            System.err.println("[ERRO] Diretório de trabalho não encontrado: " + absoluteWorkDir);
            System.exit(1);
        }
        System.out.println("[INFO] Diretório de trabalho definido como: " + absoluteWorkDir);

        this.node = new Node(absoluteWorkDir, port);
        frame = new JFrame("Aplicação de Pesquisa ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        addFrameContent();
    }

    public void open() {
        frame.setVisible(true);

    }

    public void addFrameContent() {
        frame.setLayout(new BorderLayout());

        // Painel superior -> Contem a label, campo de texto e botao de pesquisa
        topPanel = new JPanel(new GridLayout());
        label = new JLabel("Texto a procurar: ");
        textField = new JTextField();
        searchButton = new JButton("Procurar");

        topPanel.add(label);
        topPanel.add(textField);
        topPanel.add(searchButton);

        // Lista de resultados -> Apresenta os resultados da pesquisa
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> resultList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(resultList);

        // Painel lateral direito -> Contem os botoes "Descarregar" e "Ligar no"
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 5, 5));
        downloadButton = new JButton("Descarregar");
        connectButton = new JButton("Ligar a nó");

        rightPanel.add(downloadButton);
        rightPanel.add(connectButton);

        // Painel inferior -> Combina a lista de resultados e o painel lateral direito
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        // Adicionar tudo na frame principal
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NodeFrame nodeFrame = new NodeFrame(node);
                nodeFrame.open();
            }

        });
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java IscTorrent <porto> <pasta>");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        String folder = args[1];

        SwingUtilities.invokeLater(() -> {
            try {
                IscTorrent iscTorrent = new IscTorrent(port, folder);
                iscTorrent.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
