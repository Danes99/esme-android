package org.libreapps.thinkit.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.libreapps.thinkit.models.UserPostModel;
import org.libreapps.thinkit.utils.AppUtils;

import java.util.ArrayList;

public class DbController {

    private SQLiteDatabase database;

    public DbController(Context context) {
        database = DatabaseHelper.getInstance(context).getWritableDatabase();
    }


    /*
    * Insert Post Data to Database
    * */
    public int insertData(String title, String content, String owner, int isComplete){

        ContentValues contentValue = new ContentValues();
        contentValue.put(DbConstants.ID, AppUtils.getUniqueId());
        contentValue.put(DbConstants.TITLE, title);
        contentValue.put(DbConstants.CONTENT, content);
        contentValue.put(DbConstants.OWNER, owner);
        contentValue.put(DbConstants.CREATED_AT, AppUtils.getCurrentTime());
        contentValue.put(DbConstants.UPDATED_AT, AppUtils.getCurrentTime());
        contentValue.put(DbConstants.VERSION, "1.0.0");
        contentValue.put(DbConstants.IS_COMPLETE, isComplete);
        contentValue.put(DbConstants.IS_FAVORITE, 0);

        return (int) database.insert(DbConstants.POST_TABLE_NAME, null, contentValue);

    }

    public int addToFavorite(int id){

        ContentValues contentValue = new ContentValues();
        contentValue.put(DbConstants.IS_FAVORITE, 1);
        contentValue.put(DbConstants.UPDATED_AT, AppUtils.getCurrentTime());
        return (int) database.update(DbConstants.POST_TABLE_NAME,  contentValue,"_id="+id,null);
    }

    public int removeFromFavorite(int id){

        ContentValues contentValue = new ContentValues();
        contentValue.put(DbConstants.IS_FAVORITE, 0);
        contentValue.put(DbConstants.UPDATED_AT, AppUtils.getCurrentTime());
        return (int) database.update(DbConstants.POST_TABLE_NAME,  contentValue,"_id="+id,null);
    }

    /*
     * Update Post Data to Database
     * */
    public int updateData(int id,String title, String content){

        ContentValues contentValue = new ContentValues();
        contentValue.put(DbConstants.ID, AppUtils.getUniqueId());
        contentValue.put(DbConstants.TITLE, title);
        contentValue.put(DbConstants.CONTENT, content);
        contentValue.put(DbConstants.UPDATED_AT, AppUtils.getCurrentTime());

        return (int) database.update(DbConstants.POST_TABLE_NAME,  contentValue,"_id="+id,null);

    }
    /*
     * Get Single post by ID
     * */
    public UserPostModel getSinglePost(int post_id){
        Cursor cursor = database.rawQuery("select  * from " + DbConstants.POST_TABLE_NAME + " where " + DbConstants._ID + "=" + post_id + "", null);
        UserPostModel post = null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants._ID));
            String postId = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.CONTENT));
            String owner = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.OWNER));
            String createdAt = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.CREATED_AT));
            String updatedAt = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.UPDATED_AT));
            String version = cursor.getString(cursor.getColumnIndexOrThrow(DbConstants.VERSION));
            int complete = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.IS_COMPLETE));
            int isFav = cursor.getInt(cursor.getColumnIndexOrThrow(DbConstants.IS_FAVORITE));

            // wrap up data list and return
            post = new UserPostModel(id,complete,postId,title,content,owner,createdAt,updatedAt,version,isFav);
        }
        return post;
    }
    /*
     * Get All Post from Database
     * */
    public ArrayList<UserPostModel> getAllPost(){
        String[] projection = {
                DbConstants._ID,
                DbConstants.ID,
                DbConstants.TITLE,
                DbConstants.CONTENT,
                DbConstants.OWNER,
                DbConstants.CREATED_AT,
                DbConstants.UPDATED_AT,
                DbConstants.VERSION,
                DbConstants.IS_COMPLETE,
                DbConstants.IS_FAVORITE,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DbConstants._ID + " DESC";

        Cursor c = database.query(
                DbConstants.POST_TABLE_NAME,  // The table name to query
                projection,                        // The columns to return
                null,                     // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                      // don't group the rows
                null,
                sortOrder                           // The sort order
        );

        return fetchNotesData(c);
    }

    /*
     * Get All Post from Database
     * */
    public ArrayList<UserPostModel> getAllLikedPost(){
        String[] projection = {
                DbConstants._ID,
                DbConstants.ID,
                DbConstants.TITLE,
                DbConstants.CONTENT,
                DbConstants.OWNER,
                DbConstants.CREATED_AT,
                DbConstants.UPDATED_AT,
                DbConstants.VERSION,
                DbConstants.IS_COMPLETE,
                DbConstants.IS_FAVORITE,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DbConstants._ID + " DESC";

        Cursor c = database.query(
                DbConstants.POST_TABLE_NAME,  // The table name to query
                projection,                        // The columns to return
                DbConstants.IS_FAVORITE + "=" + 1,                     // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                      // don't group the rows
                null,
                sortOrder                           // The sort order
        );

        return fetchNotesData(c);
    }

    private ArrayList<UserPostModel> fetchNotesData(Cursor c) {
        ArrayList<UserPostModel> favDataArray = new ArrayList<>();

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    // get  the  data into array,or class variable
                    int id = c.getInt(c.getColumnIndexOrThrow(DbConstants._ID));
                    String postId = c.getString(c.getColumnIndexOrThrow(DbConstants.ID));
                    String title = c.getString(c.getColumnIndexOrThrow(DbConstants.TITLE));
                    String content = c.getString(c.getColumnIndexOrThrow(DbConstants.CONTENT));
                    String owner = c.getString(c.getColumnIndexOrThrow(DbConstants.OWNER));
                    String createdAt = c.getString(c.getColumnIndexOrThrow(DbConstants.CREATED_AT));
                    String updatedAt = c.getString(c.getColumnIndexOrThrow(DbConstants.UPDATED_AT));
                    String version = c.getString(c.getColumnIndexOrThrow(DbConstants.VERSION));
                    int complete = c.getInt(c.getColumnIndexOrThrow(DbConstants.IS_COMPLETE));
                    int isFav = c.getInt(c.getColumnIndexOrThrow(DbConstants.IS_FAVORITE));
                    // wrap up data list and return
                    UserPostModel note = new UserPostModel(id,complete,postId,title,content,owner,createdAt,updatedAt,version,isFav);
                    favDataArray.add(note);
                } while (c.moveToNext());
            }
            c.close();
        }
        return favDataArray;
    }

    /*
     * Delete post from Database
     * */
    public void deletePost(int id) {
        database.delete(DbConstants.POST_TABLE_NAME, DbConstants._ID + "=" + id, null);
    }
}
