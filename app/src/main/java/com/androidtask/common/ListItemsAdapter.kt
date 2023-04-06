package com.androidtask.common

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.androidtask.R

class ListItemsAdapter(
    context: Activity,
    private val items: List<Any>,
    theLayoutResId: Int
) :
    ArrayAdapter<Any>(context, theLayoutResId, items) {

    private val inflater: LayoutInflater = context.layoutInflater
    override fun getCount(): Int {
        // don't display last item. It is used as hint.
        val count = super.getCount()
        return if (count > 0) count - 1 else count
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.item_text, null, true)
        val text = view?.findViewById<TextView>(R.id.tvText)
        if (SharedPreferencesUtils.getLanguage() == "en") {
            when (val item = items[position]) {
                is String -> text?.text = item
            }
        } else {
            when (val item = items[position]) {
                is String -> text?.text = item
            }
        }

        return view
    }

    @SuppressLint("InflateParams")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.item_text_drop, null, true)
        val text = view?.findViewById<TextView>(R.id.tvText)
        if (SharedPreferencesUtils.getLanguage() == "en") {
            when (val item = items[position]) {
                is String -> text?.text = item
            }
        } else {
            when (val item = items[position]) {
                is String -> text?.text = item
            }
        }

        return view
    }
}