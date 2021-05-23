package kz.kaztransgas.kaztransgas.ui.ScannerScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.databinding.AccountItemBinding

class AccountsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = ArrayList<AccountsItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var listener:AccountsAdapterListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.account_item,
            parent,
            false
        ))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if(holder is ViewHolder){
            holder.bind(item,listener)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

interface AccountsAdapterListener {

    fun itemClicked(id:Int)

}

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
    private val binding = AccountItemBinding.bind(containerView)
    fun bind(item:AccountsItem,listener: AccountsAdapterListener?){
        binding.name.text = item.name
        binding.accountNumber.text = item.numberAccount
        binding.root.setOnClickListener {
            listener?.itemClicked(item.id)
        }
    }


}
