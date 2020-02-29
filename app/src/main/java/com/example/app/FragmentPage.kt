package com.example.app

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.friday.*
import org.json.JSONObject
import java.io.IOException


class FragmentPage : Fragment() {
    val ARG_PAGE = "ARG_PAGE"

    private var mPage: Int = 0

    fun newInstance(page: Int): FragmentPage {
        val args = Bundle()
        args.putInt(ARG_PAGE, page)
        val fragment = FragmentPage()
        fragment.setArguments(args)
        return fragment
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPage = arguments!!.getInt(ARG_PAGE)
        }
        parseJson()
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.friday, container, false)
        var list = arrayListOf<Predmet>(Predmet("name", "type", "teacher", "time", "hall"))
        val adapter = Adapter(list)
        var recycle = root.findViewById<RecyclerView>(R.id.recycle_view)
        recycle.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recycle.adapter = adapter
        return recycle
    }

    fun parseJson() {
        var str = getAssetJsonData(context!!)
        var arr = JSONObject(str).getJSONArray("понедельник")
        Log.e("print", arr[0].toString())
    }

    fun getAssetJsonData(context: Context): String? {
        val json: String
        try {
            val inputStream = context.getAssets().open("timetable.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        // print the data
        Log.e("data", json)
        return json
    }
}