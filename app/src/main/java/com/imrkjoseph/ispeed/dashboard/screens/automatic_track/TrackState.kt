package com.imrkjoseph.ispeed.dashboard.screens.automatic_track

import com.imrkjoseph.ispeed.app.shared.data.TrackInternetModel

open class TrackState

object ShowTrackNoData : TrackState()

object ShowTrackLoading : TrackState()

object ShowTrackDismissLoading : TrackState()

data class GetTrackList(val response: MutableList<TrackInternetModel>?) : TrackState()

data class GetUiItems(val uiItems: List<Any>) : TrackState()

data class ShowTrackError(val throwable: Throwable) : TrackState()