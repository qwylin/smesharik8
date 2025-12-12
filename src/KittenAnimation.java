import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class KittenAnimation extends JFrame {
    private Timer timer;
    private Image kittenImage;
    private int x = 0;
    private int y = 0;
    private int dx = 5;
    private int dy = 3;
    private int rotation = 0;
    private double scale = 1.0;
    private boolean growing = true;

    public KittenAnimation() {
        super("Анимация спящего котенка");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        loadKittenImage();

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                repaint();
            }
        });
        timer.start();
    }

    private void loadKittenImage() {
        try {
            URL url = new URL("https://img.freepik.com/premium-psd/sleeping-kitten-white-bedding_538547-8871.jpg?semt=ais_hybrid");
            kittenImage = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Не удалось загрузить изображение котенка, используется запасной вариант");
            kittenImage = createFallbackImage();
        }
    }

    private Image createFallbackImage() {
        Image img = new ImageIcon().getImage();
        // В реальном приложении здесь создается простое изображение
        return img;
    }

    private void updateAnimation() {
        x += dx;
        y += dy;

        if (x <= 0 || x >= getWidth() - 150) dx = -dx;
        if (y <= 0 || y >= getHeight() - 150) dy = -dy;

        rotation = (rotation + 2) % 360;

        if (growing) {
            scale += 0.02;
            if (scale >= 1.5) growing = false;
        } else {
            scale -= 0.02;
            if (scale <= 0.5) growing = true;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(240, 248, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (kittenImage != null) {
            AffineTransform originalTransform = g2d.getTransform();

            g2d.translate(x + 75, y + 75);
            g2d.rotate(Math.toRadians(rotation));
            g2d.scale(scale, scale);

            g2d.drawImage(kittenImage, -75, -75, 150, 150, this);

            g2d.setTransform(originalTransform);
        }

        g2d.setColor(Color.BLACK);
        g2d.drawString("Позиция: (" + x + ", " + y + ")", 10, 20);
        g2d.drawString("Вращение: " + rotation + "°", 10, 40);
        g2d.drawString("Масштаб: " + String.format("%.2f", scale), 10, 60);
        g2d.drawString("Анимация спящего котенка", 10, getHeight() - 10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KittenAnimation().setVisible(true);
            }
        });
    }
}