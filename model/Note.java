/*     */ package model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Note
/*     */   implements Comparable<Note>
/*     */ {
/*  16 */   private int m_iVelocity = 70;
/*  17 */   private int m_iBeat = 0;
/*  18 */   private Instrument m_Instrument = null;
/*  19 */   private Long m_lTick = null;
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
/*     */   public Note(Note p_SourceNote) {
/*  31 */     this.m_Instrument = p_SourceNote.getInstrument();
/*  32 */     this.m_iBeat = p_SourceNote.getBeat();
/*  33 */     this.m_iVelocity = p_SourceNote.getVelocity().intValue();
/*  34 */     this.m_lTick = p_SourceNote.getTick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Note(Instrument p_Instrument, int p_iBeat, int p_iVelocity) {
/*  41 */     this.m_Instrument = p_Instrument;
/*  42 */     this.m_iBeat = p_iBeat;
/*  43 */     this.m_iVelocity = p_iVelocity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Note(Instrument p_Instrument, int p_iBeat, Long p_lTick, int p_iVelocity) {
/*  50 */     this.m_Instrument = p_Instrument;
/*  51 */     this.m_iBeat = p_iBeat;
/*  52 */     this.m_iVelocity = p_iVelocity;
/*  53 */     this.m_lTick = p_lTick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getVelocity() {
/*  60 */     return Integer.valueOf(this.m_iVelocity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(int p_Velocity) {
/*  67 */     this.m_iVelocity = p_Velocity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void modifyVelocity(int p_Adjustment) {
/*  74 */     this.m_iVelocity += p_Adjustment;
/*  75 */     this.m_iVelocity = Math.min(this.m_iVelocity, 127);
/*  76 */     this.m_iVelocity = Math.max(1, this.m_iVelocity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBeat() {
/*  83 */     return this.m_iBeat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBeat(int p_iPosition) {
/*  90 */     this.m_iBeat = p_iPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEvent(int p_iPPQ) {
/*  97 */     return toMidiEvent(p_iPPQ, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEvent(int p_iPPQ, Integer p_iStartingBeat) {
/* 105 */     long iPulse = 0L;
/*     */ 
/*     */     
/* 108 */     List<MidiEvent> events = new ArrayList<>(2);
/* 109 */     if (this.m_lTick == null) {
/*     */       
/* 111 */       int iBeat = this.m_iBeat;
/* 112 */       if (p_iStartingBeat != null)
/*     */       {
/* 114 */         iBeat -= p_iStartingBeat.intValue();
/*     */       }
/* 116 */       iPulse = (iBeat * p_iPPQ / 16);
/*     */     }
/*     */     else {
/*     */       
/* 120 */       iPulse = this.m_lTick.longValue();
/* 121 */       if (p_iStartingBeat != null)
/*     */       {
/* 123 */         iPulse -= (p_iStartingBeat.intValue() * p_iPPQ / 16);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 131 */       ShortMessage messageOn = new ShortMessage(144, 9, this.m_Instrument.getMidiNote().intValue(), 
/* 132 */           this.m_iVelocity);
/* 133 */       ShortMessage messageOff = new ShortMessage(128, 9, this.m_Instrument.getMidiNote().intValue(), 
/* 134 */           this.m_iVelocity);
/* 135 */       MidiEvent eventOn = new MidiEvent(messageOn, iPulse);
/* 136 */       MidiEvent eventOff = new MidiEvent(messageOff, iPulse + (p_iPPQ / 16));
/* 137 */       events.add(eventOn);
/* 138 */       events.add(eventOff);
/* 139 */     } catch (InvalidMidiDataException e) {
/*     */       
/* 141 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 144 */     return events;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument getInstrument() {
/* 151 */     return this.m_Instrument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void quantize() {
/* 158 */     this.m_lTick = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomize(int p_iFactor, int p_iPPQ) {
/* 165 */     p_iFactor += 2;
/* 166 */     int iBeat = this.m_iBeat;
/* 167 */     long lPulse = (iBeat * p_iPPQ / 16);
/* 168 */     Random rand = new Random();
/* 169 */     int iOffset = rand.nextInt(p_iFactor);
/* 170 */     iOffset -= p_iFactor / 2;
/* 171 */     long lNewPulse = lPulse + iOffset;
/* 172 */     if (lNewPulse < 0L)
/*     */     {
/* 174 */       lNewPulse = 0L;
/*     */     }
/* 176 */     this.m_lTick = Long.valueOf(lNewPulse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getTick() {
/* 184 */     return this.m_lTick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTick(long p_lTick) {
/* 191 */     this.m_lTick = Long.valueOf(p_lTick);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Note p_Note) {
/* 201 */     Integer iBeat = new Integer(this.m_iBeat);
/* 202 */     Integer iCompBeat = new Integer(p_Note.getBeat());
/* 203 */     return iBeat.compareTo(iCompBeat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adjustStart(int p_iOffset, int p_iPPQ) {
/* 210 */     this.m_iBeat -= p_iOffset;
/*     */     
/* 212 */     if (this.m_lTick != null) {
/*     */       
/* 214 */       long lOffsetTicks = (p_iOffset * p_iPPQ / 16);
/* 215 */       this.m_lTick = Long.valueOf(this.m_lTick.longValue() - lOffsetTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStart(int p_iOffset, int p_iPPQ) {
/* 221 */     this.m_iBeat += p_iOffset;
/*     */     
/* 223 */     if (this.m_lTick != null) {
/*     */       
/* 225 */       long lOffsetTicks = (p_iOffset * p_iPPQ / 16);
/* 226 */       this.m_lTick = Long.valueOf(this.m_lTick.longValue() + lOffsetTicks);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Note() {}
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\Note.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */