package com.example.labthree

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottomsheet,container,false)
        val titleTextView = view.findViewById<TextView>(R.id.imageTitle)
        titleTextView.setText(arguments?.getString("title"))
        val descriptionTextView = view.findViewById<TextView>(R.id.imageDescription)
        descriptionTextView.setText(arguments?.getString("description"))
        val dateTextView = view.findViewById<TextView>(R.id.imageDate)
        dateTextView.setText(arguments?.getString("date"))
        val sizeTextView = view.findViewById<TextView>(R.id.imageSize)
        sizeTextView.setText(arguments?.getString("size"))
        val placeTextView = view.findViewById<TextView>(R.id.imagePlace)
        placeTextView.setText(arguments?.getString("place"))
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }
}