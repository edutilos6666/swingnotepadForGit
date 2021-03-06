/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingnotepad;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edutilos
 */
public class HelpGUI extends javax.swing.JFrame {

    
    private DefaultTableModel tableModel; 
    
    /**
     * Creates new form HelpGUI
     */
    public HelpGUI() {
        initComponents();
        registerEvents(); 
    }

    /*
    Keyboard Accelerator - Action
Ctrl+N - New 
Ctrl+O - Open
Ctrl+S - Save 
Ctrl+P - Print 
Ctrl+Z - Undo 
Ctrl+Y - Redo
Ctrl+X - Cut 
Ctrl+C - Copy 
Ctrl+V - Paste
Delete - Delete
Ctrl+F - Find 
F3 - Find Next 
Ctrl+H - Replace 
Ctrl+G - Go To
Ctrl+A - Select All
F5 - Time/Date

    */
    private void registerEvents() {
        Vector<Object> columnNames = new Vector<>(); 
        columnNames.add("Keyboard Accelerator"); 
        columnNames.add("Action");
        Vector<Vector<Object>> data = new Vector<>(); 
        data.addAll(Arrays.asList(
                new Vector<Object>(Arrays.asList("Ctrl+N", "New")), 
                new Vector<Object>(Arrays.asList("Ctrl+O", "Open")),
                new Vector<Object>(Arrays.asList("Ctrl+S", "Save")),
                new Vector<Object>(Arrays.asList("Ctrl+P", "Print")),
                new Vector<Object>(Arrays.asList("Ctrl+Z", "Undo")),
                new Vector<Object>(Arrays.asList("Ctrl+Y", "Redo")),
                new Vector<Object>(Arrays.asList("Ctrl+X", "Cut")),
                new Vector<Object>(Arrays.asList("Ctrl+C", "Copy")),
                new Vector<Object>(Arrays.asList("Ctrl+V", "Paste")),
                new Vector<Object>(Arrays.asList("Delete", "Delete")),
                new Vector<Object>(Arrays.asList("Ctrl+F", "Find")),
                new Vector<Object>(Arrays.asList("F3", "Find Next")),
                new Vector<Object>(Arrays.asList("Ctrl+H", "Replace")),
                new Vector<Object>(Arrays.asList("Ctrl+G", "Go To")),
                new Vector<Object>(Arrays.asList("Ctrl+A", "Select All")),
                new Vector<Object>(Arrays.asList("F5", "Time/Date")))); 
        
        tableModel = new DefaultTableModel(data, columnNames); 
        tableKeyboard.setModel(tableModel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableKeyboard = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Help Center");

        tableKeyboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableKeyboard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableKeyboard;
    // End of variables declaration//GEN-END:variables
}
