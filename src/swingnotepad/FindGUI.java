/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingnotepad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

/**
 *
 * @author edutilos
 */
public class FindGUI extends javax.swing.JFrame {

    private JTextArea areaDisplay ; 
    
    /**
     * Creates new form FindGUI
     */
    public FindGUI(JTextArea areaDisplay) {
        this.areaDisplay = areaDisplay ; 
        initComponents();
        registerEvents(); 
    }
    
    
    private void registerEvents() {
        radioUp.setSelected(true);
        prepareContent();
        btnFind.addActionListener(l-> {
           onBtnFind();
        });
        
        btnCancel.addActionListener(l-> {
             onBtnCancel();
        }); 
        
        radioUp.addChangeListener(l-> {
            prepareContent();
        });
        
        radioDown.addChangeListener(l-> {
            prepareContent();
        });
        
        cbMatchCase.addChangeListener(l-> {
            prepareContent();
        });
    }

    private int currentIndex; 
    private String token; 
    private String content; 
    int mark = -1; 
    Pattern p ; 
    Matcher m; 
    
    private void prepareContent() {
       token = editFind.getText();    
       content = areaDisplay.getText(); 
       if(!cbMatchCase.isSelected()) {
           token = token.toLowerCase(); 
           content = content.toLowerCase(); 
       } 
       
       p = Pattern.compile(token); 
       m = p.matcher(content); 
    }
    
    private void onBtnFind() {
        prepareContent(); 
        if(mark!= -1) areaDisplay.setCaretPosition(mark);
        mark = areaDisplay.getCaretPosition(); 
        
        System.out.println("mark = " + mark);
    //   currentIndex = mark; 
     
           
    if(radioDown.isSelected()) findForwards(); 
    else if(radioUp.isSelected()) findBackwards(); 
       
    }
    
    
    private void findForwards() {
       if(m.find(mark)) {
           areaDisplay.setSelectionStart(m.start());
           areaDisplay.setSelectionEnd(m.end()); 
           mark = m.end(); 
           //areaDisplay.setCaretPosition(mark);
       } else {
//           if(mark < content.length())
//           mark += 1 ; 
       }
    }
    
    private void findBackwards() {
        
      //  if(mark == content.length()) mark = mark- token.length();
         m = p.matcher(content.substring(0, mark)); 
        while(!m.find(mark)) {
            if(mark > 0)
            mark -= 1 ; 
            else break; 
        }
        if(m.find(mark)) {
            System.out.println(m.start()+ " , " + m.end());
           areaDisplay.setSelectionStart(m.start());
           areaDisplay.setSelectionEnd(m.end()); 
           mark = m.start() - token.length(); 
           if(mark < 0 ) mark =  0; 
          // areaDisplay.setCaretPosition(mark);
        } else {
            
        }
    }
    
    
//    private void findForwards() {
//          currentIndex  = content.indexOf(token, mark); 
//        System.out.println("currentIndex = " + currentIndex);
//       if(currentIndex == -1) {
//           areaDisplay.setSelectionStart(mark);
//           areaDisplay.setSelectionEnd(mark); 
//           return ; 
//       }
//       
//       mark = currentIndex ; 
//       int index0= -1, index1= -1 ; 
//     
//            index0 = mark ; 
//           index1 = index0 + token.length() ; 
//      
//     
//        System.out.println(index0 + " , " + index1);
//        mark = index1 ; 
//      //  if(radioDown.isSelected()) mark +=1 ; 
//       // else if(radioUp.isSelected()) mark -=1 ; 
//        areaDisplay.setCaretPosition(mark);
//           areaDisplay.setSelectionStart(index0); 
//           areaDisplay.setSelectionEnd(index1); 
//    }
//    
//    private void findBackwards(){
//        System.out.println("content length = " +content.length());
//        if(mark == content.length()) mark = mark - token.length(); 
//        System.out.println("mark after = "+ mark);
//      //  mark -= token.length(); 
//             currentIndex  = content.indexOf(token, mark); 
//        System.out.println("currentIndex = " + currentIndex);
//       if(currentIndex == -1) {
//           areaDisplay.setSelectionStart(mark);
//           areaDisplay.setSelectionEnd(mark); 
//           return ; 
//       }
//       
//       mark = currentIndex ; 
//       int index0= -1, index1= -1 ; 
//     
//            index0 = mark ; 
//           index1 = index0 + token.length() ; 
//      
//     
//        System.out.println(index0 + " , " + index1);
//        mark = index0- token.length() ; 
//      //  if(radioDown.isSelected()) mark +=1 ; 
//       // else if(radioUp.isSelected()) mark -=1 ; 
//        areaDisplay.setCaretPosition(mark);
//           areaDisplay.setSelectionStart(index0); 
//           areaDisplay.setSelectionEnd(index1); 
//    }
    
    
//    private void prepareContent() {
//             token = editFind.getText(); 
//         content = areaDisplay.getText(); 
//        if(!cbMatchCase.isSelected())  {
//            token = token.toLowerCase(); 
//            content = content.toLowerCase(); 
//        }
//       Caret caret =  areaDisplay.getCaret(); 
//        mark = caret.getMark(); 
//        int dot = caret.getDot(); 
//  //     if(mark != dot) return ; 
//       
//       
//        if(radioDown.isSelected()) {
//            content = content.substring(mark); 
//        } else if(radioUp.isSelected()){
//            content = content.substring(0, mark); 
//        }
//        
//        currentIndex = mark; 
//    }
//    private void onBtnFind() {
//           token = editFind.getText(); 
//       Caret caret =  areaDisplay.getCaret(); 
//     //  int mark = caret.getMark(); 
//      //  int dot = caret.getDot(); 
//  //     if(mark != dot) return ; 
//       
//       
//        
//
//      // currentIndex = mark ; 
//      System.out.print(currentIndex + " , ");
//       currentIndex= content.indexOf(token, currentIndex);
//        System.out.print(currentIndex+ " , ");
//       if(currentIndex == -1) {
//           areaDisplay.setSelectionStart(0);
//           areaDisplay.setSelectionEnd(0);
//           currentIndex = areaDisplay.getCaretPosition(); 
//           return ;
//       } 
//       areaDisplay.setSelectionStart(currentIndex);
//       currentIndex += token.length()+1; 
//       areaDisplay.setSelectionEnd(currentIndex); 
//       System.out.println(currentIndex);
//    }
    
    private void onBtnCancel() {
        this.dispose();
    }
    
    public String getFindToken() {
        return editFind.getText(); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupFindDirection = new javax.swing.ButtonGroup();
        lblFind = new javax.swing.JLabel();
        editFind = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        lblDirection = new javax.swing.JLabel();
        radioUp = new javax.swing.JRadioButton();
        radioDown = new javax.swing.JRadioButton();
        cbMatchCase = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Find");

        lblFind.setText("Find What:");

        editFind.setColumns(10);

        btnCancel.setText("Cancel");

        btnFind.setText("Find Next");

        lblDirection.setText("Direction");

        groupFindDirection.add(radioUp);
        radioUp.setText("Up");

        groupFindDirection.add(radioDown);
        radioDown.setText("Down");

        cbMatchCase.setText("Match Case");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFind)
                                .addGap(18, 18, 18)
                                .addComponent(editFind, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDirection)
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioDown)
                                    .addComponent(radioUp)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(cbMatchCase)))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(294, Short.MAX_VALUE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFind)
                    .addComponent(editFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDirection)
                    .addComponent(radioUp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDown)
                .addGap(39, 39, 39)
                .addComponent(cbMatchCase)
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(btnFind)
                    .addContainerGap(247, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FindGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FindGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FindGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FindGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FindGUI(null).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFind;
    private javax.swing.JCheckBox cbMatchCase;
    private javax.swing.JTextField editFind;
    private javax.swing.ButtonGroup groupFindDirection;
    private javax.swing.JLabel lblDirection;
    private javax.swing.JLabel lblFind;
    private javax.swing.JRadioButton radioDown;
    private javax.swing.JRadioButton radioUp;
    // End of variables declaration//GEN-END:variables
}
