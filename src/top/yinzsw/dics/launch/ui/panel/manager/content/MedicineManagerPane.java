package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.Medicine;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class MedicineManagerPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public MedicineManagerPane(String name) {
        setName(name);
        addTab("添加药品信息", MedicineManagerAdd.getInstance());
        addTab("查询,修改,删除药品信息", MedicineManagerQMD.getInstance());
        loadManufacturer();
        loadGenera();
        loadMedicine();
    }

    static class MedicineManagerAdd extends JPanel {
        private static final MedicineManagerAdd MEDICINE_MANAGER_ADD = new MedicineManagerAdd();
        JButton okBtn, resetBtn;
        JTextField nameTextField, standardTextField, doseTextField, priceTextField;
        JTextArea descTextArea;
        JComboBox<String> manufacturerCBox, typeCBox;

        public static MedicineManagerAdd getInstance() {
            return MEDICINE_MANAGER_ADD;
        }

        private MedicineManagerAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(10, 5, 5, 5));

            Dimension vGap = new Dimension(0, 5);
            Dimension size = new Dimension(0, 20);

            JLabel nameLab = new JLabel("药品名称: ");
            nameTextField = new JTextField(30);
            nameTextField.setMaximumSize(size);

            JLabel descLab = new JLabel("药品描述: ");
            descTextArea = new JTextArea(30, Short.MAX_VALUE);
            descTextArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(descTextArea);
            Component[][] components1 = {{nameLab, nameTextField, Box.createHorizontalGlue()}, {descLab, scrollPane}};
            BoxGenerator box1 = new BoxGenerator(components1, 5, 8);
            box1.setBorder(new TitledBorder(""));
            add(box1);
            add(Box.createRigidArea(vGap));


            JLabel standardLab = new JLabel("药品规格: ");
            standardTextField = new JTextField(30);
            standardTextField.setMaximumSize(size);
            JLabel manufacturerLab = new JLabel("药品生产商: ");
            manufacturerCBox = new JComboBox<>();
            manufacturerCBox.setPrototypeDisplayValue("类型名长度最大为十位十位");
            manufacturerCBox.setMaximumSize(manufacturerCBox.getPreferredSize());

            JLabel doseLab = new JLabel("药品剂量: ");
            doseTextField = new JTextField(30);
            doseTextField.setMaximumSize(size);
            JLabel typeLab = new JLabel("药品类别名: ");
            typeCBox = new JComboBox<>();
            typeCBox.setPrototypeDisplayValue("类型名长度最大为十位十位");
            typeCBox.setMaximumSize(typeCBox.getPreferredSize());

            Component[][] components2 = {
                    {standardLab, standardTextField, manufacturerLab, manufacturerCBox, Box.createHorizontalGlue()},
                    {doseLab, doseTextField, typeLab, typeCBox, Box.createHorizontalGlue()}
            };
            BoxGenerator box2 = new BoxGenerator(components2, 5, 8);
            box2.setBorder(new TitledBorder(""));
            add(box2);
            add(Box.createRigidArea(vGap));


            JLabel priceLab = new JLabel("零售价格: ");
            priceTextField = new JTextField(30);
            priceTextField.addKeyListener(ComponentUtils.limitFieldInput(priceTextField, "^\\d+(\\.\\d{0,2})?$"));
            priceTextField.addKeyListener(ComponentUtils.limitFieldLength(priceTextField, 12));
            priceTextField.setMaximumSize(size);
            Component[][] components3 = {{priceLab, priceTextField, Box.createHorizontalGlue()}};
            BoxGenerator box3 = new BoxGenerator(components3, 5, 8);
            box3.setBorder(new TitledBorder(""));
            add(box3);
            add(Box.createRigidArea(vGap));

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");
            Component[][] components4 = {{okBtn, resetBtn, Box.createHorizontalGlue()},};
            add(new BoxGenerator(components4, 5, 0));
            add(Box.createRigidArea(vGap));

            MedicineManagerAddController medicineManagerAddController = new MedicineManagerAddController();
            okBtn.addActionListener(medicineManagerAddController.add());
            resetBtn.addActionListener(medicineManagerAddController.reset());
        }

        class MedicineManagerAddController {
            public ActionListener add() {
                return e -> {
                    boolean hasEmptyField = ComponentUtils.hasEmptyField(nameTextField, descTextArea, standardTextField, doseTextField, priceTextField);
                    if (hasEmptyField) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    Medicine medicine = new Medicine();
                    medicine.setvMedicineName(nameTextField.getText().trim());
                    medicine.setvMedicineDescription(descTextArea.getText().trim());
                    medicine.setvMedicineStandard(standardTextField.getText().trim());
                    medicine.setvFacturerName(manufacturerCBox.getSelectedItem() + "");
                    medicine.setvMedicineDosage(doseTextField.getText().trim());
                    medicine.setvMedicineGeneraName(typeCBox.getSelectedItem() + "");
                    medicine.setmRetailPrices(Double.parseDouble(priceTextField.getText().trim()));

                    int row = DAO_STORE.addMedicine(medicine);
                    if (row > 0) {
                        ComponentUtils.resetField(nameTextField, descTextArea, standardTextField, doseTextField, priceTextField);
                        MedicineManagerPane.loadMedicine();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(nameTextField, descTextArea, standardTextField, doseTextField, priceTextField);
            }
        }
    }

    static class MedicineManagerQMD extends JPanel {
        private static final MedicineManagerQMD MEDICINE_MANAGER_QMD = new MedicineManagerQMD();
        JButton searchBtn, resetBtn, refreshBtn, saveBtn, cancelBtn, delBtn;
        ArrayList<Medicine> medicine;
        JTextField idTextField, nameTextField;
        JComboBox<String> manufacturerCBox, typeCBox, manufacturerCBox1, typeCBox1;
        JComboBox<Object> manufacturerCBoxR, typeCBoxR;
        DataTable dataTable;

        public static MedicineManagerQMD getInstance() {
            return MEDICINE_MANAGER_QMD;
        }

        private MedicineManagerQMD() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(10, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel idLab = new JLabel("药品编号: ");
            idTextField = new JTextField(30);
            idTextField.addKeyListener(ComponentUtils.limitFieldInput(idTextField, "^\\d+$"));
            idTextField.addKeyListener(ComponentUtils.limitFieldLength(idTextField, 9));
            idTextField.setMaximumSize(size);

            JLabel vendorLab = new JLabel("生产厂商: ");
            manufacturerCBox1 = new JComboBox<>();
            manufacturerCBox1.setPrototypeDisplayValue("类型名长度最大为十位十位");
            manufacturerCBox1.setMaximumSize(manufacturerCBox1.getPreferredSize());

            searchBtn = new JButton("搜索");


            JLabel nameLab = new JLabel("药品名称: ");
            nameTextField = new JTextField(30);
            nameTextField.setMaximumSize(size);
            JLabel typeLab = new JLabel("药品类别: ");
            typeCBox1 = new JComboBox<>();
            typeCBox1.setPrototypeDisplayValue("类型名长度最大为十位十位");
            typeCBox1.setMaximumSize(typeCBox1.getPreferredSize());

            resetBtn = new JButton("重置");

            Component[][] topComponents = {
                    {idLab, idTextField, vendorLab, manufacturerCBox1, searchBtn, Box.createHorizontalGlue()},
                    {nameLab, nameTextField, typeLab, typeCBox1, resetBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("搜索条件"));
            add(top);

            Object[] objects = {"编号", "名称", "描述", "零售价", "药品规格", "药品剂量", "生产厂商", "药品类别"};
            dataTable = new DataTable(objects, 0);
            manufacturerCBoxR = dataTable.setColCellEditor(6, manufacturerCBox = new JComboBox<>());
            typeCBoxR = dataTable.setColCellEditor(7, typeCBox = new JComboBox<>());
            JScrollPane scrollPane = new JScrollPane(dataTable);

            refreshBtn = new JButton("刷新列表");
            saveBtn = new JButton("保存修改");
            cancelBtn = new JButton("放弃修改");
            delBtn = new JButton("删除记录");

            Component[][] bottomComponents = {{scrollPane}, {refreshBtn, saveBtn, cancelBtn, delBtn, Box.createHorizontalGlue()},};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("搜索结果"));
            add(bottom);

            MedicineManagerQMDController medicineManagerQMDController = new MedicineManagerQMDController();
            searchBtn.addActionListener(medicineManagerQMDController.search());
            resetBtn.addActionListener(medicineManagerQMDController.reset());
            refreshBtn.addActionListener(medicineManagerQMDController.query());
            saveBtn.addActionListener(medicineManagerQMDController.update());
            cancelBtn.addActionListener(medicineManagerQMDController.cancel());
            delBtn.addActionListener(medicineManagerQMDController.delete());
        }

        class MedicineManagerQMDController {
            public Boolean reSearch() {
                ArrayList<Integer> ids = new ArrayList<>();
                if (medicine == null || medicine.size() == 0) return false;
                medicine.forEach(item -> ids.add(item.getiMedicineId()));
                medicine = DAO_STORE.getMedicine(ids.toArray());

                dataTable.clear();

                medicine.forEach(medicine -> dataTable.addRow(new Object[]{
                        medicine.getiMedicineId(), medicine.getvMedicineName(), medicine.getvMedicineDescription(), medicine.getmRetailPrices(),
                        medicine.getvMedicineStandard(), medicine.getvMedicineDosage(), medicine.getvFacturerName(),
                        medicine.getvMedicineGeneraName()
                }));
                return true;
            }

            public ActionListener search() {
                return e -> {
                    String id = idTextField.getText().trim();
                    String vendor = manufacturerCBox1.getSelectedItem() + "";
                    String name = nameTextField.getText().trim();
                    String type = typeCBox1.getSelectedItem() + "";

                    if ("".equals(id) && "".equals(vendor) && "".equals(name) && "".equals(type)) {
                        JOptionPane.showMessageDialog(null, "你还没有输入关键词");
                        return;
                    }

                    medicine = DAO_STORE.getMedicine(Integer.parseInt(0 + id), vendor, name, type);

                    dataTable.clear();

                    medicine.forEach(medicine -> dataTable.addRow(new Object[]{
                            medicine.getiMedicineId(), medicine.getvMedicineName(), medicine.getvMedicineDescription(), medicine.getmRetailPrices(),
                            medicine.getvMedicineStandard(), medicine.getvMedicineDosage(), medicine.getvFacturerName(),
                            medicine.getvMedicineGeneraName()
                    }));

                    if (medicine.size() > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField);
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(medicine.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField);
            }

            public ActionListener query() {
                return e -> {
                    medicine = null;
                    MedicineManagerPane.loadMedicine();
                };
            }

            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    Medicine medicine = new Medicine();
                    medicine.setiMedicineId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    medicine.setvMedicineName(tableSelectRowData.elementAt(1));
                    medicine.setvMedicineDescription(tableSelectRowData.elementAt(2));
                    medicine.setmRetailPrices(Double.parseDouble(tableSelectRowData.elementAt(3)));
                    medicine.setvMedicineStandard(tableSelectRowData.elementAt(4));
                    medicine.setvMedicineDosage(tableSelectRowData.elementAt(5));
                    medicine.setvFacturerName(tableSelectRowData.elementAt(6));
                    medicine.setvMedicineGeneraName(tableSelectRowData.elementAt(7));

                    int row = DAO_STORE.updateMedicine(medicine);
                    if (row > 0) {
                        Boolean search = reSearch();
                        if (!search) {
                            MedicineManagerPane.loadMedicine();
                        }
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(未知错误)");
                };
            }

            public ActionListener cancel() {
                return e -> {
                    Boolean search = reSearch();
                    if (!search) {
                        MedicineManagerPane.loadMedicine();
                    }
                };
            }

            public ActionListener delete() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;

                    int row = DAO_STORE.deleteMedicine(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        Boolean search = reSearch();
                        if (!search) {
                            MedicineManagerPane.loadMedicine();
                        }
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }
        }
    }

    public static void loadManufacturer() {
        MedicineManagerAdd medicineManagerAdd = MedicineManagerAdd.getInstance();
        MedicineManagerQMD medicineManagerQMD = MedicineManagerQMD.getInstance();

        medicineManagerQMD.dataTable.stopEditing();

        medicineManagerAdd.manufacturerCBox.removeAllItems();
        medicineManagerQMD.manufacturerCBoxR.removeAllItems();
        medicineManagerQMD.manufacturerCBox1.removeAllItems();
        medicineManagerQMD.manufacturerCBox.removeAllItems();

        DAO_STORE.getManufacturer().forEach(manufacturer -> {
            medicineManagerAdd.manufacturerCBox.addItem(manufacturer.getvFacturerName());
            medicineManagerQMD.manufacturerCBoxR.addItem(manufacturer.getvFacturerName());
            medicineManagerQMD.manufacturerCBox1.addItem(manufacturer.getvFacturerName());
            medicineManagerQMD.manufacturerCBox.addItem(manufacturer.getvFacturerName());
        });
    }

    public static void loadGenera() {
        MedicineManagerAdd medicineManagerAdd = MedicineManagerAdd.getInstance();
        MedicineManagerQMD medicineManagerQMD = MedicineManagerQMD.getInstance();

        medicineManagerQMD.dataTable.stopEditing();

        medicineManagerAdd.typeCBox.removeAllItems();
        medicineManagerQMD.typeCBoxR.removeAllItems();
        medicineManagerQMD.typeCBox1.removeAllItems();
        medicineManagerQMD.typeCBox.removeAllItems();

        DAO_STORE.getGenera().forEach(genera -> {
            medicineManagerAdd.typeCBox.addItem(genera.getvMedicineGeneraName());
            medicineManagerQMD.typeCBoxR.addItem(genera.getvMedicineGeneraName());
            medicineManagerQMD.typeCBox1.addItem(genera.getvMedicineGeneraName());
            medicineManagerQMD.typeCBox.addItem(genera.getvMedicineGeneraName());
        });
    }

    public static void loadMedicine() {
        MedicineManagerQMD medicineManagerQMD = MedicineManagerQMD.getInstance();

        medicineManagerQMD.dataTable.clear();

        DAO_STORE.getMedicine().forEach(medicine -> medicineManagerQMD.dataTable.addRow(new Object[]{
                medicine.getiMedicineId(), medicine.getvMedicineName(), medicine.getvMedicineDescription(),
                medicine.getmRetailPrices(), medicine.getvMedicineStandard(), medicine.getvMedicineDosage(),
                medicine.getvFacturerName(), medicine.getvMedicineGeneraName()
        }));
    }
}
