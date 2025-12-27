import java.awt.*; // graphic libaries
import java.awt.event.*; // event handling .
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder; // border fo the button .


public class Calculator{
    int boardWidth = 360;
    int boardHeight = 540;
    // Color Template .
    Color customLightGray = new Color(212,212,210);
    Color customDarkGray = new Color(80,80,80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255,149,0);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};


    // frame,label and panel .
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    // a + b , a - b , a * b , a / b  .
    String A = "0";
    String operator = null;
    String B = null ;

    // constructor .
    Calculator(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial",Font.PLAIN,80));
        displayLabel.setText("0");
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for(int i = 0 ; i < buttonValues.length ; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setBorder(new LineBorder(customBlack));
            button.setOpaque(true);
            button.setText(buttonValue);

            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOrange);
                button.setForeground(Color.white);

            }
            else{
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource(); // e - action . source = where the event come form .
                    String buttonValue = button.getText(); // get the text of the button .

                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){

                        }
                        else if ("÷×-+".contains(buttonValue)){
                            if(operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue ;

                        }
                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue.equals("AC")){
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if (buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1 ;
                            displayLabel.setText(removeDecimalNumber(numDisplay));
                        }
                        else if (buttonValue.equals("%")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100 ;
                            displayLabel.setText(removeDecimalNumber(numDisplay));
                        }
                    }
                    else{
                        if(buttonValue.equals(".")){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                        else if ("0123456789".contains(buttonValue)){
                            if(displayLabel.getText().equals("0")){
                                displayLabel.setText(buttonValue); // 05 - 5 ;
                            }
                            else {
                                displayLabel.setText(displayLabel.getText()+ buttonValue);
                            }
                        }
                    }
                }
            });
        }
    }

    void clearAll(){
        A = "0";
        operator  = null ;
        B = null ;
    }
    String removeDecimalNumber(double numDisplay){
        if(numDisplay % 1 == 0 ) {
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

}