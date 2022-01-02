package top.yinzsw.dics.launch.ui;

import top.yinzsw.dics.launch.ui.component.LinkDocLabel;
import top.yinzsw.dics.launch.ui.component.TimeLabel;
import top.yinzsw.dics.launch.ui.panel.login.LoginPanel;
import top.yinzsw.dics.launch.ui.panel.manager.ManagerPanel;
import top.yinzsw.dics.launch.ui.panel.pharmacy.PharmacyPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 670;
    JPanel centerPnl, southPnl;

    public MainFrame() {
        //可滚动内容面板
        centerPnl = new JPanel(new CardLayout());

        LoginPanel loginPnl = new LoginPanel();
        centerPnl.add(loginPnl, loginPnl.getName());

        ManagerPanel managerPnl = new ManagerPanel();
        centerPnl.add(managerPnl, managerPnl.getName());

        PharmacyPanel pharmacyPnl = new PharmacyPanel();
        centerPnl.add(pharmacyPnl, pharmacyPnl.getName());

        add(centerPnl);

        //底栏面板
        southPnl = new JPanel(new BorderLayout());
        southPnl.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.gray, 1, false),
                new EmptyBorder(0, 10, 0, 10))
        );
        southPnl.setPreferredSize(new Dimension(0, 30));

        LinkDocLabel linkDocLab = new LinkDocLabel("欢迎使用药品库房管理系统, 如果使用中遇到问题, 单机此处打开说明书.");
        southPnl.add(linkDocLab, BorderLayout.WEST);

        JLabel timeLab = new TimeLabel();
        southPnl.add(timeLab, BorderLayout.EAST);

        add(southPnl, BorderLayout.SOUTH);
    }

    public void switchPanel(String name) {
        CardLayout card = (CardLayout) centerPnl.getLayout();
        card.show(centerPnl, name);
    }

    public void loadSystemDefaultLookAndFeel() {
        String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void createAndShowGUI() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.loadSystemDefaultLookAndFeel();
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setTitle("欣然药品库房管理系统");
        mainFrame.setLocationRelativeTo(null);//窗口在屏幕中间启动
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
