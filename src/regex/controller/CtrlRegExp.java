package regex.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import regex.model.ReadData;
import regex.model.GenerateReports;
import regex.view.JFPrincipal;

/**
 *
 * @author Mark Zamudio
 */
public class CtrlRegExp implements ActionListener{
    private ReadData readData;
    private GenerateReports generateReport;
    private JFPrincipal jfPrincipal;
    private String tableContent[][];
    
    public CtrlRegExp(JFPrincipal jfPrincipal) {
        this.readData = new ReadData();
        this.generateReport = new GenerateReports();
        this.jfPrincipal = jfPrincipal;
        
        this.jfPrincipal.btnReadInputs.addActionListener(this);
        this.jfPrincipal.jbnAnalizeInputs.addActionListener(this);
        this.jfPrincipal.jbnShowReport.addActionListener(this);
        this.jfPrincipal.jtbData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mc) {
                jfPrincipal.jbnShowReport.setEnabled(true);
            }
                    
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jfPrincipal.btnReadInputs) {
            readData.readFile();
            tableContent = new String[readData.getStorage().size()][2];
            showEntries();
            jfPrincipal.jbnAnalizeInputs.setEnabled(true);
            jfPrincipal.jbnShowReport.setEnabled(false);
        } else if (ae.getSource() == jfPrincipal.jbnAnalizeInputs) {
            showResult();
        } else if (ae.getSource() == jfPrincipal.jbnShowReport) {
            int selectedRow = jfPrincipal.jtbData.getSelectedRow();
            
            if(selectedRow > -1) {
                String regex = (String) jfPrincipal.jtbData.getValueAt(selectedRow, 0);
                generateReport.createReport(regex,generateReport(regex));
                JOptionPane.showMessageDialog(null, "Reporte: \n" + readData.readReport(regex),"REGEX",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor selecciona una exp reg de la tabla","REGEX",JOptionPane.WARNING_MESSAGE);
            }
        } else {
            
        }
    }
    
    private String[] getTableColums() {
        String columns[] = {"Cadenas","Resultado"};
        return columns;
    }
    
    private void showEntries() {
        for (int i = 0; i < tableContent.length; i++) {
            tableContent[i][0] = readData.getStorage().get(i);
            tableContent[i][1] = "";
        }
        
        jfPrincipal.jtbData.setModel(new javax.swing.table.DefaultTableModel(tableContent,getTableColums()));
    }
    
    private void showResult() {
        for (int i = 0; i < tableContent.length; i++) {
            if (tableContent[i][0].matches("((@@)|(@@@@))[aeiouAEIOU]{2}[#&]{2}[0-9]$")) {
                tableContent[i][1] = "Aceptada";
                continue;
            }
            
            tableContent[i][1] = "Rechazada";
        }
        
        jfPrincipal.jtbData.setModel(new javax.swing.table.DefaultTableModel(tableContent,getTableColums()));
    }
    
    private String generateReport(String in) {
        String result = "Cadena = " + in + "\n\n";
        
        if (in.matches("^(@@@@).*")) {
            result += "Cumple con la regla 1 (@@@@) \n";
            in = in.substring(4);
            result += nextRules(in);
        } else if (in.matches("^(@@)[^@]*")) {
            result += "Cumple con la regla 1 (@@@@) \n";
            in = in.substring(2);
            result += nextRules(in);
        } else {
            result += "No cumple con ninguna regla  \n";
            result += "Cadena Rechada";
        }
        
        return result;
    }
    
    private String nextRules(String in) {
        String result = "";
        
        if (in.matches("^[aeiouAEIOU]{2}.*")) {
            result += "Cumple con la regla dos [aeiouAEIOU]{2} \n";
            in = in.substring(2);
                
            if (in.matches("^[#&]{2}.*")) {
                result += "Cumple con la regla tres [#&]{2} \n";
                in = in.substring(2);
                  
                if (in.matches("[0-9]")) {
                    result += "Cumple con la regla Cuatro [0-9] \n";
                    result += "Cadena Aceptada";
                } else {
                    result += "No cumple con la regla cuatro  [0-9] \n";
                    result += "Cadena Rechada";
                }
                    
            } else {
                result += "No cumple con la regla tres [#&]{2} \n";
                result += "No cumple con la regla cuatro  [0-9] \n";
                result += "Cadena Rechada";
            }
        } else {
            result += "No cumple con la regla dos [aeiouAEIOU]{2} \n";
            result += "No cumple con la regla tres [#&]{2} \n";
            result += "No cumple con la regla cuatro [0-9] \n";
            result += "Cadena Rechada";
        }
        
        return result;
    }
    
    public void run() {
        jfPrincipal.setTitle("EXPRESIONES REGULARES");
        jfPrincipal.setLocationRelativeTo(null);
        jfPrincipal.setVisible(true);
    }
}
