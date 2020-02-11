/*     */ package model;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Instrument
/*     */ {
/*     */   private int midiNote;
/*  21 */   private String name = null;
/*  22 */   private HashMap<Integer, Note> notes = null;
/*  23 */   private Color color = null;
/*  24 */   private String colorName = null;
/*     */   
/*  26 */   private HashMap<Integer, Note> notesBuffer = new HashMap<>();
/*  27 */   private int m_iPasteBlockSize = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument(String p_name, int p_midiNote, String p_strColor) {
/*  33 */     this.midiNote = p_midiNote;
/*  34 */     this.name = p_name;
/*  35 */     if (p_name.contains("hat")) {
/*     */       
/*  37 */       this.notes = new HashMap<>(32);
/*     */     } else {
/*     */       
/*  40 */       this.notes = new HashMap<>();
/*     */     } 
/*     */     
/*  43 */     this.colorName = p_strColor;
/*  44 */     setColor(p_strColor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pack() {
/*  51 */     HashMap<Integer, Note> notesCopy = new HashMap<>(this.notes.size());
/*  52 */     notesCopy.putAll(this.notes);
/*  53 */     this.notes = notesCopy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  60 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String p_strName) {
/*  67 */     this.name = p_strName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor() {
/*  74 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNote(int p_beat, int p_velocity) {
/*  81 */     this.notes.put(Integer.valueOf(p_beat), new Note(this, p_beat, p_velocity));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNote(Note p_Note) {
/*  88 */     this.notes.put(Integer.valueOf(p_Note.getBeat()), p_Note);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNotes(List<Note> p_Notes) {
/*  95 */     for (Note note : p_Notes)
/*     */     {
/*  97 */       this.notes.put(Integer.valueOf(note.getBeat()), note);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotes(List<Note> p_Notes) {
/* 105 */     clearNotes();
/* 106 */     addNotes(p_Notes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNotes(Map<Integer, Note> p_Notes) {
/* 113 */     clearNotes();
/* 114 */     for (Note note : p_Notes.values())
/*     */     {
/* 116 */       this.notes.put(Integer.valueOf(note.getBeat()), note);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeNote(int p_beat) {
/* 124 */     this.notes.remove(Integer.valueOf(p_beat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Note> getNotes() {
/* 131 */     return this.notes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Note getNote(int p_beat) {
/* 138 */     return this.notes.get(Integer.valueOf(p_beat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNote(int p_beat) {
/* 145 */     if (this.notes.isEmpty())
/*     */     {
/* 147 */       return false;
/*     */     }
/* 149 */     return this.notes.containsKey(Integer.valueOf(p_beat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnyNotes() {
/* 156 */     return !this.notes.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(int p_velocity) {
/* 163 */     for (Note note : this.notes.values())
/*     */     {
/* 165 */       note.setVelocity(p_velocity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void modifyVelocity(int p_adjustment) {
/* 173 */     for (Note note : this.notes.values())
/*     */     {
/* 175 */       note.modifyVelocity(p_adjustment);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomizeNoteVolumes(Map<String, Integer> p_mRange) {
/* 183 */     int iMin = ((Integer)p_mRange.get("MIN")).intValue();
/* 184 */     int iMax = ((Integer)p_mRange.get("MAX")).intValue();
/* 185 */     int iRange = iMax - iMin;
/* 186 */     Random rand = new Random();
/*     */     
/* 188 */     for (Note note : this.notes.values()) {
/*     */       
/* 190 */       int velocity = rand.nextInt(iRange) + iMin;
/* 191 */       note.setVelocity(velocity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomizeNotes(int p_iFactor, int p_iPPQ) {
/* 199 */     for (Note note : this.notes.values())
/*     */     {
/* 201 */       note.randomize(p_iFactor, p_iPPQ);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void quantizeNotes() {
/* 209 */     for (Note note : this.notes.values())
/*     */     {
/* 211 */       note.quantize();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMidiNote() {
/* 219 */     return Integer.valueOf(this.midiNote);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMidiNote(int p_iNote) {
/* 226 */     this.midiNote = p_iNote;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEvents(int i_PPQ) {
/* 233 */     List<MidiEvent> events = new ArrayList<>(this.notes.size());
/* 234 */     for (Note note : this.notes.values())
/*     */     {
/* 236 */       events.addAll(note.toMidiEvent(i_PPQ));
/*     */     }
/* 238 */     return events;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEventsFromStartpoint(int i_PPQ, Integer p_iStart) {
/* 245 */     List<MidiEvent> events = new ArrayList<>(this.notes.size());
/* 246 */     for (Note note : this.notes.values()) {
/*     */       
/* 248 */       if (note.getBeat() >= p_iStart.intValue())
/*     */       {
/* 250 */         events.addAll(note.toMidiEvent(i_PPQ, p_iStart));
/*     */       }
/*     */     } 
/* 253 */     return events;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEventsToEndpoint(int i_PPQ, int p_iEnd) {
/* 260 */     List<MidiEvent> events = new ArrayList<>(this.notes.size());
/* 261 */     for (Note note : this.notes.values()) {
/*     */       
/* 263 */       if (note.getBeat() <= p_iEnd)
/*     */       {
/* 265 */         events.addAll(note.toMidiEvent(i_PPQ));
/*     */       }
/*     */     } 
/* 268 */     return events;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MidiEvent> toMidiEvents(int i_PPQ, int p_iStart, int p_iEnd) {
/* 275 */     List<MidiEvent> events = new ArrayList<>(this.notes.size());
/* 276 */     for (int i = p_iStart; i <= p_iEnd; i++) {
/*     */       
/* 278 */       Note note = this.notes.get(Integer.valueOf(i));
/* 279 */       if (note != null)
/*     */       {
/* 281 */         events.addAll(note.toMidiEvent(i_PPQ, Integer.valueOf(p_iStart)));
/*     */       }
/*     */     } 
/* 284 */     return events;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toFile(Writer p_writer) throws IOException {
/* 291 */     p_writer.write(String.valueOf(this.name) + "=" + this.midiNote + "," + this.colorName + System.lineSeparator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColorName() {
/* 298 */     return this.colorName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(String p_strColor) {
/*     */     try {
/* 307 */       Field field = Class.forName("tools.InstrumentColor").getField(p_strColor);
/* 308 */       this.color = (Color)field.get(null);
/* 309 */       this.colorName = p_strColor;
/* 310 */     } catch (Exception e) {
/*     */       
/* 312 */       this.color = Color.BLUE;
/* 313 */       this.colorName = "blue";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNotes() {
/* 321 */     this.notes.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Instrument(String p_strFileLine) {
/* 328 */     String strName = p_strFileLine.substring(0, p_strFileLine.indexOf("="));
/* 329 */     String note = p_strFileLine.substring(p_strFileLine.indexOf("=") + 1, 
/* 330 */         p_strFileLine.indexOf(","));
/* 331 */     String color = p_strFileLine.substring(p_strFileLine.indexOf(",") + 1);
/*     */     
/* 333 */     this.midiNote = Integer.valueOf(note).intValue();
/* 334 */     this.name = strName;
/* 335 */     this.notes = new HashMap<>();
/* 336 */     this.colorName = color;
/* 337 */     setColor(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBars(int p_lOffsetBeat, int p_lLength) {
/* 344 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 348 */     int lHighestBeat = getLastBeat();
/* 349 */     for (int i = lHighestBeat; i >= p_lOffsetBeat; i--) {
/*     */       
/* 351 */       Note note = this.notes.remove(Integer.valueOf(i));
/* 352 */       if (note != null) {
/*     */ 
/*     */         
/* 355 */         int lNewBeat = i + p_lLength;
/* 356 */         this.notes.put(Integer.valueOf(lNewBeat), note);
/* 357 */         note.setBeat(lNewBeat);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paste(int p_iOffset, int p_iPPQ) {
/* 366 */     for (Note note : this.notesBuffer.values()) {
/*     */       
/* 368 */       Note pasteNote = new Note(note);
/* 369 */       pasteNote.setStart(p_iOffset, p_iPPQ);
/* 370 */       this.notes.put(Integer.valueOf(pasteNote.getBeat()), pasteNote);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pasteInsert(int p_iOffsetBeat, int p_iPPQ) {
/* 380 */     insertBars(p_iOffsetBeat, this.m_iPasteBlockSize + 1);
/* 381 */     paste(p_iOffsetBeat, p_iPPQ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deletenotes(int p_iStart, int p_iEnd) {
/* 388 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 392 */     for (int i = p_iStart; i <= p_iEnd; i++)
/*     */     {
/* 394 */       this.notes.remove(Integer.valueOf(i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deletebars(int p_iStart, int p_iEnd, int p_iPPQ) {
/* 402 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 407 */     int iOffset = p_iEnd - p_iStart + 1;
/* 408 */     for (int i = p_iStart; i <= p_iEnd; i++)
/*     */     {
/* 410 */       this.notes.remove(Integer.valueOf(i));
/*     */     }
/* 412 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 417 */     int lHighestBeat = getLastBeat();
/*     */     
/* 419 */     for (int j = p_iEnd + 1; j <= lHighestBeat; j++) {
/*     */       
/* 421 */       Note note = this.notes.get(Integer.valueOf(j));
/*     */       
/* 423 */       if (note != null) {
/*     */         
/* 425 */         int newBeat = j - iOffset;
/*     */ 
/*     */         
/* 428 */         note.adjustStart(iOffset, p_iPPQ);
/* 429 */         this.notes.put(Integer.valueOf(newBeat), note);
/* 430 */         this.notes.remove(Integer.valueOf(j));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(int p_iStart, int p_iEnd, int p_iPPQ) {
/* 440 */     this.notesBuffer.clear();
/* 441 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 445 */     this.m_iPasteBlockSize = p_iEnd - p_iStart;
/*     */     
/* 447 */     for (int i = p_iStart; i <= p_iEnd; i++) {
/*     */       
/* 449 */       Note note = this.notes.get(Integer.valueOf(i));
/* 450 */       if (note != null) {
/*     */ 
/*     */ 
/*     */         
/* 454 */         Note newNote = new Note(note);
/* 455 */         newNote.adjustStart(p_iStart, p_iPPQ);
/* 456 */         this.notesBuffer.put(Integer.valueOf(newNote.getBeat()), newNote);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cutnotes(int p_iStart, int p_iEnd, int p_iPPQ) {
/* 466 */     this.notesBuffer.clear();
/* 467 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 471 */     copy(p_iStart, p_iEnd, p_iPPQ);
/* 472 */     deletenotes(p_iStart, p_iEnd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cutbars(int p_iStart, int p_iEnd, int p_iPPQ) {
/* 479 */     this.notesBuffer.clear();
/* 480 */     if (this.notes.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 484 */     this.m_iPasteBlockSize = p_iEnd - p_iStart;
/* 485 */     copy(p_iStart, p_iEnd, p_iPPQ);
/* 486 */     deletebars(p_iStart, p_iEnd, p_iPPQ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_Instrument) {
/* 493 */     if (p_Instrument instanceof Instrument)
/*     */     {
/* 495 */       return ((Instrument)p_Instrument).getName().equals(getName());
/*     */     }
/* 497 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBuffer() {
/* 504 */     this.notesBuffer.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLastBeat() {
/* 511 */     if (this.notes.isEmpty())
/*     */     {
/* 513 */       return 0;
/*     */     }
/* 515 */     ArrayList<Integer> noteList = new ArrayList<>(this.notes.keySet());
/* 516 */     Collections.sort(noteList);
/* 517 */     return ((Integer)noteList.get(noteList.size() - 1)).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\Instrument.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */