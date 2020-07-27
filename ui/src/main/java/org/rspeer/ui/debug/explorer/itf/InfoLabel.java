package org.rspeer.ui.debug.explorer.itf;

/*
    Author: zScorpio
    Time: 13:35 (UTC+2)
    Date: Sunday - 05/31/2020
*/

import javax.swing.*;

class InfoLabel extends JTextArea {

    InfoLabel(String text) {
        setRows(1);
        setEditable(false);
        setBackground(null);
        setBorder(null);
        setText(text);
        setCaretPosition(0);
    }
}
