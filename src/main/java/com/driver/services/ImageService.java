package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image(description, dimensions);
        imageRepository2.save(image);

        List<Image> imageList = new ArrayList<>();
        imageList.add(image);
        blog.setListOfImage(imageList);

        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.delete(image);
    }

//    public Image findById(int id) {
//
//    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        String imageDimension= image.getDimensions();
        String current = "";
        for(int i = 0; i < imageDimension.length(); i++) {
            if(imageDimension.charAt(i) == 'X') {
                break;
            }
            else {
                current += screenDimensions.charAt(i);
            }
        }
        int currentDim = Integer.parseInt(current);

        String current1 = "";
        for(int i = 0; i < screenDimensions.length(); i++) {
            if(screenDimensions.charAt(i) == 'X'){
                break;
            }
            else {
                current1 += screenDimensions.charAt(i);
            }
        }
        int current1Dim = Integer.parseInt(current1);

        return current1Dim / currentDim;
    }
}
