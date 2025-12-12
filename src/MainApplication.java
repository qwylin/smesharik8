import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame {
    private JTabbedPane tabbedPane;

    public MainApplication() {
        super("Практическая работа - Приложения с котенком");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        tabbedPane = new JTabbedPane();

        // Вкладка с просмотрщиком изображений
        JPanel imageViewerPanel = new JPanel(new BorderLayout());
        JButton openImageViewerBtn = new JButton("Открыть просмотрщик изображений");
        imageViewerPanel.add(openImageViewerBtn, BorderLayout.NORTH);
        imageViewerPanel.add(new JLabel("<html><center>Загружает и отображает изображение котенка из интернета<br>"
                + "Позволяет вводить свой URL для загрузки</center></html>", JLabel.CENTER), BorderLayout.CENTER);

        // Вкладка с анимацией
        JPanel animationPanel = new JPanel(new BorderLayout());
        JButton openAnimationBtn = new JButton("Открыть анимацию котенка");
        animationPanel.add(openAnimationBtn, BorderLayout.NORTH);
        animationPanel.add(new JLabel("<html><center>Анимация с движущимся, вращающимся и масштабирующимся котенком<br>"
                + "Котенок перемещается по экрану с отскоками от границ</center></html>", JLabel.CENTER), BorderLayout.CENTER);

        // Вкладка с информацией
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(new JLabel("<html><center><h2>Практическая работа №5</h2>"
                + "<h3>Создание программ с графическим интерфейсом пользователя</h3>"
                + "<p><b>Выполнил:</b> [Матюхина Алина]</p>"
                + "<p><b>Группа:</b> [УИБО-02-23]</p>"
                + "<p><b>Дата:</b> " + new java.util.Date() + "</p>"
                + "<br><p>Используемое изображение:<br>"
                + "Спящий котенок на белой постели</p>"
                + "</center></html>", JLabel.CENTER), BorderLayout.CENTER);

        tabbedPane.addTab("Главная", infoPanel);
        tabbedPane.addTab("Просмотр изображений", imageViewerPanel);
        tabbedPane.addTab("Анимация", animationPanel);

        add(tabbedPane);

        // Обработчики кнопок
        openImageViewerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ImageViewerWithURL().setVisible(true);
            }
        });

        openAnimationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new KittenAnimation().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApplication().setVisible(true);
            }
        });
    }
}