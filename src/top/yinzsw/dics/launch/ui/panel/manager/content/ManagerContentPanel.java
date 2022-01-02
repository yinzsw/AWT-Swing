package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.ui.panel.common.ContentPanel;
import top.yinzsw.dics.launch.ui.panel.manager.nav.ManagerMenuEnum;

import javax.swing.*;

public class ManagerContentPanel extends ContentPanel {
    private static final long serialVersionUID = 1L;

    public ManagerContentPanel() {
        super(new JTabbedPane[]{
                new AccountPane(ManagerMenuEnum.ACCOUNT.name()),
                new ManufacturePane(ManagerMenuEnum.MANUFACTURE.name()),
                new SuppliersPane(ManagerMenuEnum.SUPPLIERS.name()),
                new MedicineManagerPane(ManagerMenuEnum.M_MANAGER.name()),
                new MedicineRoomPane(ManagerMenuEnum.M_ROOM.name()),
                new MedicineTypePane(ManagerMenuEnum.M_TYPE.name()),
        });
    }
}
