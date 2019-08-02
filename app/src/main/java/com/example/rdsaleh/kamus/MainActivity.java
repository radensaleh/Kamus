package com.example.rdsaleh.kamus;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.rdsaleh.kamus.Database.KamusHelper;
import com.example.rdsaleh.kamus.Model.KamusModel;
import com.example.rdsaleh.kamus.Prefs.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        new LoadData().execute();

    }

    private class LoadData extends AsyncTask<Void, Integer, Void>{
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(MainActivity.this);
            appPreference = new AppPreference(MainActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();

            if(firstRun){
                ArrayList<KamusModel> kamusEn = preLoadRaw(R.raw.en_in);
                ArrayList<KamusModel> kamusIn = preLoadRaw(R.raw.in_en);

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / (kamusEn.size() + kamusIn.size());

                    if(kamusEn.size() > 0){
                        kamusHelper.open();
                        try{
                            for(KamusModel model : kamusEn){
                                kamusHelper.insertTransactionEN(model);
                                progress += progressDiff;
                                publishProgress((int) progress);
                            }
                            kamusHelper.setTransactionSuccess();
                        } catch (Exception e){
                            Log.e(TAG, "doInBackground: Exception");
                        }
                        kamusHelper.close();
                    }

                    if(kamusIn.size() > 0){
                        kamusHelper.open();
                        try{
                            for(KamusModel model : kamusIn){
                                kamusHelper.insertTransactionIN(model);
                                progress += progressDiff;
                                publishProgress((int) progress);
                            }
                            kamusHelper.setTransactionSuccess();
                        } catch (Exception e){
                            Log.e(TAG, "doInBackground: Exception");
                        }
                        kamusHelper.close();
                    }

                appPreference.setFirstRun(false);
                publishProgress((int) maxprogress);
            }else{
                try {
                    synchronized (this) {
                        this.wait(2000);

                        publishProgress(50);

                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i = new Intent(MainActivity.this, INENActivity.class);
            startActivity(i);
            finish();
        }

        public ArrayList<KamusModel> preLoadRaw(int raw){
            ArrayList<KamusModel> kamusModels = new ArrayList<>();
            String line = null;
            BufferedReader reader;

            try {
                Resources res = getResources();
                InputStream raw_dict = res.openRawResource(raw);

                reader = new BufferedReader(new InputStreamReader(raw_dict));
                int count = 0;
                do {
                    line = reader.readLine();
                    String[] splitstr = line.split("\t");

                    KamusModel kamusModel;

                    kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                    kamusModels.add(kamusModel);
                    count++;
                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return kamusModels;
        }

    }

}
