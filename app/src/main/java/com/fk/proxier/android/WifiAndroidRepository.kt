package com.fk.proxier.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.fk.proxier.domain.Wifi
import io.reactivex.Single
import io.reactivex.SingleEmitter
import android.net.wifi.WifiManager
import android.util.Log

class WifiAndroidRepository(private val context: Context) {

    fun getNetworksList(): Single<List<Wifi>> {
        return Single.create {
            val service = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            context.registerReceiver(getBroadCastReceiver(it, service), IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            service.startScan()
        }
    }

    private fun getBroadCastReceiver(emitter: SingleEmitter<List<Wifi>>, manager: WifiManager) = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            if (intent?.action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                Log.d("FKZ", "${manager.scanResults}")
                manager.scanResults
                        .groupBy { it.SSID }
                        .map {
                            Log.d("FKZ", "$it")
                            Wifi(it.value.first().SSID)
                        }
                        //TODO edge cases
                        .apply { emitter.onSuccess(this) }
            }
        }
    }
}