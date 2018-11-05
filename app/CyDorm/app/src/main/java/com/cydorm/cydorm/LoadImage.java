package com.cydorm.cydorm;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.techdew.stomplibrary.StompClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.util.ArrayList;

public class LoadImage extends AppCompatActivity {

    StompConnection sc;

    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);

        image = BitmapFactory.decodeResource(getResources(), R.drawable.receipt1);
        String language = "eng";
        datapath = getFilesDir()+ "/tesseract/";
        mTess = new TessBaseAPI();
        checkFile(new File(datapath + "tessdata/"));
        mTess.init(datapath, language);

        this.sc = new StompConnection("8B8CD50EF9319D75C54BB3489A8810D3");



    }

    private void checkFile(File dir) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    private void copyFiles() {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = getAssets();
            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();
            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String runOCR(View view){
        String OCRresult = null;
        mTess.setImage(image);
        OCRresult = mTess.getUTF8Text();
        TextView tv_OCR_Result = (TextView) findViewById(R.id.tv_OCR_Result);
        OCRresult = OCRresult.toLowerCase();
        ArrayList<GroceryItem> groceryList = getGroceryList(sc.sc);
        String output = "";
        for (int i = 0; i < groceryList.size(); i++) {
            if(OCRresult.contains(groceryList.get(i).getItem().toLowerCase())){
                removeGrocery(groceryList.get(i));
                output = output.concat("Removed " + groceryList.get(i).getItem() + " From shopping list\n");
            }
        }
        tv_OCR_Result.setText(output);
        return OCRresult;
    }

    private String[] getGroceries(){
        String[] testVals = new String[]{"Aquafina", "Sponge", "NOT REAL"};
        return testVals;
    }

    private void removeGrocery(GroceryItem item){
        this.sc.sc.send("/deleteGroceryItem", "{\"netID\":\"jpotter\" , " +
                "\"grocery_id\" : \"" + item.getID() + "\" }").subscribe();
    }

    private ArrayList<GroceryItem> getGroceryList(StompClient mStompClient) {

        ArrayList<GroceryItem> groceryList = new ArrayList<GroceryItem>();
        this.sc.sc.send("/dumpGrocery", "{ \"netID\":\"jpotter\" }").subscribe(topicMessage -> {
            try {
                JSONArray ja =
                        new JSONArray(Normalizer.normalize(topicMessage.toString(),
                                Normalizer.Form.NFC));
                for(int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    groceryList.add(new GroceryItem(jo));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return groceryList;
    }

    private void removeGroceryAndUpdate(StompClient mStompClient, String id) {
        this.sc.sc.send("/deleteGroceryItem", "{\"netID\":\"jpotter\" , " +
                "\"grocery_id\" : \"" + id + "\" }").subscribe();

        dumpGroceryAndUpdate(mStompClient);
    }

}
