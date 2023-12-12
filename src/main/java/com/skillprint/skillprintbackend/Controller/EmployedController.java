package com.skillprint.skillprintbackend.Controller;

import com.skillprint.skillprintbackend.DTO.EmployedCreationDto;
import com.skillprint.skillprintbackend.Entity.Employed;
import com.skillprint.skillprintbackend.Service.EmployedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employed")
public class EmployedController {

    private final EmployedService employedService;

    public EmployedController(EmployedService employedService) {
        this.employedService = employedService;
    }

    @PostMapping("/register")
    public ResponseEntity<Employed> registerEmployed(@RequestBody EmployedCreationDto employedCreationDto) {
        Employed createEmployed = employedService.registerEmployed(employedCreationDto);
        return ResponseEntity.ok(createEmployed);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Employed> getEmployedById(@PathVariable String username) {
        Employed employed = employedService.getEmployedByUsername(username);
        return ResponseEntity.ok(employed);
    }


}
