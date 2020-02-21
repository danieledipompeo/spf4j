/*
 * Copyright (c) 2001-2017, Zoltan Farkas All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Additionally licensed with:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spf4j.ui;
//CHECKSTYLE:OFF
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.CodedInputStream;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit;
import org.spf4j.base.AbstractRunnable;
import org.spf4j.base.Pair;
import org.spf4j.base.avro.Method;
import org.spf4j.stackmonitor.SampleNode;
import org.spf4j.stackmonitor.Sampler;
import org.spf4j.stackmonitor.proto.Converter;
import org.spf4j.stackmonitor.proto.gen.ProtoSampleNodes;

/**
 * @author zoly
 */
@SuppressFBWarnings({"FCBL_FIELD_COULD_BE_LOCAL",
  "UP_UNUSED_PARAMETER", "S508C_NON_TRANSLATABLE_STRING", "SE_BAD_FIELD"})
public class Explorer extends javax.swing.JFrame {

  private static final long serialVersionUID = 1L;

  private File folder;

  private Sampler sampler;

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    desktopPane = new javax.swing.JDesktopPane();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    openMenuItem = new javax.swing.JMenuItem();
    fromTextMenuItem = new javax.swing.JMenuItem();
    exitMenuItem = new javax.swing.JMenuItem();
    editMenu = new javax.swing.JMenu();
    cutMenuItem = new javax.swing.JMenuItem();
    copyMenuItem = new javax.swing.JMenuItem();
    pasteMenuItem = new javax.swing.JMenuItem();
    deleteMenuItem = new javax.swing.JMenuItem();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();
    helpMenu = new javax.swing.JMenu();
    contentMenuItem = new javax.swing.JMenuItem();
    aboutMenuItem = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    desktopPane.setAutoscrolls(true);
    desktopPane.setDoubleBuffered(true);
    desktopPane.setPreferredSize(new java.awt.Dimension(800, 600));
    desktopPane.setLayout(null);

    fileMenu.setMnemonic('f');
    fileMenu.setText("File");

    openMenuItem.setMnemonic('o');
    openMenuItem.setText("Open");
    openMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        openMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(openMenuItem);

    fromTextMenuItem.setMnemonic('o');
    fromTextMenuItem.setText("From Text/URL");
    fromTextMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        fromTextMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(fromTextMenuItem);

    exitMenuItem.setMnemonic('x');
    exitMenuItem.setText("Exit");
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitMenuItemActionPerformed(evt);
      }
    });
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    editMenu.setMnemonic('e');
    editMenu.setText("Edit");

    cutMenuItem.setMnemonic('t');
    cutMenuItem.setText("Cut");
    editMenu.add(cutMenuItem);

    copyMenuItem.setMnemonic('y');
    copyMenuItem.setText("Copy");
    editMenu.add(copyMenuItem);

    pasteMenuItem.setMnemonic('p');
    pasteMenuItem.setText("Paste");
    editMenu.add(pasteMenuItem);

    deleteMenuItem.setMnemonic('d');
    deleteMenuItem.setText("Delete");
    editMenu.add(deleteMenuItem);

    menuBar.add(editMenu);

    jMenu1.setText("SelfProfile");

    jMenuItem1.setText("Start");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem1ActionPerformed(evt);
      }
    });
    jMenu1.add(jMenuItem1);

    jMenuItem2.setText("Stop & Display");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem2ActionPerformed(evt);
      }
    });
    jMenu1.add(jMenuItem2);

    menuBar.add(jMenu1);

    helpMenu.setMnemonic('h');
    helpMenu.setText("Help");

    contentMenuItem.setMnemonic('c');
    contentMenuItem.setText("Contents");
    helpMenu.add(contentMenuItem);

    aboutMenuItem.setMnemonic('a');
    aboutMenuItem.setText("About");
    helpMenu.add(aboutMenuItem);

    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(desktopPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, desktopPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Creates new form Explorer
   */
  @SuppressFBWarnings("PATH_TRAVERSAL_IN")
  public Explorer(final File... openFiles) throws IOException {
    if (openFiles.length == 0) {
      folder = new File(org.spf4j.base.Runtime.USER_DIR);
    } else {
      folder = openFiles[0].getParentFile();
    }
    initComponents();
    for (File file : openFiles) {
      openFile(file);
    }
  }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
      System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
      JFileChooser chooser = new JFileChooser();
      chooser.setName("openFileDialog");
      chooser.setMultiSelectionEnabled(true);
      chooser.setDialogType(JFileChooser.OPEN_DIALOG);
      chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      chooser.addChoosableFileFilter(Spf4jFileFilter.D3_JSON);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SPF4J_JSON);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SSDUMP);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SSDUMP2);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SSDUMP3);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SSDUMP2_GZ);
      chooser.addChoosableFileFilter(Spf4jFileFilter.SSDUMP3_GZ);
      chooser.addChoosableFileFilter(Spf4jFileFilter.TSDB);
      chooser.addChoosableFileFilter(Spf4jFileFilter.TSDB2);
      chooser.addChoosableFileFilter(Spf4jFileFilter.AVRO_TABLEDEF);
      if (folder != null) {
        chooser.setCurrentDirectory(folder);
      }

      int returnVal = chooser.showOpenDialog(this);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File [] files = chooser.getSelectedFiles();
        for (File file : files) {
          try {
            openFile(file);
          } catch (IOException ex) {
            throw new UncheckedIOException(ex);
          }
        }
      }
    }//GEN-LAST:event_openMenuItemActionPerformed

  /**
   * Create an Edit menu to support cut/copy/paste.
   */
  public JMenuBar createContextMenuBar() {
    JMenuItem menuItem = null;
    JMenuBar menuBar = new JMenuBar();
    JMenu mainMenu = new JMenu("Edit");
    mainMenu.setMnemonic(KeyEvent.VK_E);

    menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
    menuItem.setText("Cut");
    menuItem.setMnemonic(KeyEvent.VK_X);
    mainMenu.add(menuItem);

    menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
    menuItem.setText("Copy");
    menuItem.setMnemonic(KeyEvent.VK_C);
    mainMenu.add(menuItem);

    menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
    menuItem.setText("Paste");
    menuItem.setMnemonic(KeyEvent.VK_V);
    mainMenu.add(menuItem);

    menuBar.add(mainMenu);
    return menuBar;
  }


  private void fromTextMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromTextMenuItemActionPerformed

    final JDialog frame = new JDialog(this, "Enter json stack samples", true);
    frame.setName("fromTextDialog");
    frame.setJMenuBar(createContextMenuBar());
    TextEntryPanel panel = new TextEntryPanel((title, samples) -> {
        JInternalFrame f = new StackDumpJInternalFrame(samples, title, true);
        frame.setVisible(false);
        f.setVisible(true);
        desktopPane.add(f, javax.swing.JLayeredPane.DEFAULT_LAYER);
    }, (ex) -> {
      JOptionPane.showMessageDialog(frame, "Invalid input " + ex);
      frame.setVisible(false);
      frame.dispose();
    });
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }//GEN-LAST:event_fromTextMenuItemActionPerformed

  private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    try {
      sampler = Sampler.getSampler(10, 100000, new File("."), "dump");
      sampler.start();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(ex);
    }
  }//GEN-LAST:event_jMenuItem1ActionPerformed

  private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    if (sampler != null) {
      try {
        sampler.stop();
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(ex);
      }
      Map<String, SampleNode> samples = sampler.getStackCollectionsAndReset();
      for (Map.Entry<String, SampleNode> entry : samples.entrySet()) {
        try {
          setFrames(entry.getValue(), "self:" + entry.getKey());
        } catch (IOException ex) {
          throw new UncheckedIOException(ex);
        }
      }
    }
  }//GEN-LAST:event_jMenuItem2ActionPerformed

  private void openFile(final File file) throws IOException {
    String fileName = file.getName();
    JInternalFrame frame;
    if (Spf4jFileFilter.TSDB.accept(file)) {
      frame = new TSDBViewJInternalFrame(file);
      frame.setVisible(true);
      desktopPane.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
    } else if (Spf4jFileFilter.TSDB2.accept(file)) {
      frame = new TSDB2ViewJInternalFrame(file);
      frame.setVisible(true);
      desktopPane.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
    } else if (Spf4jFileFilter.AVRO_TABLEDEF.accept(file)) {
      frame = new AvroTSViewJInternalFrame(file);
      frame.setVisible(true);
      desktopPane.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
    } else if (Spf4jFileFilter.SSDUMP.accept(file)) {
      SampleNode samples = loadLegacyFormat(file);
      setFrames(samples, fileName);
    } else if (Spf4jFileFilter.SSDUMP2.accept(file) || Spf4jFileFilter.SSDUMP2_GZ.accept(file)) {
      SampleNode samples = org.spf4j.ssdump2.Converter.load(file);
      setFrames(samples, fileName);
    } else if (Spf4jFileFilter.SSDUMP3.accept(file) || Spf4jFileFilter.SSDUMP3_GZ.accept(file)) {
      Map<String, SampleNode> loadLabeledDumps = org.spf4j.ssdump2.Converter.loadLabeledDumps(file);
      for (Map.Entry<String, SampleNode> entry : loadLabeledDumps.entrySet()) {
        setFrames(entry.getValue(), fileName + ':' + entry.getKey());
      }
    } else if (Spf4jFileFilter.D3_JSON.accept(file)) {
      try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
        Pair<Method, SampleNode> parse = SampleNode.parseD3Json(br);
        setFrames(parse.getSecond(), fileName);
      }
    } else if (Spf4jFileFilter.SPF4J_JSON.accept(file)) {
      try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
        Pair<Method, SampleNode> parse = SampleNode.parse(br);
        setFrames(parse.getSecond(), fileName);
      }
    } else {
      throw new IOException("Unsupported file format " + fileName);
    }
  }

  private static SampleNode loadLegacyFormat(final File file) throws IOException {
    InputStream fis = Files.newInputStream(file.toPath());
    return loadLegacyFormat(fis);
  }

  @VisibleForTesting
  static SampleNode loadLegacyFormat(InputStream fis) throws IOException {
    try (BufferedInputStream bis = new BufferedInputStream(fis)) {
      final CodedInputStream is = CodedInputStream.newInstance(bis);
      is.setRecursionLimit(Short.MAX_VALUE);
      return Converter.fromProtoToSampleNode(ProtoSampleNodes.SampleNode.parseFrom(is));
    }
  }

  private void setFrames(SampleNode samples, String fileName) throws IOException {
    JInternalFrame frame = new StackDumpJInternalFrame(samples, fileName, false);
    frame.setVisible(true);
    desktopPane.add(frame, javax.swing.JLayeredPane.DEFAULT_LAYER);
  }


  /**
   * @param args the command line arguments
   */
  public static void main(final String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionDisplayer());
    System.setProperty("spf4j.tsdb2.lenientRead", "true");
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Explorer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new AbstractRunnable(false) {
      @Override
      @SuppressFBWarnings("PATH_TRAVERSAL_IN")
      public void doRun() throws IOException {
        File[] files = new File[args.length];
        for (int i = 0; i < args.length; i++) {
          files[i] = new File(args[i]);
        }
        new Explorer(files).setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem aboutMenuItem;
  private javax.swing.JMenuItem contentMenuItem;
  private javax.swing.JMenuItem copyMenuItem;
  private javax.swing.JMenuItem cutMenuItem;
  private javax.swing.JMenuItem deleteMenuItem;
  private javax.swing.JDesktopPane desktopPane;
  private javax.swing.JMenu editMenu;
  private javax.swing.JMenuItem exitMenuItem;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenuItem fromTextMenuItem;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem openMenuItem;
  private javax.swing.JMenuItem pasteMenuItem;
  // End of variables declaration//GEN-END:variables


}
