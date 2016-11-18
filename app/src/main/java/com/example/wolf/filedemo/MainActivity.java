package com.example.wolf.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fphonedirectory;
    private File fExterbalStoragePublicdirectory;
    private File fExterbalStoragedirectory;
    private File fDataStorage;
    private File fDownloadCachedirectory;
    private File fRootdirectory;
    private String name,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (TextView) findViewById(R.id.result);
        //获取各种储存位置
        fphonedirectory=this.getFilesDir();
        fExterbalStoragePublicdirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExterbalStoragedirectory=Environment.getExternalStorageDirectory();
        fDataStorage=Environment.getDataDirectory();
        fDownloadCachedirectory=Environment.getDownloadCacheDirectory();
        fRootdirectory=Environment.getRootDirectory();

        //没有储存卡时,外部储存按钮无效
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_REMOVED)){
            Button btn= (Button) findViewById(R.id.extrenalStorageDirectory);
            btn.setEnabled(false);
        }
    }
    public void phoneDirectory(View v){
        path=fphonedirectory.getPath();
        try{
            FileOutputStream fos=openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listFiles(path);
    }
    public void externalStoragePublicDirectory(View v){
        path=fExterbalStoragePublicdirectory.getAbsolutePath();
        listFiles(path);
    }
    public void extrenalStorageDirectory(View v){
        path=fExterbalStoragedirectory.getAbsolutePath();
        listFiles(path);
    }
    public void dataStorage(View v){
        path=fDataStorage.getAbsolutePath();
        listFiles(path);
    }
    public void downloadCacheDirectory(View v){
        path=fDownloadCachedirectory.getAbsolutePath();
        listFiles(path);
    }
    public void rootDirectory(View v){
        path=fRootdirectory.getAbsolutePath();
        listFiles(path);
    }
    private boolean listFiles(String path){
        name = "路径:"+path+"\n文件清单:\n";
        File file=new File(path);
        if (file.listFiles()!=null&&file.listFiles().length>0){
            for (File f: file.listFiles()){
                path=f.getAbsolutePath();
                name=name+f.getName()+"\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}


