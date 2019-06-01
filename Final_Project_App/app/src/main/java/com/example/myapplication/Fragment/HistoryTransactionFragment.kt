package com.example.myapplication.Fragment


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.myapplication.Helper.core
import com.example.myapplication.Http.doAsync
import com.example.myapplication.Model.Coffee
import com.example.myapplication.Model.Order
import com.example.myapplication.Model.OrderDetail
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_layout_items_report.view.*
import kotlinx.android.synthetic.main.fragment_history_transaction.*
import org.json.JSONArray
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryTransactionFragment : Fragment() {

    var coffeeList = arrayListOf<Coffee>()
    val orderList = arrayListOf<Order>()
    var orderDetailList = arrayListOf<OrderDetail>()
    var orderLoaded = false
    var orderDetailLoaded = false
    var coffeeLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_transaction, container, false)
    }


    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDateFrom.inputType = InputType.TYPE_NULL
        etDateTo.inputType = InputType.TYPE_NULL

        doAsync("${core.BASE_URL}/orders", "GET", "", "") {
            orderList.clear()
            orderDetailList.clear()
            orderLoaded = false
            orderDetailLoaded = false

            var jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val id = obj.getInt("id")
                val dateStr = obj.getString("orderDate")
                val customerId = obj.getInt("customerId")

                var detailArray = obj.getJSONArray("orderDetails")
                for (j in 0 until detailArray.length()) {
                    val objDetail = detailArray.getJSONObject(j)
                    val d_id = objDetail.getInt("id")
                    val d_orderId = objDetail.getInt("orderId")
                    val d_productId = objDetail.getInt("productId")
                    val d_qty = objDetail.getInt("qty")

                    orderDetailList.add(OrderDetail(d_id, d_orderId, d_productId, d_qty))
                }
                Log.d("test", dateStr)
                orderList.add(
                    Order(
                        id,
                        dateStr,
                        customerId
                    )
                )
            }

            Log.d("test:OrderList", orderList.toString())
            orderLoaded = true
            orderDetailLoaded = true
            loadData()
        }


        doAsync("${core.BASE_URL}/coffees", "GET", "", "") {
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
            coffeeLoaded = true
            loadData()
        }

        val calendar = Calendar.getInstance()
        val mYear = calendar.get(Calendar.YEAR)
        val mMonth = calendar.get(Calendar.MONTH)
        val mDay = calendar.get(Calendar.DAY_OF_MONTH)


        etDateFrom.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this.context, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    etDateFrom.setText("${year}/${month + 1}/${dayOfMonth}")
                }
            }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }

        etDateTo.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this.context, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    etDateTo.setText("${year}/${month + 1}/${dayOfMonth}")
                }
            }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }

        btnViewReport.setOnClickListener {
            loadData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData() {
        if (!orderLoaded || !orderDetailLoaded || !coffeeLoaded) return

        try {
            val calendar = Calendar.getInstance()


            val listOrder = arrayListOf<OrderDetail>()

            var q = orderList.toList()

            if (!etDateFrom.text.isEmpty() && !etDateTo.text.isEmpty()) {
                val sdf = SimpleDateFormat("yyyy/MM/dd")
                calendar.time = sdf.parse(etDateFrom.text.toString())
                val dateFrom = calendar.time
                Log.d("test:dateFrom", dateFrom.toString())
//        val dateFrom = LocalDate.parse(etDateFrom.text, DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString()
                calendar.time = sdf.parse(etDateTo.text.toString())
                val dateTo = calendar.time

                Log.d("test:dateTo", dateTo.toString())

                q = q.filter { t ->

                    val sdf2 = SimpleDateFormat("yyyy-MM-dd")
                    val orderDate = sdf2.parse(t.orderDate)

                    orderDate in dateFrom..dateTo
                }
            }

            q.forEach {
                Log.d("test", "per order : ${it.toString()}")
                var detail = orderDetailList.filter { t2 -> t2.orderId == it.id }

                listOrder.addAll(detail.toList())
            }

            Log.d("test:listOrder", listOrder.toString())

            var listReport = arrayListOf<ReportData>()
            val q2 = listOrder.groupBy { t -> t.coffeeId }.forEach {
                var totalSold = it.value.size
                var q3 = coffeeList.filter { t2 -> t2.id == it.key }.first()

                listReport.add(ReportData(q3, totalSold))
            }


            recyclerView_report.layoutManager = LinearLayoutManager(this.context!!)
            val adapter = ReportAdapter(this.context!!, listReport)
            recyclerView_report.adapter = adapter
        } catch (e: Exception) {
            Log.d("test", e.toString())
            Log.d("test", e.message)
        }
    }


    class ReportAdapter(var mContext: Context, var listReport: ArrayList<ReportData>) :
        RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout_items_report, p0, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listReport.size
        }

        override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
            val current = listReport.get(pos)

            Picasso.get()
                .load(current.coffee.url)
                .into(holder.imageView)

            holder.tvTitle.text = current.coffee.title
            val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)
            holder.tvPrice.text = formatter.format(current.coffee.price)
            holder.tvTotalSold.text = "Total sold : " + current.totalSold.toString() + " cups"
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.imageView
            val tvTitle = itemView.tvTitle
            val tvPrice = itemView.tvPrice
            val tvTotalSold = itemView.tvTotalSold
        }
    }

    data class ReportData(var coffee: Coffee, var totalSold: Int)
}
