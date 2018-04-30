package com.fk.proxier.ui.wifi

import android.support.v7.widget.RecyclerView
import com.fk.proxier.domain.Wifi

/**
 * Created by fk on 23.03.18.
 */
class WifiViewHolder(private val tile: WifiTile): RecyclerView.ViewHolder(tile) {

    fun setup(wifi: Wifi) {
        tile.setup(wifi)
    }
}