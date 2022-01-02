package top.yinzsw.dics.launch.ui.panel.manager.nav;


import top.yinzsw.dics.launch.ui.component.MenuLabel;
import top.yinzsw.dics.launch.ui.panel.common.NavigatorPanel;

import java.awt.event.*;

public class ManagerNavPanel extends NavigatorPanel {
    private static final long serialVersionUID = 1L;

    public ManagerNavPanel() {
        super(new MenuLabel[]{
                new MenuLabel("账号管理", ManagerMenuEnum.ACCOUNT.name()),
                new MenuLabel("生产单位 资料管理", ManagerMenuEnum.MANUFACTURE.name()),
                new MenuLabel("供货单位 资料管理", ManagerMenuEnum.SUPPLIERS.name()),
                new MenuLabel("药品信息管理", ManagerMenuEnum.M_MANAGER.name()),
                new MenuLabel("药房信息管理", ManagerMenuEnum.M_ROOM.name()),
                new MenuLabel("药品类别 信息管理", ManagerMenuEnum.M_TYPE.name()),
                new MenuLabel("退出后台 数据管理", ManagerMenuEnum.LOGON.name()),
        });
    }

    @Override
    public MouseAdapter mouseEvent(String exit) {
        return super.mouseEvent(ManagerMenuEnum.LOGON.name());
    }
}
