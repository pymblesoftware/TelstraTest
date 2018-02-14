package com.pymblesoftware.telstratest.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.pymblesoftware.telstratest.R
import com.pymblesoftware.telstratest.datamodel.TitleData
import com.pymblesoftware.telstratest.network.NetworkWrapper
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.gson.internal.LinkedTreeMap


class MainActivity : AppCompatActivity() {

     var rowData : ArrayList<LinkedTreeMap<String, Any>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetch()
    }


    fun fetch() {
        NetworkWrapper.fetch(
                successHandler = { data ->
                    Log.d( "RR:", data.toString() )
                    updateUI(data)
                }
        )
    }


    fun updateUI( data: TitleData) {

        val viewGroup = (this.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
        val lv = viewGroup.findViewById<ListView>(R.id.listview)

        lv.adapter = ListAdapter(this)


        val title = data.title
        val rows = data.rows
        rowData = rows

        for(curr in rows) {
            val title = curr["title"]
            val desc = curr["description"]
            val imageHref = curr["imageHref"]

            Log.d( "RR:", "${curr.toString()}"  )


        }
    }


    private inner class ListAdapter(context: Context) : BaseAdapter() {
        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {  if( rowData == null) return 0 else return rowData!!.size }

        override fun getItem(position: Int): Any { if( rowData == null) return Any() else return rowData!![position] }

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.list_row, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }


            if( rowData != null ) {
                val curr = rowData!![position]
                curr["title"]?.let { vh.label.text = curr["title"] as String }
                curr["description"]?.let { vh.description.text = curr["description"] as String }

            }

            return view
        }
    }

    private class ListRowHolder(row: View?) {
        val label: TextView
        val description: TextView
        val image: ImageView

        init {
            this.label = row?.findViewById<TextView>(R.id.label)!!
            this.description = row?.findViewById<TextView>(R.id.description)!!
            this.image = row?.findViewById<ImageView>(R.id.list_image)!!
        }
    }


}
