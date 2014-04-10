package com.manuelpeinado.imagelayout.demo;

/*
 * Copyright (C) 2014 Manuel Peinado
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

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.manuelpeinado.imagelayout.ImageLayout;

public class ChangeImageDynamicallyActivity extends Activity {

    private ImageLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image_dynamically);
        imageLayout = (ImageLayout)findViewById(R.id.image_layout);
    }

    public void onButtonClick(View view) {
        imageLayout.setImageResource(R.drawable.manhattan, 800, 1449);
        imageLayout.setGravity(Gravity.CENTER);
        imageLayout.setPadding(0, -100, 0, -100);
        imageLayout.setFitMode(ImageLayout.FIT_VERTICAL);
        imageLayout.removeAllViews();

        Button button = new Button(this);
        ImageLayout.LayoutParams layoutParams = new ImageLayout.LayoutParams();
        layoutParams.left = 243;
        layoutParams.top = 297;
        layoutParams.right = 432;
        layoutParams.bottom = 405;
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.normal_text));
        button.setText(R.string.harlem);
        imageLayout.addView(button, layoutParams);
    }
}
