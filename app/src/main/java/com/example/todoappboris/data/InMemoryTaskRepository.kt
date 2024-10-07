package com.example.todoappboris.data

class InMemoryTaskRepository private constructor():TaskRepository {


    companion object{
        private var instance:InMemoryTaskRepository?=null
        fun getInstance():InMemoryTaskRepository{
            if(instance==null){
                instance=InMemoryTaskRepository()
            }
            return instance!!
        }
    }

    private val _task= mutableListOf(
        Task(1, "Comprar comida", "Ir al supermercado a comprar fruta y verduras", false),
        Task(2, "Hacer ejercicio", "Realizar una rutina de 30 minutos", true),
        Task(3, "Estudiar Kotlin", "Leer sobre clases y objetos en Kotlin", false),
        Task(4, "Llamar a mamá", "Llamar para ponerse al día", true)
    )



    override fun create(task: Task): Task {
        val id:Int=if (_task.size==0) 1 else _task.last().id+1
        val taskfinal:Task=Task(id,task.title,task.body,task.completed)
        _task.add(taskfinal)
        return taskfinal
    }

    override fun update(task: Task): Task {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {

        val task=_task.find { c->c.id==id }
        if(task!=null){
            _task.removeIf { c->c.id==id }
        }

    }

    override fun readOne(id: Int): Task {

        return _task.get(id)

    }

    override fun readAll(): List<Task> {
        return _task.toList()
    }
}