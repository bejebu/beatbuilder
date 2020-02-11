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
/*     */ public class AdjustNoteVolume
/*     */   extends JDialog
/*     */ {
/*  25 */   private final JPanel contentPanel = new JPanel();
/*     */   
/*  27 */   JSpinner spinner = null;
/*  28 */   Integer iResult = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AdjustNoteVolume() {
/*  38 */     setDefaultCloseOperation(2);
/*  39 */     setTitle("Adjust Note Volume");
/*  40 */     setResizable(false);
/*  41 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  42 */     setBounds(100, 100, 266, 129);
/*  43 */     getContentPane().setLayout(new BorderLayout());
/*  44 */     this.contentPanel.setLayout(new FlowLayout());
/*  45 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  46 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  48 */     JLabel lblNewLabel = new JLabel("Adjust Note Volume By:");
/*  49 */     this.contentPanel.add(lblNewLabel);
/*     */ 
/*     */     
/*  52 */     this.spinner = new JSpinner();
/*  53 */     this.spinner.setModel(new SpinnerNumberModel(0, -127, 127, 1));
/*  54 */     this.contentPanel.add(this.spinner);
/*     */ 
/*     */     
/*  57 */     JPanel buttonPane = new JPanel();
/*  58 */     buttonPane.setLayout(new FlowLayout(2));
/*  59 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  61 */     JButton okButton = new JButton("OK");
/*  62 */     okButton.setActionCommand("OK");
/*  63 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  68 */             AdjustNoteVolume.this.iResult = (Integer)AdjustNoteVolume.this.spinner.getValue();
/*  69 */             AdjustNoteVolume.this.setVisible(false);
/*  70 */             AdjustNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  74 */     buttonPane.add(okButton);
/*  75 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/*  78 */     JButton cancelButton = new JButton("Cancel");
/*  79 */     cancelButton.setActionCommand("Cancel");
/*  80 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  85 */             AdjustNoteVolume.this.iResult = null;
/*  86 */             AdjustNoteVolume.this.setVisible(false);
/*  87 */             AdjustNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  91 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog() {
/* 100 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 101 */     Dimension screenSize = toolkit.getScreenSize();
/* 102 */     int x = (screenSize.width - getWidth()) / 2;
/* 103 */     int y = (screenSize.height - getHeight()) / 2;
/* 104 */     setLocation(x, y);
/*     */     
/* 106 */     setVisible(true);
/* 107 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\AdjustNoteVolume.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */