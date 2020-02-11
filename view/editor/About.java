/*     */ package view.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
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
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class About
/*     */   extends JDialog
/*     */ {
/*  27 */   private final JPanel contentPanel = new JPanel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public About() {
/*  38 */     setDefaultCloseOperation(2);
/*  39 */     setTitle("About");
/*  40 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  41 */     setBounds(100, 100, 450, 201);
/*  42 */     getContentPane().setLayout(new BorderLayout());
/*  43 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  44 */     getContentPane().add(this.contentPanel, "Center");
/*  45 */     GridBagLayout gbl_contentPanel = new GridBagLayout();
/*  46 */     gbl_contentPanel.columnWidths = new int[4];
/*  47 */     gbl_contentPanel.rowHeights = new int[] { 0, 30, 30 };
/*  48 */     gbl_contentPanel.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D };
/*  49 */     gbl_contentPanel.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  50 */     this.contentPanel.setLayout(gbl_contentPanel);
/*     */     
/*  52 */     JLabel lblBeatBuilder = new JLabel("Beat Builder");
/*  53 */     lblBeatBuilder.setFont(new Font("Tahoma", 1, 20));
/*  54 */     GridBagConstraints gbc_lblBeatBuilder = new GridBagConstraints();
/*  55 */     gbc_lblBeatBuilder.gridwidth = 4;
/*  56 */     gbc_lblBeatBuilder.insets = new Insets(0, 0, 5, 0);
/*  57 */     gbc_lblBeatBuilder.gridx = 0;
/*  58 */     gbc_lblBeatBuilder.gridy = 0;
/*  59 */     this.contentPanel.add(lblBeatBuilder, gbc_lblBeatBuilder);
/*     */ 
/*     */     
/*  62 */     JLabel lblVersionBeta = new JLabel("Version 2.1");
/*  63 */     lblVersionBeta.setFont(new Font("Tahoma", 0, 14));
/*  64 */     GridBagConstraints gbc_lblVersionBeta = new GridBagConstraints();
/*  65 */     gbc_lblVersionBeta.gridwidth = 3;
/*  66 */     gbc_lblVersionBeta.insets = new Insets(0, 0, 5, 5);
/*  67 */     gbc_lblVersionBeta.gridx = 1;
/*  68 */     gbc_lblVersionBeta.gridy = 2;
/*  69 */     this.contentPanel.add(lblVersionBeta, gbc_lblVersionBeta);
/*     */ 
/*     */     
/*  72 */     JLabel lblNewLabel = new JLabel("Written by: Charles Spencer");
/*  73 */     lblNewLabel.setFont(new Font("Tahoma", 0, 12));
/*  74 */     GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
/*  75 */     gbc_lblNewLabel.gridwidth = 4;
/*  76 */     gbc_lblNewLabel.gridx = 0;
/*  77 */     gbc_lblNewLabel.gridy = 3;
/*  78 */     this.contentPanel.add(lblNewLabel, gbc_lblNewLabel);
/*     */ 
/*     */     
/*  81 */     JPanel buttonPane = new JPanel();
/*  82 */     buttonPane.setLayout(new FlowLayout(2));
/*  83 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  85 */     JButton okButton = new JButton("OK");
/*  86 */     okButton.setActionCommand("OK");
/*  87 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  92 */             About.this.setVisible(false);
/*  93 */             About.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  97 */     buttonPane.add(okButton);
/*  98 */     getRootPane().setDefaultButton(okButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDialog() {
/* 106 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 107 */     Dimension screenSize = toolkit.getScreenSize();
/* 108 */     int x = (screenSize.width - getWidth()) / 2;
/* 109 */     int y = (screenSize.height - getHeight()) / 2;
/* 110 */     setLocation(x, y);
/* 111 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\About.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */