
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    EditText word,clue;
    TextView displayWord;
    Button start;
    Button check;
    Button reset;
    Button[] buttons = new Button[16];

    String word_string;
    LinearLayout initialLayout;
    ConstraintLayout gameLayout;

    Set<Integer> letterPosition = new HashSet<>() ;
    String k = "";
    int traverseArray;
    int position=0;
    int length,life = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word = findViewById(R.id.word_edittext);
        clue = findViewById(R.id.clue_edittext);





        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word_string = word.getText().toString();

                if(word_string.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Word", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(word_string.length()>16)
                    {
                        Toast.makeText(MainActivity.this, "Word Length should be less than 16", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                        startActivity(intent);
                        initialLayout.setVisibility(View.INVISIBLE);
                        gameLayout.setVisibility(View.VISIBLE);
                        setWord();
                    }
                }
            }
        });


        for (traverseArray =0;traverseArray< buttons.length;traverseArray++)
        {
            buttons[traverseArray].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tempLetter = buttons[traverseArray].getText().toString();
                    k = k.substring(0, position) + tempLetter + k.substring(position + 1);
                    position++;
                    displayWord.setText(k);
                }
            });
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<length;i++)
                {
                    k += "_";
                }
                displayWord.setText(k);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(k==word_string)
                {
                    Toast.makeText(MainActivity.this, "You Won", Toast.LENGTH_SHORT).show();
                }
                else {
                    life--;
                    if(life==0)
                    {
                        Toast.makeText(MainActivity.this, "You Lost", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for(int i=0;i<length;i++)
                        {
                            k += "_";
                        }
                        displayWord.setText(k);
                    }

                }
            }
        });


    }

    public void setWord(){
        length = word_string.length();
        for(int i=0;i<length;i++)
        {
            k += "_";
        }
        Random r = new Random();
        Random r2 = new Random();

        int tempNum = r.nextInt(16);
        Character[] myarr = new Character[16];

        while(letterPosition.size()<length)
        {
            letterPosition.add(tempNum);
        }
        int j = 0;
        for(int i: letterPosition )
        {
            myarr[i] = word_string.charAt(j);
            j++;
        }

        for(int i=0;i<16;i++)
        {
            if(!letterPosition.contains(i))
            {
                int tempnum1 = r2.nextInt(26);
                tempnum1 += 97;
                myarr[i] = (char)tempnum1;
            }

            buttons[i].setText(myarr[i]);
        }

    }
}





