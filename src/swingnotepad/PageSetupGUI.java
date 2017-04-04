/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingnotepad;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

/**
 *
 * @author edutilos
 */
public class PageSetupGUI extends javax.swing.JFrame {

    
    private OrientationRequested orientation = OrientationRequested.PORTRAIT; 
    private MediaSizeName mediaSize = MediaSizeName.ISO_A0; 
    private double marginLeft = 10 , marginRight= 10 , marginTop = 10, marginBottom = 10; 
    boolean pageSetupCancelled; 
    /**
     * Creates new form PageSetupGUI
     */
    public PageSetupGUI(OrientationRequested orientation , MediaSizeName mediaSize, 
            double marginLeft , double marginRight , double marginTop, double marginBottom) {
        this.orientation = orientation; 
        this.mediaSize = mediaSize; 
        this.marginLeft = marginLeft; 
        this.marginRight = marginRight; 
        this.marginTop = marginTop; 
        this.marginBottom = marginBottom; 
        initComponents();
         configureWindow(); 
    }

    

    public PageSetupGUI() {

        initComponents();
         configureWindow(); 
    }
    
    private void configureWindow() {
        if(orientation == OrientationRequested.PORTRAIT) {
            radioPortrait.setSelected(true);
            
        } else if(orientation == OrientationRequested.LANDSCAPE) {
            radioLandscape.setSelected(true); 
        }
        
       //comboSource.setSelectedItem(mediaSize.getName());
        comboSize.setSelectedItem(mediaSize.getName()); 
        editMarginLeft.setText(marginLeft+""); 
        editMarginRight.setText(marginRight+""); 
        editMarginTop.setText(marginTop+""); 
        editMarginBottom.setText(marginBottom+""); 
        registerEvents(); 
    }

    
    private void registerEvents() {
        
      comboSize.addItemListener(l-> {
         String name = l.getItem().toString(); 
          System.out.println(name);
          switch(name) {
              case "ISO_A0": mediaSize = MediaSizeName.ISO_A0; break; 
              case "ISO_A1": mediaSize = MediaSizeName.ISO_A1; break; 
              case "ISO_A2": mediaSize = MediaSizeName.ISO_A2; break; 
              case "ISO_A3": mediaSize = MediaSizeName.ISO_A3; break; 
              case "ISO_A4": mediaSize = MediaSizeName.ISO_A4; break; 
              case "ISO_A5": mediaSize = MediaSizeName.ISO_A5; break; 
              case "ISO_A6": mediaSize = MediaSizeName.ISO_A6; break; 
              case "ISO_A7": mediaSize = MediaSizeName.ISO_A7; break; 
              case "ISO_A8": mediaSize = MediaSizeName.ISO_A8; break; 
              case "ISO_A9": mediaSize = MediaSizeName.ISO_A9; break; 
              case "ISO_A10": mediaSize = MediaSizeName.ISO_A10; break; 
              case "ISO_B0": mediaSize = MediaSizeName.ISO_B0; break; 
              case "ISO_B1": mediaSize = MediaSizeName.ISO_B1; break; 
              case "ISO_B2": mediaSize = MediaSizeName.ISO_B2; break; 
              case "ISO_B3": mediaSize = MediaSizeName.ISO_B3; break; 
              case "ISO_B4": mediaSize = MediaSizeName.ISO_B4; break; 
              case "ISO_B5": mediaSize = MediaSizeName.ISO_B5; break; 
              case "ISO_B6": mediaSize = MediaSizeName.ISO_B6; break; 
              case "ISO_B7": mediaSize = MediaSizeName.ISO_B7; break; 
              case "ISO_B8": mediaSize = MediaSizeName.ISO_B8; break; 
              case "ISO_B9": mediaSize = MediaSizeName.ISO_B9; break; 
              case "ISO_B10": mediaSize = MediaSizeName.ISO_B10; break; 
              case "JIS_B0": mediaSize = MediaSizeName.JIS_B0; break; 
              case "JIS_B1": mediaSize = MediaSizeName.JIS_B1; break; 
              case "JIS_B2": mediaSize = MediaSizeName.JIS_B2; break; 
              case "JIS_B3": mediaSize = MediaSizeName.JIS_B3; break; 
              case "JIS_B4": mediaSize = MediaSizeName.JIS_B4; break; 
              case "JIS_B5": mediaSize = MediaSizeName.JIS_B5; break; 
              case "JIS_B6": mediaSize = MediaSizeName.JIS_B6; break; 
              case "JIS_B7": mediaSize = MediaSizeName.JIS_B7; break; 
              case "JIS_B8": mediaSize = MediaSizeName.JIS_B8; break; 
              case "JIS_B9": mediaSize = MediaSizeName.JIS_B9; break; 
              case "JIS_B10": mediaSize = MediaSizeName.JIS_B10; break; 
              case "ISO_C0": mediaSize = MediaSizeName.ISO_C0; break; 
              case "ISO_C1": mediaSize = MediaSizeName.ISO_C1; break; 
              case "ISO_C2": mediaSize = MediaSizeName.ISO_C2; break; 
              case "ISO_C3": mediaSize = MediaSizeName.ISO_C3; break; 
              case "ISO_C4": mediaSize = MediaSizeName.ISO_C4; break; 
              case "ISO_C5": mediaSize = MediaSizeName.ISO_C5; break; 
              case "ISO_C6": mediaSize = MediaSizeName.ISO_C6; break; 
              case "NA_LETTER": mediaSize = MediaSizeName.NA_LETTER; break; 
              case "NA_LEGAL": mediaSize = MediaSizeName.NA_LEGAL; break; 
              case "EXECUTIVE": mediaSize = MediaSizeName.EXECUTIVE; break; 
              case "LEDGER": mediaSize = MediaSizeName.LEDGER; break; 
              case "TABLOID": mediaSize = MediaSizeName.TABLOID; break; 
              case "INVOICE": mediaSize = MediaSizeName.INVOICE; break; 
              case "FOLIO": mediaSize = MediaSizeName.FOLIO; break; 
              case "QUARTO": mediaSize = MediaSizeName.QUARTO; break; 
              case "JAPANESE_POSTCARD": mediaSize = MediaSizeName.JAPANESE_POSTCARD; break; 
              case "JAPANESE_DOUBLE_POSTCARD": mediaSize = MediaSizeName.JAPANESE_DOUBLE_POSTCARD; break; 
              case "A": mediaSize = MediaSizeName.A; break; 
              case "B": mediaSize = MediaSizeName.B; break; 
              case "C": mediaSize = MediaSizeName.C; break; 
              case "D": mediaSize = MediaSizeName.D; break; 
              case "E": mediaSize = MediaSizeName.E; break; 
              case "ISO_DESIGNATED_LONG": mediaSize = MediaSizeName.ISO_DESIGNATED_LONG; break; 
              case "ITALY_ENVELOPE": mediaSize = MediaSizeName.ITALY_ENVELOPE; break; 
              case "MONARCH_ENVELOPE": mediaSize = MediaSizeName.MONARCH_ENVELOPE; break; 
              case "PERSONAL_ENVELOPE": mediaSize = MediaSizeName.PERSONAL_ENVELOPE; break; 
              case "NA_NUMBER_9_ENVELOPE": mediaSize = MediaSizeName.NA_NUMBER_9_ENVELOPE; break; 
              case "NA_NUMBER_10_ENVELOPE": mediaSize = MediaSizeName.NA_NUMBER_10_ENVELOPE; break; 
              case "NA_NUMBER_11_ENVELOPE": mediaSize = MediaSizeName.NA_NUMBER_11_ENVELOPE; break; 
              case "NA_NUMBER_12_ENVELOPE": mediaSize = MediaSizeName.NA_NUMBER_12_ENVELOPE; break; 
              case "NA_NUMBER_14_ENVELOPE": mediaSize = MediaSizeName.NA_NUMBER_14_ENVELOPE; break; 
              case "NA_6X9_ENVELOPE": mediaSize = MediaSizeName.NA_6X9_ENVELOPE; break; 
              case "NA_7X9_ENVELOPE": mediaSize = MediaSizeName.NA_7X9_ENVELOPE; break; 
              case "NA_9X11_ENVELOPE": mediaSize = MediaSizeName.NA_9X11_ENVELOPE; break; 
              case "NA_9X12_ENVELOPE": mediaSize = MediaSizeName.NA_9X12_ENVELOPE; break; 
              case "NA_10X13_ENVELOPE": mediaSize = MediaSizeName.NA_10X13_ENVELOPE; break; 
              case "NA_10X14_ENVELOPE": mediaSize = MediaSizeName.NA_10X14_ENVELOPE; break; 
              case "NA_10X15_ENVELOPE": mediaSize = MediaSizeName.NA_10X15_ENVELOPE; break; 
              case "NA_5X7": mediaSize = MediaSizeName.NA_5X7; break; 
              case "NA_8X10": mediaSize = MediaSizeName.NA_8X10; break; 
          }
      });
      
      
    editMarginLeft.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
              try {
                  String txt = editMarginLeft.getText(); 
                  Double d = Double.parseDouble(txt); 
                  marginLeft = d ; 
              } catch(Exception ex) {
                  ex.printStackTrace();
              }
          }
        
    });
      
      
        editMarginRight.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
              try {
                  String txt = editMarginRight.getText(); 
                  Double d = Double.parseDouble(txt); 
                  marginRight = d ; 
              } catch(Exception ex) {
                  ex.printStackTrace();
              }
          }
        
    });
      
        
        
            editMarginTop.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
              try {
                  String txt = editMarginTop.getText(); 
                  Double d = Double.parseDouble(txt); 
                  marginTop = d ; 
              } catch(Exception ex) {
                  ex.printStackTrace();
              }
          }
        
    });
      
            
            
                editMarginBottom.addKeyListener(new KeyAdapter() {
          @Override
          public void keyTyped(KeyEvent e) {
              try {
                  String txt = editMarginBottom.getText(); 
                  Double d = Double.parseDouble(txt); 
                  marginBottom = d ; 
              } catch(Exception ex) {
                  ex.printStackTrace();
              }
          }
        
    });
      
    
           btnOk.addActionListener(l-> {
               pageSetupCancelled = false ; 
               this.dispose();
           });
           
           btnCancel.addActionListener(l-> {
               pageSetupCancelled = true; 
               this.dispose(); 
           });
           
                
    }

    public boolean isPageSetupCancelled() {
        return pageSetupCancelled;
    }

    public void setPageSetupCancelled(boolean pageSetupCancelled) {
        this.pageSetupCancelled = pageSetupCancelled;
    }
    
    
    
    
    
    public OrientationRequested getOrientation() {
        return orientation;
    }

    public void setOrientation(OrientationRequested orientation) {
        this.orientation = orientation;
    }

    public MediaSizeName getMediaSize() {
        return mediaSize;
    }

    public void setMediaSize(MediaSizeName mediaSize) {
        this.mediaSize = mediaSize;
    }

    public double getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(double marginLeft) {
        this.marginLeft = marginLeft;
    }

    public double getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(double marginRight) {
        this.marginRight = marginRight;
    }

    public double getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(double marginTop) {
        this.marginTop = marginTop;
    }

    public double getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(double marginBottom) {
        this.marginBottom = marginBottom;
    }
    
    
    
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupOrientation = new javax.swing.ButtonGroup();
        lblSize = new javax.swing.JLabel();
        comboSize = new javax.swing.JComboBox<>();
        lblSource = new javax.swing.JLabel();
        comboSource = new javax.swing.JComboBox<>();
        lblOrientation = new javax.swing.JLabel();
        radioPortrait = new javax.swing.JRadioButton();
        radioLandscape = new javax.swing.JRadioButton();
        lblMargins = new javax.swing.JLabel();
        lblMarginLeft = new javax.swing.JLabel();
        editMarginLeft = new javax.swing.JTextField();
        lblMarginTop = new javax.swing.JLabel();
        editMarginTop = new javax.swing.JTextField();
        lblMarginRight = new javax.swing.JLabel();
        editMarginRight = new javax.swing.JTextField();
        lblMarginBottom = new javax.swing.JLabel();
        editMarginBottom = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblSize.setText("Size:");

        comboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISO_A0", "ISO_A1", "ISO_A2", "ISO_A3", "ISO_A4", "ISO_A5", "ISO_A6", "ISO_A7", "ISO_A8", "ISO_A9", "ISO_A10", "ISO_B0", "ISO_B1", "ISO_B2", "ISO_B3", "ISO_B4", "ISO_B5", "ISO_B6", "ISO_B7", "ISO_B8", "ISO_B9", "ISO_B10", "JIS_B0", "JIS_B1", "JIS_B2", "JIS_B3", "JIS_B4", "JIS_B5", "JIS_B6", "JIS_B7", "JIS_B8", "JIS_B9", "JIS_B10", "ISO_C0", "ISO_C1", "ISO_C2", "ISO_C3", "ISO_C4", "ISO_C5", "ISO_C6", "NA_LETTER", "NA_LEGAL", "EXECUTIVE", "LEDGER", "TABLOID", "INVOICE", "FOLIO", "QUARTO", "JAPANESE_POSTCARD", "JAPANESE_DOUBLE_POSTCARD", "A", "B", "C", "D", "E", "ISO_DESIGNATED_LONG", "ITALY_ENVELOPE", "MONARCH_ENVELOPE", "PERSONAL_ENVELOPE", "NA_NUMBER_9_ENVELOPE", "NA_NUMBER_10_ENVELOPE", "NA_NUMBER_11_ENVELOPE", "NA_NUMBER_12_ENVELOPE", "NA_NUMBER_14_ENVELOPE", "NA_6X9_ENVELOPE", "NA_7X9_ENVELOPE", "NA_9X11_ENVELOPE", "NA_9X12_ENVELOPE", "NA_10X13_ENVELOPE", "NA_10X14_ENVELOPE", "NA_10X15_ENVELOPE", "NA_5X7", "NA_8X10" }));

        lblSource.setText("Source: ");

        comboSource.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto bin" }));

        lblOrientation.setText("Orientation:");

        groupOrientation.add(radioPortrait);
        radioPortrait.setText("Portrait");

        groupOrientation.add(radioLandscape);
        radioLandscape.setText("Landscape");

        lblMargins.setText("Margins (millimeters) :");

        lblMarginLeft.setText("Left:");

        editMarginLeft.setColumns(8);

        lblMarginTop.setText("Top:");

        editMarginTop.setColumns(8);

        lblMarginRight.setText("Right:");

        editMarginRight.setColumns(8);

        lblMarginBottom.setText("Bottom:");

        editMarginBottom.setColumns(8);

        btnOk.setText("    Ok    ");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSource)
                            .addComponent(lblOrientation))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioPortrait)
                                .addGap(40, 40, 40)
                                .addComponent(radioLandscape))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboSource, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMarginTop)
                                .addGap(43, 43, 43)
                                .addComponent(editMarginTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMargins)
                                .addGap(47, 47, 47)
                                .addComponent(lblMarginLeft)
                                .addGap(43, 43, 43)
                                .addComponent(editMarginLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblMarginRight)
                                    .addGap(43, 43, 43)
                                    .addComponent(editMarginRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblMarginBottom)
                                    .addGap(43, 43, 43)
                                    .addComponent(editMarginBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(btnCancel)))))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOk)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSize)
                    .addComponent(comboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSource)
                    .addComponent(comboSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrientation)
                    .addComponent(radioPortrait)
                    .addComponent(radioLandscape))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMargins)
                    .addComponent(lblMarginLeft)
                    .addComponent(editMarginLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarginRight)
                    .addComponent(editMarginRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarginTop)
                    .addComponent(editMarginTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarginBottom)
                    .addComponent(editMarginBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> comboSize;
    private javax.swing.JComboBox<String> comboSource;
    private javax.swing.JTextField editMarginBottom;
    private javax.swing.JTextField editMarginLeft;
    private javax.swing.JTextField editMarginRight;
    private javax.swing.JTextField editMarginTop;
    private javax.swing.ButtonGroup groupOrientation;
    private javax.swing.JLabel lblMarginBottom;
    private javax.swing.JLabel lblMarginLeft;
    private javax.swing.JLabel lblMarginRight;
    private javax.swing.JLabel lblMarginTop;
    private javax.swing.JLabel lblMargins;
    private javax.swing.JLabel lblOrientation;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblSource;
    private javax.swing.JRadioButton radioLandscape;
    private javax.swing.JRadioButton radioPortrait;
    // End of variables declaration//GEN-END:variables
}
