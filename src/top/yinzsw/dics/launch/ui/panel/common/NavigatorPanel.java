package top.yinzsw.dics.launch.ui.panel.common;

import top.yinzsw.dics.launch.ui.MainFrame;
import top.yinzsw.dics.launch.ui.component.MenuLabel;
import top.yinzsw.dics.launch.ui.panel.PanelEnum;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigatorPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Color setFgColor = new Color(0, 121, 227);
    private static final Color defFgColor = new Color(0, 0, 0);
    MenuLabel[] menuLabels;

    public NavigatorPanel(MenuLabel[] menuLabels) {
        this.menuLabels = menuLabels;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 0));
        setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 0, 0, 2, new Color(193, 193, 193, 235)),
                new EmptyBorder(30, 25, 30, 0)));

        menuLabels[0].setForeground(setFgColor);
        Dimension dimension = new Dimension(150, 40);
        for (MenuLabel menuLabel : menuLabels) {
            menuLabel.setMaximumSize(dimension);
            menuLabel.setPreferredSize(dimension);
            menuLabel.addMouseListener(mouseEvent(""));
            add(menuLabel);
            add(Box.createVerticalStrut(20));
        }
    }

    public MouseAdapter mouseEvent(String exit) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getComponent();
                String name = label.getName();
                if (exit.equals(name)) {
                    MainFrame frame = (MainFrame) label.getTopLevelAncestor();
                    frame.switchPanel(PanelEnum.LOGIN.name());
                    return;
                }

                for (MenuLabel menuLabel : menuLabels) menuLabel.setForeground(defFgColor);
                label.setForeground(setFgColor);

                OperationPanel operationPanel = (OperationPanel) label.getParent().getParent();
                operationPanel.switchPane(name);
            }
        };
    }
}
