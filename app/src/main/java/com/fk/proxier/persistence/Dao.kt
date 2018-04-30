package com.fk.proxier.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
abstract class ProxyDao: BaseDao<ProxyRaw> {

    @Query("SELECT * FROM proxy")
    abstract fun getAll(): List<ProxyRaw>
}