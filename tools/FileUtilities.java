/*     */ package tools;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MetaMessage;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ import javax.sound.midi.MidiMessage;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.Track;
/*     */ import model.DrumTrack;
/*     */ import model.Drumkit;
/*     */ import model.Instrument;
/*     */ import model.Note;
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
/*     */ public class FileUtilities
/*     */ {
/*     */   public static void saveMIDIFile(File file, Sequence sequence) throws IOException {
/*  45 */     MidiSystem.write(sequence, MidiSystem.getMidiFileTypes(sequence)[0], file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LinkedHashMap<String, Drumkit> loadDrumKitFile() {
/*  52 */     LinkedHashMap<String, Drumkit> kits = new LinkedHashMap<>();
/*     */     
/*  54 */     File f = new File(getDrumkitFileName());
/*  55 */     if (!f.exists() && !f.isDirectory())
/*     */     {
/*  57 */       createDefaultKitFile();
/*     */     }
/*     */     
/*  60 */     BufferedReader br = null;
/*     */     
/*     */     try {
/*  63 */       br = new BufferedReader(new FileReader(getDrumkitFileName()));
/*     */       
/*  65 */       String line = br.readLine();
/*  66 */       Drumkit kit = new Drumkit(line.substring(1, line.length() - 1));
/*  67 */       kits.put(kit.getName(), kit);
/*     */       
/*  69 */       while (line != null) {
/*     */         
/*  71 */         if (!line.isEmpty())
/*     */         {
/*  73 */           if (line.startsWith("[")) {
/*     */             
/*  75 */             kit = new Drumkit(line.substring(1, line.length() - 1));
/*  76 */             kits.put(kit.getName(), kit);
/*     */           } else {
/*     */             
/*  79 */             Instrument instr = new Instrument(line);
/*  80 */             kit.addInstrument(instr);
/*     */           } 
/*     */         }
/*  83 */         line = br.readLine();
/*     */       } 
/*  85 */     } catch (FileNotFoundException e) {
/*     */       
/*  87 */       e.printStackTrace();
/*  88 */     } catch (IOException fex) {
/*     */       
/*  90 */       fex.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  96 */         if (br != null)
/*     */         {
/*  98 */           br.close();
/*     */         }
/* 100 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 104 */     return kits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createDefaultKitFile() {
/* 111 */     Writer writer = null;
/*     */     
/* 113 */     Drumkit kitStandard = getStandardDrumKit();
/*     */     
/* 115 */     Drumkit kitRock = new Drumkit("Rock");
/* 116 */     kitRock.addInstrument(new Instrument("Tambourine", 54, "blue"));
/* 117 */     kitRock.addInstrument(new Instrument("Handclaps", 39, "magenta"));
/* 118 */     kitRock.addInstrument(new Instrument("Splash Cymbal High", 59, "blue"));
/* 119 */     kitRock.addInstrument(new Instrument("Splash Cymbal Low", 55, "yellow"));
/*     */     
/* 121 */     kitRock.addInstrument(new Instrument("Ride Cymbal Bell", 53, "blue"));
/* 122 */     kitRock.addInstrument(new Instrument("Ride Cymbal", 51, "yellow"));
/* 123 */     kitRock.addInstrument(new Instrument("Crash Cymbal High", 49, "yellow"));
/* 124 */     kitRock.addInstrument(new Instrument("Crash Cymbal Low", 57, "yellow"));
/* 125 */     kitRock.addInstrument(new Instrument("High Tom", 50, "blue"));
/* 126 */     kitRock.addInstrument(new Instrument("Mid Tom", 48, "magenta"));
/* 127 */     kitRock.addInstrument(new Instrument("Low Tom", 45, "blue"));
/* 128 */     kitRock.addInstrument(new Instrument("Floor Tom", 43, "yellow"));
/* 129 */     kitRock.addInstrument(new Instrument("Hi-Hat Pedal", 44, "blue"));
/* 130 */     kitRock.addInstrument(new Instrument("Hi-Hat Open", 46, "yellow"));
/* 131 */     kitRock.addInstrument(new Instrument("Hi-Hat Closed", 42, "blue"));
/* 132 */     kitRock.addInstrument(new Instrument("Cross Stick", 37, "magenta"));
/* 133 */     kitRock.addInstrument(new Instrument("Snare", 38, "red"));
/* 134 */     kitRock.addInstrument(new Instrument("Bass Drum", 36, "black"));
/*     */     
/* 136 */     Drumkit kitBrushes = new Drumkit("Brushes");
/* 137 */     kitBrushes.addInstrument(new Instrument("Tambourine", 54, "blue"));
/* 138 */     kitBrushes.addInstrument(new Instrument("Handclaps", 39, "magenta"));
/* 139 */     kitBrushes.addInstrument(new Instrument("Splash Cymbal High", 59, "blue"));
/* 140 */     kitBrushes.addInstrument(new Instrument("Splash Cymbal Low", 55, "yellow"));
/*     */     
/* 142 */     kitBrushes.addInstrument(new Instrument("Ride Cymbal Bell", 53, "blue"));
/* 143 */     kitBrushes.addInstrument(new Instrument("Ride Cymbal", 51, "yellow"));
/* 144 */     kitBrushes.addInstrument(new Instrument("Crash Cymbal High", 49, "yellow"));
/* 145 */     kitBrushes.addInstrument(new Instrument("Crash Cymbal Low", 57, "yellow"));
/* 146 */     kitBrushes.addInstrument(new Instrument("High Tom", 50, "blue"));
/* 147 */     kitBrushes.addInstrument(new Instrument("Mid Tom", 48, "magenta"));
/* 148 */     kitBrushes.addInstrument(new Instrument("Low Tom", 45, "blue"));
/* 149 */     kitBrushes.addInstrument(new Instrument("Floor Tom", 43, "yellow"));
/* 150 */     kitBrushes.addInstrument(new Instrument("Hi-Hat Pedal", 44, "blue"));
/* 151 */     kitBrushes.addInstrument(new Instrument("Hi-Hat Open", 46, "yellow"));
/* 152 */     kitBrushes.addInstrument(new Instrument("Hi-Hat Closed", 42, "blue"));
/* 153 */     kitBrushes.addInstrument(new Instrument("Cross Stick", 37, "magenta"));
/* 154 */     kitBrushes.addInstrument(new Instrument("Snare", 38, "red"));
/* 155 */     kitBrushes.addInstrument(new Instrument("Bass Drum", 36, "black"));
/*     */     
/* 157 */     Drumkit kitJazz = new Drumkit("Jazz");
/*     */     
/* 159 */     kitJazz.addInstrument(new Instrument("Ride Cymbal 2", 59, "blue"));
/* 160 */     kitJazz.addInstrument(new Instrument("Ride Cymbal Bell", 53, "blue"));
/* 161 */     kitJazz.addInstrument(new Instrument("Ride Cymbal Bow", 52, "yellow"));
/* 162 */     kitJazz.addInstrument(new Instrument("Ride Cymbal", 51, "yellow"));
/* 163 */     kitJazz.addInstrument(new Instrument("Crash Cymbal High", 49, "yellow"));
/* 164 */     kitJazz.addInstrument(new Instrument("Crash Ride", 57, "yellow"));
/* 165 */     kitJazz.addInstrument(new Instrument("High Tom", 50, "blue"));
/* 166 */     kitJazz.addInstrument(new Instrument("Mid Tom", 48, "magenta"));
/* 167 */     kitJazz.addInstrument(new Instrument("Low Tom", 45, "blue"));
/* 168 */     kitJazz.addInstrument(new Instrument("Floor Tom", 43, "yellow"));
/* 169 */     kitJazz.addInstrument(new Instrument("Hi-Hat Pedal", 44, "blue"));
/* 170 */     kitJazz.addInstrument(new Instrument("Hi-Hat Open", 46, "yellow"));
/* 171 */     kitJazz.addInstrument(new Instrument("Hi-Hat Closed", 42, "blue"));
/* 172 */     kitJazz.addInstrument(new Instrument("Stick over Stick", 39, "magenta"));
/* 173 */     kitJazz.addInstrument(new Instrument("Cross Stick", 37, "magenta"));
/* 174 */     kitJazz.addInstrument(new Instrument("Snare", 38, "red"));
/* 175 */     kitJazz.addInstrument(new Instrument("Bass Drum", 36, "black"));
/*     */ 
/*     */     
/* 178 */     try { writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
/* 179 */               getDrumkitFileName()), "utf-8"));
/* 180 */       kitStandard.toFile(writer);
/* 181 */       kitRock.toFile(writer);
/* 182 */       kitBrushes.toFile(writer);
/* 183 */       kitJazz.toFile(writer); }
/* 184 */     catch (IOException iOException)
/*     */     
/*     */     { 
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 191 */         writer.close();
/* 192 */       } catch (Exception exception) {} } finally { try { writer.close(); } catch (Exception exception) {} }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveDrumKitFile(Map<String, Drumkit> p_Kits) {
/* 202 */     Writer writer = null;
/*     */ 
/*     */     
/* 205 */     try { writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
/* 206 */               getDrumkitFileName()), "utf-8"));
/* 207 */       for (Drumkit kit : p_Kits.values())
/*     */       {
/* 209 */         kit.toFile(writer);
/*     */       } }
/* 211 */     catch (IOException iOException)
/*     */     
/*     */     { 
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 218 */         writer.close();
/* 219 */       } catch (Exception exception) {} } finally { try { writer.close(); } catch (Exception exception) {} }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDrumkitFileName() {
/* 229 */     String strHomeDir = System.getProperty("user.home");
/* 230 */     String strFullFileName = String.valueOf(strHomeDir) + System.getProperty("file.separator") + 
/* 231 */       "BeatBuilderDrums.txt";
/* 232 */     return strFullFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Note> loadMIDIFile(Sequencer p_Sequencer, File file, Drumkit p_Drumkit, DrumTrack p_DrumTrack) throws InvalidMidiDataException, IOException {
/* 241 */     LinkedList<Note> notes = new LinkedList<>();
/* 242 */     ArrayList<Integer> lSkippedInstruments = new ArrayList<>();
/* 243 */     Sequence mySeq = null;
/*     */     
/* 245 */     mySeq = MidiSystem.getSequence(file);
/* 246 */     int iPPQ = mySeq.getResolution();
/* 247 */     p_DrumTrack.setPPQ(iPPQ);
/* 248 */     p_DrumTrack.setDivType(mySeq.getDivisionType());
/* 249 */     int iTempo = 120;
/* 250 */     boolean bFoundTempo = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     p_Sequencer.setSequence(mySeq);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     Track[] tracks = mySeq.getTracks(); byte b; int i;
/*     */     Track[] arrayOfTrack1;
/* 264 */     for (i = (arrayOfTrack1 = tracks).length, b = 0; b < i; ) { Track track = arrayOfTrack1[b];
/*     */       
/* 266 */       int iEventCount = track.size();
/* 267 */       for (int j = 0; j < iEventCount; j++) {
/*     */         
/* 269 */         MidiEvent event = track.get(j);
/* 270 */         MidiMessage message = event.getMessage();
/* 271 */         if (message instanceof MetaMessage) {
/*     */           
/* 273 */           MetaMessage mm = (MetaMessage)message;
/*     */           
/* 275 */           if (mm.getType() == 88) {
/*     */             
/* 277 */             byte[] timeSigData = mm.getData();
/* 278 */             int iNumerator = timeSigData[0];
/* 279 */             int iDenom = timeSigData[1];
/* 280 */             Double iDenominator = Double.valueOf(Math.pow(2.0D, iDenom));
/* 281 */             p_DrumTrack.setTimeSignature(iNumerator, iDenominator.intValue());
/*     */             
/*     */             continue;
/*     */           } 
/* 285 */           if (!bFoundTempo && mm.getType() == 81) {
/*     */             
/* 287 */             byte[] data = mm.getData();
/* 288 */             int tempo = (data[0] & 0xFF) << 16 | (data[1] & 0xFF) << 8 | 
/* 289 */               data[2] & 0xFF;
/* 290 */             iTempo = 60000000 / tempo;
/* 291 */             bFoundTempo = true;
/* 292 */             p_DrumTrack.setTempo(iTempo);
/*     */             
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */         
/* 298 */         if (message instanceof ShortMessage) {
/*     */           
/* 300 */           int iCmd = ((ShortMessage)message).getCommand();
/*     */           
/* 302 */           if (iCmd == 144) {
/*     */             
/* 304 */             int iChannel = ((ShortMessage)message).getChannel();
/* 305 */             if (iChannel == 9) {
/*     */               
/* 307 */               int iNote = ((ShortMessage)message).getData1();
/* 308 */               int iVelocity = ((ShortMessage)message).getData2();
/* 309 */               if (iVelocity != 0) {
/*     */ 
/*     */ 
/*     */                 
/* 313 */                 long lTick = event.getTick();
/*     */ 
/*     */                 
/* 316 */                 Double dBeat = Double.valueOf(Math.ceil(lTick / mySeq.getResolution() / 16.0D));
/*     */ 
/*     */                 
/* 319 */                 int iBeat = dBeat.intValue();
/*     */ 
/*     */                 
/* 322 */                 if (iNote == 35)
/*     */                 {
/* 324 */                   iNote = 36;
/*     */                 }
/* 326 */                 if (iNote == 40)
/*     */                 {
/* 328 */                   iNote = 38;
/*     */                 }
/* 330 */                 if (iNote == 41)
/*     */                 {
/* 332 */                   iNote = 43;
/*     */                 }
/* 334 */                 if (iNote == 47)
/*     */                 {
/* 336 */                   iNote = 45;
/*     */                 }
/* 338 */                 if (iNote == 62 || iNote == 63 || iNote == 64)
/*     */                 {
/* 340 */                   iNote = 0;
/*     */                 }
/* 342 */                 if (iNote == 60)
/*     */                 {
/* 344 */                   iNote = 24;
/*     */                 }
/* 346 */                 if (iNote == 61)
/*     */                 {
/* 348 */                   iNote = 32;
/*     */                 }
/*     */                 
/* 351 */                 Instrument instr = p_Drumkit.findInstrumentByMIDInote(Integer.valueOf(iNote));
/* 352 */                 if (instr != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 361 */                   Note note = null;
/* 362 */                   long lPulse = (iBeat * iPPQ / 16);
/* 363 */                   if (lTick != lPulse) {
/*     */                     
/* 365 */                     note = new Note(instr, iBeat, Long.valueOf(lTick), iVelocity);
/*     */                   }
/*     */                   else {
/*     */                     
/* 369 */                     note = new Note(instr, iBeat, iVelocity);
/*     */                   } 
/*     */                   
/* 372 */                   notes.add(note);
/*     */ 
/*     */                 
/*     */                 }
/* 376 */                 else if (!lSkippedInstruments.contains(Integer.valueOf(iNote))) {
/*     */                   
/* 378 */                   lSkippedInstruments.add(Integer.valueOf(iNote));
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       b++; }
/*     */     
/* 389 */     if (!lSkippedInstruments.isEmpty()) {
/*     */       
/* 391 */       System.out.print(String.valueOf(file.getName()) + " skipped Instruments: ");
/* 392 */       Collections.sort(lSkippedInstruments);
/* 393 */       for (Integer iInst : lSkippedInstruments)
/*     */       {
/* 395 */         System.out.print(iInst + " ");
/*     */       }
/* 397 */       System.out.println("");
/*     */     } 
/*     */     
/* 400 */     return notes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveINIfile(Map<String, String> p_Config) {
/* 408 */     String strHomeDir = System.getProperty("user.home");
/* 409 */     String strFullFileName = String.valueOf(strHomeDir) + System.getProperty("file.separator") + 
/* 410 */       "BeatBuilder.cfg";
/*     */     
/* 412 */     Writer writer = null;
/*     */ 
/*     */     
/* 415 */     try { writer = new BufferedWriter(new OutputStreamWriter(
/* 416 */             new FileOutputStream(strFullFileName), "utf-8"));
/* 417 */       for (String strKey : p_Config.keySet())
/*     */       {
/* 419 */         writer.write(String.valueOf(strKey) + "=" + (String)p_Config.get(strKey) + System.lineSeparator());
/*     */       } }
/* 421 */     catch (IOException iOException)
/*     */     
/*     */     { 
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 428 */         writer.close();
/* 429 */       } catch (Exception exception) {} } finally { try { writer.close(); } catch (Exception exception) {} }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashMap<String, String> loadINIfile() {
/* 439 */     HashMap<String, String> mapIniValues = new HashMap<>();
/* 440 */     String strHomeDir = System.getProperty("user.home");
/* 441 */     String strFullFileName = String.valueOf(strHomeDir) + System.getProperty("file.separator") + 
/* 442 */       "BeatBuilder.cfg";
/*     */     
/* 444 */     File f = new File(strFullFileName);
/* 445 */     if (!f.exists() && !f.isDirectory())
/*     */     {
/* 447 */       return mapIniValues;
/*     */     }
/*     */     
/* 450 */     BufferedReader br = null;
/*     */     
/*     */     try {
/* 453 */       br = new BufferedReader(new FileReader(strFullFileName));
/*     */       
/* 455 */       String line = br.readLine();
/* 456 */       while (line != null) {
/*     */         
/* 458 */         if (!line.isEmpty())
/*     */         {
/* 460 */           if (line.contains("=")) {
/*     */             
/* 462 */             String strName = line.substring(0, line.indexOf("="));
/* 463 */             String strValue = line.substring(line.indexOf("=") + 1);
/* 464 */             mapIniValues.put(strName, strValue);
/*     */           } 
/*     */         }
/*     */         
/* 468 */         line = br.readLine();
/*     */       } 
/* 470 */     } catch (FileNotFoundException e) {
/*     */       
/* 472 */       e.printStackTrace();
/* 473 */     } catch (IOException fex) {
/*     */       
/* 475 */       fex.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 481 */         if (br != null)
/*     */         {
/* 483 */           br.close();
/*     */         }
/* 485 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 489 */     return mapIniValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Drumkit getStandardDrumKit() {
/* 496 */     Drumkit kitStandard = new Drumkit("Standard");
/*     */     
/* 498 */     kitStandard.addInstrument(new Instrument("Cowbell", 56, "green"));
/* 499 */     kitStandard.addInstrument(new Instrument("Shaker", 104, "darkgreen"));
/* 500 */     kitStandard.addInstrument(new Instrument("Maracas (double)", 70, "purple"));
/* 501 */     kitStandard.addInstrument(new Instrument("Maracas (single)", 71, "lavender"));
/*     */     
/* 503 */     kitStandard.addInstrument(new Instrument("Conga", 0, "magenta"));
/*     */     
/* 505 */     kitStandard.addInstrument(new Instrument("Bongo (high)", 24, "lavender"));
/* 506 */     kitStandard.addInstrument(new Instrument("Bongo (low)", 32, "purple"));
/*     */     
/* 508 */     kitStandard.addInstrument(new Instrument("Handclaps", 39, "magenta"));
/*     */     
/* 510 */     kitStandard.addInstrument(new Instrument("Tambourine", 54, "green"));
/*     */     
/* 512 */     kitStandard.addInstrument(new Instrument("Splash Cymbal High", 59, "darkgreen"));
/* 513 */     kitStandard.addInstrument(new Instrument("Splash Cymbal Low", 55, "green"));
/*     */     
/* 515 */     kitStandard.addInstrument(new Instrument("Ride Cymbal Bell", 53, "lightgold"));
/* 516 */     kitStandard.addInstrument(new Instrument("Ride Cymbal", 51, "gold"));
/* 517 */     kitStandard.addInstrument(new Instrument("Crash Cymbal High", 57, "yellow"));
/* 518 */     kitStandard.addInstrument(new Instrument("Crash Cymbal Low", 49, "yellow"));
/* 519 */     kitStandard.addInstrument(new Instrument("High Tom", 50, "lightGray"));
/* 520 */     kitStandard.addInstrument(new Instrument("Mid Tom", 48, "gray"));
/* 521 */     kitStandard.addInstrument(new Instrument("Low Tom", 45, "brown"));
/* 522 */     kitStandard.addInstrument(new Instrument("Floor Tom", 43, "darkbrown"));
/* 523 */     kitStandard.addInstrument(new Instrument("Hi-Hat Pedal", 44, "darkgold"));
/* 524 */     kitStandard.addInstrument(new Instrument("Hi-Hat Open", 46, "lightgold"));
/* 525 */     kitStandard.addInstrument(new Instrument("Hi-Hat Closed", 42, "gold"));
/* 526 */     kitStandard.addInstrument(new Instrument("Cross Stick", 37, "darkorange"));
/* 527 */     kitStandard.addInstrument(new Instrument("Snare", 38, "blue"));
/* 528 */     kitStandard.addInstrument(new Instrument("Bass Drum", 36, "black"));
/*     */     
/* 530 */     return kitStandard;
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\tools\FileUtilities.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */