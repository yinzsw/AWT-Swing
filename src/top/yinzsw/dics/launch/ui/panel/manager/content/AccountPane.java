package top.yinzsw.dics.launch.ui.panel.manager.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.User;
import top.yinzsw.dics.launch.ui.MainFrame;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.component.DataTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;


public class AccountPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public AccountPane(String name) {
        setName(name);
        addTab("系统账号类型管理", AccountType.getInstance());
        addTab("添加账号", AccountAdd.getInstance());
        addTab("删除账号", AccountDel.getInstance());
        addTab("浏览和修改账号", AccountMod.getInstance());
        addTab("修改密码", AccountModPwd.getInstance());
        loadDataOfUserType();
        loadDataOfUser();
    }

    static class AccountType extends JPanel {
        private static final AccountType ACCOUNT_TYPE = new AccountType();
        ArrayList<User> userType;
        JButton topAddBtn, topResetBtn, centerDelBtn, bottomModBtn, bottomResetBtn;
        JTextField topTxtField;
        JComboBox<String> centerComboBox;
        DataTable dataTable;

        public static AccountType getInstance() {
            return ACCOUNT_TYPE;
        }

        private AccountType() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel topAddLab = new JLabel("账号类型: ");
            topTxtField = new JTextField(27);
            topTxtField.setMaximumSize(new Dimension(0, 20));
            topTxtField.addKeyListener(ComponentUtils.limitFieldLength(topTxtField, 10));
            JLabel topMsgLab = new JLabel("(类型名长度最大为10位)");
            topAddBtn = new JButton("添加");
            topResetBtn = new JButton("重置");

            Component[][] topComponents = {
                    {topAddLab, topTxtField, topMsgLab, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(61), topAddBtn, Box.createHorizontalStrut(10), topResetBtn, Box.createHorizontalGlue()}
            };
            BoxGenerator top = new BoxGenerator(topComponents, 5, 8);
            top.setBorder(new TitledBorder("添加账号类型"));
            add(top);
            add(Box.createVerticalStrut(5));


            JLabel centerDelLab = new JLabel("账号类型: ");
            centerComboBox = new JComboBox<>();
            centerComboBox.setPrototypeDisplayValue("类型名长度最大为十位十位");
            centerComboBox.setMaximumSize(centerComboBox.getPreferredSize());
            centerDelBtn = new JButton("确定删除");

            Component[][] centerComponents = {{centerDelLab, centerComboBox, Box.createHorizontalGlue(), centerDelBtn}};
            BoxGenerator center = new BoxGenerator(centerComponents, 5, 8);
            center.setBorder(new TitledBorder("删除账号类型"));
            add(center);
            add(Box.createVerticalStrut(5));

            Object[] objects = {"账号类型编号", "账号类型名称"};
            dataTable = new DataTable(objects, 0);
            JScrollPane bottomScrollPane = new JScrollPane(dataTable);

            bottomModBtn = new JButton("提交修改");
            bottomResetBtn = new JButton("重置操作");

            JPanel bottomBtnPnl = new JPanel();
            Component[][] bottomBtnComponents = {{bottomModBtn}, {bottomResetBtn}};
            bottomBtnPnl.add(new BoxGenerator(bottomBtnComponents, 5, 5));

            Component[][] bottomComponents = {{bottomScrollPane, bottomBtnPnl}};
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("查看账号类型"));
            add(bottom);

            AccountTypeController accountTypeController = new AccountTypeController();
            topAddBtn.addActionListener(accountTypeController.add());
            topResetBtn.addActionListener(accountTypeController.reset());
            centerDelBtn.addActionListener(accountTypeController.delete());
            bottomModBtn.addActionListener(accountTypeController.update());
            bottomResetBtn.addActionListener(accountTypeController.query());
        }

        class AccountTypeController {
            public ActionListener add() {
                return e -> {
                    String userType = topTxtField.getText().trim();
                    if ("".equals(userType)) return;
                    int row = DAO_STORE.addUserType(userType);
                    if (row > 0) {
                        topTxtField.setText("");
                        //AccountPane.loadDataOfUser();
                        AccountPane.loadDataOfUserType();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加失败(此类型可能已存在)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;
                    int id = userType.get(centerComboBox.getSelectedIndex()).getiTypeId();
                    int row = DAO_STORE.deleteUserType(id);
                    if (row > 0) {
                        AccountPane.loadDataOfUser();
                        AccountPane.loadDataOfUserType();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除成功(未知错误)");
                };
            }

            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    String id = tableSelectRowData.elementAt(0).trim();
                    String name = tableSelectRowData.elementAt(1).trim();

                    int row = DAO_STORE.updateUserType(Integer.parseInt(id), name);
                    if (row > 0) {
                        AccountPane.loadDataOfUser();
                        AccountPane.loadDataOfUserType();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(未知错误)");
                };
            }

            public ActionListener query() {
                return e -> AccountPane.loadDataOfUserType();
            }

            public ActionListener reset() {
                return e -> topTxtField.setText("");
            }
        }
    }

    static class AccountAdd extends JPanel {
        private static final AccountAdd ACCOUNT_ADD = new AccountAdd();
        JButton okBtn, resetBtn;
        JTextField usernameTextField, passwordTextField, nameTextField;
        JComboBox<String> typeJComboBox;


        public static AccountAdd getInstance() {
            return ACCOUNT_ADD;
        }

        private AccountAdd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel usernameLab = new JLabel("用户昵称: ");
            usernameTextField = new JTextField(40);
            usernameTextField.setMaximumSize(size);
            usernameTextField.addKeyListener(ComponentUtils.limitFieldLength(usernameTextField, 20));
            JLabel usernameInfoLab = new JLabel("昵称长度为2到20位");

            JLabel passwordLab = new JLabel("用户密码: ");
            passwordTextField = new JTextField(40);
            passwordTextField.setMaximumSize(size);
            passwordTextField.addKeyListener(ComponentUtils.limitFieldLength(passwordTextField, 20));
            JLabel passwordInfoLab = new JLabel("密码长度为2到20位");

            JLabel nameLab = new JLabel("真实姓名: ");
            nameTextField = new JTextField(40);
            nameTextField.setMaximumSize(size);
            nameTextField.addKeyListener(ComponentUtils.limitFieldLength(nameTextField, 20));
            JLabel nameInfoLab = new JLabel("姓名长度为1到20位");

            JLabel typeLab = new JLabel("账号类型: ");
            typeJComboBox = new JComboBox<>();
            typeJComboBox.setPrototypeDisplayValue("类型名长度最大为十位十位");
            typeJComboBox.setMaximumSize(typeJComboBox.getPreferredSize());

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");

            Component[][] components = {
                    {usernameLab, usernameTextField, usernameInfoLab, Box.createHorizontalGlue()},
                    {passwordLab, passwordTextField, passwordInfoLab, Box.createHorizontalGlue()},
                    {nameLab, nameTextField, nameInfoLab, Box.createHorizontalGlue()},
                    {typeLab, typeJComboBox, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(61), okBtn, resetBtn, Box.createHorizontalGlue()}
            };
            add(new BoxGenerator(components, 5, 8));
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));

            AccountAddController accountAddController = new AccountAddController();
            okBtn.addActionListener(accountAddController.add());
            resetBtn.addActionListener(accountAddController.reset());
        }

        class AccountAddController {
            public ActionListener add() {
                return e -> {
                    User user = new User();
                    user.setvUserName(usernameTextField.getText().trim());
                    user.setvUserPass(passwordTextField.getText().trim());
                    user.setvUserRealName(nameTextField.getText().trim());
                    user.setcTypeName(typeJComboBox.getSelectedItem() + "");

                    if (user.getvUserName().length() < 2 || user.getvUserPass().length() < 2 || user.getvUserRealName().length() < 1) {
                        JOptionPane.showMessageDialog(null, "添加账号失败(昵称,密码或姓名长度过短)", "", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int row = DAO_STORE.addUser(user);
                    if (row > 0) {
                        ComponentUtils.resetField(usernameTextField, passwordTextField, nameTextField);
                        AccountPane.loadDataOfUser();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "添加成功", "添加账号失败(用户可能已经存在)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(usernameTextField, passwordTextField, nameTextField);
            }
        }
    }

    static class AccountDel extends JPanel {
        private static final AccountDel ACCOUNT_DEL = new AccountDel();
        JButton queryBtn, confirmBtn, resetBtn;
        JTextField queryTextField, idTextField, nameTextField, typeTextField, holderTextField;
        ArrayList<User> users;

        public static AccountDel getInstance() {
            return ACCOUNT_DEL;
        }

        private AccountDel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));
            JLabel queryLab = new JLabel("预删除的账号名: ");
            queryTextField = new JTextField(50);
            queryBtn = new JButton("查询");

            Component[][] topComponents = {{queryLab, queryTextField, queryBtn}};
            add(new BoxGenerator(topComponents, 5, 8));
            add(Box.createRigidArea(new Dimension(0, 15)));

            Dimension size = new Dimension(0, 20);
            JLabel idLab = new JLabel("账号编号: ");
            idTextField = new JTextField(50);
            idTextField.setMaximumSize(size);
            idTextField.setEditable(false);

            JLabel nameLab = new JLabel("账号名称: ");
            nameTextField = new JTextField(50);
            nameTextField.setMaximumSize(size);
            nameTextField.setEditable(false);

            JLabel typeLab = new JLabel("账号类型: ");
            typeTextField = new JTextField(50);
            typeTextField.setMaximumSize(size);
            typeTextField.setEditable(false);

            JLabel holderLab = new JLabel("持有者: ");
            holderLab.setPreferredSize(new Dimension(61, 21));
            holderLab.setMaximumSize(new Dimension(61, 21));
            holderTextField = new JTextField(50);
            holderTextField.setMaximumSize(size);
            holderTextField.setEditable(false);

            confirmBtn = new JButton("确定删除");
            resetBtn = new JButton("重置");


            Component[][] bottomComponents = {
                    {idLab, idTextField, Box.createHorizontalGlue()},
                    {nameLab, nameTextField, Box.createHorizontalGlue()},
                    {typeLab, typeTextField, Box.createHorizontalGlue()},
                    {holderLab, holderTextField, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(61), confirmBtn, resetBtn, Box.createHorizontalGlue()},
            };
            BoxGenerator bottom = new BoxGenerator(bottomComponents, 5, 8);
            bottom.setBorder(new TitledBorder("账号详情信息"));
            add(bottom);
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));

            AccountDelController accountDelController = new AccountDelController();
            queryBtn.addActionListener(accountDelController.query());
            confirmBtn.addActionListener(accountDelController.delete());
            resetBtn.addActionListener(accountDelController.reset());
        }


        class AccountDelController {
            public ActionListener query() {
                return e -> {
                    String queryUsername = queryTextField.getText().trim();
                    if ("".equals(queryUsername)) return;

                    users = DAO_STORE.getUser(queryUsername);
                    if (users.size() > 0) {
                        User user = users.get(0);
                        queryTextField.setText("");
                        idTextField.setText(user.getiTypeId() + "");
                        nameTextField.setText(user.getvUserName());
                        typeTextField.setText(user.getcTypeName());
                        holderTextField.setText(user.getvUserRealName());
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(users.size(), "查询成功", "查询失败(未找到用户)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    if (users == null || users.size() <= 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;
                    int row = DAO_STORE.deleteUser(users.get(0).getiUserId());
                    users.clear();
                    if (row > 0) {
                        ComponentUtils.resetField(idTextField, nameTextField, typeTextField, holderTextField);
                        AccountPane.loadDataOfUser();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(idTextField, nameTextField, typeTextField, holderTextField);
            }
        }
    }

    static class AccountMod extends JPanel {
        private static final AccountMod ACCOUNT_MOD = new AccountMod();
        JButton saveBtn, cancelBtn, delBtn;
        JComboBox<String> typeJComboBox;
        JComboBox<Object> typeJComboBoxR;
        DataTable dataTable;

        public static AccountMod getInstance() {
            return ACCOUNT_MOD;
        }

        private AccountMod() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            Object[] objects = {"账号编号", "账号登录名", "账号密码", "账号拥有者", "账号类型"};
            dataTable = new DataTable(objects, 0);
            typeJComboBoxR = dataTable.setColCellEditor(4, typeJComboBox = new JComboBox<>());
            JScrollPane scrollPane = new JScrollPane(dataTable);

            saveBtn = new JButton("保存修改");
            cancelBtn = new JButton("放弃修改");
            delBtn = new JButton("删除选中数据");

            Component[][] components = {{scrollPane}, {saveBtn, cancelBtn, delBtn, Box.createHorizontalGlue()}};
            BoxGenerator box = new BoxGenerator(components, 5, 8);
            box.setBorder(new TitledBorder("账号一览"));
            add(box);

            AccountModController accountModController = new AccountModController();
            saveBtn.addActionListener(accountModController.update());
            cancelBtn.addActionListener(accountModController.query());
            delBtn.addActionListener(accountModController.delete());
        }

        class AccountModController {
            public ActionListener update() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;

                    User user = new User();
                    user.setiUserId(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    user.setvUserName(tableSelectRowData.elementAt(1));
                    user.setvUserPass(tableSelectRowData.elementAt(2));
                    user.setvUserRealName(tableSelectRowData.elementAt(3));
                    user.setcTypeName(tableSelectRowData.elementAt(4));

                    int row = DAO_STORE.updateUser(user);
                    if (row > 0) {
                        AccountPane.loadDataOfUser();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "更新成功", "更新失败(用户名已存在)");
                };
            }

            public ActionListener delete() {
                return e -> {
                    Vector<String> tableSelectRowData = dataTable.getSelectRowData();
                    if (tableSelectRowData.size() == 0) return;
                    boolean isDelete = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "确定删除吗?", "删除", JOptionPane.OK_CANCEL_OPTION);
                    if (!isDelete) return;

                    int row = DAO_STORE.deleteUser(Integer.parseInt(tableSelectRowData.elementAt(0)));
                    if (row > 0) {
                        AccountPane.loadDataOfUser();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "删除成功", "删除失败(未知错误)");
                };
            }

            public ActionListener query() {
                return e -> {
                    dataTable.stopEditing();
                    AccountPane.loadDataOfUser();
                };
            }
        }
    }

    static class AccountModPwd extends JPanel {
        private static final AccountModPwd ACCOUNT_MOD_PWD = new AccountModPwd();
        JTextField oldPwdTextField, newPwdTextField, confirmNewPwdTextField;
        JButton okBtn, resetBtn;

        public static AccountModPwd getInstance() {
            return ACCOUNT_MOD_PWD;
        }

        private AccountModPwd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(5, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel oldPwdLab = new JLabel("输入旧密码: ");
            oldPwdTextField = new JTextField(30);
            oldPwdTextField.setMaximumSize(size);
            oldPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(oldPwdTextField, 20));
            JLabel oldPwdInfoLab = new JLabel("输入你的旧密码");

            JLabel newPwdLab = new JLabel("输入新密码: ");
            newPwdTextField = new JTextField(30);
            newPwdTextField.setMaximumSize(size);
            newPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(newPwdTextField, 20));
            JLabel newPwdInfoLab = new JLabel("输入你的新密码(5-20位)");

            JLabel confirmNewPwdLab = new JLabel("确认新密码: ");
            confirmNewPwdTextField = new JTextField(30);
            confirmNewPwdTextField.setMaximumSize(size);
            confirmNewPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(confirmNewPwdTextField, 20));
            JLabel confirmNewPwdInfoLab = new JLabel("再次输入你的新密码");

            okBtn = new JButton("确定");
            resetBtn = new JButton("重置");

            Component[][] components = {
                    {oldPwdLab, oldPwdTextField, oldPwdInfoLab, Box.createHorizontalGlue()},
                    {newPwdLab, newPwdTextField, newPwdInfoLab, Box.createHorizontalGlue()},
                    {confirmNewPwdLab, confirmNewPwdTextField, confirmNewPwdInfoLab, Box.createHorizontalGlue()},
                    {Box.createHorizontalStrut(73), okBtn, resetBtn, Box.createHorizontalGlue()},
            };
            add(new BoxGenerator(components, 5, 8));
            add(Box.createRigidArea(new Dimension(0, Short.MAX_VALUE)));

            AccountModPwdController accountModPwdController = new AccountModPwdController();
            okBtn.addActionListener(accountModPwdController.update());
            resetBtn.addActionListener(accountModPwdController.reset());
        }

        class AccountModPwdController {
            public ActionListener update() {
                return e -> {
                    MainFrame frame = (MainFrame) getTopLevelAncestor();
                    int id = Integer.parseInt(frame.getName());
                    ArrayList<User> users = DAO_STORE.getUser(id);
                    if (users.size() == 0) {
                        JOptionPane.showMessageDialog(null, "此登录用户可能已经被删除");
                        ComponentUtils.resetField(oldPwdTextField, newPwdTextField, confirmNewPwdTextField);
                        return;
                    }

                    User user = users.get(0);
                    String oldPWD = oldPwdTextField.getText().trim();
                    String newPWD = newPwdTextField.getText().trim();
                    String reNewPWD = confirmNewPwdTextField.getText().trim();
                    LinkedHashMap<String, Boolean> tips = new LinkedHashMap<>() {
                        {
                            put("密码长度不合法!", oldPWD.length() < 5 || newPWD.length() < 5 || reNewPWD.length() < 5);
                            put("旧密码输入错误!", !user.getvUserPass().equals(oldPWD));
                            put("新旧密码不可一样!", oldPWD.equals(newPWD));
                            put("两次输入的密码不一致!", !newPWD.equals(reNewPWD));
                        }
                    };
                    for (Map.Entry<String, Boolean> item : tips.entrySet()) {
                        if (item.getValue()) {
                            JOptionPane.showMessageDialog(null, item.getKey());
                            return;
                        }
                    }

                    user.setvUserPass(reNewPWD);
                    int row = DAO_STORE.updateUser(user);
                    if (row > 0) {
                        ComponentUtils.resetField(oldPwdTextField, newPwdTextField, confirmNewPwdTextField);
                        AccountPane.loadDataOfUser();
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "修改成功", "修改失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(oldPwdTextField, newPwdTextField, confirmNewPwdTextField);
            }
        }
    }

    public static void loadDataOfUserType() {
        AccountType accountType = AccountType.getInstance();
        AccountAdd accountAdd = AccountAdd.getInstance();
        AccountMod accountMod = AccountMod.getInstance();

        accountMod.dataTable.stopEditing();

        accountType.centerComboBox.removeAllItems();
        accountType.dataTable.clear();
        accountAdd.typeJComboBox.removeAllItems();
        accountMod.typeJComboBox.removeAllItems();
        accountMod.typeJComboBoxR.removeAllItems();

        ArrayList<User> userType = DAO_STORE.getUserType();
        accountType.userType = userType;

        userType.forEach(user -> {
            accountType.centerComboBox.addItem(user.getcTypeName());
            accountType.dataTable.addRow(new Object[]{user.getiTypeId(), user.getcTypeName()});
            accountAdd.typeJComboBox.addItem(user.getcTypeName());
            accountMod.typeJComboBox.addItem(user.getcTypeName());
            accountMod.typeJComboBoxR.addItem(user.getcTypeName());
        });
    }

    public static void loadDataOfUser() {
        AccountMod accountMod = AccountMod.getInstance();

        accountMod.dataTable.clear();

        DAO_STORE.getUser().forEach(user -> accountMod.dataTable.addRow(new Object[]{
                user.getiUserId(), user.getvUserName(), user.getvUserPass(), user.getvUserRealName(),
                user.getcTypeName()
        }));
    }
}
