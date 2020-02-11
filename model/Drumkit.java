/*     */ package model;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class Drumkit
/*     */ {
/*  19 */   String m_strKitName = null;
/*     */   
/*  21 */   List<Instrument> m_Instruments = new ArrayList<>(15);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Drumkit(String p_name) {
/*  27 */     this.m_strKitName = p_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  34 */     return this.m_strKitName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Instrument> getInstruments() {
/*  41 */     return this.m_Instruments;
/*     */   }
/*     */ 
/*     */   
/*     */   public Instrument getInstrument(Integer p_Index) {
/*  46 */     return this.m_Instruments.get(p_Index.intValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInstrument(Instrument p_Instrument) {
/*  52 */     this.m_Instruments.add(p_Instrument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasInstrument(String p_strName) {
/*  59 */     return (getInstrument(p_strName) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument getInstrument(String p_strName) {
/*  66 */     for (Instrument inst : this.m_Instruments) {
/*     */       
/*  68 */       if (inst.getName().equals(p_strName))
/*     */       {
/*  70 */         return inst;
/*     */       }
/*     */     } 
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeInstrument(String p_strName) {
/*  80 */     Instrument instrToRemove = null;
/*  81 */     for (Instrument inst : this.m_Instruments) {
/*     */       
/*  83 */       if (inst.getName().equals(p_strName))
/*     */       {
/*  85 */         instrToRemove = inst;
/*     */       }
/*     */     } 
/*  88 */     this.m_Instruments.remove(instrToRemove);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toFile(Writer p_writer) throws IOException {
/*  95 */     p_writer.write("[" + this.m_strKitName + "]" + System.lineSeparator());
/*  96 */     for (Instrument inst : this.m_Instruments)
/*     */     {
/*  98 */       inst.toFile(p_writer);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNotes() {
/* 106 */     for (Instrument inst : this.m_Instruments)
/*     */     {
/* 108 */       inst.clearNotes();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void transferNotes(Drumkit p_SourceKit) {
/* 117 */     clearNotes();
/* 118 */     for (Instrument sourceInst : p_SourceKit.getInstruments()) {
/*     */       
/* 120 */       Instrument targetInst = findInstrumentByMIDInote(sourceInst.getMidiNote());
/* 121 */       if (targetInst != null)
/*     */       {
/* 123 */         targetInst.setNotes(sourceInst.getNotes());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNotes(List<Note> p_Notes) {
/* 130 */     clearNotes();
/* 131 */     for (Note note : p_Notes) {
/*     */       
/* 133 */       Instrument instr = note.getInstrument();
/* 134 */       getInstrument(instr.getName()).addNote(note);
/*     */     } 
/* 136 */     for (Instrument inst : this.m_Instruments)
/*     */     {
/* 138 */       inst.pack();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Instrument findInstrumentByMIDInote(Integer p_iNote) {
/* 144 */     for (Instrument inst : this.m_Instruments) {
/*     */       
/* 146 */       if (inst.getMidiNote().equals(p_iNote))
/*     */       {
/* 148 */         return inst;
/*     */       }
/*     */     } 
/* 151 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\Drumkit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */