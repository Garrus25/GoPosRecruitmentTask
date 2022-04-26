package database

import android.content.Context
import android.util.Log
import io.objectbox.BoxStore

private const val TAG = "BoxStore"

object ObjectBox {

    var initFlag = false
    lateinit var store: BoxStore
        private set

    fun init(context: Context) {
        if (!initFlag) {
            initFlag = true
            store = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
            Log.i(TAG, "store initialized")
        }
    }
}