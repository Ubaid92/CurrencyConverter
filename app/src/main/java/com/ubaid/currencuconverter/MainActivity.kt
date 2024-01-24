package com.ubaid.currencuconverter

import CustomSpinnerAdapter
import ResponseData
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ubaid.currencuconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        val spinnerItems = listOf(
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.pk, "PKR"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.sa, "SAR"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.ae, "AED"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.om, "OMR"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.gb, "GBP"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.eu, "EUR"),
            CustomSpinnerAdapter.SpinnerItem(com.ubaid.currencuconverter.R.drawable.us, "USD"),
        )

        val adapter = CustomSpinnerAdapter(this, spinnerItems)
        binding.secondSpinner.adapter = adapter
        binding.firstSpinner.adapter = adapter

        binding.firstSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        binding.btnCalculate.setOnClickListener {
            val selectedItem1 = binding.firstSpinner.selectedItem as
                    CustomSpinnerAdapter.SpinnerItem
            val selectedItem2 = binding.secondSpinner.selectedItem as
                    CustomSpinnerAdapter.SpinnerItem
            val firstCurrency = selectedItem1.text.lowercase()
            val secondCurrency: String = selectedItem2.text.lowercase()
            mainViewModel.getData(firstCurrency, secondCurrency)
        }

        mainViewModel.liveData.observe(this) { curr ->

            val currencyList = arrayListOf<ResponseData>()
            currencyList.add(curr)


            val gotAmount = curr.conversionResult.toDouble()
            val enterAmount = binding.amount.text.toString()


            val totalAmount = (gotAmount * enterAmount.toDouble())
            binding.resultNum.text = totalAmount.toFloat().toString()

        }

    }

}
