package top.yinzsw.dics.launch.ui.panel.pharmacy.nav;

import top.yinzsw.dics.launch.ui.component.MenuLabel;
import top.yinzsw.dics.launch.ui.panel.common.NavigatorPanel;

import java.awt.event.MouseAdapter;

public class PharmacyNavPanel extends NavigatorPanel {
    private static final long serialVersionUID = 1L;

    public PharmacyNavPanel() {
        super(new MenuLabel[]{
                new MenuLabel("账号管理", PharmacyMenuEnum.ACCOUNT.name()),
                new MenuLabel("业务单据录入", PharmacyMenuEnum.BILL_ENTRY.name()),
                new MenuLabel("业务单据审核", PharmacyMenuEnum.BILL_REVIEW.name()),
                new MenuLabel("药品管理", PharmacyMenuEnum.M_MANAGER.name()),
                new MenuLabel("统计与报表管理", PharmacyMenuEnum.REPORT_FROM.name()),
                new MenuLabel("退出后台 数据管理", PharmacyMenuEnum.LOGON.name()),
        });
    }

    @Override
    public MouseAdapter mouseEvent(String exit) {
        return super.mouseEvent(PharmacyMenuEnum.LOGON.name());
    }
}
