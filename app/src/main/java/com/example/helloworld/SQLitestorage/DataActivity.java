package com.example.helloworld.SQLitestorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;
import com.example.helloworld.util.ToastUtil;

/**
 * com.example.helloworld.SQLitestorage
 * HelloWorld
 *
 * @author:Tom 2020/8/7
 * 描述:
 **/

public class DataActivity extends AppCompatActivity {

    private EditText mEtName,mEtAuthor,mEtPrice,mEtPages;
    private Button mBtnCreate,mBtnAddData,mBtnUpdate,mBtnDelete,mBtnQuerydata;
    private Button mBtnSqlInsert,mBtnSqlQuery,mBtnSqlDelete,mBtnSqlUpdate;
    private StringBuffer sb;

    private MyDatabaseHelper dbHelper;
    private TextView mTvGetdata,mTvSqlGetdata;
//    private String bookName,bookAuthor,bookPrice,bookPages;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        mEtName = findViewById(R.id.et_name);
        mEtAuthor = findViewById(R.id.et_author);
        mEtPrice = findViewById(R.id.et_price);
        mEtPages = findViewById(R.id.et_page);
        mBtnCreate=findViewById(R.id.btn_createdatabase);
        mBtnUpdate = findViewById(R.id.btn_update);
        mTvGetdata = findViewById(R.id.tv_getdata);
        mBtnQuerydata = findViewById(R.id.btn_querydata);
        mTvSqlGetdata = findViewById(R.id.tv_SqlGetdata);

        //通过构造函数方法将数据库的名称指定为BookStore.db,版本号为1
        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,3);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一次点击创建时 ,会调用MyDatabaseHelper的onCreate方法,再次点击则不会创建
                dbHelper.getWritableDatabase();
            }
        });

        mBtnAddData = findViewById(R.id.btn_adddata);
        mBtnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = mEtName.getText().toString();
                String bookAuthor = mEtAuthor.getText().toString();
                String bookPrice= mEtPrice.getText().toString();
                String bookPages = mEtPages.getText().toString();
                SQLiteDatabase db =dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                /**
                 * 获取输入内容传入db
                 */
                values.put("name",bookName);
                values.put("author",bookAuthor);
                values.put("price",Double.parseDouble(bookPrice));
                values.put("pages",Integer.parseInt(bookPages));
                ToastUtil.showMsg(DataActivity.this,bookName+"插入成功");
                /**
                 * Test:测试
                 */
//                values.put("name","The Da Vince");
//                values.put("author","Dan");
//                values.put("price","26.23");
//                values.put("pages","56");

//                开始第二条数据
//                values.put("name",Name);
//                values.put("author",Author);
//                values.put("price",Price);
//                values.put("pages",Pages);
                db.insert("book",null,values);
                values.clear();

            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("book",values,"name = ?",new String[]{"The Lost Symbol"});
                ToastUtil.showMsg(DataActivity.this,"success");
            }
        });

        mBtnDelete=findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("book","pages<?",new String[]{"500"});
            }
        });

        mBtnQuerydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                sb = new StringBuffer();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){//将数据的指针移到第一行的位置
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        sb.append("id="+i+" name:"+name+"   author:"+author+"   pages:"+pages+" price:"+price+"\n");
                    }while (cursor.moveToNext());
                }
                cursor.close();
                mTvGetdata.setText(sb.toString());
            }
        });



        mBtnSqlUpdate= findViewById(R.id.btn_sql_update);
        mBtnSqlQuery = findViewById(R.id.btn_sql_query);
        mBtnSqlInsert = findViewById(R.id.btn_sql_insert);
        mBtnSqlDelete = findViewById(R.id.btn_sql_delete);

        mBtnSqlInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into book (name,author,pages,price) values (?,?,?,?)",new String[]{"The Da Vinci Code","Da Brown","510","19.95"} );
            }
        });

        mBtnSqlUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update book set price = ? where name = ?",new String[]{"19.12","The Da Vinci Code"});
            }
        });

        mBtnSqlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from book where name=?",new String[]{"The Da Vinci Code"});
            }
        });
        mBtnSqlQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                sb = new StringBuffer();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor=db.rawQuery("select * from book",null);
                if(cursor.moveToFirst()){//将数据的指针移到第一行的位置
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        sb.append("id="+i+" name:"+name+"   author:"+author+"   pages:"+pages+" price:"+price+"\n");
                    }while (cursor.moveToNext());
                }
                cursor.close();
                mTvSqlGetdata.setText(sb.toString());
            }
        });
    }
}
