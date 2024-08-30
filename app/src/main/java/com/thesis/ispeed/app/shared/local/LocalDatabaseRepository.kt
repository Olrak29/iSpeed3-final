package com.thesis.ispeed.app.shared.local

import com.thesis.ispeed.app.local.iSpeedClient
import com.thesis.ispeed.app.shared.data.TrackInternetModel
import javax.inject.Inject

class LocalDatabaseRepository @Inject constructor(
    databaseService: iSpeedClient,
) {

    private val localClient = databaseService.trackInternetDao()

    suspend fun getInternetStatusList(count: Int) =  localClient?.getTrackInternetData(count = count)

    suspend fun getAllTrackList() =  localClient?.getAllTrackList()

    suspend fun getRowCount() = localClient?.getRowCount()

    fun saveInternetStatus(trackInternetModel: TrackInternetModel) = localClient?.insert(trackInternetModel)
}