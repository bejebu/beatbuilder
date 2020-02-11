/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.SpinnerNumberModel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InsertBars
/*     */   extends JDialog
/*     */ {
/*  31 */   private final JPanel contentPanel = new JPanel();
/*     */   private JLabel lblBarsToInsert;
/*     */   private JSpinner spinner;
/*     */   JRadioButton optBeats;
/*     */   JRadioButton optQuarterNotes;
/*  36 */   Integer iResult = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InsertBars() {
/*  47 */     setTitle("Insert Bars");
/*  48 */     setDefaultCloseOperation(2);
/*  49 */     setResizable(false);
/*  50 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  51 */     setBounds(100, 100, 216, 164);
/*  52 */     getContentPane().setLayout(new BorderLayout());
/*  53 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  54 */     getContentPane().add(this.contentPanel, "Center");
/*  55 */     GridBagLayout gbl_contentPanel = new GridBagLayout();
/*  56 */     gbl_contentPanel.columnWidths = new int[] { 40, 29, 47 };
/*  57 */     gbl_contentPanel.rowHeights = new int[] { 20 };
/*  58 */     gbl_contentPanel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  59 */     gbl_contentPanel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  60 */     this.contentPanel.setLayout(gbl_contentPanel);
/*     */ 
/*     */     
/*  63 */     this.lblBarsToInsert = new JLabel("Insert");
/*  64 */     GridBagConstraints gbc_lblBarsToInsert = new GridBagConstraints();
/*  65 */     gbc_lblBarsToInsert.anchor = 17;
/*  66 */     gbc_lblBarsToInsert.insets = new Insets(0, 0, 5, 5);
/*  67 */     gbc_lblBarsToInsert.gridx = 1;
/*  68 */     gbc_lblBarsToInsert.gridy = 0;
/*  69 */     this.contentPanel.add(this.lblBarsToInsert, gbc_lblBarsToInsert);
/*     */ 
/*     */     
/*  72 */     this.spinner = new JSpinner();
/*  73 */     this.spinner.setModel(new SpinnerNumberModel(1, 1, 128, 1));
/*  74 */     this.lblBarsToInsert.setLabelFor(this.spinner);
/*  75 */     GridBagConstraints gbc_spinner = new GridBagConstraints();
/*  76 */     gbc_spinner.insets = new Insets(0, 0, 5, 0);
/*  77 */     gbc_spinner.anchor = 18;
/*  78 */     gbc_spinner.gridx = 2;
/*  79 */     gbc_spinner.gridy = 0;
/*  80 */     this.contentPanel.add(this.spinner, gbc_spinner);
/*     */ 
/*     */     
/*  83 */     this.optBeats = new JRadioButton("Beats");
/*  84 */     this.optBeats.setSelected(true);
/*  85 */     this.optBeats.setMnemonic('B');
/*  86 */     this.optBeats.setHorizontalAlignment(2);
/*  87 */     GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
/*  88 */     gbc_rdbtnNewRadioButton.anchor = 17;
/*  89 */     gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
/*  90 */     gbc_rdbtnNewRadioButton.gridwidth = 2;
/*  91 */     gbc_rdbtnNewRadioButton.gridx = 1;
/*  92 */     gbc_rdbtnNewRadioButton.gridy = 1;
/*  93 */     this.contentPanel.add(this.optBeats, gbc_rdbtnNewRadioButton);
/*     */ 
/*     */     
/*  96 */     this.optQuarterNotes = new JRadioButton("Quarter Notes");
/*  97 */     this.optQuarterNotes.setMnemonic('Q');
/*  98 */     this.optQuarterNotes.setHorizontalAlignment(2);
/*  99 */     GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
/* 100 */     gbc_rdbtnNewRadioButton_1.anchor = 17;
/* 101 */     gbc_rdbtnNewRadioButton_1.gridwidth = 2;
/* 102 */     gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
/* 103 */     gbc_rdbtnNewRadioButton_1.gridx = 1;
/* 104 */     gbc_rdbtnNewRadioButton_1.gridy = 2;
/* 105 */     this.contentPanel.add(this.optQuarterNotes, gbc_rdbtnNewRadioButton_1);
/*     */ 
/*     */     
/* 108 */     JPanel buttonPane = new JPanel();
/* 109 */     buttonPane.setLayout(new FlowLayout(2));
/* 110 */     getContentPane().add(buttonPane, "South");
/*     */     
/* 112 */     JButton okButton = new JButton("OK");
/* 113 */     okButton.setActionCommand("OK");
/* 114 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 119 */             InsertBars.this.iResult = (Integer)InsertBars.this.spinner.getValue();
/* 120 */             if (InsertBars.this.optQuarterNotes.isSelected())
/*     */             {
/* 122 */               InsertBars.this.iResult = Integer.valueOf(InsertBars.this.iResult.intValue() * 16);
/*     */             }
/* 124 */             InsertBars.this.setVisible(false);
/* 125 */             InsertBars.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 129 */     buttonPane.add(okButton);
/* 130 */     getRootPane().setDefaultButton(okButton);
/*     */ 
/*     */     
/* 133 */     JButton cancelButton = new JButton("Cancel");
/* 134 */     cancelButton.setActionCommand("Cancel");
/* 135 */     cancelButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 140 */             InsertBars.this.iResult = null;
/* 141 */             InsertBars.this.setVisible(false);
/* 142 */             InsertBars.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 146 */     buttonPane.add(cancelButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer showDialog() {
/* 154 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 155 */     Dimension screenSize = toolkit.getScreenSize();
/* 156 */     int x = (screenSize.width - getWidth()) / 2;
/* 157 */     int y = (screenSize.height - getHeight()) / 2;
/* 158 */     setLocation(x, y);
/* 159 */     setVisible(true);
/* 160 */     return this.iResult;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\InsertBars.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */