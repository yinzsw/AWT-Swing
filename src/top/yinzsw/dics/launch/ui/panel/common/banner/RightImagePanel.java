package top.yinzsw.dics.launch.ui.panel.common.banner;

import javax.swing.*;
import java.awt.*;

public class RightImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public RightImagePanel() {

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image image = Toolkit.getDefaultToolkit().getImage("sources/img/manager/foot.jpg");
        g2d.drawImage(image, 0, 0, getWidth(),getHeight(),this);
    }
}
