package com.sop.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route("index2")
public class CashierView extends VerticalLayout {
    private TextField change, b1000, b500, b100, b20, b10, b5, b1;
    private Button changeBtn;

    public CashierView() {
        change = new TextField("เงินทอน");
        b1000 = new TextField();
        b500 = new TextField();
        b100 = new TextField();
        b20 = new TextField();
        b10 = new TextField();
        b5 = new TextField();
        b1 = new TextField();
        b1000.setPrefixComponent(new Paragraph("$1000:"));
        b500.setPrefixComponent(new Paragraph("$500:"));
        b100.setPrefixComponent(new Paragraph("$100:"));
        b20.setPrefixComponent(new Paragraph("$20:"));
        b10.setPrefixComponent(new Paragraph("$10:"));
        b5.setPrefixComponent(new Paragraph("$5:"));
        b1.setPrefixComponent(new Paragraph("$1:"));
        changeBtn = new Button("คำนวณเงินทอน");
        changeBtn.getStyle().set("color", "#007aff");
        changeBtn.getStyle().set("cursor", "pointer");

        add(change, changeBtn, b1000, b500, b100, b20, b10, b5, b1);

        changeBtn.addClickListener(e -> {
            Change result = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/getChange/" + change.getValue())
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();

            assert result != null;
            b1000.setValue(result.getB1000() + "");
            b500.setValue(result.getB500() + "");
            b100.setValue(result.getB100() + "");
            b20.setValue(result.getB20() + "");
            b10.setValue(result.getB10() + "");
            b5.setValue(result.getB5() + "");
            b1.setValue(result.getB1() + "");
        });
    }
}
