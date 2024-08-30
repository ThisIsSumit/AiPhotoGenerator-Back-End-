package com.example.aiphotogenrator.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    public ChatService (ChatModel chatModel){
        this.chatModel=chatModel;
    }
    private  final ChatModel chatModel;

    public  String getResponse(String prompt){
       return  chatModel.call(prompt);
    }
    public String getResponseOptions(String prompt){
        ChatResponse response=
         chatModel.call(new Prompt(
                prompt, OpenAiChatOptions
                .builder()
                .withModel("gpt-4o-mini")
                .withTemperature(0.4F)
                .build()));

        return  response.getResult().getOutput().getContent();
    }
}
