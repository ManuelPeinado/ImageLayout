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
package com.manuelpeinado.imagelayout.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.manuelpeinado.imagelayout.ImageLayout;

public class FitAttributeActivity extends Activity {

    private ImageLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_attribute);
        imageLayout = (ImageLayout) findViewById(R.id.imageLayout);
        initFitMode(savedInstanceState);
    }

    private void initFitMode(Bundle savedInstanceState) {
        int fitMode = imageLayout.getFitMode();
        if (savedInstanceState != null) {
            fitMode = savedInstanceState.getInt("fitMode", fitMode);
        }
        setFitMode(fitMode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fit_attribute, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.fit_vertical:
            setFitMode(ImageLayout.FIT_VERTICAL);
            return true;
        case R.id.fit_horizontal:
            setFitMode(ImageLayout.FIT_HORIZONTAL);
            return true;
        case R.id.fit_auto:
            setFitMode(ImageLayout.FIT_AUTO);
            return true;
        }
        return false;
    }

    private void setFitMode(int fitMode) {
        imageLayout.setFitMode(fitMode);
        updateTitle();
    }

    private void updateTitle() {
        switch (imageLayout.getFitMode()) {
        case ImageLayout.FIT_AUTO:
            setTitle(R.string.fit_auto);
            return;
        case ImageLayout.FIT_VERTICAL:
            setTitle(R.string.fit_vertical);
            return;
        case ImageLayout.FIT_HORIZONTAL:
            setTitle(R.string.fit_horizontal);
            return;
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fitMode", imageLayout.getFitMode());
    }
}
