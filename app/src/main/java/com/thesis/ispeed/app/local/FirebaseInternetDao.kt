package com.thesis.ispeed.app.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.thesis.ispeed.app.shared.data.FirebaseInternetDataModel
import com.thesis.ispeed.app.util.LocalConverter

@Dao
interface FirebaseInternetDao {
    @Query("SELECT * FROM internetDataModel")
    @TypeConverters(LocalConverter::class)
    fun fBInternetCount() : List<FirebaseInternetDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fbInternetModel: FirebaseInternetDataModel)
}