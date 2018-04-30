package com.fk.proxier.ui.wifi

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fk.proxier.R
import kotlinx.android.synthetic.main.fragment_wifi.*

/**
 * Created by fk on 23.03.18.
 */
class WifiFragment : Fragment() {

    private lateinit var viewModel: WifiViewModel

    companion object {
        val PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 42
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wifi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { setupUi(it) }
        viewModel = ViewModelProviders.of(this).get(WifiViewModel::class.java)
        viewModel.networks.observe(this, Observer { it?.let { processResponse(it) } })
    }

    private fun processResponse(response: WifiResponse) {
        when (response) {
            is WifiResponse.Data -> {
                wifies_fl.removeAllViews()
                activity?.let { context ->
                    response.list.forEach {
                        wifies_fl.addView(WifiTile(context).apply { setup(it) })
                    }
                }
            }
            is WifiResponse.Error -> Log.e("FKZ", response.error.message, response.error)

        }
    }

    private fun setupUi(context: Context) {
        if(checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Do something with granted permission
        }
    }
}