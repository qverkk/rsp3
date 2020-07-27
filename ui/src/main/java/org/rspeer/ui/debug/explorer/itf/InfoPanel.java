package org.rspeer.ui.debug.explorer.itf;

/*
    Author: zScorpio
    Time: 14:10 (UTC+2)
    Date: Sunday - 05/31/2020
*/

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

class InfoPanel extends JPanel {

    private final JPanel content;
    private final GridBagConstraints contentConstraints;

    InfoPanel() {
        setLayout(new GridBagLayout());

        content = new JPanel(new GridBagLayout());
        JPanel filler = new JPanel();
        JButton copy = new JButton("Copy");

        copy.addActionListener(e -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Component component : content.getComponents()) {
                if (component instanceof JTextComponent) {
                    stringBuilder.append(((JTextComponent) component).getText())
                                 .append(System.lineSeparator());
                }
            }
            if (stringBuilder.length() > 0) {
                StringSelection stringSelection = new StringSelection(stringBuilder.toString());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
            }
        });

        contentConstraints = new GridBagConstraints();
        contentConstraints.gridwidth = GridBagConstraints.REMAINDER;
        contentConstraints.weightx = 1;
        contentConstraints.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints baseConstraints = new GridBagConstraints();
        baseConstraints.gridwidth = GridBagConstraints.REMAINDER;
        baseConstraints.weightx = 1;
        baseConstraints.fill = GridBagConstraints.HORIZONTAL;
        baseConstraints.insets.bottom = 4;

        add(copy, baseConstraints);
        baseConstraints.insets.bottom = 0;
        add(content, baseConstraints);
        baseConstraints.weighty = 1;
        add(filler, baseConstraints);
    }

    @Override
    public Component add(Component component) {
        content.add(component, contentConstraints);
        revalidate();
        repaint();
        return component;
    }

    @Override
    public void removeAll() {
        content.removeAll();
    }
}
