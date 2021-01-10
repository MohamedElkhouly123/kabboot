package com.example.kabboot.data.local;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.kabboot.data.model.getAllproductsResponce.AllProduct;
import com.example.kabboot.data.model.getAllproductsResponce.AllProductForRom;

import java.util.List;


@Dao
public interface ClientMakeNewOrderItemForRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AllProductForRom... orderItem);

    @Insert
    void add(AllProductForRom... orderItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(AllProductForRom... orderItem);

    @Delete
    void delete(AllProductForRom... orderItem);

    @Update
    void updateUserDate(AllProductForRom orderItem);

//    @Query("SELECT * FROM clientNewOrder")
//    MutableLiveData<List<ClientMakeNewOrderItemForRoom>> getusers();

    @Query("SELECT * FROM clientNewOrder")
    List<AllProductForRom> getAllItems();
//    LiveData<List<ClientMakeNewOrderItemForRoom>> getAllItems();

    @Query("SELECT * FROM clientNewOrder WHERE  itemId = :id")
    AllProductForRom getUsersById(int id);


    @Query("SELECT * FROM clientNewOrder WHERE itemId = :id")
    LiveData<AllProductForRom> getSubjectById(int id);

//    @Query("select * from clientNewOrder where active = 1;")
//    ClientData checkIfUserExist();

    @Query("DELETE FROM clientNewOrder;")
    void deletAll();
}
