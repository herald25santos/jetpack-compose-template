package id.herald.core.util

import android.util.Log
import id.herald.core.BuildConfig
import java.text.NumberFormat
import java.util.Locale

/** Created by Herald Santos on 04/12/2024. */

object UtilFunctions {
    private val localeID = Locale("in", "ID")

    fun logE(message: String) {
        if (BuildConfig.DEBUG) Log.e("ERROR_IMO", message)
    }

    fun Long?.fromDollarToPhp(): String {
        val localId = localeID
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val fakeDollarToday = 15000
        val intValue = (this ?: 0) * fakeDollarToday
        return when {
            intValue > 0 -> formatter.format(intValue).replace(",00", "")
            intValue < 0 -> formatter.format(intValue).replace(",00", "")
            else -> "Php0"
        }
    }

    fun Double?.fromDollarToPhp(): String {
        val localId = localeID
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val fakeDollarToday = 15000
        val intValue = (this ?: 0.0) * fakeDollarToday
        return when {
            intValue > 0 -> formatter.format(intValue).replace(",00", "")
            intValue < 0 -> formatter.format(intValue).replace(",00", "")
            else -> "Php0"
        }
    }
}