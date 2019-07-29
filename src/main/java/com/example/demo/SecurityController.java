package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class SecurityController {

//    to link cloudinary config

    @Autowired
    CloudinaryConfig cloudc;


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
        return "redirect:/login";
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

    @RequestMapping("/")
    public String index(){
        return "index";
    }

//    @RequestMapping("/")
//    public String listMessages(Model model){
//        model.addAttribute("messages", messageRepository.findAll());
//
//        return "msgList";
//    }

    @GetMapping("/add")
    public String MsgForm(Model model){
        model.addAttribute("message", new Message());
        return "msgForm";
    }



    @PostMapping("/add")
    public String processMessage(@ModelAttribute Message message,
                                 @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            message.setUser(userService.getUser());
            messageRepository.save(message);
            return "redirect:/";
        }
        try{
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            message.setPhoto(uploadResult.get("url").toString());
            message.setUser(userService.getUser());
            messageRepository.save(message);
        }catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return  "redirect:/";
    }

//    @PostMapping("/add")
//    public String processMsgForm(@Valid Message message,
//                                 BindingResult result){
//        if (result.hasErrors()){
//            return "msgForm";
//        }


//        Date date = new Date();
//        try{
//            date = new SimpleDateFormat("yyyy-mm-dd").parse("posteddate");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        message.setPosteddate(date);
//        messageRepository.save(message);
//        return "redirect:/";
//    }

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

    @RequestMapping("add .")
    public String deleteMessages(@PathVariable("id") long id){
        messageRepository.deleteById(id);;
        return "redirect:/";
    }
//create profile page...
//    @RequestMapping ("/view")
//    public String editprofile (Model model){
//        model.addAttribute("user",userService.getUser());
//        return "profile";
//    }
}
