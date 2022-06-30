package com.example.basic_beginning;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class unInstaller extends AppCompatActivity {

    ListView listView;
//    List<MainData> dataList=new ArrayList<> ();
    List<MainData> dataList = new ArrayList<> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_un_installer );

        listView=findViewById ( R.id.list_view );
        PackageManager manager=getPackageManager ();
        List<ApplicationInfo> infoList=manager.getInstalledApplications ( PackageManager.GET_META_DATA );
        for(ApplicationInfo info: infoList){
            if((info.flags & ApplicationInfo.FLAG_SYSTEM)==0){
                MainData data= new MainData();
                data.setName(info.loadLabel ( manager ).toString ());
                data.setPackagename ( info.packageName );
                data.setLogo(info.loadIcon ( manager ));
                dataList.add ( (data) );
            }
        }

        listView.setAdapter ( new BaseAdapter () {
            @Override
            public int getCount() {
                return dataList.size ();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position , View convertView , ViewGroup parent) {
                View view=getLayoutInflater ().inflate(R.layout.list_row_item, null);
                MainData data =dataList.get ( position );
                ImageView ivAppLogo = view.findViewById ( R.id.id_app_logo);
                TextView tvAppName= view.findViewById ( R.id.tv_app_name );
                Button btUninstall = view.findViewById ( R.id.bt_uninstall );
                ivAppLogo.setImageDrawable ( data.getLogo() );
                tvAppName.setText ( data.getName() );
                btUninstall.setOnClickListener ( new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        MainData maindata= dataList.get ( position );
                        Intent intent = new Intent ( Intent.ACTION_UNINSTALL_PACKAGE);
                        intent.setData ( Uri.parse ("package:" + maindata.getPackagename()) );
                        intent.putExtra ( Intent.EXTRA_RETURN_RESULT, true );
                        startActivityForResult ( intent,100);
                    }
                } );

                return view;
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult ( requestCode , resultCode , data );
        if(requestCode==100  && resultCode==RESULT_OK){
            Toast.makeText ( getApplicationContext (),"App Uninstall Successfully", Toast.LENGTH_LONG ).show();
            recreate ();
        }
    }

}