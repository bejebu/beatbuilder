/*     */ package view.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
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
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TipWindow
/*     */   extends JDialog
/*     */ {
/*  29 */   private final JPanel contentPanel = new JPanel();
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
/*     */   public TipWindow() {
/*  42 */     setResizable(false);
/*  43 */     setAlwaysOnTop(true);
/*  44 */     setTitle("BeatBuilder");
/*  45 */     setDefaultCloseOperation(2);
/*  46 */     setBounds(100, 100, 486, 350);
/*  47 */     getContentPane().setLayout(new BorderLayout());
/*  48 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  49 */     getContentPane().add(this.contentPanel, "Center");
/*  50 */     GridBagLayout gbl_contentPanel = new GridBagLayout();
/*  51 */     gbl_contentPanel.columnWidths = new int[2];
/*  52 */     gbl_contentPanel.rowHeights = new int[3];
/*  53 */     gbl_contentPanel.columnWeights = new double[] { 1.0D, Double.MIN_VALUE };
/*  54 */     gbl_contentPanel.rowWeights = new double[] { 1.0D, 0.0D, Double.MIN_VALUE };
/*  55 */     this.contentPanel.setLayout(gbl_contentPanel);
/*     */     
/*  57 */     JTextPane txtpnWelcomeToBeatbuilder = new JTextPane();
/*  58 */     txtpnWelcomeToBeatbuilder.setText("Welcome to BeatBuilder\r\n\r\nBeatBuilder is a drum track creator and editor designed for use with the BeatBuddy drum pedal and the BeatBuddy Manager software which is used to create custom drum tracks.\r\n\r\nBeatbuilder includes functions that assist in the creation of drum tracks like bulk volume adjustments for one or all instruments, automatic \"humanizing\" of drum patterns through randomization of volumes and dequantization of notes, and standard editor functions: cut, copy, paste, delete, and insert.    \r\n\r\nBeatbuilder assists with the preparation of MIDI format files for use by the BeatBuddy pedal by remapping drum instruments to the subset of instruments included in the standard BeatBuddy kits. \r\n");
/*  59 */     txtpnWelcomeToBeatbuilder.setFont(new Font("Tahoma", 0, 12));
/*  60 */     txtpnWelcomeToBeatbuilder.setEditable(false);
/*  61 */     GridBagConstraints gbc_txtpnWelcomeToBeatbuilder = new GridBagConstraints();
/*  62 */     gbc_txtpnWelcomeToBeatbuilder.insets = new Insets(0, 0, 5, 0);
/*  63 */     gbc_txtpnWelcomeToBeatbuilder.fill = 1;
/*  64 */     gbc_txtpnWelcomeToBeatbuilder.gridx = 0;
/*  65 */     gbc_txtpnWelcomeToBeatbuilder.gridy = 0;
/*  66 */     this.contentPanel.add(txtpnWelcomeToBeatbuilder, gbc_txtpnWelcomeToBeatbuilder);
/*     */ 
/*     */     
/*  69 */     JButton btnNewButton = new JButton("Open Documentation...");
/*  70 */     btnNewButton.setMnemonic('O');
/*  71 */     btnNewButton.setHorizontalAlignment(2);
/*  72 */     btnNewButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  77 */             HelpWindow dlgHelp = new HelpWindow();
/*  78 */             dlgHelp.showDialog();
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/*  84 */     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
/*  85 */     gbc_btnNewButton.gridx = 0;
/*  86 */     gbc_btnNewButton.gridy = 1;
/*  87 */     this.contentPanel.add(btnNewButton, gbc_btnNewButton);
/*     */ 
/*     */     
/*  90 */     JPanel buttonPane = new JPanel();
/*  91 */     buttonPane.setLayout(new FlowLayout(2));
/*  92 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  94 */     JButton okButton = new JButton("Close");
/*  95 */     okButton.setMnemonic('c');
/*  96 */     okButton.setActionCommand("OK");
/*  97 */     buttonPane.add(okButton);
/*  98 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 103 */             TipWindow.this.setVisible(false);
/* 104 */             TipWindow.this.dispose();
/*     */           }
/*     */         });
/*     */     
/* 108 */     getRootPane().setDefaultButton(okButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDialog() {
/* 116 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 117 */     Dimension screenSize = toolkit.getScreenSize();
/* 118 */     int x = (screenSize.width - getWidth()) / 2;
/* 119 */     int y = (screenSize.height - getHeight()) / 2;
/* 120 */     setLocation(x, y);
/* 121 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\TipWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */