package com.sop.week4;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value = "index")
public class View extends VerticalLayout {

    private final TextField n1, n2, n3;
    private final Button btnPlus, btnMinus, btn;

    public View() {
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        n3 = new TextField("Answer");
        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btn = new Button("Show names");
        add(n1, n2, n3, btnPlus, btnMinus, btn);
        btnPlus.addClickListener(e -> {
           double num1 = Double.parseDouble(n1.getValue());
           double num2 = Double.parseDouble(n2.getValue());

           String out = WebClient.create()
                   .get()
                   .uri("http://localhost:8080/"+num1+"+"+num2)
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();

                    n3.setValue(out);
        });

        btn.addClickListener(e -> {
            MyData out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/names")
                    .retrieve()
                    .bodyToMono(MyData.class)
                    .block();

            for(String name: out.getName()) {
                new Notification(name, 5000).open();
            }
        });
//        btnMinus.addClickListener(e -> {
//            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//            formData.add("n1", n1.getValue());
//            formData.add("n2", n2.getValue());
//
//            String out = WebClient.create()
//                    .get()
//                    .uri("http://localhost:8080/test1")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .body(BodyInserters.fromFormData(formData))
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block()
//            n3.setValue(out);
//        });
    }

    public static class MyData {
        private ArrayList<String> name = new ArrayList<>();
        public ArrayList<String> getName() { return name; }
        public void setName(ArrayList<String> name) { this.name = name; }
    }
}
