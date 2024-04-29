import javax.swing.*;
import java.awt.*;

public class PonerImagenPanel extends JPanel {
    private Image image;

    public PonerImagenPanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
