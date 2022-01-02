package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.Medicine;
import top.yinzsw.dics.launch.pojo.Supplier;
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

public class SuppliersPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public SuppliersPane(String name) {
        setName(name);
        addTab("添加供货商资料", SuppliersAdd.getInstance());
        addTab("删除供货商资料", SuppliersDel.getInstance());
        addTab("浏览和修改供货商资料", SuppliersMod.getInstance());
        loadSupplier();
    }

    static class SuppliersAdd extends JPanel {
        private static final SuppliersAdd SUPPLIERS_ADD = new SuppliersAdd();
        JButton okBtn, resetBtn;
        JTextField nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField,
                addressTextField, descTextField, scopeTextField;


        public static SuppliersAdd getInstance() {
            return SUPPLIERS_ADD;
        }

        private SuppliersAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 5, 5, 5));

            Dimension size = new Dimension(73, 20);

            JLabel nameLab = new JLabel("供货商名称: ");
            nameTextField = new JTextField(30);

            JLabel shortNameLab = new JLabel("名称简码: ");
            shortNameLab.setPreferredSize(size);
            shortNameTextField = new JTextField(30);
            JLabel zipCodeLab = new JLabel("邮政编码: ");
            zipCodeTextField = new JTextField(30);

            JLabel contactsLab = new JLabel("供货商联系人: ");
            contactsLab.setFont(new Font(null, Font.PLAIN, 10));
            contactsLab.setPreferredSize(size);
            contactsTextField = new JTextField(30);
            JLabel phoneLab = new JLabel("联系电话: ");
            phoneTextField = new JTextField(30);

            JLabel addressLab = new JLabel("供货商地址: ");
            addressTextField = new JTextField(30);

            JLabel descLab = new JLabel("描述备注: ");
            descLab.setPreferredSize(size);
            descTextField = new JTextField(30);

            JLabel scopeLab = new JLabel("经营范围: ");
            scopeLab.setPreferredSize(size);
            scopeTextField = new JTextField(30);

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");

            Component[][] components = {
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {shortNameLab, shortNameTextField, zipCodeLab, zipCodeTextField, Box.createHorizontalGlue()},
                    {contactsLab, contactsTextField, phoneLab, phoneTextField, Box.createHorizontalGlue()},
                    {addressLab, addressTextField, Box.createHorizontalGlue()},
                    {descLab, descTextField, Box.createHorizontalGlue()},
                    {scopeLab, scopeTextField, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(73), okBtn, resetBtn, Box.createHorizontalGlue()},
            };

            add(new BoxGenerator(components, 5, 8));
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));

            SuppliersAddController suppliersAddController = new SuppliersAddController();
            okBtn.addActionListener(suppliersAddController.add());
            resetBtn.addActionListener(suppliersAddController.reset());
        }

        class SuppliersAddController {
            public ActionListener add() {
                return e -> {
                    boolean hasEmptyField = ComponentUtils.hasEmptyField(nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField, addressTextField, descTextField, scopeTextField);
                    if (hasEmptyField) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    Supplier supplier = new Supplier();
                    supplier.setvSupplierName(nameTextField.getText().trim());
                    supplier.setcSimplifiedCode(shortNameTextField.getText().trim());
                    supplier.setcPostalCode(zipCodeTextField.getText().trim());
                    supplier.setcContanctMan(contactsTextField.getText().trim());
                    supplier.setcContanctPhone(phoneTextField.getText().trim());
                    supplier.setvContanctAddress(addressTextField.getText().trim());
                    supplier.setvDescription(descTextField.getText().trim());
                    supplier.setvBusinessScope(scopeTextField.getText().trim());

                    int row = DAO_STORE.addSupplier(supplier);
                    if (row > 0) {
                        ComponentUtils.resetField(nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField, addressTextField, descTextField, scopeTextField);
                        SuppliersPane.loadSupplier();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(请检查输入是否有误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField, addressTextField, descTextField, scopeTextField);
            }
        }
    }

    static class SuppliersDel extends JPanel {
        private static final SuppliersDel SUPPLIERS_DEL = new SuppliersDel();
        JButton searchBtn, resetBtn, delBtn;
        ArrayList<Supplier> supplier;
        JTextField idTextField, nameTextField, shortNameTextField;
        JTextArea textArea;

        public static SuppliersDel getInstance() {
            return SUPPLIERS_DEL;
        }

        private SuppliersDel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel idLab = new JLabel("供货商编号: ");
            idTextField = new JTextField(30);
            idTextField.addKeyListener(ComponentUtils.limitFieldInput(idTextField, "^\\d+$"));
            idTextField.addKeyListener(ComponentUtils.limitFieldLength(idTextField, 9));

            JLabel nameLab = new JLabel("供货商名称: ");
            nameTextField = new JTextField(30);

            JLabel shortNameLab = new JLabel("名称简码: ");
            shortNameLab.setPreferredSize(new Dimension(73, 20));
            shortNameTextField = new JTextField(30);

            searchBtn = new JButton("搜索");
            resetBtn = new JButton("重置");


            Component[][] topComponents = {
                    {idLab, idTextField, Box.createHorizontalGlue()},
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {shortNameLab, shortNameTextField, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(73), searchBtn, resetBtn, Box.createHorizontalGlue()},
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("搜索条件"));
            add(top);

            textArea = new JTextArea(30, Short.MAX_VALUE);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            delBtn = new JButton("删除记录");

            Component[][] bottomComponents = {
                    {scrollPane, Box.createHorizontalGlue()},
                    {delBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("搜索结果"));
            add(bottom);

            SuppliersDelController suppliersDelController = new SuppliersDelController();
            searchBtn.addActionListener(suppliersDelController.query());
            resetBtn.addActionListener(suppliersDelController.reset());
            delBtn.addActionListener(suppliersDelController.delete());
        }

        class SuppliersDelController {
            public ActionListener query() {
                return e -> {
                    String id = idTextField.getText().trim();
                    String name = nameTextField.getText().trim();
                    String shortName = shortNameTextField.getText().trim();

                    if ("".equals(id) && "".equals(name) && "".equals(shortName)) {
                        JOptionPane.showMessageDialog(null, "你还没有输入关键词");
                        return;
                    }

                    supplier = DAO_STORE.getSupplier(Integer.parseInt(0 + id), name, shortName);
                    if (supplier.size() > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField, shortNameTextField);
                        StringBuffer displayText = new StringBuffer();
                        supplier.forEach(item -> displayText.append(String.format(
                                "编号: %d\t名称: %s\t联系人: %s\t联系电话: %s\n邮编: %s\t简码: %s\t联系地址: %s\n描述: %s\t业务范围: %s\n\n",
                                item.getiSupplierId(), item.getvSupplierName(), item.getcContanctMan(),
                                item.getcContanctPhone(), item.getcPostalCode(), item.getcSimplifiedCode(),
                                item.getvContanctAddress(), item.getvDescription(), item.getvBusinessScope()
                        )));
                        textArea.setText(displayText.toString());
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(supplier.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    ArrayList<Integer> ids = new ArrayList<>();
                    if (supplier == null || supplier.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;
                    supplier.forEach(item -> ids.add(item.getiSupplierId()));
                    int row = DAO_STORE.deleteSupplier(ids.toArray());
                    if (row > 0) {
                        textArea.setText("");
                        supplier = null;
                        SuppliersPane.loadSupplier();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField, shortNameTextField);
            }
        }
    }

    static class SuppliersMod extends JPanel {
        private static final SuppliersMod SUPPLIERS_MOD = new SuppliersMod();
        JButton saveBtn, resetBtn, delBtn;
        DataTable dataTable;

        public static SuppliersMod getInstance() {
            return SUPPLIERS_MOD;
        }

        private SuppliersMod() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            Object[] objects = {"编号", "名称", "联系人", "联系电话", "联系地址", "描述", "邮编", "名称简码", "业务范围"};
            dataTable = new DataTable(objects, 0);
            JScrollPane scrollPane = new JScrollPane(dataTable);

            saveBtn = new JButton("保存修改");
            resetBtn = new JButton("放弃修改");
            delBtn = new JButton("删除指定数据");

            Component[][] components = {{scrollPane}, {saveBtn, resetBtn, delBtn, Box.createHorizontalGlue()}};
            BoxGenerator box = new BoxGenerator(components, 5, 8);
            box.setBorder(new TitledBorder("生产资料一览表"));
            add(box);

            SuppliersModController suppliersModController = new SuppliersModController();
            saveBtn.addActionListener(suppliersModController.update());
            resetBtn.addActionListener(suppliersModController.query());
            delBtn.addActionListener(suppliersModController.delete());
        }

        class SuppliersModController {
            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    Supplier supplier = new Supplier();
                    supplier.setiSupplierId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    supplier.setvSupplierName(tableSelectRowData.elementAt(1));
                    supplier.setcContanctMan(tableSelectRowData.elementAt(2));
                    supplier.setcContanctPhone(tableSelectRowData.elementAt(3));
                    supplier.setvContanctAddress(tableSelectRowData.elementAt(4));
                    supplier.setvDescription(tableSelectRowData.elementAt(5));
                    supplier.setcPostalCode(tableSelectRowData.elementAt(6));
                    supplier.setcSimplifiedCode(tableSelectRowData.elementAt(7));
                    supplier.setvBusinessScope(tableSelectRowData.elementAt(8));

                    int row = DAO_STORE.updateSupplier(supplier);
                    if (row > 0) {
                        SuppliersPane.loadSupplier();
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

                    int row = DAO_STORE.deleteSupplier(tableSelectRowData.elementAt(0));
                    if (row > 0) {
                        SuppliersPane.loadSupplier();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener query() {
                return e -> SuppliersPane.loadSupplier();
            }
        }
    }

    public static void loadSupplier() {
        SuppliersMod suppliersMod = SuppliersMod.getInstance();

        suppliersMod.dataTable.clear();
        DAO_STORE.getSupplier().forEach(supplier -> suppliersMod.dataTable.addRow(new Object[]{
                supplier.getiSupplierId(), supplier.getvSupplierName(), supplier.getcContanctMan(),
                supplier.getcContanctPhone(), supplier.getvContanctAddress(), supplier.getvDescription(),
                supplier.getcPostalCode(), supplier.getcSimplifiedCode(), supplier.getvBusinessScope()
        }));
    }
}
