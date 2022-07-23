package com.fascinate.mykotlinapplication.ObjectClasses

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings

object NoGpsAlert {
    fun showSettingsAlert(mContext: Context) {
        val alertDialog = AlertDialog.Builder(mContext)
        alertDialog.setCancelable(false)

        alertDialog.setTitle("GPS Settings")
        alertDialog.setMessage("GPS Off!. Do you want to turn it on?")

        alertDialog.setPositiveButton("Settings") { _, _ ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }
        alertDialog.setNegativeButton("Dismiss") { dialog,   which -> dialog.dismiss() }
        alertDialog.show()
    }

}