package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.Pharmacy;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MedicineRoomPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public MedicineRoomPane(String name) {
        setName(name);
        addTab("添加药房信息", MedicineRoomAdd.getInstance());
        addTab("查询,修改,删除药房信息", MedicineRoomQMD.getInstance());
        loadPharmacy();
    }

    static class MedicineRoomAdd extends JPanel {
        private static final MedicineRoomAdd MEDICINE_ROOM_ADD = new MedicineRoomAdd();
        JButton okBtn, resetBtn;
        JTextField nameTextField;
        JTextArea descArea;
        DataTable dataTable;

        public static MedicineRoomAdd getInstance() {
            return MEDICINE_ROOM_ADD;
        }

        private MedicineRoomAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(10, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel nameLab = new JLabel("药房名称: ");
            nameTextField = new JTextField(30);
            nameTextField.setMaximumSize(size);

            JLabel descLab = new JLabel("药房描述: ");
            descArea = new JTextArea(12, Short.MAX_VALUE);
            descArea.setLineWrap(true);
            JScrollPane descScrollPane = new JScrollPane(descArea);

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");

            Component[][] topComponents = {
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {descLab, descScrollPane, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(61), okBtn, resetBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder(""));
            add(top);

            Object[] objects = {"邮编", "名称", "描述"};
            dataTable = new DataTable(objects, 0, 1, 2);
            JScrollPane tableScrollPane = new JScrollPane(dataTable);
            Component[][] bottomComponents = {{tableScrollPane}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("药房信息一览"));
            add(bottom);

            MedicineRoomAddController medicineRoomAddController = new MedicineRoomAddController();
            okBtn.addActionListener(medicineRoomAddController.add());
            resetBtn.addActionListener(medicineRoomAddController.reset());
        }

        class MedicineRoomAddController {
            public ActionListener add() {
                return e -> {
                    boolean hasEmptyField = ComponentUtils.hasEmptyField(nameTextField, descArea);
                    if (hasEmptyField) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    Pharmacy pharmacy = new Pharmacy();
                    pharmacy.setvPharmacyName(nameTextField.getText().trim());
                    pharmacy.setvPharmacyDescription(descArea.getText().trim());

                    int row = DAO_STORE.addPharmacy(pharmacy);
                    if (row > 0) {
                        ComponentUtils.resetField(nameTextField, descArea);
                        MedicineRoomPane.loadPharmacy();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(nameTextField, descArea);
            }
        }
    }

    static class MedicineRoomQMD extends JPanel {
        private static final MedicineRoomQMD MEDICINE_ROOM_QMD = new MedicineRoomQMD();
        JButton searchBtn, resetBtn, saveBtn, cancelBtn, delBtn;
        ArrayList<Pharmacy> pharmacy;
        JTextField idTextField, nameTextField;
        DataTable dataTable;

        public static MedicineRoomQMD getInstance() {
            return MEDICINE_ROOM_QMD;
        }

        private MedicineRoomQMD() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel idLab = new JLabel("药房编号: ");
            idTextField = new JTextField(30);
            idTextField.addKeyListener(ComponentUtils.limitFieldInput(idTextField, "^\\d+$"));
            idTextField.addKeyListener(ComponentUtils.limitFieldLength(idTextField, 9));

            JLabel nameLab = new JLabel("药房名称: ");
            nameTextField = new JTextField(30);

            searchBtn = new JButton("搜索");
            resetBtn = new JButton("重置");

            Component[][] topComponents = {{idLab, idTextField, searchBtn}, {nameLab, nameTextField, resetBtn}};
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("搜索条件"));
            add(top);


            Object[] objects = {"编码", "名称", "描述"};
            dataTable = new DataTable(objects, 0);
            JScrollPane scrollPane = new JScrollPane(dataTable);

            saveBtn = new JButton("保存修改");
            cancelBtn = new JButton("放弃修改");
            delBtn = new JButton("删除记录");
            Component[][] bottomComponents = {{scrollPane}, {saveBtn, cancelBtn, delBtn, Box.createHorizontalGlue()}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("浏览及修改"));
            add(bottom);

            MedicineRoomQMDController medicineRoomQMDController = new MedicineRoomQMDController();
            searchBtn.addActionListener(medicineRoomQMDController.search());
            resetBtn.addActionListener(medicineRoomQMDController.reset());
            saveBtn.addActionListener(medicineRoomQMDController.update());
            cancelBtn.addActionListener(medicineRoomQMDController.cancel());
            delBtn.addActionListener(medicineRoomQMDController.delete());
        }

        class MedicineRoomQMDController {
            public Boolean reSearch() {
                ArrayList<Integer> ids = new ArrayList<>();
                if (pharmacy == null || pharmacy.size() == 0) return false;

                pharmacy.forEach(item -> ids.add(item.getiPharmacyId()));
                pharmacy = DAO_STORE.getPharmacy(ids.toArray());

                dataTable.clear();

                pharmacy.forEach(pharmacy -> dataTable.addRow(new Object[]{
                        pharmacy.getiPharmacyId(), pharmacy.getvPharmacyName(), pharmacy.getvPharmacyDescription()
                }));
                return true;
            }

            public ActionListener search() {
                return e -> {
                    String id = idTextField.getText().trim();
                    String name = nameTextField.getText().trim();

                    if ("".equals(id) && "".equals(name)) {
                        JOptionPane.showMessageDialog(null, "你还没有输入关键词");
                        return;
                    }

                    pharmacy = DAO_STORE.getPharmacy(Integer.parseInt(0 + id), name);

                    dataTable.clear();

                    pharmacy.forEach(pharmacy -> dataTable.addRow(new Object[]{
                            pharmacy.getiPharmacyId(), pharmacy.getvPharmacyName(), pharmacy.getvPharmacyDescription()
                    }));

                    if (pharmacy.size() > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField);
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(pharmacy.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField);
            }

            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    Pharmacy pharmacy = new Pharmacy();
                    pharmacy.setiPharmacyId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    pharmacy.setvPharmacyName(tableSelectRowData.elementAt(1));
                    pharmacy.setvPharmacyDescription(tableSelectRowData.elementAt(2));

                    int row = DAO_STORE.updatePharmacy(pharmacy);
                    if (row > 0) {
                        reSearch();
                        MedicineRoomPane.loadPharmacy();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(未知错误)");
                };
            }

            public ActionListener cancel() {
                return e -> reSearch();
            }

            public ActionListener delete() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;

                    int row = DAO_STORE.deletePharmacy(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        reSearch();
                        MedicineRoomPane.loadPharmacy();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }
        }
    }

    public static void loadPharmacy() {
        MedicineRoomAdd medicineRoomAdd = MedicineRoomAdd.getInstance();

        medicineRoomAdd.dataTable.clear();

        DAO_STORE.getPharmacy().forEach(pharmacy -> medicineRoomAdd.dataTable.addRow(new Object[]{
                pharmacy.getiPharmacyId(), pharmacy.getvPharmacyName(), pharmacy.getvPharmacyDescription()
        }));
    }
}
