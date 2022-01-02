package top.yinzsw.dics.launch.ui.panel.common;

import javax.swing.*;
import java.awt.*;

public class OperationPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    ContentPanel contentPanel;
    public OperationPanel(String name) {
        setName(name);
        setLayout(new BorderLayout());
    }

    public void render( NavigatorPanel navigatorPanel, ContentPanel contentPanel) {
        this.contentPanel=contentPanel;
        removeAll();
        add(new BannerPanel(), BorderLayout.NORTH);
        add(navigatorPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void switchPane(String name) {
        CardLayout card = (CardLayout) contentPanel.getLayout();
        card.show(contentPanel, name);
    }
}
