/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package test_demo.example.com.applicationimgurapi.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import test_demo.example.com.applicationimgurapi.R;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private Button btnSubmit;
    private EditText edtImgId;
    private ImageView image;
    private LinearLayout llImageID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtImgId =findViewById(R.id.edt_img_id);
        btnSubmit =findViewById(R.id.btn_submit);
        image =findViewById(R.id.image);
        llImageID =findViewById(R.id.ll_img_id);
        btnSubmit.setOnClickListener(this);

        progressBar = findViewById(R.id.progress);
        presenter = new MainPresenter(this, new FindItemsInteractor());
    }

    @Override
    protected void onResume() {
        super.onResume();
       // presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);

    }



    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadImage() {
        Picasso.get().load("https://imgur.com/a/ywMQNfC").placeholder(R.drawable.ic_image).into(image);
    }

    @Override
    public void onClick(View v) {
        llImageID.setVisibility(View.GONE);
        presenter.loadImage();
        //hideProgress();
    }
}
