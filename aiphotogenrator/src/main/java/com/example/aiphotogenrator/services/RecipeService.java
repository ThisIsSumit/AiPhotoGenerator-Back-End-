package com.example.aiphotogenrator.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    @Autowired
    private ChatModel chatModel;
    public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestriction){
        var temp= """
I want to crete a recipe using the following ingredients :{ingredients};
The  cuisine type I prefer is {cuisine}.
Please consider the following dietary restriction :{dietaryRestriction}.
Please provide me with a detailed recipe including title , list of ingredients , and cooking instructions
 """;
        PromptTemplate promptTemplate=new PromptTemplate(temp);
        Map<String, Object> param=Map.of(
                "ingredients" , ingredients,
                "cuisin e",cuisine,
                "dietaryRestrictions", dietaryRestriction
        );
Prompt prompt = promptTemplate.create(param);
return chatModel.call(prompt).getResult().getOutput().getContent();

    }

}
