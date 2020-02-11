/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import model.Instrument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Transpose
/*     */   extends JDialog
/*     */ {
/*  27 */   private final JPanel contentPanel = new JPanel();
/*     */   JComboBox<String> comboBox;
/*     */   private JLabel lblMoveNotesTo;
/*  30 */   Integer iResult = null;
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
/*     */   public Transpose() {
/*  42 */     setResizable(false);
/*  43 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  44 */     setDefaultCloseOperation(2);
/*  45 */     setTitle("Transpose");
/*  46 */     setBounds(100, 100, 395, 150);
/*  47 */     getContentPane().setLayout(new BorderLayout());
/*  48 */     this.contentPanel.setLayout(new FlowLayout());
/*  49 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  50 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  52 */     this.lblMoveNotesTo = new JLabel("Move Notes To:");
/*  53 */     this.contentPanel.add(this.lblMoveNotesTo);
/*     */ 
/*     */     
/*  56 */     this.comboBox = new JComboBox<>();
/*  57 */     this.lblMoveNotesTo.setLabelFor(this.comboBox);
/*  58 */     this.contentPanel.add(this.comboBox);
/*     */ 
/*     */     
/*  61 */     JPanel buttonPane = new JPanel();
/*  62 */     buttonPane.setLayout(new FlowLayout(2));
/*  63 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  65 */     JButton okButton = new JButton("OK");
/*  66 */     okButton.setActionCommand("OK");
/*  67 */     buttonPane.add(okButton);
/*  68 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  73 */             Transpose.this.iResult = Integer.valueOf(Transpose.this.comboBox.getSelectedIndex());
/*     */             
/*  75 */             Transpose.this.setVisible(false);
/*     */           }
/*     */         });
/*     */     
/*  79 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/*  82 */     JButton cancelButton = new JButton("Cancel");
/*  83 */     cancelButton.setActionCommand("Cancel");
/*  84 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  89 */             Transpose.this.iResult = null;
/*  90 */             Transpose.this.setVisible(false);
/*     */           }
/*     */         });
/*     */     
/*  94 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog(List<Instrument> instruments) {
/* 103 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 104 */     Dimension screenSize = toolkit.getScreenSize();
/* 105 */     int x = (screenSize.width - getWidth()) / 2;
/* 106 */     int y = (screenSize.height - getHeight()) / 2;
/* 107 */     setLocation(x, y);
/* 108 */     for (Instrument instr : instruments)
/*     */     {
/* 110 */       this.comboBox.addItem(instr.getName());
/*     */     }
/* 112 */     setVisible(true);
/*     */     
/* 114 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\Transpose.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */