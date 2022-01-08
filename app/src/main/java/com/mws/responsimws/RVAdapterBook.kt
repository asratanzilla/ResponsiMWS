import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mws.responsimws.ManageBookActivity
import com.mws.responsimws.R
import kotlinx.android.synthetic.main.book_list.view.*


class RVAdapterBook (private val context: Context, private val arrayList: ArrayList<Book>) : RecyclerView.Adapter<RVAdapterBook.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.book_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.lbIdBuku.text = "Id Buku : "+arrayList?.get(position)?.id_buku
        holder.view.lbJudulBuku.text = "Judul Buku : "+arrayList?.get(position)?.judul_buku
        holder.view.lbNamaPengarang.text = "Nama Pengarang : "+arrayList?.get(position)?.nama_pengarang
        holder.view.lbTahunTebit.text = "Tahun Terbit : "+arrayList?.get(position)?.tahun_terbit
        holder.view.lbPenerbit.text = "Penerbit : "+arrayList?.get(position)?.penerbit
        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ManageBookActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_buku",arrayList?.get(position)?.id_buku)
            i.putExtra("judul_buku",arrayList?.get(position)?.judul_buku)
            i.putExtra("nama_pengarang",arrayList?.get(position)?.nama_pengarang)
            i.putExtra("tahun_terbit",arrayList?.get(position)?.tahun_terbit)
            i.putExtra("penerbit",arrayList?.get(position)?.penerbit)
            context.startActivity(i)
        }
    }

    class Holder (val view: View) : RecyclerView.ViewHolder(view) {

    }

}
