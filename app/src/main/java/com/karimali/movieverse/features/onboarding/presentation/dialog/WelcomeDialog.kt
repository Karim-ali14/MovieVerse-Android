package com.karimali.movieverse.features.onboarding.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle

class WelcomeDialog() : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Welcome in out app")
            .setMessage("We appreciate your coming hare -_-")

        return dialogBuilder.create()
    }
}