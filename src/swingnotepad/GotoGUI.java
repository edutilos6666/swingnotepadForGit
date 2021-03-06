/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingnotepad;

import javax.swing.JTextArea;

/**
 *
 * @author edutilos
 */
public class GotoGUI extends javax.swing.JFrame {

    private JTextArea areaDisplay ; 
    /**
     * Creates new form GotoGUI
     */
    public GotoGUI(JTextArea areaDisplay) {
        this.areaDisplay = areaDisplay; 
        initComponents();
        registerEvents(); 
    }
    
    
    private void registerEvents() {
        btnOk.addActionListener(l-> {
            try {
                String lineStr = editGoto.getText(); 
                int line = Integer.parseInt(lineStr); 
                RXTextUtilities.gotoStartOfLine(areaDisplay, line);
                this.setVisible(false);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        
        btnCancel.addActionListener(l-> {
            this.setVisible(false); 
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGoto = new javax.swing.JLabel();
        editGoto = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GoTo");

        lblGoto.setText("Goto: ");

        btnCancel.setText("Cancel");

        btnOk.setText("Ok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblGoto)
                        .addGap(64, 64, 64)
                        .addComponent(editGoto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(btnCancel)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGoto)
                    .addComponent(editGoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JTextField editGoto;
    private javax.swing.JLabel lblGoto;
    // End of variables declaration//GEN-END:variables
}
