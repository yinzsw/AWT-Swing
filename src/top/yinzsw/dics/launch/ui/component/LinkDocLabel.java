package top.yinzsw.dics.launch.ui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class LinkDocLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private static final Color textDefColor = new Color(51, 153, 255);
    private static final Color textActColor = new Color(255, 51, 0);

    public LinkDocLabel(String text) {
        this.setText(text);
        this.setForeground(textDefColor);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(mouseEvent());
    }

    private MouseAdapter mouseEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file = new File("sources/doc/用户手册.doc");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "用户手册丢失, 请与提供商联系!", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(textActColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(textDefColor);
            }
        };
    }
}
