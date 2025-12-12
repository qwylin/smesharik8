import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ImageViewerWithURL extends JFrame {
    private Image image;
    private JLabel imageLabel;
    private JTextField urlTextField;

    public ImageViewerWithURL() {
        super("Просмотрщик изображений с котенком");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        createUI();

        // Загружаем изображение котенка по умолчанию
        loadImage("https://img.freepik.com/premium-psd/sleeping-kitten-white-bedding_538547-8871.jpg?semt=ais_hybrid");
    }

    private void createUI() {
        setLayout(new BorderLayout());

        // Панель для ввода URL
        JPanel topPanel = new JPanel(new BorderLayout());
        urlTextField = new JTextField("https://img.freepik.com/premium-psd/sleeping-kitten-white-bedding_538547-8871.jpg?semt=ais_hybrid");
        JButton loadButton = new JButton("Загрузить изображение");

        topPanel.add(new JLabel("URL изображения: "), BorderLayout.WEST);
        topPanel.add(urlTextField, BorderLayout.CENTER);
        topPanel.add(loadButton, BorderLayout.EAST);

        // Метка для отображения изображения
        imageLabel = new JLabel("", JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Обработчик кнопки загрузки
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage(urlTextField.getText());
            }
        });
    }

    private void loadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            image = ImageIO.read(url);

            if (image != null) {
                Image scaledImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(imageIcon);
                imageLabel.setText("");

                setTitle("Просмотрщик изображений - " + image.getWidth(null) + "x" + image.getHeight(null));
                JOptionPane.showMessageDialog(this, "Изображение успешно загружено!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } else {
                imageLabel.setIcon(null);
                imageLabel.setText("Не удалось загрузить изображение");
            }
        } catch (IOException e) {
            imageLabel.setIcon(null);
            imageLabel.setText("Ошибка загрузки: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Ошибка загрузки изображения: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageViewerWithURL().setVisible(true);
            }
        });
    }
}