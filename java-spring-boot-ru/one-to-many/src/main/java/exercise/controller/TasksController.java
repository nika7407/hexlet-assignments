package exercise.controller;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.mapper.UserMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("")
    public List<TaskDTO> getTasks() {
        List<TaskDTO> list = new ArrayList<>();

        var toMap = taskRepository.findAll();
        toMap.forEach(task -> list.add(taskMapper.map(task)));
        return list;
    }

    @GetMapping("/{id}")
    public TaskDTO getTasks(@PathVariable Long id) {


        var task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id + " not found"));
        return taskMapper.map(task);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskCreateDTO createDTO) {
        var task = taskMapper.map(createDTO);
        taskRepository.save(task);

    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO){
        var task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id + " not found"));

        if (taskUpdateDTO.getAssigneeId() != null) {
            User assignee = userRepository.findById(taskUpdateDTO.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            task.setAssignee(assignee);
        }

        taskMapper.update(taskUpdateDTO, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
         taskRepository.deleteById(id);
    }
    // END
}
