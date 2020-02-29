package com.example.app.dayOfWeeks

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Adapter
import com.example.app.Predmet
import com.example.app.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class Monday : Fragment() {
    val ARG_PAGE = "ARG_PAGE"
    var parse : ArrayList<Predmet> = arrayListOf()

    private var mPage: Int = 0

    fun newInstance(page: Int): Monday {
        val args = Bundle()
        args.putInt(ARG_PAGE, page)
        val fragment = Monday()
        fragment.arguments = args
        return fragment
    }

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
        val root = inflater.inflate(R.layout.day, container, false)
        //parseJson()
        val adapter = Adapter(parse)
        var recycle = root.findViewById<RecyclerView>(R.id.recycle_view)
        recycle.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recycle.adapter = adapter
        return root
    }



    fun parseJson() {
        var str = getAssetJsonData(context!!)
        lateinit var arr : JSONArray
        if (mPage == 1) {
            arr = JSONObject(str).getJSONArray("понедельник")
        }
        else {
            arr = JSONObject(str).getJSONArray("вторник")
        }
        arr.let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
            .map {
                parse.add(
                    Predmet(
                        it.get("name").toString(),
                        it.get("type").toString(),
                        it.get("teacher").toString(),
                        it.get("timeStart").toString(),
                        it.get("hall").toString()
                    )
                )
            }
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
        Log.e("data", json)
        return json
    }
}