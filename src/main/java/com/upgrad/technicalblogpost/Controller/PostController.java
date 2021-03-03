package com.upgrad.technicalblogpost.Controller;

import com.upgrad.technicalblogpost.model.Post;
import com.upgrad.technicalblogpost.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    public PostController(){
        System.out.println("*********** PostController ***********");
    }
    //localhost:8080/posts : GET
    @Autowired
    PostService postService;
    @RequestMapping("/posts")
    public String getUserPost(Model model){
        List<Post> posts= postService.getAllPosts();
        model.addAttribute("posts",posts);
        return "posts";
    }
    //TODO: GET : posts/newpost  ,  POST: post/create
    @RequestMapping("/posts/newpost")
    public String newPost(){
        return "posts/create";
    }
    @RequestMapping(value="/posts/create", method= RequestMethod.POST)
    public String createPost(Post newPost){
        newPost.setDate(new Date());
        postService.createPost(newPost);
        return "redirect:/posts";
    }
    @RequestMapping(value="/deletepost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editpost", method = RequestMethod.GET)
    public String editPost(@RequestParam(value = "postId") Integer postId, Model model){
        Post post=postService.getPost(postId);
        model.addAttribute("post",post);
        return "posts/edit";
    }

    @RequestMapping(value = "/editpost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(value = "postId") Integer postId, Post updatedPost){
        updatedPost.setId(postId);
        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }


}