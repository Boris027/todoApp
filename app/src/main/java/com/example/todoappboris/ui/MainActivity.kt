package com.example.todoappboris.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappboris.R
import com.example.todoappboris.data.InMemoryTaskRepository
import com.example.todoappboris.data.Task
import com.example.todoappboris.data.TaskRepository
import com.example.todoappboris.databinding.ActivityMainBinding
import com.example.todoappboris.databinding.TaskdetailBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository:TaskRepository = InMemoryTaskRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val adapter:TaskListAdapter=TaskListAdapter.getInstance()

        adapter.submitList(repository.readAll())

        val reciclerview:RecyclerView = binding.tasklist
        reciclerview.layoutManager=LinearLayoutManager(this)
        reciclerview.adapter=adapter

        binding.botonanadir.setOnClickListener{
            val intent:Intent=Intent(this@MainActivity,TaskDetailActivity::class.java)
            startActivity(intent)
        }

    }
}