package PeakSoft.controller;

import PeakSoft.entyti.User;
import PeakSoft.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import PeakSoft.service.UserService;



@Controller
@RequestMapping( "/users")
@RequiredArgsConstructor
public class RegistrControll {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("findUser", userService.findAll());
        return "/users";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "/userForm";
    }

    @PostMapping("/saveUser")
    public String signUp(@ModelAttribute("newUser") User user) {
        userService.signUp(user);
        return "redirect:/users";
    }


    @GetMapping("/login")
    public String sigIn(Model model){
        model.addAttribute("newUser", new User());
        return "login";
    }

    @PostMapping("/signIn")
    public String logIn(@ModelAttribute("newUser") User user, Model model){
        try {
           User currentUser = userService.signIn(user);
            if (currentUser != null){
                model.addAttribute("currentUser", currentUser);
                return "/main";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/profilePage")
    public String profileee(Model model){
        model.addAttribute("newUser", userService.findById(UserServiceImpl.current.getId()));
        return "/main";
    }

}





