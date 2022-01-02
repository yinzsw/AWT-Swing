package top.yinzsw.dics.launch.ui.component;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public TimeLabel() {
        new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                setText("当前时间: " + sdf.format(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
