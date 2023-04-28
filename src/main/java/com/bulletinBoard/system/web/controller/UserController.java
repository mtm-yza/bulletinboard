package com.bulletinBoard.system.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
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
import com.bulletinBoard.system.bl.service.user.UserService;
import com.bulletinBoard.system.common.Constant;
import com.bulletinBoard.system.common.ControllerUtil;
import com.bulletinBoard.system.web.form.UserForm;
import com.google.gson.Gson;

/**
 * <h2>UserController Class</h2>
 * <p>
 * User Controller
 * </p>
 * 
 * @author YeZawAung
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * <h2>HOME_VIEW</h2>
     * <p>
     * HOME_VIEW
     * </p>
     */
    private static final String HOME_VIEW = "userListView";

    /**
     * <h2>HOME_REDIRECT</h2>
     * <p>
     * View Name of User Home
     * </p>
     */
    private static final String HOME_REDIRECT = "redirect:/user/list";

    /**
     * <h2>ADD_VIEW</h2>
     * <p>
     * View Name of User List page
     * </p>
     */
    private static final String ADD_VIEW = "addUserView";

    /**
     * <h2>userService</h2>
     * <p>
     * User Service
     * </p>
     */
    @Autowired
    UserService userService;

    /**
     * <h2>getHomeView</h2>
     * <p>
     * Get Home View Of User
     * </p>
     *
     * @param session HttpSession
     * @return ModelAndView
     */
    @GetMapping({ "/", "" })
    protected ModelAndView getHomeView(HttpSession session) {
        session.removeAttribute("totalCount");
        session.removeAttribute("pageIndex");
        session.removeAttribute("pageSize");
        return new ModelAndView(HOME_REDIRECT);
    }

    /**
     * <h2>getAddUserForm</h2>
     * <p>
     * Redirect To Add User Form Page
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @GetMapping("add")
    protected ModelAndView getAddUserForm() {
        ModelAndView mv = new ModelAndView(ADD_VIEW);
        mv.addObject("user", new UserForm());
        return mv;
    }

    /**
     * <h2>addUser</h2>
     * <p>
     * Add User
     * </p>
     *
     * @param user              UserForm
     * @param bindingResult     BindingResult
     * @param redirectAttribute RedirectAttributes
     * @return ModelAndView
     */
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

    /**
     * <h2>getUserListView</h2>
     * <p>
     * Redirect To User List Page
     * </p>
     *
     * @param page    int
     * @param user    UserForm
     * @param session HttpSession
     * @return mv ModelAndView
     */
    @GetMapping("list")
    protected ModelAndView getUserListView(@RequestParam(defaultValue = "0") int page,
            @ModelAttribute("user") UserForm user, HttpSession session) {
        ModelAndView mv = new ModelAndView(HOME_VIEW);
        int count = userService.doGetUserCount();
        int offset = ControllerUtil.setPaginationData(session, page, count);
        // Get Data for Users
        List<UserDTO> users = this.userService.doGetUserList(offset, ControllerUtil.PAGE_SIZE);
        mv.addObject("users", (new Gson()).toJson(users));
        // User Form to Edit
        if (user.getId() != 0) {
            mv.addObject("user", user);
        } else {
            mv.addObject("user", new UserForm());
        }
        return mv;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user              UserForm
     * @param bindingResult     BindingResult
     * @param redirectAttribute RedirectAttribute
     * @return ModelAndView
     */
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
        this.userService.doUpdateUser(user);
        ControllerUtil.addRedirectMessages(redirectAttribute, "success", "Update Success",
                "User Has Been Updated Successfuly");
        return mv;
    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
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

    /**
     * <h2>getMessage</h2>
     * <p>
     * Get Message from User Service
     * </p>
     *
     * @param result int
     * @return message String
     */
    private String getMessage(int result) {
        String message = null;
        switch (result) {
        case Constant.SUCCESS:
            message = "User is Successfully Added";
            break;
        case Constant.EMAIL_ALREADY_REGISTERED:
            message = "Email is Already Registered";
            break;
        case Constant.INVALID_CREDENTIAL:
            message = "Invalid Username or Password";
            break;
        }
        return message;
    }
}
