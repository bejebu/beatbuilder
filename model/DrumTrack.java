/*     */ package model;
/*     */ 
/*     */ import java.io.File;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrumTrack
/*     */ {
/*  10 */   private int m_iPPQ = 96;
/*  11 */   private int m_iTempo = 120;
/*  12 */   private String m_strTrackName = "";
/*  13 */   private int m_iNotesPerMeasure = 4;
/*  14 */   private int m_iNoteType = 4;
/*     */   private boolean m_bIsDirty = false;
/*  16 */   private String m_strFileName = "";
/*     */   private boolean m_bIsNew = true;
/*  18 */   private float m_fDivType = 0.0F;
/*  19 */   private int m_iBeatDivisor = 64;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNew() {
/*  25 */     return this.m_bIsNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNew(boolean p_bIsNew) {
/*  32 */     this.m_bIsNew = p_bIsNew;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrumTrack() {
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  46 */     this.m_iPPQ = 96;
/*  47 */     this.m_iTempo = 120;
/*  48 */     this.m_strTrackName = "";
/*  49 */     this.m_iNotesPerMeasure = 4;
/*  50 */     this.m_iNoteType = 4;
/*  51 */     this.m_bIsDirty = false;
/*  52 */     this.m_strFileName = "";
/*  53 */     this.m_bIsNew = true;
/*  54 */     this.m_fDivType = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPPQ() {
/*  61 */     return this.m_iPPQ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPPQ(int p_iPPQ) {
/*  68 */     this.m_iPPQ = p_iPPQ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTempo() {
/*  75 */     return this.m_iTempo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTempo(int p_iTempo) {
/*  82 */     this.m_iTempo = p_iTempo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTrackName() {
/*  89 */     return this.m_strTrackName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrackName(File p_File) {
/*  96 */     this.m_strTrackName = p_File.getName();
/*  97 */     int iExtPos = this.m_strTrackName.toUpperCase().lastIndexOf(".MID");
/*  98 */     if (iExtPos > -1)
/*     */     {
/* 100 */       this.m_strTrackName = this.m_strTrackName.substring(0, iExtPos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNotesPerMeasure() {
/* 108 */     return this.m_iNotesPerMeasure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotesPerMeasure(int p_iNotesPerMeasure) {
/* 115 */     this.m_iNotesPerMeasure = p_iNotesPerMeasure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getNoteType() {
/* 122 */     return Integer.valueOf(this.m_iNoteType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoteType(int p_iNoteType) {
/* 129 */     this.m_iNoteType = p_iNoteType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDirty() {
/* 136 */     return this.m_bIsDirty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirty(boolean p_bIsDirty) {
/* 143 */     this.m_bIsDirty = p_bIsDirty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileName(File p_File) {
/* 152 */     this.m_strFileName = p_File.getPath();
/* 153 */     setTrackName(p_File);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileName() {
/* 160 */     return this.m_strFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDivType() {
/* 167 */     return this.m_fDivType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDivType(float p_fDivType) {
/* 174 */     this.m_fDivType = p_fDivType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSignature(int p_iNotesPerMeasure, int p_iNoteType) {
/* 181 */     this.m_iNotesPerMeasure = p_iNotesPerMeasure;
/* 182 */     this.m_iNoteType = p_iNoteType;
/* 183 */     this.m_iBeatDivisor = 64 / this.m_iNoteType * this.m_iNotesPerMeasure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBeatDivisor() {
/* 191 */     return this.m_iBeatDivisor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTimeSignatureAsString() {
/* 198 */     return String.valueOf(this.m_iNotesPerMeasure) + "/" + this.m_iNoteType;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\DrumTrack.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */