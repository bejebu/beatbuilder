/*     */ package model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditorTableModel
/*     */   extends DefaultTableModel
/*     */ {
/*  14 */   private List<Instrument> m_Instruments = new ArrayList<>(15);
/*  15 */   private Drumkit m_DrumKit = null;
/*  16 */   private List<String> m_lInstrumentNames = new ArrayList<>(15);
/*     */ 
/*     */ 
/*     */   
/*     */   public EditorTableModel(Drumkit p_DrumKit) {
/*  21 */     setDrumKit(p_DrumKit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDrumKit(Drumkit p_DrumKit) {
/*  28 */     this.m_DrumKit = p_DrumKit;
/*  29 */     this.m_Instruments.clear();
/*  30 */     for (Instrument inst : p_DrumKit.getInstruments())
/*     */     {
/*  32 */       this.m_Instruments.add(inst);
/*     */     }
/*  34 */     this.m_lInstrumentNames.clear();
/*  35 */     for (Instrument inst : this.m_Instruments)
/*     */     {
/*  37 */       this.m_lInstrumentNames.add(inst.getName());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Instrument> getInstruments() {
/*  45 */     return this.m_DrumKit.getInstruments();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument getInstrument(Integer p_iIndex) {
/*  52 */     return this.m_DrumKit.getInstrument(p_iIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument getInstrument(String p_strName) {
/*  59 */     return this.m_DrumKit.getInstrument(p_strName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getInstrumentNames() {
/*  66 */     return this.m_lInstrumentNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNotes() {
/*  73 */     for (Instrument inst : this.m_Instruments)
/*     */     {
/*  75 */       inst.clearNotes();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  83 */     return 10240;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowCount() {
/*  90 */     if (this.m_Instruments != null)
/*     */     {
/*  92 */       return this.m_Instruments.size();
/*     */     }
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValueAt(int p_iRow, int p_iColumn) {
/* 101 */     Instrument instr = this.m_Instruments.get(p_iRow);
/* 102 */     Note note = instr.getNote(p_iColumn);
/* 103 */     if (note != null)
/*     */     {
/* 105 */       return note.getVelocity().toString();
/*     */     }
/*     */     
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNote(int p_iRow, int p_iColumn) {
/* 115 */     Instrument instr = this.m_Instruments.get(p_iRow);
/* 116 */     return instr.hasNote(p_iColumn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColumnName(int col) {
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCellEditable(int p_iRow, int p_iColumn) {
/* 130 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\EditorTableModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */