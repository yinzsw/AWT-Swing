package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.Genera;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MedicineTypePane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public MedicineTypePane(String name) {
        setName(name);
        addTab("添加药品类别", MedicineTypeAdd.getInstance());
        addTab("查询,修改,删除药品类别信息", MedicineTypeQMD.getInstance());
        loadGenera();
    }

    static class MedicineTypeAdd extends JPanel {
        private static final MedicineTypeAdd MEDICINE_TYPE_ADD = new MedicineTypeAdd();
        JButton okBtn, cancelBtn;
        JTextField nameTextField;
        JTextArea descArea;
        DataTable dataTable;

        public static MedicineTypeAdd getInstance() {
            return MEDICINE_TYPE_ADD;
        }

        private MedicineTypeAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(10, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel nameLab = new JLabel("药品类别名称: ");
            nameTextField = new JTextField(30);
            nameTextField.setMaximumSize(size);

            JLabel descLab = new JLabel("药品类别描述: ");
            descArea = new JTextArea(12, Short.MAX_VALUE);
            descArea.setLineWrap(true);
            JScrollPane descScrollPane = new JScrollPane(descArea);

            okBtn = new JButton("确定");
            cancelBtn = new JButton("取消");
            Component[][] topComponents = {
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {descLab, descScrollPane, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(85), okBtn, cancelBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder(""));
            add(top);

            Object[] objects = {"编号", "名称", "描述"};
            dataTable = new DataTable(objects, 0, 1, 2);
            JScrollPane tableScrollPane = new JScrollPane(dataTable);
            Component[][] bottomComponents = {{tableScrollPane}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("药品类别一览"));
            add(bottom);

            MedicineTypeAddController medicineTypeAddController = new MedicineTypeAddController();
            okBtn.addActionListener(medicineTypeAddController.add());
            cancelBtn.addActionListener(medicineTypeAddController.reset());
        }

        class MedicineTypeAddController {
            public ActionListener add() {
                return e -> {
                    boolean hasEmptyField = ComponentUtils.hasEmptyField(nameTextField, descArea);
                    if (hasEmptyField) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    Genera genera = new Genera();
                    genera.setvMedicineGeneraName(nameTextField.getText().trim());
                    genera.setvMedicineDescription(descArea.getText().trim());

                    int row = DAO_STORE.addGenera(genera);
                    if (row > 0) {
                        ComponentUtils.resetField(nameTextField, descArea);
                        MedicineTypePane.loadGenera();
                        MedicineManagerPane.loadGenera();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(nameTextField, descArea);
            }
        }
    }

    static class MedicineTypeQMD extends JPanel {
        private static final MedicineTypeQMD MEDICINE_TYPE_QMD = new MedicineTypeQMD();
        JButton searchBtn, resetBtn, saveBtn, cancelBtn, delBtn;
        ArrayList<Genera> genera;
        JTextField idTextField, nameTextField;
        DataTable dataTable;

        public static MedicineTypeQMD getInstance() {
            return MEDICINE_TYPE_QMD;
        }

        public MedicineTypeQMD() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel idLab = new JLabel("药品类别编号: ");
            idTextField = new JTextField(30);
            idTextField.addKeyListener(ComponentUtils.limitFieldInput(idTextField, "^\\d+$"));
            idTextField.addKeyListener(ComponentUtils.limitFieldLength(idTextField, 9));

            JLabel nameLab = new JLabel("药品类别名称: ");
            nameTextField = new JTextField(30);

            searchBtn = new JButton("搜索");
            resetBtn = new JButton("重置");

            Component[][] topComponents = {{idLab, idTextField, searchBtn}, {nameLab, nameTextField, resetBtn},};
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("搜索条件"));
            add(top);


            Object[] objects = {"编号", "名称", "描述"};
            dataTable = new DataTable(objects, 0);
            JScrollPane scrollPane = new JScrollPane(dataTable);

            saveBtn = new JButton("保存修改");
            cancelBtn = new JButton("放弃修改");
            delBtn = new JButton("删除记录");
            Component[][] bottomComponents = {{scrollPane}, {saveBtn, cancelBtn, delBtn, Box.createHorizontalGlue()}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("浏览及修改"));
            add(bottom);

            MedicineTypeQMDController medicineTypeQMDController = new MedicineTypeQMDController();
            searchBtn.addActionListener(medicineTypeQMDController.search());
            resetBtn.addActionListener(medicineTypeQMDController.reset());
            saveBtn.addActionListener(medicineTypeQMDController.update());
            cancelBtn.addActionListener(medicineTypeQMDController.cancel());
            delBtn.addActionListener(medicineTypeQMDController.delete());
        }

        class MedicineTypeQMDController {
            public Boolean reSearch() {
                ArrayList<Integer> ids = new ArrayList<>();
                if (genera == null || genera.size() == 0) return false;

                genera.forEach(item -> ids.add(item.getiMedicineGeneraId()));
                genera = DAO_STORE.getGenera(ids.toArray());

                dataTable.clear();

                genera.forEach(genera -> dataTable.addRow(new Object[]{
                        genera.getiMedicineGeneraId(), genera.getvMedicineGeneraName(), genera.getvMedicineDescription()
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

                    genera = DAO_STORE.getGenera(Integer.parseInt(0 + id), name);

                    dataTable.clear();

                    genera.forEach(genera -> dataTable.addRow(new Object[]{
                            genera.getiMedicineGeneraId(), genera.getvMedicineGeneraName(), genera.getvMedicineDescription()
                    }));

                    if (genera.size() > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField);
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(genera.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField);
            }

            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    Genera genera = new Genera();
                    genera.setiMedicineGeneraId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    genera.setvMedicineGeneraName(tableSelectRowData.elementAt(1));
                    genera.setvMedicineDescription(tableSelectRowData.elementAt(2));

                    int row = DAO_STORE.updateGenera(genera);
                    if (row > 0) {
                        reSearch();
                        MedicineTypePane.loadGenera();
                        MedicineManagerPane.loadGenera();
                        MedicineManagerPane.loadMedicine();
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

                    int row = DAO_STORE.deleteGenera(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        reSearch();
                        MedicineTypePane.loadGenera();
                        MedicineManagerPane.loadGenera();
                        MedicineManagerPane.loadMedicine();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }
        }
    }

    public static void loadGenera() {
        MedicineTypeAdd medicineTypeAdd = MedicineTypeAdd.getInstance();

        medicineTypeAdd.dataTable.clear();

        DAO_STORE.getGenera().forEach(genera -> medicineTypeAdd.dataTable.addRow(new Object[]{
                genera.getiMedicineGeneraId(), genera.getvMedicineGeneraName(), genera.getvMedicineDescription()
        }));
    }
}
