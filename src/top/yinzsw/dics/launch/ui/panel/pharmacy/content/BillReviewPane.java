package top.yinzsw.dics.launch.ui.panel.pharmacy.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.ApplicationList;
import top.yinzsw.dics.launch.pojo.Manufacturer;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;
import top.yinzsw.dics.launch.ui.panel.manager.content.ManufacturePane;
import top.yinzsw.dics.launch.ui.panel.manager.content.MedicineManagerPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class BillReviewPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BillReviewPane(String name) {
        setName(name);
        addTab("单据审核", BillReview.getInstance());
        loadApplicationList();
    }

    static class BillReview extends JPanel {
        private static final BillReview BILL_REVIEW = new BillReview();
        JButton commitBtn, delBtn, cancelBtn;
        DataTable dataTable;

        public static BillReview getInstance() {
            return BILL_REVIEW;
        }

        private BillReview() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel infoLab = new JLabel("需要审核的单据如下: ");
            JLabel detailInfoLab = new JLabel("(通过打钩来来决定审核是否通过)");

            Object[] objects = {"编号", "药品编号", "申请购买数量", "现存数量", "申请日期", "审核状态"};
            dataTable = new DataTable(objects, 0, 1, 2, 3, 4);
            JCheckBox jCheckBox = new JCheckBox("通过");
            dataTable.setColCellEditor(5, jCheckBox);
            JScrollPane scrollPane = new JScrollPane(dataTable);

            commitBtn = new JButton("提交审核");
            delBtn = new JButton("删除选中行");
            cancelBtn = new JButton("取消审核");
            Component[][] components = {
                    {infoLab, Box.createHorizontalGlue()},
                    {detailInfoLab, Box.createHorizontalGlue()},
                    {scrollPane},
                    {commitBtn, delBtn, cancelBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator boxGenerator = new BoxGenerator(components, 5, 8);
            add(boxGenerator);

            BillReviewController billReviewController = new BillReviewController();
            commitBtn.addActionListener(billReviewController.update());
            delBtn.addActionListener(billReviewController.delete());
            cancelBtn.addActionListener(billReviewController.query());
        }

        class BillReviewController {
            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    ApplicationList applicationList = new ApplicationList();
                    applicationList.setiApplicationId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    String state = "true".equals(tableSelectRowData.elementAt(5)) ? "1" : "0";
                    applicationList.setbStateOfApplication(state);

                    int row = DAO_STORE.updateApplicationList(applicationList);
                    if (row > 0) {
                        BillReviewPane.loadApplicationList();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(未知错误)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;

                    int row = DAO_STORE.deleteApplicationList(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        BillReviewPane.loadApplicationList();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener query() {
                return e -> BillReviewPane.loadApplicationList();
            }
        }
    }


    public static void loadApplicationList() {
        BillReview billReview = BillReview.getInstance();

        billReview.dataTable.clear();
        DAO_STORE.getApplicationList().forEach(applicationList -> {
            String dateFormat = applicationList.getdDateOfApplication() == null ? "-" : SIMPLE_DATE_FORMAT.format(applicationList.getdDateOfApplication());
            boolean state = "1".equals(applicationList.getbStateOfApplication());
            billReview.dataTable.addRow(new Object[]{
                    applicationList.getiApplicationId(), applicationList.getiMedicineId(),
                    applicationList.getiQuantityOfApplication(), applicationList.getiPresentQuantity(),
                    dateFormat, state
            });
        });
    }
}