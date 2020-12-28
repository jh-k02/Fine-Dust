package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.MyAdapter.MyViewHolder

import java.util.ArrayList

class MyAdapter(private val mContext: Context, private val mList: ArrayList<Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val mInflate: LayoutInflater

    init {
        this.mInflate = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = mInflate.inflate(R.layout.activity_main, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (mList[position].colorViewColor10 == "blue") {
            holder.colorView10.setBackgroundResource(R.color.blue)
            holder.statusImage10.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
        } else if (mList[position].colorViewColor10 == "green") {
            holder.colorView10.setBackgroundResource(R.color.green)
            holder.statusImage10.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
        } else if (mList[position].colorViewColor10 == "orange") {
            holder.colorView10.setBackgroundResource(R.color.orange)
            holder.statusImage10.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        } else if (mList[position].colorViewColor10 == "red") {
            holder.colorView10.setBackgroundResource(R.color.red)
            holder.statusImage10.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
        } else {
            holder.colorView10.setBackgroundResource(R.color.black)
            holder.statusImage10.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
        }

        if (mList[position].colorViewColor25 == "blue") {
            holder.colorView25.setBackgroundResource(R.color.blue)
            holder.statusImage25.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
        } else if (mList[position].colorViewColor25 == "green") {
            holder.colorView25.setBackgroundResource(R.color.green)
            holder.statusImage25.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
        } else if (mList[position].colorViewColor25 == "orange") {
            holder.colorView25.setBackgroundResource(R.color.orange)
            holder.statusImage25.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        } else if (mList[position].colorViewColor25 == "red") {
            holder.colorView25.setBackgroundResource(R.color.red)
            holder.statusImage25.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
        } else {
            holder.colorView25.setBackgroundResource(R.color.black)
            holder.statusImage25.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp)
        }

        holder.colorView10.text = mList[position].colorViewText10
        holder.colorView25.text = mList[position].colorViewText25
        holder.location.text = mList[position].location
        holder.dataTime.text = mList[position].dataTime
        holder.so2Value.text = mList[position].so2Value
        holder.coValue.text = mList[position].coValue
        holder.o3Value.text = mList[position].o3Value
        holder.no2Value.text = mList[position].no2Value
        holder.pm10Value.text = mList[position].pm10Value
        holder.pm25Value.text = mList[position].pm25Value
        holder.khaiValue.text = mList[position].khaiValue
        holder.khaiGrade.text = mList[position].khaiGrade
        holder.so2Grade.text = mList[position].so2Grade
        holder.coGrade.text = mList[position].coGrade
        holder.o3Grade.text = mList[position].o3Grade
        holder.no2Grade.text = mList[position].no2Grade
        holder.pm10Grade.text = mList[position].pm10Grade
        holder.pm25Grade.text = mList[position].pm25Grade
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataTime: TextView
        val so2Value: TextView
        val coValue: TextView
        val o3Value: TextView
        val no2Value: TextView
        val pm10Value: TextView
        val pm25Value: TextView
        val khaiValue: TextView
        val khaiGrade: TextView
        val so2Grade: TextView
        val coGrade: TextView
        val o3Grade: TextView
        val no2Grade: TextView
        val pm10Grade: TextView
        val pm25Grade: TextView
        val location: TextView
        val colorView10: TextView
        val statusImage10: ImageView
        val colorView25: TextView
        val statusImage25: ImageView

        init {

            location = itemView.findViewById(R.id.location)
            dataTime = itemView.findViewById(R.id.date)
            so2Value = itemView.findViewById(R.id.so2value)
            coValue = itemView.findViewById(R.id.covalue)
            o3Value = itemView.findViewById(R.id.o3value)
            no2Value = itemView.findViewById(R.id.no2value)
            pm10Value = itemView.findViewById(R.id.pm10value)
            pm25Value = itemView.findViewById(R.id.pm25value)
            khaiValue = itemView.findViewById(R.id.khaivalue)
            khaiGrade = itemView.findViewById(R.id.khaigrade)
            so2Grade = itemView.findViewById(R.id.so2grade)
            coGrade = itemView.findViewById(R.id.cograde)
            o3Grade = itemView.findViewById(R.id.o3grade)
            no2Grade = itemView.findViewById(R.id.no2grade)
            pm10Grade = itemView.findViewById(R.id.pm10grade)
            pm25Grade = itemView.findViewById(R.id.pm25grade)
            colorView10 = itemView.findViewById(R.id.colorview10)
            statusImage10 = itemView.findViewById(R.id.statusImage10)
            colorView25 = itemView.findViewById(R.id.colorview25)
            statusImage25 = itemView.findViewById(R.id.statusImage25)
        }
    }
}
