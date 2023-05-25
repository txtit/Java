/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.main;

import com.raven.bill.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAction2 extends javax.swing.JPanel {

    public PanelAction2() {
        initComponents();
    }

  public void initEvent(TableActionEvent2 event, int row){
      
        cmDelete.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             event.onDelete(row);
          }
      });
      
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmDelete = new com.raven.bill.ActionButton();

        setOpaque(false);

        cmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/bill/delete.png"))); // NOI18N
        cmDelete.setMaximumSize(new java.awt.Dimension(12, 12));
        cmDelete.setMinimumSize(new java.awt.Dimension(12, 12));
        cmDelete.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.bill.ActionButton cmDelete;
    // End of variables declaration//GEN-END:variables
}
