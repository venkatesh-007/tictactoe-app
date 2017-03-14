package com.example.venky.myfirstapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {
    public boolean gameplay = false;
    public ArrayList<Character> button = new ArrayList(10);
    //boolean player1 = true;
    int call = 1;
    char player = ' ';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //TextView result = (TextView)findViewById(R.id.result);
    }

    public void playagain(View view) {
        //  TextView resultText = (TextView) findViewById(R.id.resultText);
        //resultText.setText(" ");
        for (int i = 1; i <= 9; i++) {
            String button_name = "button" + Integer.toString(i);
            int c_res_id = (getResources().getIdentifier(button_name, "id", "com.example.venky.myfirstapp"));
            Button c = (Button) findViewById(c_res_id);
            c.setText(" ");
        }

        if (gameplay)
            removal(button);
        for (int i = 1; i <= 10; i++) {
            button.add(' ');
        }
        gameplay = true;
        call=1;
        TextView result = (TextView)findViewById(R.id.result);
        result.setText(" ");

    }

    public void move(View view) {

        if(call % 2 == 1)
        {
            player = playerText();

        }
        else
        {
            player = computerText();
        }
       // player = true;
        if (gameplay) {
            TextView result = (TextView)findViewById(R.id.result);

                Button b = (Button) view;
                String moveid = view.getResources().getResourceName(view.getId());
                int moveidn = (int) moveid.charAt(moveid.length() - 1) - 48;
                if (isspacefree(moveidn)) {
                    b.setText(Character.toString(player));
                    button.set(moveidn, player);


                    //  TextView resultText = (TextView) findViewById(R.id.resultText);

                    // resultText.setText(/*button.get(moveidn)*/ Integer.toString(moveidn));
                    // TextView resultText = (TextView) findViewById(R.id.resultText);

                    // resultText.setText(button.toString());

                    if (iswinner(button, player)) {
                        //         TextView resultText = (TextView) findViewById(R.id.resultText);
                        // resultText.setText("Congrats, You Won!!!!");
                    /*
                        Context context = getApplicationContext();
                        CharSequence text = player + " Won!!!!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast.makeText(context, text, duration).show();
                    */
                        result.setText(player+" won!!!");
                        gameplay = false;
                        removal(button);
                        changeText();


                    } else if (isboardfull()) {
                        //         TextView resultText = (TextView) findViewById(R.id.resultText);
                        /*
                        Context context = getApplicationContext();
                        CharSequence text = "Draw";
                        int duration = Toast.LENGTH_SHORT;

                        Toast.makeText(context, text, duration).show();
                        */
                        result.setText("Draw!!");
                        gameplay = false;
                        removal(button);
                        changeText();
                    }

                    call++;
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid move";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }



        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Press Start button :)";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
        }
    }
    public void changeText(){
        Button b = (Button) findViewById(R.id.button10);
        b.setText("Play Again");
    }
    public ArrayList<Character> removal(ArrayList<Character> b){
        /*for(int i=0;i<=9;i++)
        {
            b.remove(0);
        }
        */
        b.removeAll(b);
        return b;
    }
    public boolean iswinner(ArrayList<Character> button1 , char le){
        return ((button1.get(7) == le && button1.get(8) == le && button1.get(9) == le)||
                (button1.get(4) == le && button1.get(5) == le && button1.get(6) == le)||
                (button1.get(1) == le && button1.get(2) == le && button1.get(3) == le)||
                (button1.get(7) == le && button1.get(4) == le && button1.get(1) == le)||
                (button1.get(8) == le && button1.get(5) == le && button1.get(2) == le)||
                (button1.get(9) == le && button1.get(6) == le && button1.get(3) == le)||
                (button1.get(7) == le && button1.get(5) == le && button1.get(3) == le)||
                (button1.get(9) == le && button1.get(5) == le && button1.get(1) == le));
    }

    public boolean isboardfull(){
        boolean flag = true;
        for(int i=1;i<=9;i++)
        {
            if(button.get(i) == ' ')
                flag=false;
        }

        return flag;
    }

    public char playerText(){
        return 'X';
    }

    public char computerText(){
        return 'O';
    }


    public boolean isspacefree(int i){

        if(button.get(i) == ' '){
            return true;
        }
        return false;
    }



}


