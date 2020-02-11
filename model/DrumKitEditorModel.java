/*     */ package model;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrumKitEditorModel
/*     */   extends DefaultTableModel
/*     */ {
/*  13 */   private Map<String, Drumkit> m_DrumKits = null;
/*  14 */   private String[] m_KitNames = null;
/*  15 */   private String m_strActiveKitName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrumKitEditorModel(Map<String, Drumkit> p_Kits) {
/*  21 */     this.m_DrumKits = p_Kits;
/*  22 */     this.m_KitNames = new String[p_Kits.size()];
/*  23 */     this.m_KitNames = (String[])p_Kits.keySet().toArray((Object[])this.m_KitNames);
/*  24 */     this.m_strActiveKitName = this.m_KitNames[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Drumkit getActiveKit() {
/*  31 */     return this.m_DrumKits.get(this.m_strActiveKitName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveKit(String p_strActiveKit) {
/*  38 */     this.m_strActiveKitName = p_strActiveKit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnCount() {
/*  45 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowCount() {
/*  52 */     if (this.m_strActiveKitName != null)
/*     */     {
/*  54 */       return getActiveKit().getInstruments().size();
/*     */     }
/*  56 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValueAt(int p_iRow, int p_iColumn) {
/*  64 */     if (this.m_strActiveKitName != null) {
/*     */       
/*  66 */       Instrument instr = getActiveKit().getInstruments().get(p_iRow);
/*  67 */       if (p_iColumn == 0)
/*     */       {
/*  69 */         return instr.getName();
/*     */       }
/*  71 */       if (p_iColumn == 1)
/*     */       {
/*  73 */         return instr.getMidiNote();
/*     */       }
/*  75 */       if (p_iColumn == 2)
/*     */       {
/*  77 */         return instr.getColorName();
/*     */       }
/*     */     } 
/*     */     
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueAt(Object p_oValue, int p_iRow, int p_iColumn) {
/*  88 */     String strValue = (String)p_oValue;
/*  89 */     if (this.m_strActiveKitName != null) {
/*     */       
/*  91 */       Instrument instr = getActiveKit().getInstruments().get(p_iRow);
/*  92 */       if (p_iColumn == 0) {
/*     */         
/*  94 */         if (hasKit(strValue)) {
/*     */           
/*  96 */           JOptionPane.showMessageDialog(null, "This name is already in use.");
/*     */           return;
/*     */         } 
/*  99 */         instr.setName(strValue);
/*     */       } 
/* 101 */       if (p_iColumn == 1)
/*     */       {
/* 103 */         instr.setMidiNote((new Integer(strValue)).intValue());
/*     */       }
/* 105 */       if (p_iColumn == 2)
/*     */       {
/* 107 */         instr.setColor(strValue);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColumnName(int p_iColumn) {
/* 116 */     if (p_iColumn == 0)
/*     */     {
/* 118 */       return "Name";
/*     */     }
/* 120 */     if (p_iColumn == 1)
/*     */     {
/* 122 */       return "MIDI Instr";
/*     */     }
/* 124 */     if (p_iColumn == 2)
/*     */     {
/* 126 */       return "Color";
/*     */     }
/* 128 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKit(String p_strName) {
/* 135 */     return this.m_DrumKits.keySet().contains(p_strName);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\DrumKitEditorModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */