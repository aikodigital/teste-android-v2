package br.com.aiko.projetoolhovivo.ui.bus.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.databinding.ActivityBusStopDetailsBinding
import br.com.aiko.projetoolhovivo.ui.bus.adapter.ListTimeBusStopAdapter

class BusStopDetailsActivity : AppCompatActivity() {
    companion object {
        const val BUS_STOP = "bus.BusStopDetailsActivity.BUS_STOP"

        fun startActivity(context: Context, busStop: Int) {
            val intent = Intent(context, BusStopDetailsActivity::class.java)
            intent.putExtra(BUS_STOP, busStop)
            context.startActivity(intent)
        }
    }

    lateinit var adapter: ListTimeBusStopAdapter
    private lateinit var binding: ActivityBusStopDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusStopDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarBusStopDetails)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun setupRecyclerView() {
        adapter = ListTimeBusStopAdapter()
        binding.rvTimeComingBus.layoutManager = LinearLayoutManager(this)
        binding.rvTimeComingBus.adapter = adapter
        adapter.updateList(50)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item);
    }
}