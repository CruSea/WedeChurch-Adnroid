//
//package com.gcme.wedechurch.Database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//
//import com.gcme.wedechurch.Objects.Church;
//import com.gcme.wedechurch.Objects.Fav;
//import com.gcme.wedechurch.Objects.Schedule;
//
//import java.util.ArrayList;
//
//import static com.gcme.wedechurch.wedechurch.getStrArrFromEnum;
//
//
//public class Database {
//    public static final String churchTable = "churchTable";
//    public static final String scheduleTable = "scheduleTable";
//    public static final String taskTable = "taskTable";
//    public static final String favoriteTable = "favoriteTable";
//
//
//
//    private SQLiteDatabase myDatabase;
//    private SQL_Helper mySQL;
//    private Context myContext;
//    public static final String TAG = "Database";
//
//    public enum churchColumn {
//
//        id ("id"),
//        churchName("churchName"),
//        country ("country"),
//        cities ("cities"),
//        location ("location"),
//        latitude ("latitude"),
//        longitude ("longitude"),
//        phone ("phone"),
//        webUrl ("webUrl"),
//        groupId ("groupId"),
//        banner ("banner"),
//        description ("description"),
//        logo ("logo"),
//        parentChurchId ("parentChurchId"),
//        state ("state");
//
//
//        private final String name;
//
//        churchColumn(String s) {
//            this.name = s;
//        }
//
//        public boolean equalsName(String otherName) {
//            return (otherName == null) ? false : name.equals(otherName);
//        }
//
//        @Override
//        public String toString() {
//            return this.name;
//        }
//    }
//    public enum churchSchedulsColumn {
//
//        id ("id"),
//        churchId("churchId"),
//        date ("date"),
//        startingTime ("startingTime"),
//        endTime ("endTime"),
//        redundancy ("redundancy"),
//        scheduleCategoryId("scheduleCategoryId");
//
//
//        private final String name;
//
//        churchSchedulsColumn(String s) {
//            this.name = s;
//        }
//
//        public boolean equalsName(String otherName) {
//            return (otherName == null) ? false : name.equals(otherName);
//        }
//
//        @Override
//        public String toString() {
//            return this.name;
//        }
//    }
//    public enum taskColumn {
//
//        id ("id");
//
//        private final String name;
//        taskColumn(String s) {
//            this.name = s;
//        }
//
//        public boolean equalsName(String otherName) {
//            return (otherName == null) ? false : name.equals(otherName);
//        }
//
//        @Override
//        public String toString() {
//            return this.name;
//        }
//    }
//    public enum favoriteColumn {
//
//        id ("id"),
//        userId ("userId"),
//        churchId ("churchId");
//
//        private final String name;
//        favoriteColumn(String s) {
//            this.name = s;
//        }
//
//        public boolean equalsName(String otherName) {
//            return (otherName == null) ? false : name.equals(otherName);
//        }
//
//        @Override
//        public String toString() {
//            return this.name;
//        }
//    }
//
//
//
//
//
//    public Database(Context context) {
//        myContext = context;
//        mySQL = new SQL_Helper(myContext);
//        myDatabase = mySQL.getWritableDatabase();
//        mySQL.createTable(churchTable, getStrArrFromEnum(churchColumn.class));
//        mySQL.createTable(scheduleTable, getStrArrFromEnum(churchSchedulsColumn.class));
//        mySQL.createTable(taskTable, getStrArrFromEnum(taskColumn.class));
//        mySQL.createTable(favoriteTable, getStrArrFromEnum(favoriteColumn.class));
//
//    }
//
//    public long insert(String DB_Table, ContentValues cv) {
//        long state = myDatabase.insert(DB_Table, null, cv);
//        Log.d(TAG, "Inserting->: " + cv.toString());
//        return state;
//    }
//
//    public long Delete_All(String DB_Table) {
//        long state = myDatabase.delete(DB_Table, null, null);
//        return state;
//    }
//
//    public long remove(String DB_Table, int id) {
//        String[] args = {"" + id};
//        long val = myDatabase.delete(DB_Table, "id = ?", args);
//        return val;
//    }
//
//    public long update(String DB_Table, ContentValues cv, int id) {
//        Log.d(TAG, "Updating Table: " + DB_Table);
//        String[] args = {"" + id};
//        long numRowsAffected = myDatabase.update(DB_Table, cv, "id = ?", args);
//        Log.d(TAG, "Updating Data: " + cv.toString());
//        Log.d(TAG, "Updating Completed: " + numRowsAffected + " rows affected");
//        return numRowsAffected;
//    }
//
//    public int count(String DB_Table) {
//        Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//        if (c != null) {
//            return c.getCount();
//        } else {
//            return 0;
//        }
//    }
//
//    public Cursor getAll(String DB_Table) {
//        Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//        return c;
//    }
//
//    public String get_Value_At_Top(String DB_Table, String column) {
//        String str = "";
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            str = c.getString(c.getColumnIndex(column));
//        } catch (Exception e) {
//
//        }
//
//        return str;
//    }
//
//    public String get_Value_At_Bottom(String DB_Table, String column) {
//        String str = "";
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToLast();
//            str = c.getString(c.getColumnIndex(column));
//        } catch (Exception e) {
//
//        }
//        return str;
//    }
//
//    public Cursor get_value_by_ID(String DB_Table, String id) {
//        Cursor cur = myDatabase.rawQuery("select * from " + DB_Table + " where id=" + id, null);
//        return cur;
//    }
//
//    public String get_Name_by_category(String category) {
//        String name = "";
//        try {
//            String DB_Table = churchTable;
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), churchColumn.churchName.toString() + " = '" + category + "'", null, null, null, null);
//            c.moveToLast();
//            name = c.getString(c.getColumnIndex(churchColumn.churchName.toString()));  // briggsm: This func is not ever used, but if it were, I think it would not work as is.
//        } catch (Exception e) {
//
//        }
//        return name;
//    }
//
//    public long Delete_By_ID(String DB_Table, int pos) {
//        String[] args = {"" + pos};
//        long val = myDatabase.delete(DB_Table, "id = ?", args);
//        return val;
//    }
//
//    public long Delete_By_Column(String DB_Table, String column, String val) {
//        String[] args = {val};
//        long v = myDatabase.delete(DB_Table, column + " = ?", args);
//        return v;
//    }
//
//    public int get_Top_ID(String DB_Table) {
//        int pos = -1;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            pos = Integer.valueOf(c.getString(c.getColumnIndex("id")));
//        } catch (Exception e) {
//
//        }
//        return pos;
//    }
//
//
//
//    /**Start of Church Database oprations**/
//
//    public Church getChurchByID(int id) {
//        Log.d(TAG, "getRadioChannelByID: " + id);
//        String DB_Table = churchTable;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(churchColumn.id.toString())));
//                    if (cur_id == id) {
//                        Church churchs = new Church();
//                        churchs.setId(Integer.valueOf(c.getString(c.getColumnIndex(churchColumn.id.toString()))));
//                        churchs.setChurchName(c.getString(c.getColumnIndex(churchColumn.churchName.toString())));
//                        churchs.setCountry(c.getString(c.getColumnIndex(churchColumn.country.toString())));
//                        churchs.setCities(c.getString(c.getColumnIndex(churchColumn.cities.toString())));
//                        churchs.setLocation(c.getString(c.getColumnIndex(churchColumn.location.toString())));
//                        churchs.setLatitude(c.getString(c.getColumnIndex(churchColumn.latitude.toString())));
//                        churchs.setLocation(c.getString(c.getColumnIndex(churchColumn.longitude.toString())));
//                        churchs.setPhone(c.getString(c.getColumnIndex(churchColumn.phone.toString())));
//                        churchs.setWebUrl(c.getString(c.getColumnIndex(churchColumn.webUrl.toString())));
//                        churchs.setGroupId(c.getString(c.getColumnIndex(churchColumn.groupId.toString())));
//                        churchs.setBanner(c.getString(c.getColumnIndex(churchColumn.banner.toString())));
//                        churchs.setDescription(c.getString(c.getColumnIndex(churchColumn.description.toString())));
//                        churchs.setLogo(c.getString(c.getColumnIndex(churchColumn.logo.toString())));
//                        churchs.setParentChurchId(c.getString(c.getColumnIndex(churchColumn.parentChurchId.toString())));
//                        churchs.setState(c.getString(c.getColumnIndex(churchColumn.state.toString())));
//                        return churchs;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Failed Get Disciple by ID: " + e.toString());
//            return null;
//        }
//        return null;
//
//    }
//
//
//
//    public Church getChurchByName(String name) {
//        Log.d(TAG, "Get Church by name: " + name);
//        String DB_Table = churchTable;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    String Rad_name = c.getString(c.getColumnIndex(churchColumn.churchName.toString()));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(churchColumn.id.toString())));
//                    if (Rad_name.equals(name)) {
//                        Church lvRad = getChurchByID(cur_id);
//                        return lvRad;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Failed Get Disciple by Phone: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//
//
//
//    public ArrayList<Church> getAllChurches() {
//        String DB_Table = churchTable;
//        ArrayList<Church> found = new ArrayList<Church>();
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            for (int i = 0; i < c.getCount(); i++) {
//                c.moveToPosition(i);
//                int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(churchColumn.id.toString())));
//                Church lvrad = getChurchByID(cur_id);
//                Log.d(TAG, "Get All Churches: " + lvrad);
//
//                if (lvrad != null) {
//                    Log.d(TAG, "Get All Churches: " + lvrad);
//                    found.add(lvrad);
//                   }
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return found;
//    }
//
//
//    public long AddChurches(Church church) {
//        ContentValues cv = new ContentValues();
//        cv.put(Database.churchColumn.id.toString(), church.getId());
//        cv.put(Database.churchColumn.churchName.toString(), church.getChurchName());
//        cv.put(Database.churchColumn.country.toString(), church.getCountry());
//        cv.put(Database.churchColumn.cities.toString(), church.getCities());
//        cv.put(Database.churchColumn.location.toString(), church.getLocation());
//        cv.put(Database.churchColumn.latitude.toString(), church.getLatitude());
//        cv.put(Database.churchColumn.longitude.toString(), church.getLongitude());
//        cv.put(Database.churchColumn.phone.toString(), church.getPhone());
//        cv.put(Database.churchColumn.webUrl.toString(), church.getWebUrl());
//        cv.put(Database.churchColumn.groupId.toString(), church.getGroupId());
//        cv.put(Database.churchColumn.banner.toString(), church.getBanner());
//        cv.put(Database.churchColumn.description.toString(), church.getDescription());
//        cv.put(Database.churchColumn.logo.toString(), church.getLogo());
//        cv.put(Database.churchColumn.parentChurchId.toString(), church.getParentChurchId());
//        cv.put(Database.churchColumn.state.toString(), church.getState());
//        Church old_church = getChurchByID( church.getId());
//        if (old_church == null) {
//            long x = insert(Database.churchTable, cv);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Church Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Church -> \n" + cv.toString());
//            }
//            return x;
//        }
//        else {
//            long x = update(Database.churchTable, cv, old_church.getId());
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Church Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Church -> \n" + cv.toString());
//            }
//            return x;
//        }
//
//    }
//
//    /**End of Church Database oprations**/
//
//
//
//
//
//
//
//    /**Start of Church Schedule Database oprations**/
//
//    public Schedule getChurchScheduleByID(int id) {
//        Log.d(TAG, "getRadioChannelByID: " + id);
//        String DB_Table = scheduleTable;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(churchColumn.id.toString())));
//                    if (cur_id == id) {
//                        Schedule schedule = new Schedule();
//
//                        schedule.setId(Integer.valueOf(c.getString(c.getColumnIndex(churchSchedulsColumn.id.toString()))));
//                        schedule.setChurchId(c.getString(c.getColumnIndex(churchSchedulsColumn.churchId.toString())));
//                        schedule.setDate(c.getString(c.getColumnIndex(churchSchedulsColumn.date.toString())));
//                        schedule.setStartingTime(c.getString(c.getColumnIndex(churchSchedulsColumn.startingTime.toString())));
//                        schedule.setEndTime(c.getString(c.getColumnIndex(churchSchedulsColumn.endTime.toString())));
//                        schedule.setRedundancy(c.getString(c.getColumnIndex(churchSchedulsColumn.redundancy.toString())));
//                        schedule.setScheduleCategoryId(c.getString(c.getColumnIndex(churchSchedulsColumn.scheduleCategoryId.toString())));
//
//
//                        return schedule;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Failed Get Church Schedule by ID: " + e.toString());
//            return null;
//        }
//        return null;
//
//    }
//
//
//    public long AddChurchesSchedule(Schedule schedule) {
//        ContentValues cv = new ContentValues();
//        cv.put(Database.churchSchedulsColumn.id.toString(), schedule.getId());
//        cv.put(Database.churchSchedulsColumn.churchId.toString(), schedule.getChurchId());
//        cv.put(Database.churchSchedulsColumn.date.toString(), schedule.getDate());
//        cv.put(Database.churchSchedulsColumn.startingTime.toString(), schedule.getStartingTime());
//        cv.put(Database.churchSchedulsColumn.endTime.toString(), schedule.getEndTime());
//        cv.put(Database.churchSchedulsColumn.redundancy.toString(), schedule.getRedundancy());
//        cv.put(Database.churchSchedulsColumn.scheduleCategoryId.toString(), schedule.getScheduleCategoryId());
//
//        Church old_church_schedule = getChurchByID( schedule.getId());
//        if (old_church_schedule == null) {
//            long x = insert(Database.scheduleTable, cv);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Church Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Church -> \n" + cv.toString());
//            }
//            return x;
//        }
//        else {
//            long x = update(Database.scheduleTable, cv, old_church_schedule.getId());
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Church Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Church -> \n" + cv.toString());
//            }
//            return x;
//        }
//
//    }
//
//    /**End of Church Database oprations**/
//
//
//
//
//
//    /**Start of Task table oprations**/
//
//
//    public int getTask() {
//
//        String DB_Table = taskTable;
//        int cur_id;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            cur_id= Integer.valueOf(c.getString(c.getColumnIndex(taskColumn.id.toString())));
//
//                if (cur_id!=0) {
//                    Log.d(TAG, "Get last task Id: " + cur_id);
//
//                }
//
//        } catch (Exception e) {
//            return 0;
//        }
//        return cur_id;
//    }
//
//    public void AddTaskId(int task) {
//        ContentValues cv = new ContentValues();
//        cv.put(Database.churchSchedulsColumn.id.toString(), task);
//
//
//        int old_task = getTask();
//        if (old_task < task || old_task>0) {
//            long x = update(Database.taskTable, cv, old_task);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Task Id  -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Task Id  -> \n" + cv.toString());
//            }
//        }else {
//            long x = insert(Database.taskTable, cv);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Task Id -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Task Id  -> \n" + cv.toString());
//            }
//
//        }
//
//
//    }
//
//    /**end of Task oprations**/
//
//
//
//
//
//
//    /**Start of Fav Table oprations**/
//
//    public Fav getFavByID(int id) {
//        Log.d(TAG, "getRadioChannelByID: " + id);
//        String DB_Table = churchTable;
//        try {
//            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(favoriteColumn.userId.toString())));
//                    if (cur_id == id) {
//                        Fav favorites = new Fav();
//                        favorites.setId(Integer.valueOf(c.getString(c.getColumnIndex(favoriteColumn.id.toString()))));
//                        favorites.setUserId(c.getString(c.getColumnIndex(favoriteColumn.userId.toString())));
//                        favorites.setChurchId(c.getString(c.getColumnIndex(favoriteColumn.churchId.toString())));
//                        return favorites;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, "Failed Get Favirutes by ID: " + e.toString());
//            return null;
//        }
//        return null;
//
//    }
//    public long Addfav(Fav favortes) {
//        ContentValues cv = new ContentValues();
//        cv.put(Database.favoriteColumn.id.toString(), favortes.getId());
//        cv.put(Database.favoriteColumn.userId.toString(), favortes.getUserId());
//        cv.put(Database.favoriteColumn.churchId.toString(), favortes.getChurchId());
//        Church old_church = getChurchByID( favortes.getId());
//        if (old_church == null) {
//            long x = insert(Database.churchTable, cv);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Fav Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Fav -> \n" + cv.toString());
//            }
//            return x;
//        }
//        else {
//            long x = update(Database.churchTable, cv, old_church.getId());
//            if (x > 0) {
//                Log.d(TAG, "Successfully Added: Fav Added -> \n" + cv.toString());
//            } else {
//                Log.e(TAG, "Error During Adding: Fav -> \n" + cv.toString());
//            }
//            return x;
//        }
//
//    }
//    public long Deletefav(Fav favortes) {
//        int id=favortes.getId();
//
//            long x = remove(Database.favoriteTable, id);
//            if (x > 0) {
//                Log.d(TAG, "Successfully Deleted: Fav Added -> " + id);
//            } else {
//                Log.e(TAG, "Error During Deleting: Fav -> " + id);
//            }
//            return x;
//
//
//    }
//
//    /**End of Fav Table oprations**/
//
//
//
//
//    private String[] getColumns(String DB_Table) {
//        String[] strs = null;
//        if (DB_Table == churchTable) {
//            strs = getStrArrFromEnum(churchColumn.class);
//        }
//        else if (DB_Table == scheduleTable) {
//            strs = getStrArrFromEnum(churchSchedulsColumn.class);
//        }
////        else if (DB_Table == NewsSiteTable) {
////            strs = getStrArrFromEnum(NewsSitesColumn.class);
////        }
//        return strs;
//    }
//}
