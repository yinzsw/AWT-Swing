package top.yinzsw.dics.launch.ui.panel.common;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public ContentPanel(JTabbedPane[] menuPanes) {
        setLayout(new CardLayout());
        for (JTabbedPane menuPane : menuPanes){
            add(menuPane, menuPane.getName());
        }
    }
}
