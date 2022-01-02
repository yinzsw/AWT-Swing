package top.yinzsw.dics.launch.ui.panel.common;

import top.yinzsw.dics.launch.ui.panel.common.banner.CenterImagePanel;
import top.yinzsw.dics.launch.ui.panel.common.banner.LeftImagePanel;
import top.yinzsw.dics.launch.ui.panel.common.banner.RightImagePanel;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public BannerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(0, 100));

        LeftImagePanel head = new LeftImagePanel();
        head.setPreferredSize(new Dimension(201, 100));

        CenterImagePanel center = new CenterImagePanel();
        center.setPreferredSize(new Dimension(450, 100));

        RightImagePanel right = new RightImagePanel();
        right.setPreferredSize(new Dimension(200, 100));

        add(head);
        add(center);
        add(right);
    }
}
