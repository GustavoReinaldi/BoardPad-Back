package br.com.boardpadbackend.controllers;

import br.com.boardpadbackend.dto.TaskDto;
import br.com.boardpadbackend.dto.inputs.TaskInputDto;
import br.com.boardpadbackend.entity.projections.TaskProjection;
import br.com.boardpadbackend.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/tasks")
@RestController
@Api(value = "Tasks controller", tags = {"Tasks"})
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation("Create new Tasks")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Task created successfully"),
            @ApiResponse(code = 500, message = "Internal server error. Task wasn't created")
    })
    @PostMapping
    public ResponseEntity<TaskDto> createNewTask (@RequestBody TaskInputDto inputTask){
        return ResponseEntity.ok().body(taskService.createTask(inputTask));
    }

    @ApiOperation("List all tasks")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No content to show"),
            @ApiResponse(code = 500, message = "Internal server error. Task wasn't created")
    })
    @GetMapping
    public List<TaskDto> list (){
        return taskService.listAllTasks();
    }

    @ApiOperation("Update tasks")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal server error. Task wasn't created")
    })
    @PutMapping(path = "{id}")
    public void updateTask (@PathVariable("id") Long taskId, @RequestParam("newStatusId") Long newStatusId) {
        taskService.updateStatusTask(taskId, newStatusId);
    }
}
