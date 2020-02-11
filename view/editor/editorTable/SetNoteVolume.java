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
/*     */ public class SetNoteVolume
/*     */   extends JDialog
/*     */ {
/*  25 */   private final JPanel contentPanel = new JPanel();
/*     */   
/*     */   private JLabel lblNewLabel;
/*  28 */   JSpinner spinner = null;
/*  29 */   Integer iResult = null;
/*  30 */   int m_iInitialVolume = 70;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SetNoteVolume() {
/*  39 */     setDefaultCloseOperation(2);
/*  40 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  41 */     setResizable(false);
/*  42 */     setTitle("Set Note Volume");
/*  43 */     setBounds(100, 100, 214, 131);
/*  44 */     getContentPane().setLayout(new BorderLayout());
/*  45 */     this.contentPanel.setLayout(new FlowLayout());
/*  46 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  47 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  49 */     this.lblNewLabel = new JLabel("Note Volume:");
/*  50 */     this.contentPanel.add(this.lblNewLabel);
/*     */ 
/*     */     
/*  53 */     this.spinner = new JSpinner();
/*  54 */     this.spinner.setModel(new SpinnerNumberModel(this.m_iInitialVolume, 1, 127, 1));
/*  55 */     this.lblNewLabel.setLabelFor(this.spinner);
/*  56 */     this.contentPanel.add(this.spinner);
/*     */ 
/*     */     
/*  59 */     JPanel buttonPane = new JPanel();
/*  60 */     buttonPane.setLayout(new FlowLayout(2));
/*  61 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  63 */     JButton okButton = new JButton("OK");
/*  64 */     okButton.setActionCommand("OK");
/*  65 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  70 */             SetNoteVolume.this.iResult = (Integer)SetNoteVolume.this.spinner.getValue();
/*  71 */             SetNoteVolume.this.setVisible(false);
/*  72 */             SetNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  76 */     buttonPane.add(okButton);
/*  77 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/*  80 */     JButton cancelButton = new JButton("Cancel");
/*  81 */     cancelButton.setActionCommand("Cancel");
/*  82 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  87 */             SetNoteVolume.this.iResult = null;
/*  88 */             SetNoteVolume.this.setVisible(false);
/*  89 */             SetNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  93 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog() {
/* 102 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 103 */     Dimension screenSize = toolkit.getScreenSize();
/* 104 */     int x = (screenSize.width - getWidth()) / 2;
/* 105 */     int y = (screenSize.height - getHeight()) / 2;
/* 106 */     setLocation(x, y);
/* 107 */     setVisible(true);
/* 108 */     return this.iResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog(int p_CurrentVolume) {
/* 115 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 116 */     Dimension screenSize = toolkit.getScreenSize();
/* 117 */     int x = (screenSize.width - getWidth()) / 2;
/* 118 */     int y = (screenSize.height - getHeight()) / 2;
/* 119 */     setLocation(x, y);
/* 120 */     this.m_iInitialVolume = p_CurrentVolume;
/* 121 */     this.spinner.setValue(Integer.valueOf(this.m_iInitialVolume));
/* 122 */     setVisible(true);
/* 123 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\SetNoteVolume.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */