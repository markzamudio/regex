package regex.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import regex.model.ReadData;
import regex.view.JFPrincipal;

/**
 *
 * @author Mark Zamudio
 */
public class CtrlRegExp implements ActionListener {
    private ReadData readData;
    private JFPrincipal jfPrincipal;
    private String tableContent[][];
    
    public CtrlRegExp(ReadData readData, JFPrincipal jfPrincipal) {
        this.readData = readData;
        this.jfPrincipal = jfPrincipal;
        
        this.jfPrincipal.btnReadInputs.addActionListener(this);
        this.jfPrincipal.jbnAnalizeInputs.addActionListener(this);
        this.jfPrincipal.jbnShowReport.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jfPrincipal.btnReadInputs) {
            readData.readFile();
            tableContent = new String[readData.getStorage().size()][2];
            showEntries();
        } else if (ae.getSource() == jfPrincipal.jbnAnalizeInputs) {
            showResult();
        } else if (ae.getSource() == jfPrincipal.jbnShowReport) {
            
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
            if (!tableContent[i][0].matches("aaa")) {
                tableContent[i][1] = "Valida";
                continue;
            }
            
            tableContent[i][1] = "Invalida";
        }
        
        jfPrincipal.jtbData.setModel(new javax.swing.table.DefaultTableModel(tableContent,getTableColums()));
    }
    
    public void run() {
        jfPrincipal.setTitle("Expresiones Regulares");
        jfPrincipal.setLocationRelativeTo(null);
    }
}
