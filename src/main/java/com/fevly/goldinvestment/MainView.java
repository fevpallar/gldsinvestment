package com.fevly.goldinvestment;
import com.fevly.goldinvestment.ui.UI;
import com.fevly.goldinvestment.helper.NoRekening;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        String norekening = "123456";
        String endpoint="http://localhost:8080/submit/api/saldo";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        NoRekening noRekening = new NoRekening();
        noRekening.setNorek(norekening);
        HttpEntity<NoRekening> entity = new HttpEntity<NoRekening>(noRekening,headers);
        RestTemplate restTemplate = new RestTemplate();


         NoRekening answer = restTemplate.postForObject(endpoint, entity, NoRekening.class);
         Button submitRekening = new Button("Check saldo nomor rekening 123456",
                e ->add(UI.rekeningGrid) );

        add(submitRekening);


    }
}