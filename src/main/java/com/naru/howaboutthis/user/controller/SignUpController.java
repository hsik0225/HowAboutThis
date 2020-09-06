package com.naru.howaboutthis.user.controller;

import com.naru.howaboutthis.exception.DuplicateEmailException;
import com.naru.howaboutthis.user.domain.Policy;
import com.naru.howaboutthis.user.domain.PolicySingleton;
import com.naru.howaboutthis.user.domain.User;
import com.naru.howaboutthis.user.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@Slf4j
@Validated // @RequestParam 이나 @PatVariable을 메소드에서 Validation할 수 있게 한다
@RequestMapping("/api/users")
@RestController
public class SignUpController {

    private final SignUpService signupService;

    public SignUpController(SignUpService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/policy")
    public ResponseEntity<Policy> policy() {
        return ResponseEntity.ok(PolicySingleton.getInstance());
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Valid User user) {
        signupService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Void> canUseEmail(@PathVariable @Email String email) {
        signupService.isDuplicated(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            sb.append(error.getField()).append("의 값을 잘못 입력하셨습니다\n");
            sb.append(error.getDefaultMessage()).append("\n\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        for (String error : e.getMessage().split(",")) {
            sb.append(error.trim()).append("\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
}
