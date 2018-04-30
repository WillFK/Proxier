package com.fk.proxier.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ProxyRaw::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun proxyDAO() : ProxyDao
}