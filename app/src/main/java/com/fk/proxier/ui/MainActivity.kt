package com.fk.proxier.ui

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fk.proxier.R
import com.fk.proxier.persistence.AppDataBase
import com.fk.proxier.persistence.ProxyRaw
import com.fk.proxier.ui.wifi.WifiFragment
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val db = Room.databaseBuilder(applicationContext,
                AppDataBase::class.java, "database-name").build()
        db.proxyDAO().apply {
            Observable.fromPublisher<List<ProxyRaw>> {
                it.onNext(getAll())
                it.onComplete()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete {
                        Completable.fromAction { insert(ProxyRaw(System.currentTimeMillis(), "some.ip", "8080"))  }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({},{Log.e("FKZ", it.message, it)})
                    }
                    .subscribe { Log.d("FKZ", "printing");it.forEach { Log.d("FKZ", it.toString()) } }
        }*/
        if (savedInstanceState == null)
            init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_host, WifiFragment())
                .commit()
    }
}
