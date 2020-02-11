/*     */ package view.editor;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HelpWindow
/*     */   extends JDialog
/*     */ {
/*     */   public HelpWindow() {
/*  43 */     setAlwaysOnTop(true);
/*  44 */     setDefaultCloseOperation(2);
/*  45 */     setTitle("BeatBuilder Help");
/*     */     
/*  47 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/*  48 */     Dimension screenSize = toolkit.getScreenSize();
/*     */     
/*  50 */     setBounds(100, 100, 610, screenSize.height - 60);
/*  51 */     GridBagLayout gridBagLayout = new GridBagLayout();
/*  52 */     gridBagLayout.columnWidths = new int[] { 434 };
/*  53 */     gridBagLayout.rowHeights = new int[] { 228, 33 };
/*  54 */     gridBagLayout.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
/*  55 */     gridBagLayout.rowWeights = new double[] { 1.0D, 0.0D, Double.MIN_VALUE };
/*  56 */     getContentPane().setLayout(gridBagLayout);
/*     */ 
/*     */     
/*  59 */     JTextArea txtrEditorGridAdd = new JTextArea();
/*  60 */     txtrEditorGridAdd.setEditable(false);
/*     */     
/*  62 */     txtrEditorGridAdd
/*  63 */       .setText("EDITOR GRID\r\n\r\n  Add Note: double click an empty cell or press the space bar or enter key with an empty cell selected\r\n                     notes are added with the  specified New Note Volume value\r\n                     (tip: to create a snare or cymbal roll, add notes from right to left for overlapping notes)\r\n\r\n  Delete Note: double click a note or select it and press the space bar or enter key\r\n\r\n  Right-click note options:\r\n\r\n    - Set note volume\r\n\r\n    - Adjust note volume\r\n\r\n\r\n  EDITOR GRID INSTRUMENTS\r\n\r\n  Left/Right-click options:\r\n\r\n    - Set volume of all notes for instrument\r\n\r\n    - Adjust volume of all notes for instrument\r\n\r\n    - Randomize volume of all notes for instrument\r\n\r\n    - Transpose notes from one instrument to another\r\n\r\n    - Remove all notes for instrument\r\n\r\n\r\nEDIT MENU\r\n\r\n    - Cut Notes: remove notes in selected range from track\r\n\r\n    - Cut Time: remove selected range from track\r\n\r\n    - Copy: copy notes in selected range for pasting\r\n\r\n    - Paste: place cut notes or notes selected for copy over track\r\n                  (tip: you can paste notes copied from one file into another)\r\n\r\n    - Paste Insert: insert cut notes or notes selected for copy, moving existing notes\r\n\r\n    - Delete Notes: remove notes in selected range from track\r\n\r\n    - Delete Time: remove selected range from track\r\n\r\n    - Insert Time: insert blank bars at insert point\r\n\r\n\r\nTOOLS MENU\r\n\r\n    - Adjust All Note Volumes: increase or decrease every note's volume\r\n\r\n    - Randomize Notes: \"humanize\" notes by shifting forward or backward by a random amount\r\n\r\n    - Quantize Notes: cleans up a messy track by shifting notes to 64th note boundaries, \r\n         randomize notes can then be used to \"humanize\" the result  \r\n\r\n    - Show Event Count: shows the total number of notes in the track\r\n\r\n");
/*  64 */     txtrEditorGridAdd.setFont(new Font("Dialog", 0, 12));
/*     */     
/*  66 */     txtrEditorGridAdd.setCaretPosition(0);
/*     */     
/*  68 */     JScrollPane scrollPane = new JScrollPane(txtrEditorGridAdd);
/*  69 */     scrollPane.setVerticalScrollBarPolicy(22);
/*  70 */     scrollPane
/*  71 */       .setHorizontalScrollBarPolicy(32);
/*  72 */     GridBagConstraints gbc_scrollPane = new GridBagConstraints();
/*  73 */     gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
/*  74 */     gbc_scrollPane.fill = 1;
/*  75 */     gbc_scrollPane.gridx = 0;
/*  76 */     gbc_scrollPane.gridy = 0;
/*  77 */     getContentPane().add(scrollPane, gbc_scrollPane);
/*     */ 
/*     */     
/*  80 */     JPanel buttonPane = new JPanel();
/*  81 */     buttonPane.setLayout(new FlowLayout(2));
/*  82 */     GridBagConstraints gbc_buttonPane = new GridBagConstraints();
/*  83 */     gbc_buttonPane.anchor = 11;
/*  84 */     gbc_buttonPane.fill = 2;
/*  85 */     gbc_buttonPane.gridx = 0;
/*  86 */     gbc_buttonPane.gridy = 1;
/*  87 */     getContentPane().add(buttonPane, gbc_buttonPane);
/*     */     
/*  89 */     JButton okButton = new JButton("OK");
/*  90 */     okButton.setActionCommand("OK");
/*  91 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  96 */             HelpWindow.this.setVisible(false);
/*  97 */             HelpWindow.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 101 */     buttonPane.add(okButton);
/* 102 */     getRootPane().setDefaultButton(okButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDialog() {
/* 111 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 112 */     Dimension screenSize = toolkit.getScreenSize();
/* 113 */     int x = screenSize.width - getWidth() + 30;
/*     */     
/* 115 */     setLocation(x, 20);
/*     */     
/* 117 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\HelpWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */