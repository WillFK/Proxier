package com.fk.proxier.ui.wifi

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.fk.proxier.R
import com.fk.proxier.domain.Wifi
import kotlinx.android.synthetic.main.layout_wifi_tile.view.*

class WifiTile(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_wifi_tile, this)
    }

    fun setup(wifi: Wifi) {
        title_tv?.text = wifi.name
    }
}