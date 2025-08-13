package id.herald.core.util

import android.content.Context
import android.widget.Toast

/** Created by Herald Santos on 04/12/2024. */

object Extensions {
    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}