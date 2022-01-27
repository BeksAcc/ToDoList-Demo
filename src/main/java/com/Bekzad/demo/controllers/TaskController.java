package com.Bekzad.demo.controllers;

import com.Bekzad.demo.models.Task;
import com.Bekzad.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String taskMain(Model model){
        model.addAttribute("title", "Tasks");
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks",tasks);
        return "task";
    }

    @GetMapping("/task/add")
    public String taskAdd(Model model){
        model.addAttribute("title", "Add");
        return "task-add";
    }

    @PostMapping("/task/add")
    public String taskAddDo(@RequestParam String title, @RequestParam String description, @RequestParam Date date, @RequestParam String priority, Model model){
        Task task = new Task(title, description, date, priority);
        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/task/update")
    public String taskUpdateDo(@RequestParam(value = "id") long id, @RequestParam boolean done, Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(!done);
        taskRepository.save(task);
        return "redirect:/";
    }
    @PostMapping("/task/delete")
    public String taskDeleteDo(@RequestParam(value = "id") long id, Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/";
    }
}
