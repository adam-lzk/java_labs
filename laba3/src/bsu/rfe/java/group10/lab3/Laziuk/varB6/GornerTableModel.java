package bsu.rfe.java.group10.lab3.Laziuk.varB6;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[]
            coefficients) {
            this.from = from;
            this.to = to;
            this.step = step;
            this.coefficients = coefficients;
            }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                    return "Значение X";
            case 1:
                    return "Значение многочлена";
            default:
                    return "Целая часть палиндром?";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
        case 0:
        case 1:    
                return Double.class;
        default:
                return Boolean.class;
        }
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + step*row;
        if (col == 0) {
            return x;
        } else {
            double result = 0.0;
            for (int i = coefficients.length - 1; i >= 0; i--) {
                result += coefficients[i] * Math.pow(x, coefficients.length - 1 - i);
            }
            if (col == 1) {
                return result;
            }
            else {
                int res = (int)result;
                int length = String.valueOf(res).length();
                String str_res = String.valueOf(res);
                for(int i = 0, k = length - 1; k >= 0; i++, k--) {
                    if(str_res.charAt(i) != str_res.charAt(k)) return false;
                }
                return true;
            }
        }
    }

}
