package com.spring.restapi.fooddelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restapi.fooddelivery.dao.AdminRepository;
import com.spring.restapi.fooddelivery.dto.ResponseDto;
import com.spring.restapi.fooddelivery.dto.SignInDto;
import com.spring.restapi.fooddelivery.dto.SignInResponseDto;
import com.spring.restapi.fooddelivery.dto.SignupDto;
import com.spring.restapi.fooddelivery.dto.UserUpdateDto;
import com.spring.restapi.fooddelivery.exceptions.AuthenticationFailException;
import com.spring.restapi.fooddelivery.exceptions.CustomException;
import com.spring.restapi.fooddelivery.model.User;
import com.spring.restapi.fooddelivery.service.AdminService;
import com.spring.restapi.fooddelivery.service.AuthenticationService;

@RequestMapping("admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AdminService adminService;

    //TODO token should be updated
    
    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return adminService.signUp(signupDto);
    }
    
    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        return adminService.signIn(signInDto);
    }
    
  /* @PostMapping("/updateFoodItem")
   public ResponseDto updateFoodItem(@RequestParam("token") String token, @RequestBody FoodItemUpdateDto fooditemUpdateDto) {
   authenticationService.authenticate(token);
    return adminService.updateFoodItem(token, fooditemUpdateDto);
   }
   
   @DeleteMapping("/deleteFoodItem")
   public ResponseDto deleteFoodItem(@RequestParam("token") String token, @RequestBody FoodItemDeleteDto fooditemDeleteDto) {
   authenticationService.authenticate(token);
    return adminService.deleteFoodItem(token, fooditemDeleteDto);
   }*/
}