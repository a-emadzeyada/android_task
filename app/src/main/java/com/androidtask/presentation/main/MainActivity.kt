package com.androidtask.presentation.main

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.lifecycleScope
import com.androidtask.R
import com.androidtask.common.ListItemsAdapter
import com.androidtask.common.SharedPreferencesUtils
import com.androidtask.common.initDialog
import com.androidtask.common.showToast
import com.androidtask.databinding.ActivityMainBinding
import com.androidtask.domin.entities.RateEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: RateViewModel by viewModel()
    private val dialog: Dialog by lazy { initDialog(false) }
    private lateinit var mBinding: ActivityMainBinding
    private var currencyCodeList = ArrayList<String>()
    private var currencyValueList = ArrayList<Double>()
    private var isTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
        setObservers()
        setListeners()
    }

    private fun init() {
        val data = SharedPreferencesUtils.getObject("RATES", RateEntity::class.java)
        if (data != null) {
            if (data.rates != null) {
                getPropertiesNames(data.rates).forEach { item ->
                    currencyCodeList.add(item.first)
                    Log.i("First", item.first)
                    Log.i("Second", item.second.toString())
                    currencyValueList.add(item.second as Double)
                }
                val adapter = ListItemsAdapter(
                    this,
                    currencyCodeList,
                    R.layout.item_text
                )
                if (!isTrue) {
                    mBinding.spFirst.adapter = adapter
                    mBinding.spSecond.adapter = adapter
                }
            } else {
                viewModel.getRates("AED")
            }
        } else {
            viewModel.getRates("AED")
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.rates.collect { state ->
                if (state.isLoading)
                    dialog.show()
                else {
                    dialog.hide()
                    if (state.data != null) {
                        currencyCodeList.clear()
                        currencyValueList.clear()
                        SharedPreferencesUtils.saveObject("RATES", state.data)
                        init()
                    } else {
                        if (!state.message.isNullOrEmpty())
                            showToast(state.message )
                    }
                }
            }
        }
    }

    private fun setListeners() {
        mBinding.etFirst.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (mBinding.etFirst.text.toString().isEmpty()) {
                    mBinding.etFirst.setText("0")
                    mBinding.etFirst.selectAll()
                }
                val two = currencyValueList[mBinding.spSecond.selectedItemPosition]
                val value = two * mBinding.etFirst.text.toString().toDouble()
                mBinding.etSecond.setText("$value")
            }

        })
        mBinding.spFirst.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isTrue)
                        viewModel.getRates(mBinding.spFirst.selectedItem as String)
                    isTrue = true
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }


    }

    private fun getPropertiesNames(obj: Any): List<Pair<String, Any?>> {
        return obj::class.java.declaredFields.map {
            it.isAccessible = true
            it.name to it.get(obj)
        }
    }
}