package com.android.example.mydiary.ui.main

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.example.mydiary.R
import com.android.example.mydiary.database.DiaryDatabase
import com.android.example.mydiary.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceholderFragment()
    }

    private lateinit var pageViewModel: PageViewModel
    private lateinit var entry: String
    private lateinit var title: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        val pageViewModelFactory = PageViewModelFactory(dataSource)
        pageViewModel = ViewModelProviders.of(this,pageViewModelFactory).get(PageViewModel::class.java)
        binding.pageViewModel = pageViewModel

        binding.buttonRecord.setOnClickListener{
            title = binding.titleText.text.toString()
            entry = binding.diaryEntry.text.toString()
            Log.i("PlaceholderFragment","Title is $title, Entry is $entry")
            pageViewModel.onCLickRecord(title,entry)
        }

        pageViewModel.showSnackbar.observe(this, Observer {
            if( it==true ) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Record Added!!",
                    Snackbar.LENGTH_SHORT
                ).show()
                pageViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }

}