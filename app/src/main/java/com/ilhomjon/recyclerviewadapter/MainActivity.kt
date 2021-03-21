package com.ilhomjon.recyclerviewadapter

import Adapter.ContactAdapter
import Models.Contact
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var contactList: ArrayList<Contact>
    lateinit var contactAdapter:ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        contactAdapter = ContactAdapter(
            this,
            contactList,
            object : ContactAdapter.OnMyItemClickListener {
                override fun onMyItemClck(contact: Contact, position:Int) {
//                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
//                    startActivity(intent)

//                    contact.name = "Ilhomjon"
//                    contact.number = "123"
//
//                    contactAdapter.notifyItemChanged(position)

                    contactList.remove(contact)
                    contactAdapter.notifyItemRemoved(position)
                    contactAdapter.notifyItemRangeChanged(position, contactList.size)
                }

//                override fun onMyItemLongCLick(contact: Contact) {
//                    Toast.makeText(this@MainActivity, "$contact", Toast.LENGTH_SHORT).show()
//                }

            })
        val linearLayoutManager0 = LinearLayoutManager(this)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val gridLayoutManager = GridLayoutManager(this, 3,GridLayoutManager.HORIZONTAL, false)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        rv.layoutManager = staggeredGridLayoutManager
        rv.adapter = contactAdapter

        btn_add.setOnClickListener {
            val name = edt_name.text.toString()
            val number = edt_number.text.toString()
            val contact = Contact(0, name, number)
            contactList.add(contact)
//            contactAdapter.notifyDataSetChanged() // bu listViewda ham bor
            contactAdapter.notifyItemInserted(contactList.size) // bu oxirgi bitta itemni yangilaydi
//            contactAdapter.notifyItemRangeChanged(   ) //ko'proq itemlarni yangilaydi

        }
    }

    private fun loadData() {
        contactList = ArrayList()
    }
}