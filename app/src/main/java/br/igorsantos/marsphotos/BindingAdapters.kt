package br.igorsantos.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.igorsantos.marsphotos.network.MarsPhoto
import br.igorsantos.marsphotos.overview.MarsApiStatus
import br.igorsantos.marsphotos.overview.PhotoGridAdapter
import coil.load

/**
 * Created by IgorSantos on 8/19/2021.
 */

@BindingAdapter("imageUrl")
fun bindingImage(imgView: ImageView,
                 imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl
            .toUri()
            .buildUpon()
            .scheme("https")
            .build()

        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MarsApiStatus){
    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}