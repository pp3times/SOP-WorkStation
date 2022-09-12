package com.sop.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.text.ParagraphView;

@Route(value = "index1")
public class MathView extends VerticalLayout {
    private NumberField n1, n2;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnMod, btnMax;
    private Paragraph p1;
    private TextField ans;
    private HorizontalLayout h1;

    public MathView() {
        n1 = new NumberField("Number 1");
        n2 = new NumberField("Number 2");
        ans = new TextField("Answer");
        btnPlus = new Button("+");
        btnPlus.getStyle().set("cursor", "pointer");
        btnMinus = new Button("-");
        btnMinus.getStyle().set("cursor", "pointer");
        btnMultiply = new Button("x");
        btnMultiply.getStyle().set("cursor", "pointer");
        btnDivide = new Button("/");
        btnDivide.getStyle().set("cursor", "pointer");
        btnMod = new Button("Mod");
        btnMod.getStyle().set("cursor", "pointer");
        btnMax = new Button("Max");
        btnMax.getStyle().set("cursor", "pointer");
        n1.setSizeFull();
        n2.setSizeFull();
        h1 = new HorizontalLayout();
        h1.setSizeFull();
        btnPlus.setSizeFull();
        btnMinus.setSizeFull();
        btnMultiply.setSizeFull();
        btnDivide.setSizeFull();
        btnMod.setSizeFull();
        btnMax.setSizeFull();
        ans.setSizeFull();
        p1 = new Paragraph("Operator");
        p1.getStyle().set("margin-bottom", "-14px");;
        p1.getStyle().set("color", "hsla(214, 40%, 20%, 0.94)");
        p1.getStyle().set("font-weight", "500");
        p1.getStyle().set("font-size", "0.875rem");
        h1.add(btnPlus, btnMinus, btnMultiply, btnDivide, btnMod, btnMax);
        add(n1, n2, p1, h1, ans);

        btnPlus.addClickListener(e -> {
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/plus/" + a + "/" + b)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });

        btnMinus.addClickListener(e -> {
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/minus/" + a + "/" + b)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });

        btnMultiply.addClickListener(e -> {
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/multi/" + a + "/" + b)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });

        btnDivide.addClickListener(e -> {
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/divide/" + a + "/" + b)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });

        btnMod.addClickListener(e -> {
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/mod/" + a + "/" + b)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });

        btnMax.addClickListener(e -> {
//            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            double a = n1.getValue();
            double b = n2.getValue();

            String v = WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/max")
                    .body(Mono.just(new RequestNumber(a, b)), RequestNumber.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ans.setValue(v);
        });
    }
}
