package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculadora c=new Calculadora();
                c.setVisible(true);
            }
        });
    }
}
