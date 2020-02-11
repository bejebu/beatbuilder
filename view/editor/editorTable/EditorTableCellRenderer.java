/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ import model.DrumTrack;
/*     */ import model.EditorTableModel;
/*     */ import model.Instrument;
/*     */ import tools.InstrumentColor;
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
/*     */ public class EditorTableCellRenderer
/*     */   extends DefaultTableCellRenderer
/*     */ {
/*  33 */   private Font m_fVelocityFont = null;
/*  34 */   private EditorTableModel m_Model = null;
/*  35 */   private Border m_bFocused = null;
/*  36 */   private Border m_bHeavy = null;
/*  37 */   private Border m_bHeavyL = null;
/*  38 */   private Border m_bMedium = null;
/*  39 */   private Border m_bLight = null;
/*  40 */   private Color m_cBandRowColor = null;
/*  41 */   private Color m_cSelectedColor = null;
/*  42 */   private DrumTrack m_DrumTrack = null;
/*     */ 
/*     */   
/*     */   public EditorTableCellRenderer(EditorTableModel p_Model, DrumTrack p_Track) {
/*  46 */     this.m_Model = p_Model;
/*  47 */     Font defaultFont = (new JLabel()).getFont();
/*     */     
/*  49 */     this.m_fVelocityFont = new Font(defaultFont.getName(), 0, 9);
/*  50 */     setFont(this.m_fVelocityFont);
/*     */     
/*  52 */     setHorizontalAlignment(4);
/*     */     
/*  54 */     this.m_bFocused = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
/*  55 */         BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLUE));
/*     */ 
/*     */     
/*  58 */     this.m_bHeavy = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
/*  59 */         BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
/*  60 */     this.m_bHeavyL = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
/*  61 */         BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
/*  62 */     this.m_bMedium = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
/*  63 */         BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0, 0, 0)));
/*  64 */     this.m_bLight = new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
/*  65 */         BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
/*  66 */     setHorizontalAlignment(0);
/*  67 */     this.m_cBandRowColor = new Color(250, 250, 250);
/*  68 */     this.m_cSelectedColor = new Color(190, 240, 220);
/*  69 */     this.m_DrumTrack = p_Track;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getTableCellRendererComponent(JTable p_Table, Object p_iVelocityValue, boolean p_bIsSelected, boolean p_bHasFocus, int p_iRow, int p_iColumn) {
/*  77 */     if (p_bHasFocus) {
/*     */       
/*  79 */       setBorder(this.m_bFocused);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  84 */     else if (p_iColumn % this.m_DrumTrack.getBeatDivisor() == 0) {
/*     */       
/*  86 */       setBorder(this.m_bHeavy);
/*  87 */     } else if (p_iColumn % 16 == 0) {
/*     */       
/*  89 */       setBorder(this.m_bMedium);
/*  90 */     } else if (p_iColumn % 4 == 0) {
/*     */       
/*  92 */       setBorder(this.m_bLight);
/*     */     
/*     */     }
/*  95 */     else if (p_iColumn > 0 && (p_iColumn + 1) % this.m_DrumTrack.getBeatDivisor() == 0) {
/*     */       
/*  97 */       setBorder(this.m_bHeavyL);
/*     */     }
/*     */     else {
/*     */       
/* 101 */       setBorder(null);
/*     */     } 
/*     */ 
/*     */     
/* 105 */     Instrument instr = this.m_Model.getInstruments().get(p_iRow);
/* 106 */     if (!instr.hasNote(p_iColumn)) {
/*     */       
/* 108 */       if (p_bIsSelected) {
/*     */         
/* 110 */         setBackground(this.m_cSelectedColor);
/*     */       
/*     */       }
/* 113 */       else if (p_iRow % 2 == 0) {
/*     */         
/* 115 */         setBackground(this.m_cBandRowColor);
/*     */       }
/*     */       else {
/*     */         
/* 119 */         setBackground(Color.WHITE);
/*     */       } 
/*     */ 
/*     */       
/* 123 */       setValue((Object)null);
/* 124 */       return this;
/*     */     } 
/*     */     
/* 127 */     Color cl = instr.getColor();
/* 128 */     if (cl.equals(Color.YELLOW) || cl.equals(Color.CYAN) || cl.equals(Color.ORANGE) || cl.equals(Color.LIGHT_GRAY) || 
/* 129 */       cl.equals(Color.PINK) || cl.equals(Color.MAGENTA) || cl.equals(Color.GREEN) || 
/* 130 */       cl.equals(InstrumentColor.GOLD) || cl.equals(InstrumentColor.LIGHTGOLD) || cl.equals(InstrumentColor.DARKGOLD)) {
/*     */       
/* 132 */       setForeground(Color.BLACK);
/*     */     } else {
/*     */       
/* 135 */       setForeground(Color.WHITE);
/*     */     } 
/*     */ 
/*     */     
/* 139 */     setValue(p_iVelocityValue);
/* 140 */     setBackground(instr.getColor());
/* 141 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\EditorTableCellRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */