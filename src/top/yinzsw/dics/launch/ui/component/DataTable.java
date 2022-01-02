package top.yinzsw.dics.launch.ui.component;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class DataTable extends JTable {
    private final DefaultTableModel model;

    public DataTable(Object[] objects, Integer... cols) {
        super(createModel(objects, cols));
        setRowHeight(20);
        getTableHeader().setReorderingAllowed(false);
        model = (DefaultTableModel) getModel();
    }

    public void stopEditing() {
        if (isEditing()) {
            getCellEditor().stopCellEditing();
        }
    }

    public Vector<String> getSelectRowData() {
        Vector<String> vector = new Vector<>();

        int editingRow = getSelectedRow();

        if (editingRow == -1) return vector;

        stopEditing();

        int columnCount = getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            vector.addElement(getValueAt(editingRow, i).toString());
        }
        return vector;
    }

    public void clear() {
        stopEditing();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    private static DefaultTableModel createModel(Object[] objects, Integer[] cols) {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !Arrays.asList(cols).contains(column);
            }
        };
        defaultTableModel.setColumnIdentifiers(objects);
        return defaultTableModel;
    }

    public JComboBox<Object> setColCellEditor(int col, JComboBox<?> editor) {
        JComboBoxRenderer jComboBoxRenderer = new JComboBoxRenderer();
        TableColumn column = getColumnModel().getColumn(col);
        column.setCellEditor(new DefaultCellEditor(editor));
        column.setCellRenderer(jComboBoxRenderer);
        return jComboBoxRenderer;
    }

    public JCheckBox setColCellEditor(int col, JCheckBox editor) {
        JCheckBoxRenderer jCheckBoxRenderer = new JCheckBoxRenderer(editor.getText());
        TableColumn column = getColumnModel().getColumn(col);
        column.setCellEditor(new DefaultCellEditor(editor));
        column.setCellRenderer(jCheckBoxRenderer);
        return jCheckBoxRenderer;
    }

    private static class JComboBoxRenderer extends JComboBox<Object> implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelectedItem(value);
            return this;
        }
    }

    private static class JCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public JCheckBoxRenderer(String text) {
            super(text);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value == null) {
                setSelected(false);
            } else {
                setSelected((boolean) value);
            }
            return this;
        }
    }
}

