import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Main extends JFrame {
    private static final int WIDTH = 480;
    private static final int HEIGHT = 320;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;  // значение = 1 или 2 - выбор 1 или 2 формулы
    private Double sum = 0.;

    public Double calculate1(Double x, Double y, Double z) {
        return (1 / Math.sqrt(x) + Math.cos(Math.exp(y)) + Math.cos(z * z)) / Math.pow(2 * Math.log(1 + z) + Math.sqrt(Math.exp(Math.cos(y)) + Math.sin(Math.PI * x)), 1 / 3);
    }

    public Double calculate2(Double x, Double y, Double z) {
        return Math.pow(y + x * x * x, 1 / z) / Math.log(z);
    }

    private void addRadioButton(String buttonName, final int formulaId) {  // создание радиокнопки
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {  // выбор радиокнопки
                Main.this.formulaId = formulaId;
            }});
        radioButtons.add(button);
        hboxFormulaType.add(button);
        hboxFormulaType.add(Box.createHorizontalStrut(10));
    }

    public Main() {
        super("Вычисление значения функции трех переменных");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
        radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();

        //  заменил выравнвание полей X и Z по центру на выравнивание по левому и правому боку
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        //hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldX);
            hboxVariables.add(Box.createHorizontalGlue());
        //hboxVariables.add(Box.createHorizontalStrut(20));
        hboxVariables.add(labelForY);
        //hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldY);
            hboxVariables.add(Box.createHorizontalGlue());
        //hboxVariables.add(Box.createHorizontalStrut(20));
        hboxVariables.add(labelForZ);
        //hboxVariables.add(Box.createHorizontalStrut(5));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {  // если нажали кнопку то выполнится:
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    
                    Main.this.sum += result;
                    textFieldResult.setText(Main.this.sum.toString());
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("0");
                Main.this.sum = 0.;
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(20));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonM);
        hboxButtons.add(Box.createHorizontalStrut(20));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxFormulaType);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
