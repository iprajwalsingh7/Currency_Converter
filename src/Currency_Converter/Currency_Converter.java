package Currency_Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Currency_Converter extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR"};
    private double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};

    public Currency_Converter() {
        setTitle("Currency Converter");
        setLayout(new BorderLayout());
        
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(230, 230, 250);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mainPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        amountField = new JTextField(10);
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(amountField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mainPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        fromComboBox = new JComboBox<>(currencies);
        fromComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        fromComboBox.setBackground(Color.WHITE);
        mainPanel.add(fromComboBox, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mainPanel.add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        toComboBox = new JComboBox<>(currencies);
        toComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        toComboBox.setBackground(Color.WHITE);
        mainPanel.add(toComboBox, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(convertButton, gbc);

        // Result label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(new Color(0, 100, 0));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        mainPanel.add(resultLabel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Currency_Converter();
            }
        });
    }
}
