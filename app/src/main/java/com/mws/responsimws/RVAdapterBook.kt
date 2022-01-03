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
        holder.view.lbIdBuku.text = arrayList?.get(position)?.idbuku
        holder.view.lbJudulBuku.text = "Judul Buku : "+arrayList?.get(position)?.judulbuku
        holder.view.lbNamaPengarang.text = "Nama Pengarang : "+arrayList?.get(position)?.namapengarang
        holder.view.lbTahunTebit.text = "Tahun Terbit : "+arrayList?.get(position)?.tahunterbit
        holder.view.lbPenerbit.text = "Penerbit : "+arrayList?.get(position)?.penerbit
        holder.view.cvList.setOnClickListener {
            val i = Intent(context,ManageBookActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_buku",arrayList?.get(position)?.idbuku)
            i.putExtra("judul_buku",arrayList?.get(position)?.judulbuku)
            i.putExtra("nama_pengarang",arrayList?.get(position)?.namapengarang)
            i.putExtra("tahun_terbit",arrayList?.get(position)?.tahunterbit)
            i.putExtra("penerbit",arrayList?.get(position)?.penerbit)
            context.startActivity(i)
        }
    }

    class Holder (val view: View) : RecyclerView.ViewHolder(view) {

    }

}
