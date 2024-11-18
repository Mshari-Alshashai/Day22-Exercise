package com.example.lab5q2.Controller;

import com.example.lab5q2.ApiResponse.ApiResponse;
import com.example.lab5q2.Module.Project;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(200).body("Project added successfully");
    }

    @GetMapping("/display")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body("Project updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body("Project deleted successfully");
    }

    @PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if (projects.get(index).getStatus().equals("Not Started")){
            projects.get(index).setStatus("In Progress");
            return ResponseEntity.status(200).body("Project status changed successfully");
        } else if (projects.get(index).getStatus().equals("In Progress")) {
            projects.get(index).setStatus("Completed");
            return ResponseEntity.status(200).body("Project status changed successfully");
        }else
        return ResponseEntity.status(200).body("Can't change project status");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equals(title)) {
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(200).body("Not found");
    }

    @GetMapping("/display-by-company/{companyName}")
    public ResponseEntity displayProjectsByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> projectsByCompany = new ArrayList<>();

        for (Project project : projects) {
            if (project.getCompanyName().equals(companyName)) {
                projectsByCompany.add(project);
            }
        }
        return ResponseEntity.status(200).body(projectsByCompany);
    }
}
