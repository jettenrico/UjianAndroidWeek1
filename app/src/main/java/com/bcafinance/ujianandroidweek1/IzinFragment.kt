package com.bcafinance.ujianandroidweek1

import android.app.Activity
import android.app.Application
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_absen.*
import kotlinx.android.synthetic.main.fragment_izin.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IzinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IzinFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val REQUEST_CAPTURE_LAMPIRAN1 = 601
    private val REQUEST_CAPTURE_LAMPIRAN2 = 602
    private val REQUEST_CAPTURE_LAMPIRAN3 = 603
    private val REQUEST_CAPTURE_LAMPIRAN4 = 604

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_izin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnStart.setOnClickListener({
            pickDateStart()
        })
        btnEnd.setOnClickListener({
            pickDateEnd()
        })

        lampiran1.setOnClickListener {
            captureCamera(REQUEST_CAPTURE_LAMPIRAN1)
        }
        lampiran2.setOnClickListener {
            captureCamera(REQUEST_CAPTURE_LAMPIRAN2)
        }
        lampiran3.setOnClickListener {
            captureCamera(REQUEST_CAPTURE_LAMPIRAN3)
        }
        lampiran4.setOnClickListener {
            captureCamera(REQUEST_CAPTURE_LAMPIRAN4)
        }

        btnKirim.setOnClickListener({
            if(txtStart.text.toString().equals("") && txtEnd.text.toString().equals("")
                && txtPerihal.text.toString().equals("") && txtKeterangan.text.toString().equals("")){
                Toast.makeText(this.context, "Silahkan Melengkapi Data!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this.context, "Berhasil Mengajukan Izin!", Toast.LENGTH_LONG).show()
                (activity as MainActivity).supportFragmentManager.popBackStack()
            }
        })
    }

    fun captureCamera(RQCode:Int){
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeCamera, RQCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CAPTURE_LAMPIRAN1 && resultCode == AppCompatActivity.RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            lampiran1.setImageBitmap(bitmapImage)
        }else if(requestCode == REQUEST_CAPTURE_LAMPIRAN2 && resultCode == AppCompatActivity.RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            lampiran2.setImageBitmap(bitmapImage)
        }else if(requestCode == REQUEST_CAPTURE_LAMPIRAN3 && resultCode == AppCompatActivity.RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            lampiran3.setImageBitmap(bitmapImage)
        }else{
            val bitmapImage = data?.extras?.get("data") as Bitmap
            lampiran4.setImageBitmap(bitmapImage)
        }
    }

    fun pickDateStart() {
        val c = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

                txtStart.setText(sdf.format(c.getTime()))
            }
        }

        DatePickerDialog(
            (activity as MainActivity), dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun pickDateEnd() {
        val c = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

                txtEnd.setText(sdf.format(c.getTime()))
            }
        }

        DatePickerDialog(
            (activity as MainActivity), dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IzinFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                IzinFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}