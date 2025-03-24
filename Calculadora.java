package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    Operadors ops=new Operadors();

    private JPanel p2;
    private JTextField input;
    private String[] txt = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", "C", "=", "+"
    };
    int n1=0, n2=0, res=0;
    boolean opClick=false;
    String op="";

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 500);
        //setResizable(false);
        setLayout(new BorderLayout());

        input = new JTextField();
        input.setFont(new Font("Arial", Font.BOLD, 24));
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setEditable(false);
        add(input, BorderLayout.NORTH);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(4, 4, 5, 5));
        p2.setBackground(Color.DARK_GRAY);

        for (String texto : txt) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(Color.LIGHT_GRAY);
            btn.addActionListener(this);
            p2.add(btn);
        }

        add(p2, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String n=e.getActionCommand();
        JButton tmp= (JButton) e.getSource();

        if(n.matches("[0-9]")){
            input.setText(n);
        }
        if(n.matches("[/*\\-+]")){
            opClick=true;
            op=n;
        }
        if(!opClick){
            if(n.matches("[0-9]")){
                n1=Integer.parseInt(tmp.getText());
            }else if(res!=0){
                n1=res;
            }

            System.out.println(n1);
        }else{
            if(n.matches("[0-9]")){
                n2=Integer.parseInt(tmp.getText());
            }

            System.out.println(n2);
            switch (op){
                case "+": res=ops.suma(n1, n2);  break;
                case "-": res=ops.resta(n1, n2); break;
                case "*": res=ops.multi(n1, n2); break;
                case "/":
                    try{
                        res=ops.div(n1, n2);
                    }catch(ArithmeticException msg){
                        input.setText("Syntax Error");
                    }
                    break;
            }
        }
        if(n.matches("[=]")){
            input.setText(String.valueOf(res));
        }
        if(n.matches("[C]")){
            input.setForeground(Color.BLACK);
            input.setText("");
        }
    }
}
