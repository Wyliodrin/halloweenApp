package com.wyliodrin.halloween;

import org.json.JSONException;
import org.json.JSONObject;

import com.wyliodrin.WylioBoard;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;


public class MainActivity extends Activity 
{
	int selected;
	TextView selectedColor;
	WylioBoard jack;
	EditText line;
	 final int[] myColors = new int[] {
     		Color.parseColor("#0000FF"),
     		Color.parseColor("#00FF00"),
     		Color.parseColor("#00FFFF"),
     		Color.parseColor("#FF0000"),
     		Color.parseColor("#FF00FF"),
     		Color.parseColor("#FFFF00")
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        jack = new WylioBoard("Please insert your board token");
      
        selected = myColors[1];
        selectedColor = (TextView) findViewById(R.id.color);
        selectedColor.setBackgroundColor(selected);
        
        line = (EditText) findViewById(R.id.pumpkinline);
        
        TableLayout table = (TableLayout) findViewById(R.id.color_table);
        int l = 1;
        TableRow row = new TableRow(this);
        row.setPadding(0, 10, 0, 10);
        for (int i=0; i<myColors.length; i++)
        {
        	final int pos = i;
        	Button b = new Button(this);
            b.setBackgroundColor(myColors[i]);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 0);
            b.setLayoutParams(layoutParams);
            row.addView(b); 
            b.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				selected = myColors[pos];
    				selectedColor.setBackgroundColor(selected);
    			}
    		});
        	if(l%3 == 0)
        	{        		
        		table.addView(row);
        		row = new TableRow(this);
        		row.setPadding(0, 10, 0, 10);
        	}
        	l++;
        }
    }
    
    public void animate(View v) 
    {    	
    	try {
    		JSONObject mes = new JSONObject();
    		String hexColor = String.format("#%06X", (0xFFFFFF & selected));
    		mes.put("color", hexColor);
    		Editable l = line.getText();
    		String jackLine;
    		if(l != null)
    		{
    			jackLine = l.toString();
    		}
    		else
    		{
    			jackLine = "";
    		}
    		mes.put("line",jackLine);
			jack.sendMessage ("jack",mes);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("could no sent");
		}
    }
}
