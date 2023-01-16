package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        imageRepository2.save(image);
        blogRepository.save(blog);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        String imageDimension = image.getDimensions();
        int screenDimensionsLength = Integer.parseInt(screenDimensions.split("X")[0]);
        int screenDimensionsBreadth = Integer.parseInt(screenDimensions.split("X")[1]);

        int imageLength = Integer.parseInt(imageDimension.split("X")[0]);
        int imageBreadth = Integer.parseInt(imageDimension.split("X")[1]);

        int maxLength = screenDimensionsLength / imageLength;
        int maxBreadth = screenDimensionsBreadth / imageBreadth;

        return maxLength * maxBreadth;
    }
}
