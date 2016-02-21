package generalassembly.yuliyakaleda.startercode;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  EditText mEditText;
  TextView mTextView;
  ListView mListView;
  Button mButton;
  ArrayList<String> mWishList;
  ArrayAdapter<String> mAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mEditText = (EditText) findViewById(R.id.edit_text);

    mTextView = (TextView) findViewById(R.id.textview);
    mListView = (ListView) findViewById(R.id.listview_wishes);
    mButton = (Button) findViewById(R.id.button_add);
    mWishList = new ArrayList<>();
    mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mWishList);
    mListView.setAdapter(mAdapter);

    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //       1. get the text from the input field
          String userInput = mEditText.getText().toString();

        //       2. animate it in the center of the screen

          mTextView.setText(userInput);
          LayoutTransition layout = new LayoutTransition();
          layout.enableTransitionType(LayoutTransition.CHANGING);
          mListView.setLayoutTransition(layout);

          Animation textAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
          mTextView.startAnimation(textAnimation);

        //       3. add it to the wish list
          mWishList.add(0, userInput);
          mAdapter.notifyDataSetChanged();

        //       4. clear the input field
          mEditText.setText("");
      }
    });

  }

    public void add(int index, String wish){
        String userInput = mEditText.getText().toString();


    }


}
