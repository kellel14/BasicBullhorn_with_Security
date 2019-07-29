//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Controller
//public class MessageController {
//    @Autowired
//    MessageRepository messageRepository;
//
//    @RequestMapping("/home")
//    public String listMessages(Model model){
//        model.addAttribute("messages", messageRepository.findAll());
//        return "msgList";
//    }
//
//    @GetMapping("/add")
//    public String MsgForm(Model model){
//        model.addAttribute("message", new Message());
//        return "msgForm";
//    }
//
//    @PostMapping("/process")
//    public String processMsgForm(@Valid Message message,
//                                 BindingResult result){
//        if (result.hasErrors()){
//            return "msgForm";
//        }
//
////        Date date = new Date();
////        try{
////            date = new SimpleDateFormat("yyyy-mm-dd").parse("posteddate");
////        }
////        catch (Exception e){
////            e.printStackTrace();
////        }
////        message.setPosteddate(date);
//        messageRepository.save(message);
//        return "redirect:/";
//    }
//
//    @RequestMapping("/detail/{id}")
//    public String showMessages(@PathVariable("id") long id, Model model){
//        model.addAttribute("message", messageRepository.findById(id).get());
//        return "msgShow";
//    }
//
//    @RequestMapping("/update/{id}")
//    public String updateMessages(@PathVariable("id") long id, Model model){
//        model.addAttribute("message", messageRepository.findById(id).get());
//        return "msgForm";
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String deleteMessages(@PathVariable("id") long id){
//        messageRepository.deleteById(id);;
//        return "redirect:/";
//    }
//
//}
