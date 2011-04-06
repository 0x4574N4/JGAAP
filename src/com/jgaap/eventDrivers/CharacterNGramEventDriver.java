// Copyright (c) 2009, 2011 by Patrick Juola.   All rights reserved.  All unauthorized use prohibited.  
/**
 **/
package com.jgaap.eventDrivers;

import com.jgaap.generics.Document;
import com.jgaap.generics.EventSet;
import javax.swing.*;

/**
 * Extract character N-grams as features.
 *
 */
public class CharacterNGramEventDriver extends NGramEventDriver {

    JLabel NLabel = new JLabel();
    JComboBox NBox = new JComboBox();

    @Override
    public String displayName(){
    	return "Character NGrams";
    }

    @Override
    public String tooltipText(){
    	return "Groups of N successive characters";
    }

    @Override
    public String longDescription(){
    	return "Groups of N successive characters (sliding window); N is given as a parameter.";
    }

    @Override
    public boolean showInGUI(){
    	return true;
    }

    @Override
    public GroupLayout getGUILayout(JPanel panel){
    	
    	NLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    	NLabel.setText("N");

    	NBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        String temp = this.getParameter("N");
        if (temp.equals(""))
        {
            this.setParameter("N", 2);
        }
        NBox.setSelectedIndex(Integer.parseInt(this.getParameter("N")) - 1);
        NBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        layout.setHorizontalGroup(
        		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NLabel)
                        .addComponent(NBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(275, Short.MAX_VALUE))
            );

        layout.setVerticalGroup(
        		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(NBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(255, Short.MAX_VALUE))
            );
        return layout;
    }

    private void NBoxActionPerformed(java.awt.event.ActionEvent evt) {
        this.setParameter("N", NBox.getSelectedIndex()+1);
    }

    private NGramEventDriver theDriver;


    @Override
    public EventSet createEventSet(Document ds) {
        theDriver = new NGramEventDriver();
        String temp = this.getParameter("N");
        if (temp.equals(""))
        {
            this.setParameter("N", 2);
        }
        theDriver.setParameter("N",  this.getParameter("N"));
        theDriver.setParameter("underlyingEvents", "CharacterEventDriver");
        theDriver.setParameter("opendelim", "null");
        theDriver.setParameter("closedelim", "null");
        theDriver.setParameter("separator", "null");
        return theDriver.createEventSet(ds);
    }
}