package com.bulletinBoard.system.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bulletinBoard.system.bl.dto.UserDTO;
import com.bulletinBoard.system.bl.service.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.common.ControllerUtil;
import com.bulletinBoard.system.web.form.UserForm;
import com.google.gson.Gson;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String HOME_VIEW = "userListView";
    private static final String HOME_REDIRECT = "redirect:/user/list";
    private static final String ADD_VIEW = "addUserView";

    @Autowired
    UserService userService;

    @GetMapping({ "/", "" })
    protected ModelAndView getHomeView(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        return new ModelAndView(HOME_REDIRECT);
    }

    @GetMapping("add")
    protected ModelAndView getAddUserForm() {
        ModelAndView mv = new ModelAndView(ADD_VIEW);
        mv.addObject("user", new UserForm());
        return mv;
    }

    @PostMapping("add")
    protected ModelAndView addUser(@ModelAttribute @Valid UserForm user, BindingResult bindingResult,
            RedirectAttributes redirectAttribute) {
        ModelAndView mv = new ModelAndView();
        // Check Validation Error
        if (bindingResult.hasErrors()) {
            mv.setViewName(ADD_VIEW);
            mv.addObject("user", user);
            mv.addObject("errors", ControllerUtil.getErrorMessages(bindingResult));
            return mv;
        }
        // Check if User is Saved
        int result = userService.doAddUser(user);
        if (result != Constant.SUCCESS) {
            mv.setViewName(ADD_VIEW);
            mv.addObject("user", user);
            mv.addObject("msgType", "error");
            mv.addObject("msgHeader", "Failed to Add User");
            mv.addObject("msg", this.getMessage(result));
            return mv;
        }
        // Execute if successful
        mv.setViewName(HOME_REDIRECT);
        ControllerUtil.addRedirectMessages(redirectAttribute, "success", "Adding User Successfully",
                "User has been Added Successfully");
        return mv;
    }

    @GetMapping("list")
    protected ModelAndView getUserListView(@RequestParam(defaultValue = "1") int page,
            @ModelAttribute("user") UserForm user) {
        int size = 10;
        ModelAndView mv = new ModelAndView(HOME_VIEW);
        // Get List of Post by offset
        int offset = (page - 1) * size;
        List<UserDTO> userDtos = userService.doGetUserList(offset, size);
        // Calculate Total Page for Pagination
        int count = userService.doGetUserCount();
        int pageCount = count / size;
        int remainder = count % size;
        if (remainder > 0) {
            pageCount += 1;
        }
        mv.addObject("users", (new Gson()).toJson(userDtos));
        mv.addObject("pageIndex", page);
        mv.addObject("pageCount", pageCount);
        mv.addObject("pageSize", size);
        // User Form to Edit
        if (user.getId() != 0) {
            mv.addObject("user", user);
        } else {
            mv.addObject("user", new UserForm());
        }
        return mv;
    }

    @PostMapping("update")
    protected ModelAndView updateUser(@Valid @ModelAttribute UserForm user, BindingResult bindingResult,
            RedirectAttributes redirectAttribute) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        // Check Validation
        if (bindingResult.hasErrors()) {
            redirectAttribute.addFlashAttribute("errors", ControllerUtil.getErrorMessages(bindingResult));
            redirectAttribute.addFlashAttribute("user", user);
            return mv;
        }
        this.userService.doUpdateUser(user, 1);
        ControllerUtil.addRedirectMessages(redirectAttribute, "success", "Update Success",
                "User Has Been Updated Successfuly");

        // Check UserForm Update Result
//        checkResult(mv, userService.doUpdateUser(user, 1));
//        return mv;
        
        return mv;
    }

    @PostMapping("delete")
    protected ModelAndView deleteUser(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView(HOME_REDIRECT);
        if (id <= 0) {
            mv.addObject("msg", "Invalid User ID");
            return mv;
        }
        userService.doDeleteUser(id);
        return mv;
    }

    private String getMessage(int result) {
        String message = null;
        switch (result) {
        case Constant.SUCCESS:
            message = "User is Successfully Added";
            break;
        case Constant.EMAIL_ALREADY_REGISTERED:
            message = "Email is Already Registered";
            break;
        }
        return message;
    }
}
