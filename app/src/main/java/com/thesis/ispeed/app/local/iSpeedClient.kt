package com.thesis.ispeed.app.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thesis.ispeed.app.shared.data.DisconnectedModel
import com.thesis.ispeed.app.shared.data.FirebaseInternetDataModel
import com.thesis.ispeed.app.shared.data.TrackInternetModel

@Database(entities = [DisconnectedModel::class, FirebaseInternetDataModel::class, TrackInternetModel::class], version = 1, exportSchema = false)
abstract class iSpeedClient : RoomDatabase() {

    abstract fun disconnectedDao(): DisconnectedDao?

    abstract fun firebaseInternetDao(): FirebaseInternetDao?

    abstract fun trackInternetDao(): TrackInternetDao?

    companion object {
        @Volatile
        private var DB_INSTANCE: iSpeedClient? = null

        fun getInstance(context: Context) = DB_INSTANCE ?: synchronized(this) {
            DB_INSTANCE ?: buildDatabase(context).also { DB_INSTANCE = it }
        }

        private fun buildDatabase(context: Context): iSpeedClient {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    iSpeedClient::class.java,
                    "iSpeed.db"
                ).allowMainThreadQueries().build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }
}