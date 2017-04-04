/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingnotepad;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.HashPrintJobAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 *
 * @author edutilos
 */
public class NotepadGUI extends javax.swing.JFrame {

    
        private File file; 
    private PageSetupGUI pageSetup = null; 
    private UndoManager undoManager ;
    private JFontChooser fontChooser = new JFontChooser(); 
   // private boolean isCutOrPaste = false ; 
    
    
    /**
     * Creates new form NotepadGUI
     */
    public NotepadGUI() {
        configureWindow(); 
        initComponents();
        registerEvents(); 
    }
    
    
    
    private void configureWindow() {
        this.setTitle("Untitled - Notepad");
        undoManager = new UndoManager(); 
      
    }
    
       private List<String> getDisplayContent()  {
        String content = areaDisplay.getText(); 
          List<String> lines = new ArrayList<>(); 
        String[] splitted = content.split("\n"); 
        for(String str: splitted) lines.add(str); 
        return lines ; 
    }
    
    private boolean handleFileExists(File file) {
         if(Files.exists(Paths.get(file.getAbsolutePath()))) {
             Object[] options = {"Yes", "No"}; 
             String fileName = file.getName(); 
             String title = "File Already exists"; 
             String msg = fileName + " exists. Do you want to overwrite it?"; 
             
             int selection = JOptionPane.showOptionDialog(this, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
             if(selection == JOptionPane.YES_OPTION) return true ; 
             else if(selection == JOptionPane.NO_OPTION) return false ; 
            }
         
         return true ; 
    }
    
     
   private void updateUndoRedoItems() {
       itemUndo.setEnabled(undoManager.canUndo());
       itemRedo.setEnabled(undoManager.canRedo());
   }
   
   
   
    private void registerEvents() {
          itemCopy.setEnabled(false);
        itemCut.setEnabled(false);
        itemPaste.setEnabled(false); 
        itemUndo.setEnabled(false); 
        itemRedo.setEnabled(false); 
        
        areaDisplay.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK), "none");
        areaDisplay.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK), "none");
        areaDisplay.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "none");
        
        itemNew.addActionListener(l-> {
            onItemNew(); 
        });
        
        
        itemOpen.addActionListener(l-> {
            onItemOpen();
        }); 
        
        
        itemSave.addActionListener(l-> {
           onItemSave(); 
        }); 
        
        itemSaveAs.addActionListener(l-> {
            onItemSaveAs();
        }); 
        
        
        itemPageSetup.addActionListener(l-> {
            onItemPageSetup();
        });
        
        itemPrint.addActionListener(l-> {
            onItemPrint();
        });
        
        itemExit.addActionListener(l-> {
            onItemExit();
        }); 
        
        
        //areaDisplay.getDocument().removeUndoableEditListener(undoManager);
        areaDisplay.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
           undoManager.addEdit(e.getEdit()); 
           updateUndoRedoItems();
        });
        
        areaDisplay.addCaretListener(l-> {
            //System.out.println("CaretListener invoked.");
            int dot = l.getDot(); 
            int mark = l.getMark(); 
            if(dot == mark) {
                itemCopy.setEnabled(false);
                itemCut.setEnabled(false);
                itemDelete.setEnabled(false); 
            } else {
                itemCopy.setEnabled(true); 
                itemCut.setEnabled(true); 
                itemDelete.setEnabled(true); 
            }
            
            int col = RXTextUtilities.getColumnAtCaret(areaDisplay); 
            int line  = RXTextUtilities.getLineAtCaret(areaDisplay); 
            fieldStatusBar.setText("Status Bar: line = "+ line + ", col = "+ col); 
        });
        
        
        
        
        
        itemUndo.addActionListener(l-> {
            onItemUndo();
        });
        
        itemRedo.addActionListener(l-> {
            onItemRedo(); 
        }); 
        
        
        itemCut.addActionListener(l-> {
            onItemCut();
        });
        
        itemCopy.addActionListener(l-> {
            onItemCopy();
        });
        
        itemPaste.addActionListener(l-> {
            onItemPaste();
        });
        
        
        itemDelete.addActionListener(l-> {
            onItemDelete();
        });

        
        itemFind.addActionListener(l-> {
            onItemFind();
        });
        
        itemReplace.addActionListener(l-> {
            onItemReplace();
        }); 
        
        itemGoTo.addActionListener(l-> {
            onItemGoto(); 
        }); 
        
        
        itemSelectAll.addActionListener(l-> {
            onItemSelectAll();
        }); 
        
        
        
        itemTimeDate.addActionListener(l-> {
              onItemTimeDate();
        }); 
        
        
        
        itemWordWrap.addChangeListener(l-> {
            System.out.println("inside itemWordWrap = " + itemWordWrap.isSelected());
            if(itemWordWrap.isSelected()) {
                areaDisplay.setWrapStyleWord(true);
            } else {
                areaDisplay.setWrapStyleWord(false);
            }
        });
        
        
        
        itemFont.addActionListener(l-> {
             onItemFont();
        }); 
        
        itemStatusBar.addChangeListener(l-> {
            if(itemStatusBar.isSelected()) {
                fieldStatusBar.setVisible(true);
            } else  {
                fieldStatusBar.setVisible(false);
            }
        });
        
        itemViewHelp.addActionListener(l-> {
            onItemViewHelp();
        }); 
        
        itemAbout.addActionListener(l-> {
          onItemAbout();
        });
    }

    
    private void onItemAbout() {
          AboutGUI about =new AboutGUI(); 
            about.setVisible(true); 
    }

   private void onItemViewHelp() {
       HelpGUI help = new HelpGUI(); 
       help.setVisible(true);
   } 
    
   private void onItemFont() {
       
       int selection = fontChooser.showDialog(this); 
      if(selection == JOptionPane.OK_OPTION) {
          Font font = fontChooser.getSelectedFont(); 
          areaDisplay.setFont(font);
      } else {
          System.err.println("error occurred in JFontChooser#showDialog()");
      }
   }    
    
   private void onItemTimeDate() {
       LocalDateTime now = LocalDateTime.now();
       //18:09 04/04/2017
       int hour = now.getHour(), 
       min = now.getMinute(), 
       day = now.getDayOfMonth(), 
       month = now.getMonthValue(), 
       year = now.getYear(); 
       StringBuilder builder = new StringBuilder(); 
       builder.append(" ")
               .append(String.valueOf(hour))
               .append(":")
               .append(String.valueOf(min))
               .append(" ")
               .append(String.valueOf(day))
               .append("/")
               .append(String.valueOf(month))
               .append("/")
               .append(String.valueOf(year))
               .append(" ")
               ; 
       String tdStr = builder.toString(); 
       int pos = areaDisplay.getCaretPosition(); 
       areaDisplay.insert(tdStr, pos);
   }
   
    private void onItemSelectAll() {
        areaDisplay.setSelectionStart(0); 
        //areaDisplay.setSelectionEnd()
    }
    
    
    
    private void onItemGoto() {
        GotoGUI gotoGUI = new GotoGUI(areaDisplay); 
        gotoGUI.setVisible(true);
    }
   
    private void onItemReplace() {
        ReplaceGUI replace = new ReplaceGUI(areaDisplay); 
        replace.setVisible(true);
    }
   
   private void onItemFind() {
       FindGUI find = new FindGUI(areaDisplay); 
       find.setVisible(true);
   }
   
   
   private void onItemDelete() {
       int start = areaDisplay.getSelectionStart(); 
       int end = areaDisplay.getSelectionEnd(); 
       String str =  areaDisplay.getText(); 
       String temp = str.substring(0, start) + str.substring(end, str.length()); 
       areaDisplay.setText(temp);
   }
   
   
   private void onItemCut() {
       areaDisplay.cut();
       itemPaste.setEnabled(true); 
   }
   
   private void onItemCopy() {
       areaDisplay.copy();
       itemPaste.setEnabled(true);
   }
   
   private void onItemPaste() {
       System.out.println("itemPaste");
       if(!itemPaste.isEnabled())return ; 
       areaDisplay.paste();
       //itemPaste.setEnabled(false); 
   }
   
   
   private void onItemUndo() {
       undoManager.undo();
       updateUndoRedoItems();
   }
   
   private void onItemRedo() {
       undoManager.redo(); 
       updateUndoRedoItems();
   }
    
    
    private void onItemExit() {
        this.setVisible(false);
    }
    
    private void onItemPrint() {
        try {
            System.out.println(pageSetup.isPageSetupCancelled());
            if(pageSetup.isPageSetupCancelled()) {
                  Printable printable = areaDisplay.getPrintable(new MessageFormat("Print demo header"), new MessageFormat("footer")); 
            PrinterJob pjob = PrinterJob.getPrinterJob(); 
            pjob.setPrintable(printable);
            if(pjob.printDialog()) {
                try {
                    pjob.print();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            } else {
                 double marginLeft = pageSetup.getMarginLeft(), 
                marginRight = pageSetup.getMarginRight(), 
                marginTop = pageSetup.getMarginTop(), 
                marginBottom = pageSetup.getMarginBottom(); 
        
        OrientationRequested orientation = pageSetup.getOrientation(); 
        MediaSizeName mediaSize = pageSetup.getMediaSize(); 
        //StringBuilder builder = new StringBuilder();
        
                StringBuilder builder = new StringBuilder(); 
        builder.append("orientation = ").append(orientation.getName()).append("\n")
                .append("mediaSize = ").append(mediaSize.getName()).append("\n")
                .append("[l, r, t, b] = [").append(marginLeft+"").append(", ")
                .append(marginRight+"").append(", ")
                .append(marginTop+", ")
                .append(marginBottom+"]"); 
        System.out.println(builder.toString());
        
            HashPrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet(); 
            attrs.add(orientation); 
            attrs.add(mediaSize); 
            MediaSize ms = MediaSize.getMediaSizeForName(mediaSize); 
            float  mediaWidth = ms.getX(Size2DSyntax.MM); 
            float mediaHeight = ms.getY(Size2DSyntax.MM); 
            MediaPrintableArea printableArea = new MediaPrintableArea((float)marginLeft, (float)marginTop, (float)(mediaWidth - marginLeft - marginRight)
                   , (float)(mediaHeight- marginTop- marginBottom), Size2DSyntax.MM); 
            attrs.add(printableArea); 
            Printable printable = areaDisplay.getPrintable(new MessageFormat("Print demo header"), new MessageFormat("footer")); 
            PrinterJob pjob = PrinterJob.getPrinterJob(); 
            pjob.setPrintable(printable);
            if(pjob.printDialog(attrs)) {
                try {
                    pjob.print(attrs);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            
            }
         } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }   
    }
   
    private void onItemPageSetup() {
        pageSetup = new PageSetupGUI(); 
        pageSetup.setVisible(true);
        double marginLeft = pageSetup.getMarginLeft(), 
                marginRight = pageSetup.getMarginRight(), 
                marginTop = pageSetup.getMarginTop(), 
                marginBottom = pageSetup.getMarginBottom(); 
        
        OrientationRequested orientation = pageSetup.getOrientation(); 
        MediaSizeName mediaSize = pageSetup.getMediaSize(); 
//        StringBuilder builder = new StringBuilder(); 
//        builder.append("orientation = ").append(orientation.getName()).append("\n")
//                .append("mediaSize = ").append(mediaSize.getName()).append("\n")
//                .append("[l, r, t, b] = [").append(marginLeft+"").append(", ")
//                .append(marginRight+"").append(", ")
//                .append(marginTop+", ")
//                .append(marginBottom+"]"); 
//        System.out.println(builder.toString());
    }
    
    private void onItemSaveAs() {
        JFileChooser fileChooser = new JFileChooser(); 
        int selection = fileChooser.showSaveDialog(this); 
        if(selection == JFileChooser.APPROVE_OPTION) {
            try {
            File file2 = fileChooser.getSelectedFile(); 
            boolean handleExists = handleFileExists(file2); 
            if(!handleExists) return ; 
            file = file2 ; 
            Files.write(Paths.get(file.getAbsolutePath()), getDisplayContent()); 
            this.setTitle(file.getName()); 
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            
        }
    }
    
    
    private void onItemSave() {
        try { 
        Files.write(Paths.get(file.getAbsolutePath()), getDisplayContent()); 
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
     }
    
    
    
    private void onItemOpen() {
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setDialogTitle("Open Existing File"); 
        int selection = fileChooser.showOpenDialog(this); 
        if(selection == JFileChooser.APPROVE_OPTION) {
            try {
            file = fileChooser.getSelectedFile(); 
            this.setTitle(file.getName()); 
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath())); 
              StringBuilder  builder = new StringBuilder()  ; 
              for(String line : lines) builder.append(line).append("\n"); 
              areaDisplay.setText(builder.toString()); 
            } catch(IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            
        }
    }

     private void onItemNew() {
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setDialogTitle("Create New File");
        int selection = fileChooser.showSaveDialog(this); 
        if(selection == JFileChooser.APPROVE_OPTION) {
            try {
            File file2 =  fileChooser.getSelectedFile(); 
            boolean handleExists = handleFileExists(file2); 
            if(!handleExists) return ; 
            file = file2 ; 
            Files.write(Paths.get(file.getAbsolutePath()), new ArrayList<String>()); //, Charset.defaultCharset() , StandardOpenOption.WRITE); 
           this.setTitle(file.getName()); 
            areaDisplay.setText("");
            } catch(IOException  ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        areaDisplay = new javax.swing.JTextArea();
        fieldStatusBar = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemNew = new javax.swing.JMenuItem();
        itemOpen = new javax.swing.JMenuItem();
        itemSave = new javax.swing.JMenuItem();
        itemSaveAs = new javax.swing.JMenuItem();
        itemPageSetup = new javax.swing.JMenuItem();
        itemPrint = new javax.swing.JMenuItem();
        itemExit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        itemUndo = new javax.swing.JMenuItem();
        itemRedo = new javax.swing.JMenuItem();
        itemCut = new javax.swing.JMenuItem();
        itemCopy = new javax.swing.JMenuItem();
        itemPaste = new javax.swing.JMenuItem();
        itemDelete = new javax.swing.JMenuItem();
        itemFind = new javax.swing.JMenuItem();
        itemFindNext = new javax.swing.JMenuItem();
        itemReplace = new javax.swing.JMenuItem();
        itemGoTo = new javax.swing.JMenuItem();
        itemSelectAll = new javax.swing.JMenuItem();
        itemTimeDate = new javax.swing.JMenuItem();
        menuFormat = new javax.swing.JMenu();
        itemWordWrap = new javax.swing.JCheckBoxMenuItem();
        itemFont = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        itemStatusBar = new javax.swing.JCheckBoxMenuItem();
        menuHelp = new javax.swing.JMenu();
        itemViewHelp = new javax.swing.JMenuItem();
        itemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaDisplay.setColumns(80);
        areaDisplay.setRows(40);
        jScrollPane2.setViewportView(areaDisplay);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        fieldStatusBar.setText("Status Bar:");
        fieldStatusBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldStatusBarActionPerformed(evt);
            }
        });
        getContentPane().add(fieldStatusBar, java.awt.BorderLayout.PAGE_END);

        menuFile.setText("File");

        itemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNew.setText("New");
        menuFile.add(itemNew);

        itemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itemOpen.setText("Open...");
        itemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOpenActionPerformed(evt);
            }
        });
        menuFile.add(itemOpen);

        itemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itemSave.setText("Save");
        menuFile.add(itemSave);

        itemSaveAs.setText("Save As...");
        itemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(itemSaveAs);

        itemPageSetup.setText("Page Setup...");
        menuFile.add(itemPageSetup);

        itemPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        itemPrint.setText("Print...");
        itemPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrintActionPerformed(evt);
            }
        });
        menuFile.add(itemPrint);

        itemExit.setText("Exit");
        menuFile.add(itemExit);

        jMenuBar1.add(menuFile);

        menuEdit.setText("Edit");

        itemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        itemUndo.setText("Undo");
        menuEdit.add(itemUndo);

        itemRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        itemRedo.setText("Redo");
        menuEdit.add(itemRedo);

        itemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itemCut.setText("Cut");
        menuEdit.add(itemCut);

        itemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemCopy.setText("Copy");
        menuEdit.add(itemCopy);

        itemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        itemPaste.setText("Paste");
        menuEdit.add(itemPaste);

        itemDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemDelete.setText("Delete");
        menuEdit.add(itemDelete);

        itemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        itemFind.setText("Find...");
        menuEdit.add(itemFind);

        itemFindNext.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemFindNext.setText("Find Next");
        menuEdit.add(itemFindNext);

        itemReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        itemReplace.setText("Replace...");
        menuEdit.add(itemReplace);

        itemGoTo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        itemGoTo.setText("Go To...");
        menuEdit.add(itemGoTo);

        itemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemSelectAll.setText("Select All");
        menuEdit.add(itemSelectAll);

        itemTimeDate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemTimeDate.setText("Time/Date");
        menuEdit.add(itemTimeDate);

        jMenuBar1.add(menuEdit);

        menuFormat.setText("Format");

        itemWordWrap.setSelected(true);
        itemWordWrap.setText("Word Wrap");
        menuFormat.add(itemWordWrap);

        itemFont.setText("Font...");
        menuFormat.add(itemFont);

        jMenuBar1.add(menuFormat);

        menuView.setText("View");

        itemStatusBar.setSelected(true);
        itemStatusBar.setText("Status Bar");
        menuView.add(itemStatusBar);

        jMenuBar1.add(menuView);

        menuHelp.setText("Help");

        itemViewHelp.setText("View Help");
        menuHelp.add(itemViewHelp);

        itemAbout.setText("About Notepad");
        menuHelp.add(itemAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOpenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemOpenActionPerformed

    private void itemPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemPrintActionPerformed

    private void itemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveAsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemSaveAsActionPerformed

    private void fieldStatusBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldStatusBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldStatusBarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDisplay;
    private javax.swing.JTextField fieldStatusBar;
    private javax.swing.JMenuItem itemAbout;
    private javax.swing.JMenuItem itemCopy;
    private javax.swing.JMenuItem itemCut;
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JMenuItem itemExit;
    private javax.swing.JMenuItem itemFind;
    private javax.swing.JMenuItem itemFindNext;
    private javax.swing.JMenuItem itemFont;
    private javax.swing.JMenuItem itemGoTo;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenuItem itemOpen;
    private javax.swing.JMenuItem itemPageSetup;
    private javax.swing.JMenuItem itemPaste;
    private javax.swing.JMenuItem itemPrint;
    private javax.swing.JMenuItem itemRedo;
    private javax.swing.JMenuItem itemReplace;
    private javax.swing.JMenuItem itemSave;
    private javax.swing.JMenuItem itemSaveAs;
    private javax.swing.JMenuItem itemSelectAll;
    private javax.swing.JCheckBoxMenuItem itemStatusBar;
    private javax.swing.JMenuItem itemTimeDate;
    private javax.swing.JMenuItem itemUndo;
    private javax.swing.JMenuItem itemViewHelp;
    private javax.swing.JCheckBoxMenuItem itemWordWrap;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuFormat;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuView;
    // End of variables declaration//GEN-END:variables
}
