package com.example.lab5q3.Controller;

import com.example.lab5q3.ApiResponse.ApiResponse;
import com.example.lab5q3.Module.Event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        events.add(event);
        return ResponseEntity.status(200).body("Event added");
    }

    @GetMapping("/display")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        events.set(index, event);
        return ResponseEntity.status(200).body("Event updated");
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body("Event deleted");
    }

    @PutMapping("/change-capacity/{index}")
    public ResponseEntity changeCapacity(@PathVariable int index,@RequestParam @Digits(integer = 5,fraction = 0,message = "The capacity should be a number with 5 digits maximum")
    @Min(value = 25,message = "The Capacity should be more than 25") int newCapacity) {
        events.get(index).setCapacity(newCapacity);
        return ResponseEntity.status(200).body("Event updated");
    }

    @GetMapping("/search/{index}")
    public ResponseEntity getEventById(@PathVariable int index) {
        return ResponseEntity.status(200).body(events.get(index+1));
    }

}
