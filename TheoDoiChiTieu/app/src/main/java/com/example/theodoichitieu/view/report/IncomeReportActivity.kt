package com.example.theodoichitieu.view.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyIncomeAdapter
import com.example.theodoichitieu.adapter.MyPayReportAdapter
import com.example.theodoichitieu.util.DAY_NUM_SPACING
import com.example.theodoichitieu.util.previousDay
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.example.theodoichitieu.viewmodel.PayViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_income_report.*
import kotlinx.android.synthetic.main.activity_pay_report.*

class IncomeReportActivity : AppCompatActivity() {
    private lateinit var myIncomeViewModel: IncomeViewModel
    private  var today:Long = 0
    private var listData= mutableListOf<Int>()
    val linearLayoutManager = LinearLayoutManager(this)
    lateinit var myIncomeAdapter:MyPayReportAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_report)
        this.today=intent.getLongExtra("today",today)
        getViewModel()
        getData()
        setContent()
    }
    private fun getData() {
        var daytemp=today
        for (i in 0.. 6){
            myIncomeViewModel.sumMoneyOfDay(daytemp).observe(this, Observer {
                it?.let {
                    myIncomeAdapter.add(it)
                }
            })
            daytemp= previousDay(daytemp)
        }
    }


    private fun getViewModel() {
        myIncomeViewModel= ViewModelProvider(this).get(IncomeViewModel::class.java)
    }

    private fun setContent() {
        myIncomeAdapter= MyPayReportAdapter()
        rvIncomeReport.adapter=myIncomeAdapter
        rvIncomeReport.layoutManager=linearLayoutManager

    }
}