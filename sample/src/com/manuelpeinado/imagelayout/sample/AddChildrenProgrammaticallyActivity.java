/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manuelpeinado.imagelayout.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.manuelpeinado.imagelayout.ImageLayout;

public class AddChildrenProgrammaticallyActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_children_programmatically);
        
        ImageLayout imageLayout = (ImageLayout) findViewById(R.id.imageLayout);
        Button mastCamButton = new Button(this);
        ImageLayout.LayoutParams layoutParams = new ImageLayout.LayoutParams();
        layoutParams.right = 110;
        layoutParams.centerY = 80;
        mastCamButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.small_text));
        mastCamButton.setText(R.string.mastcam);
        mastCamButton.setOnClickListener(this);
        imageLayout.addView(mastCamButton, layoutParams);
    }

    @Override
    public void onClick(View view) {
        String msg = getString(R.string.you_have_selected);
        msg = msg + ": " + ((Button)view).getText();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
