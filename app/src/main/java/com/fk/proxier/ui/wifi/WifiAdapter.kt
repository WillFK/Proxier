package com.fk.proxier.ui.wifi

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.fk.proxier.domain.Wifi

/**
 * Created by fk on 23.03.18.
 */
class WifiAdapter(var wifis: List<Wifi>) : RecyclerView.Adapter<WifiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
        return WifiViewHolder(WifiTile(parent.context))
    }

    override fun getItemCount(): Int {
        return wifis.size
    }

    override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
        holder.setup(wifis[position])
    }
}