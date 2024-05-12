package com.aliza.alizaandroid

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.DialogAddFileBinding
import com.aliza.alizaandroid.databinding.DialogAddFolderBinding
import com.aliza.alizaandroid.databinding.DialogDeleteItemBinding
import com.aliza.alizaandroid.databinding.FragmentFileBinding
import java.io.File

class FileFragment(val path: String) : BaseFragment<FragmentFileBinding>(FragmentFileBinding::inflate), FileAdapter.FileEvent {
    private lateinit var adapter: FileAdapter


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (MainActivity.ourViewType == 0) {
            binding.btnShowType.setImageResource(R.drawable.ic_grid)
        } else {
            binding.btnShowType.setImageResource(R.drawable.ic_list)
        }

        val ourFile = File(path)
        binding.txtPath.text = ourFile.name + ">"

        if (ourFile.isDirectory) {

            val listOfFiles = arrayListOf<File>()
            listOfFiles.addAll(ourFile.listFiles()!!)
            listOfFiles.sort()

            adapter = FileAdapter(listOfFiles, this)
            binding.recyclerMain.adapter = adapter
            binding.recyclerMain.layoutManager = GridLayoutManager(
                context,
                MainActivity.ourSpanCount,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter.changeViewType(MainActivity.ourViewType)

            if (listOfFiles.size > 0) {

                binding.recyclerMain.visibility = View.VISIBLE
                binding.imgNoData.visibility = View.GONE


            } else {

                binding.recyclerMain.visibility = View.GONE
                binding.imgNoData.visibility = View.VISIBLE

            }

        }

        binding.btnAddFolder.setOnClickListener {
            createNewFolder()
        }
        binding.btnAddFile.setOnClickListener {
            createNewFile()
        }
        binding.btnShowType.setOnClickListener {

            if (MainActivity.ourViewType == 0) {

                MainActivity.ourViewType = 1
                MainActivity.ourSpanCount = 3

                adapter.changeViewType(MainActivity.ourViewType)
                binding.recyclerMain.layoutManager =
                    GridLayoutManager(context, MainActivity.ourSpanCount)

                binding.btnShowType.setImageResource(R.drawable.ic_list)

            } else if (MainActivity.ourViewType == 1) {

                MainActivity.ourViewType = 0
                MainActivity.ourSpanCount = 1

                adapter.changeViewType(MainActivity.ourViewType)
                binding.recyclerMain.layoutManager =
                    GridLayoutManager(context, MainActivity.ourSpanCount)

                binding.btnShowType.setImageResource(R.drawable.ic_grid)

            }

        }

    }

    private fun createNewFile() {

        val dialog = AlertDialog.Builder(context).create()

        val addFileBinding = DialogAddFileBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(addFileBinding.root)

        dialog.show()

        addFileBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        addFileBinding.btnCreate.setOnClickListener {

            val nameOfNewFolder = addFileBinding.edtAddFolder.text.toString()

            val newFile = File(path + File.separator + nameOfNewFolder)

            if (!newFile.exists()) {
                if (newFile.createNewFile()) {
                    adapter.addNewFile(newFile)
                    binding.recyclerMain.scrollToPosition(0)

                    binding.recyclerMain.visibility = View.VISIBLE
                    binding.imgNoData.visibility = View.GONE
                }
            }

            dialog.dismiss()

        }


    }

    private fun createNewFolder() {

        val dialog = AlertDialog.Builder(context).create()

        val addFolderBinding = DialogAddFolderBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setView(addFolderBinding.root)
        dialog.show()


        addFolderBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        addFolderBinding.btnCreate.setOnClickListener {

            val nameOfNewFolder = addFolderBinding.edtAddFolder.text.toString()

            val newFile = File(path + File.separator + nameOfNewFolder)
            if (!newFile.exists()) {
                if (newFile.mkdir()) {
                    adapter.addNewFile(newFile)
                    binding.recyclerMain.scrollToPosition(0)

                    binding.recyclerMain.visibility = View.VISIBLE
                    binding.imgNoData.visibility = View.GONE
                }
            }

            dialog.dismiss()

        }


    }

    override fun onFileClicked(file: File, type: String) {

        val intent = Intent(Intent.ACTION_VIEW)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val fileProvider = FileProvider.getUriForFile(
                requireContext(),
                requireActivity().packageName + ".provider",
                file
            )
            intent.setDataAndType(fileProvider, type)

        } else {

            intent.setDataAndType(Uri.fromFile(file), type)

        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)

    }

    override fun onFolderClicked(path: String) {

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main_container, FileFragment(path))
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun onLongClicked(file: File, position: Int) {

        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(context).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(dialogDeleteBinding.root)
        dialog.show()

        dialogDeleteBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.btnCreate.setOnClickListener {

            if (file.exists()) {
                if (file.deleteRecursively()) {
                    adapter.removeFile(file, position)
                }
            }

            dialog.dismiss()
        }

    }


}