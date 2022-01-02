package top.yinzsw.dics.launch.ui.panel.manager;

import top.yinzsw.dics.launch.ui.panel.PanelEnum;
import top.yinzsw.dics.launch.ui.panel.common.OperationPanel;
import top.yinzsw.dics.launch.ui.panel.manager.content.ManagerContentPanel;
import top.yinzsw.dics.launch.ui.panel.manager.nav.ManagerNavPanel;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ManagerPanel extends OperationPanel {
    private static final long serialVersionUID = 1L;

    public ManagerPanel() {
        super(PanelEnum.MANAGER.name());
        super.render(new ManagerNavPanel(), new ManagerContentPanel());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                ManagerPanel.super.render(new ManagerNavPanel(), new ManagerContentPanel());
            }
        });
    }

}