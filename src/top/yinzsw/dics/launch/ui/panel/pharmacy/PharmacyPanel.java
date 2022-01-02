package top.yinzsw.dics.launch.ui.panel.pharmacy;

import top.yinzsw.dics.launch.ui.panel.PanelEnum;
import top.yinzsw.dics.launch.ui.panel.common.OperationPanel;
import top.yinzsw.dics.launch.ui.panel.pharmacy.content.PharmacyContentPanel;
import top.yinzsw.dics.launch.ui.panel.pharmacy.nav.PharmacyNavPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PharmacyPanel extends OperationPanel {
    private static final long serialVersionUID = 1L;

    public PharmacyPanel() {
        super(PanelEnum.PHARMACY.name());
        super.render(new PharmacyNavPanel(), new PharmacyContentPanel());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                PharmacyPanel.super.render(new PharmacyNavPanel(), new PharmacyContentPanel());
            }
        });
    }

}
