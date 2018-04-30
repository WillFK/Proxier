package com.fk.proxier.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "proxy")
data class ProxyRaw(
        @PrimaryKey
        var id: Long? = null,
        var ip: String? = null,
        var port: String? = null)