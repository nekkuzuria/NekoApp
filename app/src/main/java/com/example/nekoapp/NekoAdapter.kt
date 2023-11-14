import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nekoapp.Neko
import com.example.nekoapp.R
import com.example.nekoapp.databinding.ItemNekoBinding
import com.squareup.picasso.Picasso

class NekoAdapter(private val listNeko: List<Neko>) :
    RecyclerView.Adapter<NekoAdapter.ItemNekoViewHolder>() {

    inner class ItemNekoViewHolder(private val binding: ItemNekoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data : Neko){
            with(binding){
                namaAuthorTxt.text = data.namaAuthor
                linkProfileTxt.text = data.author_href
                // Muat gambar Neko dari URL menggunakan Picasso
                Picasso.get().load(data.url).into(imgNeko)

//                Ketika item diklik
                itemView.setOnClickListener{
                    val dialog = Dialog(itemView.context)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setContentView(R.layout.detail_neko)


                    val detailImgAuthor = dialog.findViewById<ImageView>(R.id.detailImgAuthor)
                    val detailNamaAuthorTxt = dialog.findViewById<TextView>(R.id.detailNamaAuthorTxt)
                    val detailLinkProfileTxt = dialog.findViewById<TextView>(R.id.detailLinkProfileTxt)

                    Picasso.get().load(data.url).into(detailImgAuthor)
                    detailNamaAuthorTxt.text = namaAuthorTxt.text
                    detailLinkProfileTxt.text = linkProfileTxt.text

                    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    dialog.show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNekoViewHolder {
        val binding = ItemNekoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return  ItemNekoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNeko.size
    }

    override fun onBindViewHolder(holder: ItemNekoViewHolder, position: Int) {
        holder.bind(listNeko[position])
    }

}
