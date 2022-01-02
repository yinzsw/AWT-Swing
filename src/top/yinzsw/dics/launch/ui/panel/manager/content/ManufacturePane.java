package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.Manufacturer;
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

public class ManufacturePane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public ManufacturePane(String name) {
        setName(name);
        addTab("添加生产单位资料", ManufactureAdd.getInstance());
        addTab("删除生产单位资料", ManufactureDel.getInstance());
        addTab("浏览和修改生产单位资料", ManufactureMod.getInstance());
        loadManufacturer();
    }

    static class ManufactureAdd extends JPanel {
        private static final ManufactureAdd MANUFACTURE_ADD = new ManufactureAdd();
        JButton okBtn, resetBtn;
        JTextField nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField, addressTextField, descTextField, scopeTextField;


        public static ManufactureAdd getInstance() {
            return MANUFACTURE_ADD;
        }

        private ManufactureAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 5, 5, 5));

            JLabel nameLab = new JLabel("单位名称: ");
            nameTextField = new JTextField(30);

            JLabel shortNameLab = new JLabel("名称简码: ");
            shortNameTextField = new JTextField(30);
            JLabel zipCodeLab = new JLabel("邮政编码: ");
            zipCodeTextField = new JTextField(30);

            JLabel contactsLab = new JLabel("单位联系人: ");
            contactsLab.setFont(new Font(null, Font.PLAIN, 10));
            contactsLab.setPreferredSize(new Dimension(61, 20));
            contactsTextField = new JTextField(30);
            JLabel phoneLab = new JLabel("联系电话: ");
            phoneTextField = new JTextField(30);

            JLabel addressLab = new JLabel("单位地址: ");
            addressTextField = new JTextField(30);

            JLabel descLab = new JLabel("描述备注: ");
            descTextField = new JTextField(30);

            JLabel scopeLab = new JLabel("经营范围: ");
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
                    {Box.createHorizontalStrut(61), okBtn, resetBtn, Box.createHorizontalGlue()},
            };

            add(new BoxGenerator(components, 5, 8));
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));

            ManufactureAddController manufactureAddController = new ManufactureAddController();
            okBtn.addActionListener(manufactureAddController.add());
            resetBtn.addActionListener(manufactureAddController.reset());
        }

        class ManufactureAddController {
            public ActionListener add() {
                return e -> {
                    boolean hasEmptyField = ComponentUtils.hasEmptyField(nameTextField, shortNameTextField, zipCodeTextField, contactsTextField, phoneTextField, addressTextField, descTextField, scopeTextField);
                    if (hasEmptyField) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setvFacturerName(nameTextField.getText().trim());
                    manufacturer.setcSimplifiedCode(shortNameTextField.getText().trim());
                    manufacturer.setcPostalCode(zipCodeTextField.getText().trim());
                    manufacturer.setcContanctMan(contactsTextField.getText().trim());
                    manufacturer.setcContanctPhone(phoneTextField.getText().trim());
                    manufacturer.setcContanctAddress(addressTextField.getText().trim());
                    manufacturer.setvDescription(descTextField.getText().trim());
                    manufacturer.setvBusinessScope(scopeTextField.getText().trim());

                    int row = DAO_STORE.addManufacturer(manufacturer);
                    if (row > 0) {
                        ComponentUtils.resetField(
                                nameTextField, shortNameTextField, zipCodeTextField,
                                contactsTextField, phoneTextField, addressTextField,
                                descTextField, scopeTextField
                        );
                        ManufacturePane.loadManufacturer();
                        MedicineManagerPane.loadManufacturer();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(请检查输入是否有误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(
                        nameTextField, shortNameTextField, zipCodeTextField,
                        contactsTextField, phoneTextField, addressTextField,
                        descTextField, scopeTextField
                );
            }
        }
    }

    static class ManufactureDel extends JPanel {
        private static final ManufactureDel MANUFACTURE_DEL = new ManufactureDel();
        JButton searchBtn, resetBtn, delBtn;
        ArrayList<Manufacturer> manufacturers;
        JTextField idTextField, nameTextField, shortNameTextField;
        JTextArea textArea;


        public static ManufactureDel getInstance() {
            return MANUFACTURE_DEL;
        }

        private ManufactureDel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel idLab = new JLabel("单位编号: ");
            idTextField = new JTextField(30);
            idTextField.addKeyListener(ComponentUtils.limitFieldInput(idTextField, "^\\d+$"));
            idTextField.addKeyListener(ComponentUtils.limitFieldLength(idTextField, 9));

            JLabel nameLab = new JLabel("单位名称: ");
            nameTextField = new JTextField(30);

            JLabel shortNameLab = new JLabel("名称简码: ");
            shortNameTextField = new JTextField(30);

            searchBtn = new JButton("搜索");
            resetBtn = new JButton("重置");

            Component[][] topComponents = {
                    {idLab, idTextField, Box.createHorizontalGlue()},
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {shortNameLab, shortNameTextField, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(61), searchBtn, resetBtn, Box.createHorizontalGlue()},
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("搜索条件"));
            add(top);

            textArea = new JTextArea(30, Short.MAX_VALUE);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            delBtn = new JButton("删除记录");

            Component[][] bottomComponents = {{scrollPane, Box.createHorizontalGlue()}, {delBtn, Box.createHorizontalGlue()}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("搜索结果"));
            add(bottom);

            ManufactureDelController manufactureDelController = new ManufactureDelController();
            searchBtn.addActionListener(manufactureDelController.search());
            resetBtn.addActionListener(manufactureDelController.reset());
            delBtn.addActionListener(manufactureDelController.delete());
        }

        class ManufactureDelController {
            public ActionListener search() {
                return e -> {
                    String id = idTextField.getText().trim();
                    String name = nameTextField.getText().trim();
                    String shortName = shortNameTextField.getText().trim();

                    if ("".equals(id) && "".equals(name) && "".equals(shortName)) {
                        JOptionPane.showMessageDialog(null, "有未填项");
                        return;
                    }

                    manufacturers = DAO_STORE.getManufacturer(Integer.parseInt(0 + id), name, shortName);
                    if (manufacturers.size() > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField, shortNameTextField);
                        StringBuffer displayText = new StringBuffer();
                        manufacturers.forEach(item -> displayText.append(String.format(
                                "编号: %d\t名称: %s\t联系人: %s\t联系电话: %s\n邮编: %s\t简码: %s\t联系地址: %s\n描述: %s\t业务范围: %s\n\n",
                                item.getiFacturerId(), item.getvFacturerName(), item.getcContanctMan(),
                                item.getcContanctPhone(), item.getcPostalCode(), item.getcSimplifiedCode(),
                                item.getcContanctAddress(), item.getvDescription(), item.getvBusinessScope()
                        )));
                        textArea.setText(displayText.toString());
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(manufacturers.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    ArrayList<Integer> ids = new ArrayList<>();
                    if (manufacturers == null || manufacturers.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;
                    manufacturers.forEach(item -> ids.add(item.getiFacturerId()));
                    int row = DAO_STORE.deleteManufacturer(ids.toArray());
                    if (row > 0) {
                        textArea.setText("");
                        manufacturers = null;
                        ManufacturePane.loadManufacturer();
                        MedicineManagerPane.loadManufacturer();
                        MedicineManagerPane.loadMedicine();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField, shortNameTextField);
            }
        }
    }

    static class ManufactureMod extends JPanel {
        private static final ManufactureMod MANUFACTURE_MOD = new ManufactureMod();
        JButton saveBtn, resetBtn, delBtn;
        DataTable dataTable;

        public static ManufactureMod getInstance() {
            return MANUFACTURE_MOD;
        }

        private ManufactureMod() {
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

            ManufactureModController manufactureModController = new ManufactureModController();
            saveBtn.addActionListener(manufactureModController.update());
            resetBtn.addActionListener(manufactureModController.query());
            delBtn.addActionListener(manufactureModController.delete());
        }

        class ManufactureModController {
            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setiFacturerId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    manufacturer.setvFacturerName(tableSelectRowData.elementAt(1));
                    manufacturer.setcContanctMan(tableSelectRowData.elementAt(2));
                    manufacturer.setcContanctPhone(tableSelectRowData.elementAt(3));
                    manufacturer.setcContanctAddress(tableSelectRowData.elementAt(4));
                    manufacturer.setvDescription(tableSelectRowData.elementAt(5));
                    manufacturer.setcPostalCode(tableSelectRowData.elementAt(6));
                    manufacturer.setcSimplifiedCode(tableSelectRowData.elementAt(7));
                    manufacturer.setvBusinessScope(tableSelectRowData.elementAt(8));

                    int row = DAO_STORE.updateManufacturer(manufacturer);
                    if (row > 0) {
                        ManufacturePane.loadManufacturer();
                        MedicineManagerPane.loadManufacturer();
                        MedicineManagerPane.loadMedicine();
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

                    int row = DAO_STORE.deleteManufacturer(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        ManufacturePane.loadManufacturer();
                        MedicineManagerPane.loadManufacturer();
                        MedicineManagerPane.loadMedicine();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }
            public ActionListener query() {
                return e -> ManufacturePane.loadManufacturer();
            }
        }
    }

    public static void loadManufacturer() {
        ManufactureMod manufactureMod = ManufactureMod.getInstance();

        manufactureMod.dataTable.clear();

        DAO_STORE.getManufacturer().forEach(manufacturer -> manufactureMod.dataTable.addRow(new Object[]{
                manufacturer.getiFacturerId(), manufacturer.getvFacturerName(), manufacturer.getcContanctMan(),
                manufacturer.getcContanctPhone(), manufacturer.getcContanctAddress(), manufacturer.getvDescription(),
                manufacturer.getcPostalCode(), manufacturer.getcSimplifiedCode(), manufacturer.getvBusinessScope()
        }));
    }
}
