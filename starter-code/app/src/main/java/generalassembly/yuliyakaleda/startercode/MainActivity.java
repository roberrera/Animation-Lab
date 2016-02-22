package generalassembly.yuliyakaleda.startercode;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    TextView mTextView;
    ListView mListView;
    Button mButton;
    ArrayList<String> mWishList;
    ArrayAdapter<String> mAdapter;
    Animation mAnimation;
    RelativeLayout mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.textview);
        mListView = (ListView) findViewById(R.id.listview_wishes);
        mButton = (Button) findViewById(R.id.button_add);
        mAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
        mLayout = (RelativeLayout) findViewById(R.id.layout);

        mWishList = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mWishList);
        mListView.setAdapter(mAdapter);

        // Give the listView an animated transition.
        LayoutTransition layout = new LayoutTransition();
        layout.enableTransitionType(LayoutTransition.CHANGING);
        mLayout.setLayoutTransition(layout);

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Clear the animated text.
                mTextView.setText("");
                // Get the user's input.
                String userInput = mEditText.getText().toString();
                // Clear the editText box.
                mEditText.setText("");
                // Add the user's input as the item at the top of the list.
                mWishList.add(0, userInput);
                // Refresh the list.
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //       1. get the text from the input field
                //       2. animate it in the center of the screen
                //       3. add it to the wish list
                //       4. clear the input field

                String userInput = mEditText.getText().toString();

                // Check if the user has typed anything.
                if (userInput.isEmpty()) {
                    mEditText.setError("Enter a wish");
                    return;
                }

                // Set the user's type as the animated text below the button.
                mTextView.setText(userInput);
                // Begin the animation.
                mTextView.startAnimation(mAnimation);

            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Check if there are items in the list, and if so, remove the list item and refresh the list.
                if (!mWishList.isEmpty()) {
                    mWishList.remove(position);
                    mAdapter.notifyDataSetChanged();
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

}