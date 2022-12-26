package bsu.rfe.java.group10.lab3.Laziuk.varB6;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
    // (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
    // стога сена - таблица
    private String needle = null;
    
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
    
    public GornerTableCellRenderer() {
            // Показывать только 4 знаков после запятой
            formatter.setMaximumFractionDigits(4);
            // Не использовать группировку (не отделять тысячи)
            // Т.е. показывать число как "1000", а не "1 000" или "1,000"
            formatter.setGroupingUsed(false);
            // Установить в качестве разделителя дробной части точку, а не запятую
            // По умолчанию, в региональных настройках Россия/Беларусь дробная часть
            // отделяется запятой
            DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
            dottedDouble.setDecimalSeparator('.');
            formatter.setDecimalFormatSymbols(dottedDouble);
            // Разместить надпись внутри панели
            panel.add(label);
            // Установить выравнивание надписи по левому краю панели
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        // Преобразовать число в строку с помощью форматировщика
        String formattedDouble = (String)formatter.format(value);
        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        panel.setBackground(Color.WHITE);
        if((col == 1 || col == 0) && needle != null && needle.equals(formattedDouble)) {
            // Номер столбца = 1 (т.е. второй столбец)
            // + иголка не null (т.е. мы что-то ищем)
            // + значение иголки совпадает со значением ячейки таблицы -
            // окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
        } else if (col == 1 || col == 0) {
            double d = (double)value;
            double b = d % 1;
            int a = (int)d;
            String int_str = Integer.toString(a);
            String double_str = String.format("%.3f", b);
            String double_substr = double_str.substring(2);
            for(int i = double_substr.length() - 1; i > 0; i--) {
                if(double_substr.charAt(i) == '0') {
                    double_substr = double_substr.substring(0, i);
                }
                else {
                    break;
                }
            }
            //label.setText(double_substr);
            if(int_str.equals(double_substr)) panel.setBackground(Color.ORANGE);
        } else {
            // Иначе - в обычный белый
            panel.setBackground(Color.WHITE);
        }
        
        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }

}
