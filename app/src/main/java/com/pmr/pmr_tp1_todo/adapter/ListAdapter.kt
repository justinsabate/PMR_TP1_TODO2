package fr.ec.app.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pmr.pmr_tp1_todo.R
import com.pmr.pmr_tp1_todo.model.ListeToDo


class ListAdapter(
    private val actionListener: ActionListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = dataSet.size

    private val dataSet: MutableList<ListeToDo> = mutableListOf()

    fun showData(newDataSet : List<ListeToDo>) {
        dataSet.clear()
        dataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_list,parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,position:Int) {
        Log.d("ListAdapter", "onBindViewHolder")
         val title=dataSet[position]
        (holder as ListViewHolder).bind(title.label)//titreListeToDo)
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(
            R.id.title
        )   
        
        /* Pour faire fonctionner le oitemclicked de la classe choixlistactivity il faut initialiser l'onclicklistener ici */
        init {

            itemView.setOnClickListener {
                val postPosition = adapterPosition
                if (postPosition != RecyclerView.NO_POSITION) {
                    val clickedList = dataSet[postPosition]
                    actionListener.onListClicked(clickedList)
                }

            }
        }

        fun bind(title:String
             ) {
            //title.text = "${listeToDo.titreListeToDo}"
            textView.text = title
        }
    }

    interface ActionListener {
        fun onListClicked(listeToDo: ListeToDo)
    }


}