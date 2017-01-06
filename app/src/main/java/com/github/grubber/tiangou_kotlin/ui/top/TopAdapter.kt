package com.github.grubber.tiangou_kotlin.ui.top

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.github.grubber.tiangou_kotlin.R
import com.github.grubber.tiangou_kotlin.data.api.IMG_URL
import com.github.grubber.tiangou_kotlin.data.api.model.Top
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*

/**
 * Created by grubber on 2017/1/6.
 */
class TopAdapter(context: Context, val picasso: Picasso) : BaseAdapter() {
    private var _result = ArrayList<Top>()
    private var _layoutInflater = LayoutInflater.from(context)

    fun setResult(result: List<Top>) {
        _result.addAll(result)
        notifyDataSetChanged()
    }

    override fun getCount() = _result.size

    override fun getItem(position: Int) = _result[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            view = _layoutInflater.inflate(R.layout.item_common, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        picasso.load(IMG_URL + _result[position].img)
                .placeholder(R.drawable.list_icon_no_image)
                .error(R.drawable.list_icon_error_image).into(viewHolder.icon)

        with(viewHolder) {
            title.text = _result[position].title
            tag.text = _result[position].keywords
            time.text = DateFormat.getDateTimeInstance().format(_result[position].time)
            browser.text = "浏览：" + _result[position].count
        }

        return view
    }

    class ViewHolder(view: View) {
        var icon: ImageView = view.findViewById(R.id.list_item_icon) as ImageView
        var title: TextView = view.findViewById(R.id.list_item_title) as TextView
        var tag: TextView = view.findViewById(R.id.list_item_tag) as TextView
        var time: TextView = view.findViewById(R.id.list_item_time) as TextView
        var browser: TextView = view.findViewById(R.id.list_item_browser) as TextView
    }
}