package com.fk.proxier.ui.wifi

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.fk.proxier.android.WifiAndroidRepository
import com.fk.proxier.domain.Wifi
import io.reactivex.disposables.CompositeDisposable

class WifiViewModel(application: Application) : AndroidViewModel(application) {

    val networks = MutableLiveData<WifiResponse>()
    private val repo = WifiAndroidRepository(application)
    private val disposables = CompositeDisposable()

    init {
        loadNetworks()
    }

    private fun loadNetworks() {
        repo.getNetworksList()
                .subscribe(
                        { networks.postValue(WifiResponse.Data(it)) },
                        { networks.postValue(WifiResponse.Error(it)) })
                .apply { disposables.add(this) }
    }

    override fun onCleared() {
        disposables.clear()
    }
}

sealed class WifiResponse {

    data class Data(val list: List<Wifi>) : WifiResponse()
    data class Error(val error: Throwable) : WifiResponse()
}