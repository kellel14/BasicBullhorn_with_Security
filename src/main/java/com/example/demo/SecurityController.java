package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SecurityController {

//    to link cloudinary config

//    @Autowired
//    CloudinaryConfig cloudc;


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
// option to use Get + Most method or RequestMapping
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String showRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result,
//                                       Model model) {
//        model.addAttribute("user", user);
//        if(result.hasErrors()){
//            return "registration";
//        }
//        else{
//            userService.saveUser(user);
//            model.addAttribute("message", "User Account Created");
//        }
//        return "index";
//    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result, Model model){
        model.addAttribute("user", user);
        if (result.hasErrors()){
            return "registration";
        }
        else{
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "index";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

//    cloudinary stuff


//    message controller stuff



    @RequestMapping("/home")
    public String listMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "msgList";
    }

    @GetMapping("/add")
    public String MsgForm(Model model){
        model.addAttribute("message", new Message());
        return "msgForm";
    }

    @PostMapping("/process")
    public String processMsgForm(@Valid Message message,
                                 BindingResult result){
        if (result.hasErrors()){
            return "msgForm";
        }

//        Date date = new Date();
//        try{
//            date = new SimpleDateFormat("yyyy-mm-dd").parse("posteddate");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        message.setPosteddate(date);
        messageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMessages(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "msgShow";
    }

    @RequestMapping("/update/{id}")
    public String updateMessages(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "msgForm";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMessages(@PathVariable("id") long id){
        messageRepository.deleteById(id);;
        return "redirect:/";
    }

//    @RequestMapping ("/viewprofile")
//    public String editprofile (Model model){
//        model.addAttribute("user",userService.getUser());
//        return "profile";
//    }
}
