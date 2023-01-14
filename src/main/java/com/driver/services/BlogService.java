package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageRepository imageRepository1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = blogRepository1.findAll();
        return blogList;

    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog(title, content);
        blogRepository1.save(blog);
        //updating the blog details

        //Updating the userInformation and changing its blogs
        List<Blog> blogList = new ArrayList<>();
        blogList.add(blog);
        User user1 = userRepository1.findById(userId).get();
        user1.setListOfBlogs(blogList);
    }

//    public Blog findBlogById(int blogId){
//        //find a blog
//    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Image image = new Image(description, dimensions);
        imageRepository1.save(image);


        List<Image> imagelist = new ArrayList<>();
        imagelist.add(image);
        Blog blog = blogRepository1.findById(blogId).get();
        blog.setListOfImage(imagelist);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
