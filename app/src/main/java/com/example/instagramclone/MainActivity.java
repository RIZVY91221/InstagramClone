package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   private Button button;
   private EditText eName,ePunchSpeed,ePunchPower;
   private TextView textView;
   private Button allDatafromServer;
   private String allBoxer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        eName=(EditText)findViewById(R.id.editText2);
        ePunchSpeed=(EditText)findViewById(R.id.editText3);
        ePunchPower=(EditText)findViewById(R.id.editText4);
        textView=(TextView)findViewById(R.id.textView);
        allDatafromServer=(Button)findViewById(R.id.allQueary);
        button.setOnClickListener(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject>query= ParseQuery.getQuery("KickBoxer");
                query.getInBackground("yUdfxLfdyf", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e==null){
                            textView.setText(object.get("name")+ " "+ object.get("PunchPower") +object.get("punchSpeed"));
                        }
                        else {
                            FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true);
                        }
                    }
                });
            }
        });

        allDatafromServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBoxer="";
                final ParseQuery<ParseObject>allQeary=ParseQuery.getQuery("KickBoxer");
                allQeary.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e==null){
                            if (objects.size()>0){
                                for (ParseObject kickBoxer:objects){
                                   allBoxer=allBoxer + kickBoxer.get("name")+ " ,"+"punchPower: "+ kickBoxer.get("PunchPower")
                                           + " ," +"punchSpeed " + kickBoxer.get("PunchSpeed") + "\n";
                                }
                                FancyToast.makeText(MainActivity.this, allBoxer , FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                textView.setText(allBoxer);
                            }
                            else {
                                FancyToast.makeText(MainActivity.this, "Error!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });
            }
        });
        

    }


    @Override
    public void onClick(View v)
    {
        try {
            final ParseObject object = new ParseObject("KickBoxer");
            object.put("name", eName.getText().toString());
            object.put("PunchSpeed", Integer.parseInt(ePunchSpeed.getText().toString()));
            object.put("PunchPower", Integer.parseInt(ePunchPower.getText().toString()));

            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, "Successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, "Error!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }
        catch (Exception e){
            FancyToast.makeText(MainActivity.this,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
