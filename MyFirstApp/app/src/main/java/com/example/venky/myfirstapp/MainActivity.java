package com.example.venky.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

//import static com.example.venky.myfirstapp.R.id.button2;


public class MainActivity extends AppCompatActivity {
    public final static String pkg = "com.example.venky.myfirstapp.R.id";
    public static final String EXTRA_MESSAGE = "com.example.venky.myfirstapp";
  /*
    private static MainActivity _instance = null;
    public static MainActivity getInstance(){
        if(_instance == null)
            _instance= new MainActivity();
        return _instance;
    }
*/


    public boolean gameplay = false;
    public ArrayList<Character> button = new ArrayList(10);
   boolean player = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void playagain(View view){
       //  TextView resultText = (TextView) findViewById(R.id.resultText);
         //resultText.setText(" ");
        TextView result = (TextView)findViewById(R.id.result);
        result.setText(" ");
        for(int i=1;i<=9;i++) {
            String button_name = "button" + Integer.toString(i);
            int c_res_id = (getResources().getIdentifier(button_name, "id", "com.example.venky.myfirstapp"));
            Button c = (Button) findViewById(c_res_id);
            c.setText(" ");
        }

        if(gameplay)
            removal(button);
            for(int i=1;i<=10;i++)
        {
            button.add(' ');
        }
        gameplay=true;

    }


    public void move(View view) {


        player = true;
        if(gameplay) {
            TextView result = (TextView)findViewById(R.id.result);
            if(player) {
                Button b = (Button) view;
                String moveid = view.getResources().getResourceName(view.getId());
                int moveidn = (int) moveid.charAt(moveid.length() - 1) - 48;
                if (isspacefree(moveidn)) {
                    b.setText(Character.toString(playerText()));
                    button.set(moveidn, playerText());


                    //  TextView resultText = (TextView) findViewById(R.id.resultText);

                    // resultText.setText(/*button.get(moveidn)*/ Integer.toString(moveidn));
                    // TextView resultText = (TextView) findViewById(R.id.resultText);

                    // resultText.setText(button.toString());

                    if (iswinner(button, playerText())) {
                        //         TextView resultText = (TextView) findViewById(R.id.resultText);
                        // resultText.setText("Congrats, You Won!!!!");
                    /*
                        Context context = getApplicationContext();
                        CharSequence text = "Congrats, You Won!!!!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast.makeText(context, text, duration).show();
                    */

                        result.setText("Congrats, you won!!!");
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

                    player = false;
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid move";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                }
            }
            if(!player && gameplay) {


                int cmove = computermove();


                button.set(cmove, computerText());


                String cbuttonid = "button" + Integer.toString(cmove);


                int c_res_id = (getResources().getIdentifier(cbuttonid, "id", "com.example.venky.myfirstapp"));
                Button c = (Button) findViewById(c_res_id);
                c.setText(Character.toString(computerText()));

                if (iswinner(button, computerText())) {
                    // TextView resultText = (TextView) findViewById(R.id.resultText);
                /*
                    Context context = getApplicationContext();
                    CharSequence text = "Sorry, you lost:)";
                    int duration = Toast.LENGTH_SHORT;

                    Toast.makeText(context, text, duration).show();
                  */
                    result.setText("Sorry, you lost:)");
                    gameplay = false;
                    removal(button);
                    changeText();
                } else if (isboardfull()) {
                    //  TextView resultText = (TextView) findViewById(R.id.resultText);
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

    public ArrayList duplicate(ArrayList<Character> b){

        ArrayList<Character> copy;
        copy = new ArrayList(10);
        copy.add(' ');
        for(int i=1;i<=9;i++)
        {
            copy.add(b.get(i));
        }
        return copy;
    }
    public boolean isspacefree(int i){

        if(button.get(i) == ' '){
           return true;
        }
        return false;
    }

    public int computermove(){

        ArrayList<Character> buttoncopy;

        for(int i=1;i<=9;i++)
        {
            buttoncopy = duplicate(button);
               if(isspacefree(i)){
                   buttoncopy.set(i,computerText());
                   if(iswinner(buttoncopy , computerText()))
                       return i;
               }

        }

        for(int i=1;i<=9;i++)
        {
            buttoncopy = duplicate(button);
            if(isspacefree(i)){
                buttoncopy.set(i,playerText());
                if(iswinner(buttoncopy , playerText()))
                    return i;
            }

        }

        Random r = new Random();
        ArrayList<Integer> corner = new ArrayList<>();

        if(isspacefree(1))
            corner.add(1);
        if(isspacefree(3))
            corner.add(3);
        if(isspacefree(7))
            corner.add(7);
        if(isspacefree(9))
            corner.add(9);

        int crand = r.nextInt(corner.size());
        if(!(corner.size() == 0))
        return corner.get(crand);

        if(isspacefree(5))
            return 5;

        ArrayList<Integer> side = new ArrayList<>();

        if(isspacefree(2))
            side.add(2);
        if(isspacefree(4))
            side.add(4);
        if(isspacefree(6))
            side.add(6);
        if(isspacefree(8))
            side.add(8);

        int srand = r.nextInt(side.size());
            return corner.get(srand);
    }


}