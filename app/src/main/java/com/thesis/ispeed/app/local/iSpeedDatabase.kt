package com.thesis.ispeed.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thesis.ispeed.app.shared.data.DisconnectedModel
import com.thesis.ispeed.app.shared.data.FirebaseInternetDataModel
import com.thesis.ispeed.app.shared.data.TrackInternetModel

@Database(
    entities = [DisconnectedModel::class, FirebaseInternetDataModel::class, TrackInternetModel::class],
    version = 1,
    exportSchema = false
)
abstract class iSpeedDatabase : RoomDatabase() {

    abstract fun disconnectedDao(): DisconnectedDao?

    abstract fun firebaseInternetDao(): FirebaseInternetDao?

    abstract fun trackInternetDao(): TrackInternetDao?
}