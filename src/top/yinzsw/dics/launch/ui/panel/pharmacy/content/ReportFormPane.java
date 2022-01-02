package top.yinzsw.dics.launch.ui.panel.pharmacy.content;

import com.mysql.cj.protocol.x.OkBuilder;
import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;
import top.yinzsw.dics.launch.ui.panel.manager.content.SuppliersPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReportFormPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public ReportFormPane(String name) {
        setName(name);
        addTab("库存统计与报表", ReportForm.getInstance());
        addTab("缺货统计与报表", ReportFormStockOut.getInstance());
        loadStoreRoom();
    }

    static class ReportForm extends JPanel {
        private static final ReportForm REPORT_FORM = new ReportForm();
        DataTable dataTable;

        public static ReportForm getInstance() {
            return REPORT_FORM;
        }

        private ReportForm() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            Object[] objects = {"药品编号", "药品名称", "生产商名称", "库存数量"};
            dataTable = new DataTable(objects, 0, 1, 2, 3);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            Component[][] components = {{scrollPane}};

            BoxGenerator box = new BoxGenerator(components, 5, 8);
            box.setBorder(new TitledBorder("药品库存数量一览"));
            add(box);
        }
    }

    static class ReportFormStockOut extends JPanel {
        private static final ReportFormStockOut REPORT_FORM_STOCK_OUT = new ReportFormStockOut();
        JButton reBtn;
        DataTable dataTable;

        public static ReportFormStockOut getInstance() {
            return REPORT_FORM_STOCK_OUT;
        }

        private ReportFormStockOut() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            Dimension size = new Dimension(Short.MAX_VALUE, 20);

            Object[] objects = {"药品编号", "药品名称", "药品现存数量", "库存最低下线"};
            dataTable = new DataTable(objects, 0, 1, 2, 3);
            JScrollPane scrollPane = new JScrollPane(dataTable);
            reBtn = new JButton("重新查询");
            reBtn.setMaximumSize(size);

            Component[][] components = {{scrollPane}, {reBtn}};

            BoxGenerator box = new BoxGenerator(components, 5, 8);
            box.setBorder(new TitledBorder("紧缺药品一览"));
            add(box);

            reBtn.addActionListener(ReportFormStockOutController.query());
        }

        static class ReportFormStockOutController {
            public static ActionListener query() {
                return e -> ReportFormPane.loadStoreRoom();
            }
        }
    }

    public static void loadStoreRoom() {
        ReportForm reportForm = ReportForm.getInstance();
        ReportFormStockOut reportFormStockOut = ReportFormStockOut.getInstance();

        reportForm.dataTable.clear();
        reportFormStockOut.dataTable.clear();

        DAO_STORE.getStoreRoom().forEach(storeRoom -> {
            reportForm.dataTable.addRow(new Object[]{storeRoom.getiMedicineId(), storeRoom.getvMedicineName(), storeRoom.getvFacturerName(), storeRoom.getiPresentQuantity()});
            if (storeRoom.getiPresentQuantity() < storeRoom.getiMinimumStorage()) {
                reportFormStockOut.dataTable.addRow(new Object[]{storeRoom.getiMedicineId(), storeRoom.getvMedicineName(), storeRoom.getiPresentQuantity(), storeRoom.getiMinimumStorage()});
            }
        });
    }
}
