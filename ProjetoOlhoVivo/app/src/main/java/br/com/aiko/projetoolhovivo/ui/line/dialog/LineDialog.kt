package br.com.aiko.projetoolhovivo.ui.line.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aiko.projetoolhovivo.data.model.position.PositionLine
import br.com.aiko.projetoolhovivo.databinding.DialogLineMapMainFilterBinding
import br.com.aiko.projetoolhovivo.ui.line.adapter.LineDialogAdapter
import br.com.aiko.projetoolhovivo.ui.position.PositionMapViewModel

class LineDialog constructor(
    private val activity: Activity,
    private val onClick: (PositionLine) -> Unit
) : Dialog(activity) {
    private val adapter = LineDialogAdapter(this, onClick)

    private lateinit var binding: DialogLineMapMainFilterBinding
    lateinit var viewModel: PositionMapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLineMapMainFilterBinding.inflate(activity.layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        setupSearchTil()
        onCloseClick()
    }

    private fun setupRecyclerView() {
        binding.rvAccountDialog.layoutManager = LinearLayoutManager(activity)
        binding.rvAccountDialog.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(activity as FragmentActivity)[PositionMapViewModel::class.java]
        viewModel.lines.observe(activity, adapter::updateList)
    }

    private fun setupSearchTil() {
        binding.tilSearchLineDialog.editText!!.addTextChangedListener {
            adapter.getFilter().filter(it.toString())
        }
    }

    private fun onCloseClick() {
        binding.ivCloseLineDialog.setOnClickListener { dismiss() }
    }
}