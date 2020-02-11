/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomizeNotes
/*     */   extends JDialog
/*     */ {
/*  25 */   private final JPanel contentPanel = new JPanel();
/*  26 */   Integer iResult = null;
/*  27 */   JSpinner spinner = null;
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
/*     */   public RandomizeNotes() {
/*  39 */     setTitle("Randomize Notes");
/*  40 */     setResizable(false);
/*  41 */     setDefaultCloseOperation(2);
/*  42 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  43 */     setBounds(100, 100, 299, 119);
/*  44 */     getContentPane().setLayout(new BorderLayout());
/*  45 */     this.contentPanel.setLayout(new FlowLayout());
/*  46 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  47 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  49 */     JLabel lblRandomizationFactor = new JLabel("Randomization Factor:");
/*  50 */     this.contentPanel.add(lblRandomizationFactor);
/*     */ 
/*     */     
/*  53 */     this.spinner = new JSpinner();
/*  54 */     this.spinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
/*  55 */     this.contentPanel.add(this.spinner);
/*     */ 
/*     */     
/*  58 */     JPanel buttonPane = new JPanel();
/*  59 */     buttonPane.setLayout(new FlowLayout(2));
/*  60 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  62 */     JButton okButton = new JButton("OK");
/*  63 */     okButton.setActionCommand("OK");
/*  64 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  69 */             RandomizeNotes.this.iResult = (Integer)RandomizeNotes.this.spinner.getValue();
/*  70 */             RandomizeNotes.this.setVisible(false);
/*  71 */             RandomizeNotes.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  75 */     buttonPane.add(okButton);
/*  76 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/*  79 */     JButton cancelButton = new JButton("Cancel");
/*  80 */     cancelButton.setActionCommand("Cancel");
/*  81 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  86 */             RandomizeNotes.this.iResult = null;
/*  87 */             RandomizeNotes.this.setVisible(false);
/*  88 */             RandomizeNotes.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  92 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog() {
/* 101 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 102 */     Dimension screenSize = toolkit.getScreenSize();
/* 103 */     int x = (screenSize.width - getWidth()) / 2;
/* 104 */     int y = (screenSize.height - getHeight()) / 2;
/* 105 */     setLocation(x, y);
/* 106 */     setVisible(true);
/* 107 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\RandomizeNotes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */