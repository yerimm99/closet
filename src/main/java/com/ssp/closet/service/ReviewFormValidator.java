//package com.ssp.closet.service;
//
//import com.ssp.closet.controller.ReviewCommand;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//
//@Component
//public class ReviewFormValidator implements Validator {
//    
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return ReviewCommand.class.isAssignableFrom(clazz);
//    }
//    
//    @Override
//    public void validate(Object target, Errors errors) {
//        ReviewCommand reviewCommand = (ReviewCommand) target;
//        
//        // userId 필드 검증
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "required.field");
//        
//        // writeDate 필드 검증 (예시로 빈 값은 허용하고, 필요에 따라 유효성 검증 로직 추가)
//        
//        // content 필드 검증
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required.field");
//        
//        // rating 필드 검증 (예시로 범위를 0.5부터 5.0까지로 설정하고, 필요에 따라 유효성 검증 로직 추가)
//        if (reviewCommand.getRating() < 0.5 || reviewCommand.getRating() > 5.0) {
//            errors.rejectValue("rating", "invalid.rating.range");
//        }
//        
//        // 필요한 다른 리뷰 정보의 유효성 검증 로직 추가
//    }
//}