package Adapter

import Models.Contact
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.recyclerviewadapter.MainActivity2
import com.ilhomjon.recyclerviewadapter.R
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(var context: Context, var contactList: List<Contact>, var onMyItemClickListener: OnMyItemClickListener)
    : RecyclerView.Adapter<ContactAdapter.MyViewHolder>(){

    inner class MyViewHolder(var itemView:View): RecyclerView.ViewHolder(itemView){

        fun onBind(contact: Contact, position: Int){
            itemView.name.text = contact.name
            itemView.number.text = contact.number


            itemView.setOnClickListener{
//                Toast.makeText(context, "$contact", Toast.LENGTH_SHORT).show()
//                val intent = Intent(context, MainActivity2::class.java)
//                context.startActivity(intent)
                onMyItemClickListener.onMyItemClck(contact, position)
            }

            //Lekin LongClick onClick bilan birga ishlamadi
//            itemView.setOnLongClickListener(object : View.OnLongClickListener{
//                override fun onLongClick(v: View?): Boolean {
//                    onMyItemClickListener.onMyItemLongCLick(contact)
//                    return true
//                }
//
//            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        val myViewHolder = MyViewHolder(itemView)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.onBind(contact, position)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    interface OnMyItemClickListener{
        fun onMyItemClck(contact: Contact, position: Int)
     //   fun onMyItemLongCLick(contact: Contact)
    }
}