package org.rspeer.ui;

public abstract class Window<T extends java.awt.Window> {

    protected final T frame;

    protected Window(T frame) {
        this.frame = frame;
    }

    public abstract void display();

    public abstract void dispose();
}
