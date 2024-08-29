package br.com.aiko.projetoolhovivo.ui.forecast.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastVehicle
import br.com.aiko.projetoolhovivo.data.model.line.Line
import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import br.com.aiko.projetoolhovivo.databinding.ActivityForecastDetailsBinding
import br.com.aiko.projetoolhovivo.ui.forecast.ForecastViewModel
import br.com.aiko.projetoolhovivo.ui.forecast.adapter.ListForecastVehicleAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ForecastDetailsActivity : DaggerAppCompatActivity() {
    companion object {
        const val STOP = "forecast.ForecastDetailsActivity.STOP"
        const val LINE = "forecast.ForecastDetailsActivity.LINE"

        fun startActivity(context: Context, stop: Stop) {
            val intent = Intent(context, ForecastDetailsActivity::class.java)
            intent.putExtra(STOP, stop)
            context.startActivity(intent)
        }

        fun startActivity(context: Context, line: Line) {
            val intent = Intent(context, ForecastDetailsActivity::class.java)
            intent.putExtra(LINE, line)
            context.startActivity(intent)
        }
    }

    lateinit var adapter: ListForecastVehicleAdapter
    private lateinit var binding: ActivityForecastDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ForecastViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupToolbar()
        setupViewModel()
        setupView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarForecastDetails)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun setupView() {
        if (intent.hasExtra(STOP)) {
            val stop = intent.getSerializableExtra(STOP) as Stop
            binding.toolbarTitleForecast.text = stop.name
            binding.tvDetailsForecast.text = stop.address
            viewModel.getForecastByStopCode(stop.code)
        }
        if (intent.hasExtra(LINE)) {
            val line = intent.getSerializableExtra(LINE) as Line
            binding.toolbarTitleForecast.text = line.firstSign.plus(" ").plus(line.secondSign)
            binding.tvDetailsForecast.visibility = View.GONE
            viewModel.getForecastByLineCode(line.code)
        }
    }

    private fun setupViewModel() {
        viewModel.token = getString(R.string.app_token_sp_trans)

        viewModel.isLoading.observe(this, this::updateLoading)
        viewModel.error.observe(this) { error ->
            run {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.forecastByStop.observe(this) { forecast ->
            val vehicles: ArrayList<ForecastVehicle> = arrayListOf()
            if (forecast != null) {
                binding.tvLastUpdateForecastDetails.text =
                    getString(R.string.til_last_update_position).plus(" ").plus(forecast.lastUpdate)
                for (line in forecast.stop.lines) {
                    for (vehicle in line.vehicles) {
                        if (vehicles.none { v -> v.prefixVehicle == vehicle.prefixVehicle })
                            vehicles.add(vehicle)
                    }
                }
            }
            adapter.updateList(vehicles)
        }
        viewModel.forecastByLine.observe(this) { forecast ->
            val vehicles: ArrayList<ForecastVehicle> = arrayListOf()
            if (forecast != null) {
                binding.tvLastUpdateForecastDetails.text =
                    getString(R.string.til_last_update_position).plus(" ").plus(forecast.lastUpdate)
                for (stop in forecast.stops) {
                    for (vehicle in stop.vehicles) {
                        if (vehicles.none { v -> v.prefixVehicle == vehicle.prefixVehicle })
                            vehicles.add(vehicle)
                    }
                }
            }
            adapter.updateList(vehicles)
        }
    }

    private fun setupRecyclerView() {
        adapter = ListForecastVehicleAdapter()
        binding.rvTimeComingBus.layoutManager = LinearLayoutManager(this)
        binding.rvTimeComingBus.adapter = adapter
    }

    private fun updateLoading(isLoading: Boolean) {
        binding.pbLoadingForescat.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }

        else -> super.onOptionsItemSelected(item);
    }
}