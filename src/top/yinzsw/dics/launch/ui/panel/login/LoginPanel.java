package top.yinzsw.dics.launch.ui.panel.login;


import top.yinzsw.dics.launch.dao.DaoStoreImpl;
import top.yinzsw.dics.launch.pojo.User;
import top.yinzsw.dics.launch.ui.MainFrame;
import top.yinzsw.dics.launch.ui.component.BoxGenerator;
import top.yinzsw.dics.launch.ui.component.ComponentUtils;
import top.yinzsw.dics.launch.ui.panel.PanelEnum;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class LoginPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    JPanel boxWrap;
    JButton loginBtn, resetBtn;

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginPanel() {
        setName(PanelEnum.LOGIN.name());
        setLayout(new BorderLayout());

        boxWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        boxWrap.setOpaque(false);
        boxWrap.add(getLoginBox());
        add(boxWrap, BorderLayout.SOUTH);
        addComponentListener(componentEvent());

        LoginController loginController = new LoginController();
        loginBtn.addActionListener(loginController.login());
        resetBtn.addActionListener(loginController.reset());
    }

    private Box getLoginBox() {
        Font labFont = new Font("微软雅黑", Font.PLAIN, 16);
        Color labFgColor = new Color(238, 238, 238);

        Font filedFont = new Font("微软雅黑", Font.PLAIN, 13);
        Color filedFgColor = new Color(255, 255, 255);
        CompoundBorder filedBorder = BorderFactory.createCompoundBorder(
                new EtchedBorder(EtchedBorder.LOWERED),
                new EmptyBorder(0, 4, 0, 4));

        JLabel usernameLab = new JLabel("账号: ");
        compStyleFactory(usernameLab, labFont, labFgColor, null);

        usernameField = new JTextField("", 14);
        compStyleFactory(usernameField, filedFont, filedFgColor, filedBorder);
        usernameField.setOpaque(false);

        JLabel passwordLab = new JLabel("密码: ");
        compStyleFactory(passwordLab, labFont, labFgColor, null);

        passwordField = new JPasswordField("", 14);
        compStyleFactory(passwordField, filedFont, filedFgColor, filedBorder);
        passwordField.setOpaque(false);
        passwordField.setEchoChar('*');

        loginBtn = new JButton("登陆");
        loginBtn.setOpaque(false);

        resetBtn = new JButton("重置");
        resetBtn.setOpaque(false);

        Component[][] loginComponents = {
                {usernameLab, usernameField},
                {passwordLab, passwordField},
                {Box.createHorizontalStrut(41), loginBtn, resetBtn, Box.createHorizontalGlue()}};

        return new BoxGenerator(loginComponents, 5, 5);
    }

    private void compStyleFactory(JComponent comp, Font font, Color fgColor, Border border) {
        if (font != null) comp.setFont(font);
        if (fgColor != null) comp.setForeground(fgColor);
        if (border != null) comp.setBorder(border);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image bg = Toolkit.getDefaultToolkit().getImage("sources/img/login/bg.jpg");
        g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }

    private ComponentAdapter componentEvent() {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                double bottom = component.getHeight() * 0.11;
                double width = component.getWidth() * 0.13;
                boxWrap.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) bottom, (int) width));
            }
        };
    }

    class LoginController {
        private final DaoStoreImpl DAO_STORE = DaoStoreImpl.getInstance();

        public ActionListener login() {
            return e -> {
                String username = usernameField.getText().trim();
                String password = String.valueOf(passwordField.getPassword());

                if ("".equals(username) || "".equals(password)) return;

                ArrayList<User> users = DAO_STORE.getUser(username, password);

                if (users.size() <= 0) {
                    JOptionPane.showMessageDialog(null, "账号密码或错误,请重新输入!", "", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = users.get(0);
                MainFrame frame = (MainFrame) getTopLevelAncestor();
                frame.setName(user.getiUserId() + "");

                ComponentUtils.resetField(usernameField, passwordField);
                switch (user.getiTypeId()) {
                    case 1000:
                    case 1001:
                        frame.switchPanel(PanelEnum.MANAGER.name());
                        break;
                    case 1002:
                        frame.switchPanel(PanelEnum.PHARMACY.name());
                        break;
                }
            };
        }

        public ActionListener reset() {
            return e -> ComponentUtils.resetField(usernameField, passwordField);
        }
    }
}
