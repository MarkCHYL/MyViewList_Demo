package demo.mark.com.mycoordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.mark.com.mycoordinatorlayout.ui.CoordinatorLayoutOne;
import demo.mark.com.mycoordinatorlayout.ui.CoordinatorLayoutThree;
import demo.mark.com.mycoordinatorlayout.ui.CoordinatorLayoutTwo;

public class MarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

    }

    public void doOne(View view){
        startActivity(new Intent(MarkActivity.this, CoordinatorLayoutOne.class));
    }

    public void doTwo(View view){
        startActivity(new Intent(MarkActivity.this, CoordinatorLayoutTwo.class));
    }

    public void doThree(View view){
        startActivity(new Intent(MarkActivity.this, CoordinatorLayoutThree.class));
    }
}
