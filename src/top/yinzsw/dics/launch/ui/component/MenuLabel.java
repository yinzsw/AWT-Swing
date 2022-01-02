package top.yinzsw.dics.launch.ui.component;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public MenuLabel(String text, String name) {
        this.setText(text);
        this.setName(name);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("微软雅黑", Font.BOLD, 15));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.setOpaque(true);
        this.addMouseListener(mouseEvent());
    }

    private MouseAdapter mouseEvent() {
        Color defBgColor = this.getBackground();
        Color labBgColor = new Color(227, 227, 227);

        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(labBgColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defBgColor);
            }
        };
    }
}
