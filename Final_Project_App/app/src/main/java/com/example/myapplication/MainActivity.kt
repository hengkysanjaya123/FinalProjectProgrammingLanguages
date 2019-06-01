package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.Helper.core
import com.example.myapplication.Http.doAsync
import com.example.myapplication.Model.Coffee
import com.example.myapplication.Model.OrderDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.custom_layout_items.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val coffeeList = ArrayList<Coffee>(
        Arrays.asList(
//            Coffee(
//                1,
//                "Caffe Americano",
//                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
//                "https://lh3.googleusercontent.com/cxXyxZ6ykwLDj1a4KzcGiRWR_MQAUwIoTP_AoeS0aSMvJz5Ycpn3dTHcqRSgNrl9aXsn9A=s128",
//                1000
//            ),
//            Coffee(
//                2,
//                "Cappuccino",
//                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
//                "https://lh3.googleusercontent.com/CuBEckH67UPtbGTQXWZqLNc5Wuk3SpHmLpMN7I-xx-jb7UkxrfiMVXVZBDnSPtPGfA-71w=s85",
//                1500
//            ),
//            Coffee(
//                3,
//                "Espresso",
//                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.",
//                "https://lh3.googleusercontent.com/yBhvpL2yQsu45cPumzMFCWjRKgNvGxDDIsuOWz5DjpIBX3SlK1AhPqbmuskN4JtV-lHks5k=s109",
//                2000
//            ),
//            Coffee(
//                4,
//                "Latte",
//                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
//                "https://lh3.googleusercontent.com/bt9IulW5-MEnCoTviReaOWvHGNOZHIi7SY3UJt5PVTOl4oQScqRrHB13zNmKwQ7rudv9GA=s85",
//                2000
//            )
        )
    )

    var orderList: ArrayList<OrderDetail> = arrayListOf()

    fun addOrder(coffeeId: Int, qty: Int) {
        var q = orderList.filter { t -> t.coffeeId == coffeeId }.firstOrNull()
        if (q == null) {
            orderList.add(OrderDetail(1, 1, coffeeId, qty))
        } else {
            q.qty += qty;
        }
    }

    fun removeOrder(coffeeId: Int) {
        var q = orderList.filter { t -> t.coffeeId == coffeeId }.firstOrNull()
        if (q != null) {
            q.qty -= 1

            if (q.qty <= 0) {
                orderList.remove(q)
            }
        }
    }

    fun displayOrder() {
        if (orderList.size == 0) {
            btnPay.visibility = View.INVISIBLE
        } else {
            btnPay.visibility = View.VISIBLE
        }

        val layout = layout_bottom_sheet_content
        layout.removeAllViewsInLayout()
        val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)

        for (i in orderList) {
            val coffee = coffeeList.filter { t -> t.id == i.coffeeId }.firstOrNull()

            val linear = LinearLayout(this)
            linear.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val tv1 = TextView(this)
            tv1.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            tv1.setTextColor(Color.BLACK)
            tv1.setBackgroundColor(Color.WHITE)
            tv1.setPadding(
                16, 16, 16, 16
            )
            tv1.text = "${coffee?.title} (${i.qty} x ${coffee?.price})"

            val tv2 = TextView(this)
            tv2.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            tv2.setTextColor(Color.BLACK)
            tv2.setBackgroundColor(Color.WHITE)
            tv2.setPadding(
                16, 16, 16, 16
            )
            tv2.gravity = Gravity.END
            val subtotal = (if (coffee == null) 0 else coffee!!.price) * i.qty
            tv2.text = "Rp ${formatter.format(subtotal)}"
            linear.addView(tv1)
            linear.addView(tv2)

            layout_bottom_sheet_content.addView(linear)
        }

        val total = orderList.sumBy { t ->
            val a = coffeeList.filter { a -> a.id == t.coffeeId }.firstOrNull()
            t.qty * (if (a == null) 0 else a!!.price)
        }

        tvTotalPrice.text = "Rp " + formatter.format(total)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(this, coffeeList)
        recyclerView.adapter = adapter

        try {
            recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        } catch (ex: Exception) {
            Log.d("test", ex.toString())
        }
        displayOrder()

        doAsync("${core.BASE_URL}/coffees", "GET", "", "", pbLoading) {
            Log.d("test", it.toString())

            coffeeList.clear()

            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val id = obj.getInt("id")
                val title = obj.getString("title")
                val desc = obj.getString("description")
                val url = obj.getString("url")
                val price = obj.getInt("price")

                val imageView = ImageView(this)
                val layout_params = LinearLayout.LayoutParams(120, 100)
                layout_params.rightMargin = 15
                imageView.layoutParams = layout_params

                Picasso.get()
                    .load(url)
                    .fit()
                    .into(imageView)

                imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                coffeeList.add(Coffee(id, title, desc, url, price))
            }

            adapter.notifyDataSetChanged()
        }

        btnPay.setOnClickListener {
            val objOrder = JSONObject()
            objOrder.put("id", 1)
            objOrder.put("orderDate", "3900-09-10")
            objOrder.put("customerId", 1)

            val arrayDetail = JSONArray()
            var n = 1
            for (i in orderList) {
                val objDetail = JSONObject()
                objDetail.put("id", n)
                objDetail.put("orderId", 1)
                objDetail.put("productId", i.coffeeId)
                objDetail.put("qty", i.qty)
                n += 1
                arrayDetail.put(objDetail)
            }
            objOrder.put("orderDetails", arrayDetail)

            doAsync("${core.BASE_URL}/orders", "POST", objOrder.toString(), "") {
                orderList.clear()
                displayOrder()
                val intt = Intent(this, OrderSuccessActivity::class.java)
                startActivity(intt)
//                core.message(this, it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_logout -> {
                doLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun doLogout() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Confirmation")
        alert.setMessage("Are you sure want to logout ?")
        alert.setPositiveButton("Yes") { dialog, which ->
            core.message(this, "Thank you")
            this.finish()
        }
        alert.setNegativeButton("No") { dialog, which ->

        }
        alert.show()
    }

    override fun onBackPressed() {
        doLogout()
//        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class ItemAdapter(var mContext: Context, var coffeeList: ArrayList<Coffee>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout_items, p0, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return coffeeList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
            val coffee = coffeeList.get(p1)

            var counter = 0;


            val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)

            holder.tvTitle.text = coffee.title
            holder.tvPrice.text = "Rp " + formatter.format(coffee.price)

            Picasso.get()
                .load(coffee.url)
                .fit()
                .into(holder.imageView)

            holder.tvQty.text = "0"
            holder.imageView.setOnClickListener {
                val intt = Intent(mContext, ScrollingActivity::class.java)
                intt.putExtra("objCoffee", coffee)
                mContext.startActivity(intt)
            }

            holder.btnIncrease.setOnClickListener {
                counter++
                holder.tvQty.text = counter.toString()
                (mContext as MainActivity).addOrder(coffee.id, 1)
                (mContext as MainActivity).displayOrder()
            }

            holder.btnDecrease.setOnClickListener {
                if (counter > 0) {
                    counter--
                    holder.tvQty.text = counter.toString()
                    (mContext as MainActivity).removeOrder(coffee.id)
                    (mContext as MainActivity).displayOrder()
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.ivLogo
            val tvTitle: TextView = itemView.tvTitle
            val tvPrice: TextView = itemView.tvPrice
            val tvQty: TextView = itemView.tvQty
            val btnDecrease: Button = itemView.btnDecrease
            val btnIncrease: Button = itemView.btnIncrease
        }
    }
}
