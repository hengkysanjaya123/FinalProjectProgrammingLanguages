package com.example.myapplication.Fragment


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.AddCoffeeActivity
import com.example.myapplication.Helper.core
import com.example.myapplication.Http.doAsync
import com.example.myapplication.Model.Coffee
import com.example.myapplication.R
import com.example.myapplication.ScrollingActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_layout_items_without_btnqty.view.*
import kotlinx.android.synthetic.main.fragment_coffee.*
import org.json.JSONArray
import java.text.NumberFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CoffeeFragment : Fragment() {

    var coffeeList = arrayListOf<Coffee>()
    var adapter: CoffeeAdapter? = null
    val REQUEST_CODE = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_fragment_coffee.layoutManager = LinearLayoutManager(this.context)
        adapter = CoffeeAdapter(this.context!!, coffeeList, this)
        recyclerView_fragment_coffee.adapter = adapter

        recyclerView_fragment_coffee.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    Log.d("test", "scrolled down")
                    fabAddCoffee.visibility = View.INVISIBLE
                } else {
                    Log.d("test", "scrolled up")
                    fabAddCoffee.visibility = View.VISIBLE
                }
            }
        })

        fabAddCoffee.setOnClickListener {
            val intt = Intent(this.context!!, AddCoffeeActivity::class.java)
            intt.putExtra("coffeeList", coffeeList)
//            (this.context!!).startActivity(intt)
            startActivityForResult(intt, REQUEST_CODE)
        }

        loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            loadData()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadData() {
        doAsync("${core.BASE_URL}/coffees", "GET", "", "", pbLoading) {
            coffeeList.clear()

            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val id = obj.getInt("id")
                val title = obj.getString("title")
                val price = obj.getInt("price")
                val url = obj.getString("url")
                val desc = obj.getString("description")
                coffeeList.add(Coffee(id, title, desc, url, price))
            }

            adapter?.notifyDataSetChanged()
        }
    }

    class CoffeeAdapter(var mContext: Context, var coffeeList: ArrayList<Coffee>, var fragment: CoffeeFragment) :
        RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout_items_without_btnqty, p0, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return coffeeList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
            val current = coffeeList.get(pos)
            Picasso.get()
                .load(current.url)
                .into(holder.coffeeLogo)

            holder.coffeeLogo.setOnClickListener {
                val intt = Intent(mContext, ScrollingActivity::class.java)
                intt.putExtra("objCoffee", current)
                mContext.startActivity(intt)
            }
            holder.tvTitle.text = current.title
            val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)
            holder.tvPrice.text = "Rp " + formatter.format(current.price)
            holder.tvDesc.text = current.description
            holder.ivDelete.setOnClickListener {
                val alert = AlertDialog.Builder(mContext)
                alert.setTitle("Confirmation")
                alert.setMessage("Are you sure ?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    doAsync("${core.BASE_URL}/coffees/${current.id}", "DELETE", "", "", fragment.pbLoading) {
                        if (it.isEmpty()) {
                            Toast.makeText(mContext, "Data deleted successfully", Toast.LENGTH_SHORT).show()
                            fragment.loadData()
                        }
                    }
                }
                alert.setNegativeButton("No") { dialog, which ->

                }
                alert.show()
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val coffeeLogo = itemView.imageView
            val tvTitle = itemView.tvTitle
            val tvPrice = itemView.tvPrice
            val tvDesc = itemView.tvDescription
            val ivDelete = itemView.imageView2
        }
    }
}
