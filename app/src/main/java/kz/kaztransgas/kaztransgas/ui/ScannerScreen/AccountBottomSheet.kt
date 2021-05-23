package kz.kaztransgas.kaztransgas.ui.ScannerScreen

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_account_bottomsheet.*
import kz.kaztransgas.kaztransgas.R
import kz.kaztransgas.kaztransgas.databinding.LayoutAccountBottomsheetBinding

class AccountBottomSheet : BottomSheetDialogFragment() {



    private val adapter = AccountsAdapter()
    var listener:AccountBottomSheetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, R.style.CoffeeDialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CoffeeDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutAccountBottomsheetBinding.inflate(inflater,container,false)
        view.root
        return View.inflate(context,R.layout.layout_account_bottomsheet,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val items:ArrayList<AccountsItem> = arrayListOf(AccountsItem(0,"Алишер","00000"),AccountsItem(0,"Алишер","00000"),AccountsItem(0,"Алишер","00000"),AccountsItem(0,"Алишер","00000"))
        accountsList.layoutManager = llm
        accountsList.adapter = adapter
        adapter.items = items
        adapter.listener = object :AccountsAdapterListener{
            override fun itemClicked(id: Int) {
                listener?.itemClicked(id)
            }

        }

    }

}

interface AccountBottomSheetListener {
    fun itemClicked(id:Int)

}
