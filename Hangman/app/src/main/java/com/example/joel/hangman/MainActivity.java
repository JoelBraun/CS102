package com.example.joel.hangman;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    protected String wordstring(String s){
        String visible = "";
        for (int i = 0; i < s.length(); ++i) {
            visible += "_ ";
        }
        return visible;
    }

    protected int convert(String s) {
        switch (s) {
            case "5":
                return 5;
            case "4":
                return 4;
            case "3":
                return 3;
            case "2":
                return 2;
            case "1":
                return 1;
        }
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = (Button) this.findViewById(R.id.play);
        final EditText input = (EditText)this.findViewById(R.id.editText);
        /*input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });*/
        final TextView wdview = (TextView)this.findViewById(R.id.WordView);
        final TextView statusdisplay = (TextView)this.findViewById(R.id.Status);
        final String word = randomword();
        final String visible1 = wordstring(word);
        wdview.setText(wordstring(word));
        final ImageView marmot = (ImageView)this.findViewById(R.id.imageView);
        marmot.setImageResource(R.drawable.face1);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.anchorsaweigh);
        final TextView tv = (TextView) this.findViewById(R.id.Remaining);
        tv.setText("5");
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.piazza);

        play.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int remainingGuesses;
                 String wrongGuesses;

                 String visible;
                 List<String> l = new ArrayList<String>();

                    remainingGuesses = convert(tv.getText().toString());
                    wrongGuesses = "";


                      visible = wdview.getText().toString();


                    String status = "You have "+remainingGuesses+" remaining";
                    String wrong = "Wrong guesses so far: "+wrongGuesses;


                            String text = input.getText().toString();
                            Boolean flag = true;

                            for (String s : l){
                                if (text.equals(s)){
                                    flag = false;
                                }

                            }


                            if(text.length()  == 1 && text.matches("[a-z]") && flag) {

                                boolean guessFound = false;

                                l.add(text);

                                for(int i = 0; i < word.length(); ++i) {
                                    if(text.charAt(0) == word.charAt(i)) {
                                        guessFound = true;

                                        String newVisible = "";
                                        for(int j = 0; j < visible.length(); ++j) {
                                            if(j == (i*2)) {
                                                newVisible += word.charAt(i);
                                            }
                                            else {
                                                newVisible += visible.charAt(j);
                                            }
                                        }
                                        visible = newVisible;
                                        wdview.setText(visible);
                                    }
                                }

                                if(!guessFound) {

                                    if(--remainingGuesses > 0) {

                                        tv.setText("" + remainingGuesses);
                                        if(remainingGuesses == 4) marmot.setImageResource(R.drawable.face1);
                                        if(remainingGuesses == 3) marmot.setImageResource(R.drawable.face2);
                                        if(remainingGuesses == 2) marmot.setImageResource(R.drawable.face3);
                                        if(remainingGuesses == 1) marmot.setImageResource(R.drawable.face4);

                                    }
                                    else {
                                        statusdisplay.setText("You lost: the word was "+word);
                                        marmot.setImageResource(R.drawable.face5);
                                        mp2.start();
                                        input.setEnabled(false);

                                    }

                                }
                                else {
                                    String actualVisible = "";
                                    for(int i = 0; i < visible.length(); i+=2) {
                                        actualVisible += visible.charAt(i);
                                    }

                                    if(actualVisible.equals(word)) {
                                        statusdisplay.setText("Congratulations, you have won!");
                                        marmot.setImageResource(R.drawable.teddy);
                                        mp.start();
                                        input.setEnabled(false);
                                    }
                                }

                            }
                            else {
                                System.out.println("Invalid input!");
                            }

                            input.setText("");

                        }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String randomword(){
        String[] test = {"time", "issue", "year", "people", "side", "kind", "way", "head", "day", "man", "service", "thing", "friend", "woman", "father", "life", "power", "child", "hour", "world", "game", "school", "line", "state", "end", "family", "member", "student", "law", "group", "car", "country", "city", "problem", "community", "hand", "name", "part", "president", "place", "team", "case", "minute", "week", "idea", "company", "kid", "system", "body", "program", "information", "question", "back", "work", "parent", "government", "face", "number", "others", "night", "level", "office", "cat", "point", "door", "home", "health", "water", "person", "room", "art", "mother", "war", "area", "history", "money", "party", "story", "result", "fact", "change", "month", "morning", "lot", "reason", "right", "research", "study", "girl", "book", "guy", "eye", "food", "job", "moment", "word", "air", "business", "teacher"};
        int randomInt = (int) (Math.random()*test.length);
        return test[randomInt];
    }
}
