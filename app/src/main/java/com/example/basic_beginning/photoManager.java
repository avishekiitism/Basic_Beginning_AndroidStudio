package com.example.basic_beginning;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class photoManager extends AppCompatActivity {

    ArrayList<File> list;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_photo_manager );

        gridView=(GridView) findViewById ( R.id.image_grid );
        list= imageReader( Environment.getExternalStorageDirectory () );
        gridView.setAdapter ( new gridAdapter ()  );
        gridView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent intent=new Intent (photoManager.this , FullImageActivity.class);
                intent.putExtra ( "img", list.get(position).toString () );
                startActivity ( intent );
            }
        } );

    }

    public class gridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position , View convertview , ViewGroup parent) {
                convertview=getLayoutInflater ().inflate ( R.layout.row_layout , parent , false );
                ImageView myImage= (ImageView) convertview.findViewById ( R.id.my_image );
                myImage.setImageURI ( Uri.parse ( list.get(position).toString () ) );

            return convertview;
        }
    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {
        ArrayList<File> b=new ArrayList<> ();
        File[] files=externalStorageDirectory.listFiles ();
        for(int i = 0; i< (files != null ? files.length : 0 ); i++){
            if(files[i].isDirectory ()){
                b.addAll ( imageReader ( files[i] ) );
            }
            else{
                if(files[i].getName ().endsWith ( ".jpg" )){
                    b.add(files[i]);
                }
            }
        }
        return b;
    }
}