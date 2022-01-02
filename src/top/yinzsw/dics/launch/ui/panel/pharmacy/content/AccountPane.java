package top.yinzsw.dics.launch.ui.panel.pharmacy.content;

import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.User;
import top.yinzsw.dics.launch.ui.MainFrame;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountPane extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private static final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

    public AccountPane(String name) {
        setName(name);
        addTab("修改账号密码", AccountModPwd.getInstance());
    }

    static class AccountModPwd extends JPanel {
        private static final AccountModPwd ACCOUNT_MOD_PWD = new AccountModPwd();
        JButton okBtn, resetBtn;
        JTextField oldPwdTextField, newPwdTextField, confirmNewPwdTextField;

        public static AccountModPwd getInstance() {
            return ACCOUNT_MOD_PWD;
        }

        private AccountModPwd() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(new EmptyBorder(30, 5, 5, 5));

            Dimension size = new Dimension(0, 20);

            JLabel oldPwdLab = new JLabel("输入旧密码: ");
            oldPwdTextField = new JTextField(30);
            oldPwdTextField.setMaximumSize(size);
            oldPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(oldPwdTextField, 20));
            JLabel oldPwdInfoLab = new JLabel("输入你的旧密码");

            JLabel newPwdLab = new JLabel("输入新密码: ");
            newPwdTextField = new JTextField(30);
            newPwdTextField.setMaximumSize(size);
            newPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(oldPwdTextField, 20));
            JLabel newPwdInfoLab = new JLabel("输入你的新密码(5-20位)");

            JLabel confirmNewPwdLab = new JLabel("确认新密码: ");
            confirmNewPwdTextField = new JTextField(30);
            confirmNewPwdTextField.setMaximumSize(size);
            confirmNewPwdTextField.addKeyListener(ComponentUtils.limitFieldLength(oldPwdTextField, 20));
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
                    User user = DAO_STORE.getUser(id).get(0);

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
                    }
                    ComponentUtils.showMessageDialogWhileUpdate(row, "修改成功", "修改失败(未知错误)");
                };
            }

            public ActionListener reset() {
                return e -> ComponentUtils.resetField(oldPwdTextField, newPwdTextField, confirmNewPwdTextField);
            }
        }
    }
}
