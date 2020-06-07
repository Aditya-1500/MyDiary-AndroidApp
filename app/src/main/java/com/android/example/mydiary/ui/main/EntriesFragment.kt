package com.android.example.mydiary.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.mydiary.R
import com.android.example.mydiary.database.DiaryDatabase
import com.android.example.mydiary.database.DiaryEntry
import com.android.example.mydiary.databinding.FragmentEntriesBinding
import kotlinx.android.synthetic.main.diary_entry.view.*


class EntriesFragment : Fragment() {

    companion object {
        fun newInstance() = EntriesFragment()
    }

    private lateinit var entriesViewModel: EntriesViewModel

//    private var adapter: DiaryAdapter? = null
//    private lateinit var entries: List<DiaryEntry>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEntriesBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_entries,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        val entriesViewModelFactory = EntriesViewModelFactory(dataSource)
        entriesViewModel = ViewModelProviders.of(this,entriesViewModelFactory).get(EntriesViewModel::class.java)
        binding.entriesViewModel = entriesViewModel

        val adapter = DiaryAdapter()
        binding.diaryEntries.adapter = adapter


        entriesViewModel.entries.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.entries = it
            }
        })

//
//        entries = database.getAllEntries()
//        adapter = DiaryAdapter(application.applicationContext,entries)
//
//        binding.entriesGrid.adapter = adapter

        return binding.root
    }

//    class DiaryAdapter(var context: Context? = null, var entries: List<DiaryEntry>): BaseAdapter() {
//
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val entry = this.entries[position]
//
//            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            val entryView = inflater.inflate(R.layout.diary_entry,null)
//            entryView.entry_title.text = entry.Title
//            entryView.entry_text.text = entry.Entry
//            entryView.date_of_entry.text = entry.dateOfEntry
//
//            return entryView
//
//        }
//
//        override fun getItem(position: Int): Any {
//            return entries[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//
//        override fun getCount(): Int {
//            return entries.size
//        }
//
//    }
}
