package top.yinzsw.dics.launch.ui.panel.pharmacy.content;

import top.yinzsw.dics.launch.ui.panel.common.ContentPanel;
import top.yinzsw.dics.launch.ui.panel.pharmacy.nav.PharmacyMenuEnum;

import javax.swing.*;

public class PharmacyContentPanel extends ContentPanel {
    private static final long serialVersionUID = 1L;

    public PharmacyContentPanel() {
        super(new JTabbedPane[]{
                new AccountPane(PharmacyMenuEnum.ACCOUNT.name()),
                new BillEntryPane(PharmacyMenuEnum.BILL_ENTRY.name()),
                new BillReviewPane(PharmacyMenuEnum.BILL_REVIEW.name()),
                new MedicineManagerPane(PharmacyMenuEnum.M_MANAGER.name()),
                new ReportFormPane(PharmacyMenuEnum.REPORT_FROM.name()),
        });
    }
}