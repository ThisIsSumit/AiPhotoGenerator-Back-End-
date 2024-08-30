package com.example.aiphotogenrator.controllers;

import com.example.aiphotogenrator.services.ChatService;
import com.example.aiphotogenrator.services.ImageService;
import com.example.aiphotogenrator.services.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAIController {
    @Autowired
    private   ChatService chatService;
   @Autowired
   private  ImageService imageService;
    @Autowired
    private RecipeService recipeService;


    @GetMapping("ask-ai")
    public  String getResponse(@RequestParam String prompt){
        return  chatService.getResponse(prompt);


    }
    @GetMapping("ask-ai-options")
    public  String getResponseOptions(@RequestParam String prompt){
        return  chatService.getResponse(prompt);

    }

    @GetMapping("generate-image")
    public  List<String> generateImage(@NotNull HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam  (defaultValue="hd") String quality,
                                       @RequestParam  (defaultValue="1") int n,
                                       @RequestParam  (defaultValue="1024") int width,
                                       @RequestParam  (defaultValue="1024") int height) throws IOException {
       ImageResponse imageResponse= imageService.generateImage(prompt, quality,n,width,height);
//     String imageUrl= imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(imageUrl);
        return imageResponse.getResults().stream()
                .map(result->result.getOutput().getUrl())
                .collect(Collectors.toList());
    }
@GetMapping("recipe-gen")
    public  String recipeCreator(@RequestParam String ingredients,
                                       @RequestParam (defaultValue ="any") String cuisine,
                                       @RequestParam (defaultValue ="")String dietaryRestriction){
        return  recipeService.createRecipe(ingredients,cuisine,dietaryRestriction);

}

}
