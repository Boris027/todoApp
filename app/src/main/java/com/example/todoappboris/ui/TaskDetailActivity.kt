package com.example.todoappboris.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoappboris.R
import com.example.todoappboris.data.InMemoryTaskRepository
import com.example.todoappboris.data.Task
import com.example.todoappboris.databinding.TaskdetailBinding

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding:TaskdetailBinding
    private var repository=InMemoryTaskRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=TaskdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonsave.setOnClickListener{
            val title:String= binding.titulo.text.toString()
            val body:String=binding.body.text.toString()
            val status:Boolean=binding.swich.isChecked

            repository.create(Task(4,title,body,status))
            println(repository.readAll())
            for(a in repository.readAll()){
              println(a)
            }
            val adapter=TaskListAdapter.getInstance()
            adapter.submitList(repository.readAll())
            finish()
        }


        binding.botonvolver.setOnClickListener{
            finish()
        }
    }







}