package com.karimali.movieverse.features.onboarding.presentation.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.karimali.movieverse.R
import androidx.core.graphics.drawable.toDrawable
import com.karimali.movieverse.databinding.WelcomeCustomDialogBinding

class WelcomeCustomDialog(): DialogFragment() {
    private var binding : WelcomeCustomDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WelcomeCustomDialogBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.setLayout(width,height)

            window.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//            window.setBackgroundDrawableResource(R.drawable.dialog_background)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                if (email.isNotEmpty()){
                    dismiss()
                }
            }

        }
    }
}