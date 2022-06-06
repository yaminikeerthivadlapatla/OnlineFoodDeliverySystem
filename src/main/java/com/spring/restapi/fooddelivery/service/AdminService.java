package com.spring.restapi.fooddelivery.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restapi.fooddelivery.configuration.MessageStrings;
import com.spring.restapi.fooddelivery.dao.AdminRepository;
import com.spring.restapi.fooddelivery.dao.UserRepository;
import com.spring.restapi.fooddelivery.dto.ResponseDto;
import com.spring.restapi.fooddelivery.dto.SignInDto;
import com.spring.restapi.fooddelivery.dto.SignInResponseDto;
import com.spring.restapi.fooddelivery.dto.SignupDto;
import com.spring.restapi.fooddelivery.dto.UserCreateDto;
import com.spring.restapi.fooddelivery.dto.UserUpdateDto;
import com.spring.restapi.fooddelivery.enums.ResponseStatus;
import com.spring.restapi.fooddelivery.enums.Role;
import com.spring.restapi.fooddelivery.exceptions.AuthenticationFailException;
import com.spring.restapi.fooddelivery.exceptions.CustomException;
import com.spring.restapi.fooddelivery.model.Admin;
import com.spring.restapi.fooddelivery.model.AuthenticationToken;
import com.spring.restapi.fooddelivery.model.User;
import com.spring.restapi.fooddelivery.utils.Helper;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(AdminService.class);


    public ResponseDto signUp(SignupDto signupDto)  throws CustomException {
        // Check to see if the current email address has already been registered.
        if (Helper.notNull(adminRepository.findByEmail(signupDto.getEmail()))) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("Admin already exists");
        }
     // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }
    
        Admin admin = new Admin(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), Role.admin, encryptedPassword );
		return null;
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

	public SignInResponseDto signIn(SignInDto signInDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/*public ResponseDto updateFoodItem(String token, FoodItemUpdateDto fooditemUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseDto deleteFoodItem(String token, FoodItemDeleteDto fooditemDeleteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseDto updateFoodItem(String token, FoodItemUpdateDto fooditemUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}*/
}