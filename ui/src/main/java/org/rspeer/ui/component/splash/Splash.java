package org.rspeer.ui.component.splash;

/*
    Author: zScorpio
    Time: 21:24 (UTC+2)
    Date: Sunday - 05/31/2020
*/

import org.rspeer.commons.Configuration;

import javax.swing.*;
import java.awt.*;

public class Splash extends JPanel {

    private final JPanel content;
    private final JLabel message;

    public Splash() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(765, 503));

        content = new JPanel(new GridBagLayout());
        content.setBackground(Color.BLACK);

        message = new JLabel("Loading " + Configuration.getApplicationTitle());
        message.setForeground(Color.WHITE);

        content.add(message);

        add(content, BorderLayout.CENTER);
        setPreferredSize(getMinimumSize());
    }

    public void setMessage(String text) {
        message.setText(text);
    }
}
