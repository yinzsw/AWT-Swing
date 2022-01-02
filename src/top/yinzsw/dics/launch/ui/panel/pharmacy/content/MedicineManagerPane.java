package top.yinzsw.dics.launch.ui.panel.pharmacy.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.ExpiredDrug;
import top.yinzsw.dics.launch.pojo.Medicine;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;
import top.yinzsw.dics.launch.ui.panel.manager.content.MedicineTypePane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class MedicineManagerPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public MedicineManagerPane(String name) {
        setName(name);
        addTab("药品零售调价", MedicineManagerPriceAdjust.getInstance());
        addTab("药品过期管理", MedicineManagerExpire.getInstance());
        loadMedicine();
        loadExpiredDrug();
    }

    static class MedicineManagerPriceAdjust extends JPanel {
        private static final MedicineManagerPriceAdjust MEDICINE_MANAGER_PRICE_ADJUST = new MedicineManagerPriceAdjust();
        JButton okBtn, resetBtn;
        ArrayList<Medicine> medicine;
        JComboBox<String> nameCBox;
        JTextField idTextField, oldPriceTextField, newPriceTextField;

        public static MedicineManagerPriceAdjust getInstance() {
            return MEDICINE_MANAGER_PRICE_ADJUST;
        }

        private MedicineManagerPriceAdjust() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 5, 5, 5));

            Dimension size = new Dimension(0, 21);

            JLabel nameLab = new JLabel("调价药品名称: ");
            nameCBox = new JComboBox<>();
            nameCBox.setPrototypeDisplayValue("类型名长度最大为十位十位");
            nameCBox.setMaximumSize(nameCBox.getPreferredSize());

            JLabel idLab = new JLabel("调价药品编号: ");
            idTextField = new JTextField(50);
            idTextField.setMaximumSize(size);
            idTextField.setEditable(false);
            JLabel idInfoLab = new JLabel("药品名称由系统给出");

            JLabel oldPriceLab = new JLabel("药品原零售价: ");
            oldPriceTextField = new JTextField(50);
            oldPriceTextField.setMaximumSize(size);
            oldPriceTextField.setEditable(false);
            JLabel oldPriceInfoLab = new JLabel("该价格由系统给出");

            JLabel newPriceLab = new JLabel("药品新零售价: ");
            newPriceTextField = new JTextField(50);
            newPriceTextField.setMaximumSize(size);
            newPriceTextField.addKeyListener(ComponentUtils.limitFieldInput(newPriceTextField, "^\\d+(\\.\\d{0,2})?$"));
            newPriceTextField.addKeyListener(ComponentUtils.limitFieldLength(newPriceTextField, 12));

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");

            Component[][] components = {
                    {nameLab, nameCBox, Box.createHorizontalGlue()},
                    {idLab, idTextField, idInfoLab, Box.createHorizontalGlue()},
                    {oldPriceLab, oldPriceTextField, oldPriceInfoLab, Box.createHorizontalGlue()},
                    {newPriceLab, newPriceTextField, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(85), okBtn, resetBtn, Box.createHorizontalGlue()},
            };
            add(new BoxGenerator(components, 5, 8));
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));
            MedicineManagerPriceAdjustController medicineManagerPriceAdjustController = new MedicineManagerPriceAdjustController();
            nameCBox.addActionListener(medicineManagerPriceAdjustController.load());
            okBtn.addActionListener(medicineManagerPriceAdjustController.update());
            resetBtn.addActionListener(medicineManagerPriceAdjustController.reset());
        }

        class MedicineManagerPriceAdjustController {
            public ActionListener load() {
                return e -> {
                    if (medicine == null || medicine.size() == 0) return;
                    int selectedIndex = nameCBox.getSelectedIndex() == -1 ? 0 : nameCBox.getSelectedIndex();
                    Medicine medicineItem = medicine.get(selectedIndex);
                    idTextField.setText(medicineItem.getiMedicineId() + "");
                    oldPriceTextField.setText(medicineItem.getmRetailPrices() + "");
                };
            }

            public ActionListener update() {
                return e -> {
                    String newPrice = newPriceTextField.getText().trim();
                    Medicine medicineItem = medicine.get(nameCBox.getSelectedIndex());
                    medicineItem.setmRetailPrices(Double.parseDouble(0 + newPrice));

                    int row = DAO_STORE.updateMedicine(medicineItem);
                    if (row > 0) {
                        newPriceTextField.setText("");
                        MedicineManagerPane.loadMedicine();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> newPriceTextField.setText("");
            }
        }
    }

    static class MedicineManagerExpire extends JPanel {
        private static final MedicineManagerExpire MEDICINE_MANAGER_EXPIRE = new MedicineManagerExpire();
        JButton okBtn, resetBtn;
        DataTable dataTable;

        public static MedicineManagerExpire getInstance() {
            return MEDICINE_MANAGER_EXPIRE;
        }

        private MedicineManagerExpire() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));


            Object[] objects = {"药品编号", "药品名称", "供应商编号", "供应商", "现存数量", "失效日期"};
            dataTable = new DataTable(objects, 0, 1, 2, 3, 4, 5);
            JScrollPane scrollPane = new JScrollPane(dataTable);

            okBtn = new JButton("清理出库");
            resetBtn = new JButton("刷新列表");

            Component[][] components = {{scrollPane}, {okBtn, resetBtn, Box.createHorizontalGlue()}};
            BoxGenerator box = new BoxGenerator(components, 5, 8);
            box.setBorder(new TitledBorder("过期药品一览"));
            add(box);

            MedicineManagerExpireController medicineManagerExpireController = new MedicineManagerExpireController();
            okBtn.addActionListener(medicineManagerExpireController.update());
            resetBtn.addActionListener(medicineManagerExpireController.query());
        }

        class MedicineManagerExpireController {
            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;
                    int mid = Integer.parseInt(tableSelectRowData.elementAt(0));
                    int sid = Integer.parseInt(tableSelectRowData.elementAt(2));

                    int row = DAO_STORE.updateStoreRoom(mid, sid);
                    if (row > 0) {
                        MedicineManagerPane.loadExpiredDrug();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "清理成功", "清理失败(未知错误)");
                };
            }

            public ActionListener query() {
                return e -> MedicineManagerPane.loadExpiredDrug();
            }
        }
    }

    public static void loadMedicine() {
        MedicineManagerPriceAdjust medicineManagerPriceAdjust = MedicineManagerPriceAdjust.getInstance();

        medicineManagerPriceAdjust.nameCBox.removeAllItems();

        medicineManagerPriceAdjust.medicine = DAO_STORE.getMedicine();
        medicineManagerPriceAdjust.medicine.forEach(medicine -> medicineManagerPriceAdjust.nameCBox.addItem(medicine.getvMedicineName()));
    }

    public static void loadExpiredDrug() {
        MedicineManagerExpire medicineManagerExpire = MedicineManagerExpire.getInstance();

        medicineManagerExpire.dataTable.clear();

        long curTime = new Date().getTime();
        DAO_STORE.getExpiredDrug().forEach(expiredDrug -> {
            if (curTime > expiredDrug.getdDateOfExpiry().getTime()) return;
            medicineManagerExpire.dataTable.addRow(new Object[]{
                    expiredDrug.getiMedicineId(), expiredDrug.getvMedicineName(),
                    expiredDrug.getiSupplierId(), expiredDrug.getvSupplierName(),
                    expiredDrug.getiPresentQuantity(), expiredDrug.getdDateOfExpiry()
            });
        });
    }
}
