package top.yinzsw.dics.launch.ui.component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Pattern;

public class ComponentUtils {
    public static void resetField(JTextComponent... components) {
        for (JTextComponent component : components) {
            component.setText("");
        }
    }

    public static boolean hasEmptyField(JTextComponent... components) {
        for (JTextComponent component : components) {
            if ("".equals(component.getText().trim())) return true;
        }
        return false;
    }

    public static KeyAdapter limitFieldLength(JTextComponent component, int length) {
        return new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (component.getText().length() >= length) {
                    e.consume();
                }
            }
        };
    }

    public static KeyAdapter limitFieldInput(JTextComponent component, String regexp) {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Pattern.matches(regexp, component.getText() + e.getKeyChar())) {
                    e.consume();
                }
            }
        };
    }

    public static void showMessageDialogWhileUpdate(int row, String tInfo, String fInfo) {
        if (row > 0) {
            JOptionPane.showMessageDialog(null, tInfo, "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, fInfo, "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
