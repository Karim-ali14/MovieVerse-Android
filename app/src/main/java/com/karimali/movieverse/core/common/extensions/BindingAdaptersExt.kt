package com.karimali.movieverse.core.common.extensions

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.karimali.movieverse.R

/////////////////////////////////////////////////////////////////////////
// ImageViews Adapters.
/////////////////////////////////////////////////////////////////////////

@BindingAdapter(value = ["loadingImageUrl", "placeholder"], requireAll = false)
fun ImageView.loadingImageUrl(
    url: String?, placeholder: Drawable? = AppCompatResources.getDrawable(
        context,
        R.drawable.ic_launcher_background
    )
) {
    this.run {

        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
                .apply(
                    RequestOptions().placeholder(
                        placeholder ?: AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_background
                        )
                    ).error(
                        placeholder ?: AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_launcher_background
                        )
                    )
                )
                .placeholder(
                    placeholder ?: AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_launcher_background
                    )
                )
                .error(
                    placeholder ?: AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_launcher_background
                    )
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .dontAnimate()
                .dontTransform()
                .priority(Priority.HIGH)
                .into(this)
        } else {
            Log.i("asdlkfjlskjdf", "$url $placeholder")
            this.setImageDrawable(
                placeholder ?: AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_launcher_background
                )
            )
        }
    }
}


@BindingAdapter(value = ["loadingImageDrawable"])
fun ImageView.loadingImageDrawable(
    @DrawableRes drawableRes : Int
){
    if (drawableRes != 0){
        Glide.with(this)
            .load(drawableRes)
            .into(this)
    } else{
        this.setImageDrawable(
            AppCompatResources.getDrawable(
                context,
                R.drawable.ic_launcher_background
            )
        )
    }
}

@BindingAdapter(value = ["isVisible"])
fun View.visibility(isVisible : Boolean){
    if (isVisible) visible() else gone()
}