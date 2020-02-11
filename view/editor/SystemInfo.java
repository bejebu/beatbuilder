/*     */ package view.editor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.sound.midi.MidiDevice;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Soundbank;
/*     */ import javax.sound.midi.Synthesizer;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemInfo
/*     */   extends JDialog
/*     */ {
/*  29 */   private final JPanel contentPanel = new JPanel();
/*  30 */   JTextArea textInfo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SystemInfo() {
/*  41 */     setTitle("Audio System Information");
/*  42 */     setResizable(false);
/*  43 */     setModal(true);
/*  44 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  45 */     setDefaultCloseOperation(2);
/*  46 */     setBounds(100, 100, 450, 369);
/*  47 */     getContentPane().setLayout(new BorderLayout());
/*  48 */     this.contentPanel.setLayout(new FlowLayout());
/*  49 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  50 */     getContentPane().add(this.contentPanel, "Center");
/*     */     
/*  52 */     this.textInfo = new JTextArea();
/*  53 */     this.textInfo.setRows(17);
/*  54 */     this.textInfo.setColumns(38);
/*  55 */     this.textInfo.setEditable(false);
/*  56 */     this.contentPanel.add(this.textInfo);
/*     */ 
/*     */     
/*  59 */     JPanel buttonPane = new JPanel();
/*  60 */     buttonPane.setLayout(new FlowLayout(2));
/*  61 */     getContentPane().add(buttonPane, "South");
/*     */     
/*  63 */     JButton okButton = new JButton("Close");
/*  64 */     okButton.setMnemonic('c');
/*  65 */     okButton.setActionCommand("OK");
/*  66 */     buttonPane.add(okButton);
/*  67 */     okButton.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  72 */             SystemInfo.this.setVisible(false);
/*  73 */             SystemInfo.this.dispose();
/*     */           }
/*     */         });
/*     */     
/*  77 */     getRootPane().setDefaultButton(okButton);
/*     */     
/*  79 */     String strInfo = "";
/*     */     
/*     */     try {
/*  82 */       Synthesizer synth = MidiSystem.getSynthesizer();
/*  83 */       Soundbank sb = synth.getDefaultSoundbank();
/*  84 */       MidiDevice.Info info = synth.getDeviceInfo();
/*     */       
/*  86 */       strInfo = String.valueOf(strInfo) + "Synthesizer\n\n";
/*  87 */       strInfo = String.valueOf(strInfo) + info.getName() + "\n";
/*  88 */       strInfo = String.valueOf(strInfo) + info.getDescription() + "\n";
/*  89 */       strInfo = String.valueOf(strInfo) + info.getVendor() + "\n";
/*  90 */       strInfo = String.valueOf(strInfo) + info.getVersion() + "\n\n\n";
/*     */       
/*  92 */       strInfo = String.valueOf(strInfo) + "Synthesizer Soundbank\n\n";
/*  93 */       strInfo = String.valueOf(strInfo) + sb.getName() + "\n";
/*  94 */       strInfo = String.valueOf(strInfo) + sb.getDescription() + "\n";
/*  95 */       strInfo = String.valueOf(strInfo) + sb.getVendor() + "\n";
/*     */       
/*  97 */       strInfo = String.valueOf(strInfo) + sb.getClass().getName() + "\n";
/*     */     }
/*  99 */     catch (MidiUnavailableException e) {
/*     */ 
/*     */       
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 105 */     this.textInfo.setText(strInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDialog() {
/* 112 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/* 113 */     Dimension screenSize = toolkit.getScreenSize();
/* 114 */     int x = (screenSize.width - getWidth()) / 2;
/* 115 */     int y = (screenSize.height - getHeight()) / 2;
/* 116 */     setLocation(x, y);
/* 117 */     setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\SystemInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */