/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ public class RandomizeNoteVolume
/*     */   extends JDialog
/*     */ {
/*  24 */   private final JPanel contentPanel = new JPanel();
/*     */   
/*     */   JSpinner spinner1;
/*     */   JSpinner spinner2;
/*  28 */   Map<String, Integer> iResult = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomizeNoteVolume() {
/*  38 */     setTitle("Randomize Volume");
/*  39 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  40 */     setDefaultCloseOperation(2);
/*  41 */     setBounds(100, 100, 404, 132);
/*  42 */     getContentPane().setLayout(new BorderLayout());
/*  43 */     this.contentPanel.setLayout(new FlowLayout());
/*  44 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  45 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  47 */     JLabel lblMinimumVolume = new JLabel("Minimum Volume:");
/*  48 */     lblMinimumVolume.setHorizontalAlignment(4);
/*  49 */     this.contentPanel.add(lblMinimumVolume);
/*     */ 
/*     */     
/*  52 */     this.spinner1 = new JSpinner();
/*  53 */     this.spinner1.setModel(new SpinnerNumberModel(65, 10, 127, 1));
/*  54 */     this.contentPanel.add(this.spinner1);
/*     */ 
/*     */     
/*  57 */     JLabel lblNewLabel = new JLabel("  Maximum Volume:");
/*  58 */     lblNewLabel.setHorizontalAlignment(4);
/*  59 */     this.contentPanel.add(lblNewLabel);
/*     */ 
/*     */     
/*  62 */     this.spinner2 = new JSpinner();
/*  63 */     this.spinner2.setModel(new SpinnerNumberModel(70, 1, 127, 1));
/*  64 */     this.contentPanel.add(this.spinner2);
/*     */ 
/*     */     
/*  67 */     JPanel buttonPane = new JPanel();
/*  68 */     buttonPane.setLayout(new FlowLayout(2));
/*  69 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  71 */     JButton okButton = new JButton("OK");
/*  72 */     okButton.setActionCommand("OK");
/*  73 */     buttonPane.add(okButton);
/*  74 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  79 */             RandomizeNoteVolume.this.iResult = new HashMap<>();
/*  80 */             RandomizeNoteVolume.this.iResult.put("MIN", (Integer)RandomizeNoteVolume.this.spinner1.getValue());
/*  81 */             RandomizeNoteVolume.this.iResult.put("MAX", (Integer)RandomizeNoteVolume.this.spinner2.getValue());
/*  82 */             RandomizeNoteVolume.this.setVisible(false);
/*  83 */             RandomizeNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  87 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/*  90 */     JButton cancelButton = new JButton("Cancel");
/*  91 */     cancelButton.setActionCommand("Cancel");
/*  92 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  97 */             RandomizeNoteVolume.this.iResult = null;
/*  98 */             RandomizeNoteVolume.this.setVisible(false);
/*  99 */             RandomizeNoteVolume.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 103 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Integer> showDialog() {
/* 112 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 113 */     Dimension screenSize = toolkit.getScreenSize();
/* 114 */     int x = (screenSize.width - getWidth()) / 2;
/* 115 */     int y = (screenSize.height - getHeight()) / 2;
/* 116 */     setLocation(x, y);
/* 117 */     setVisible(true);
/* 118 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\RandomizeNoteVolume.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */