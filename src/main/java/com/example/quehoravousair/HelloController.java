package com.example.quehoravousair;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalTime;

public class HelloController {

    @FXML
    private Label resultado = new Label();

    @FXML
    private TextField tfEntrada, tfIntervalo, tfRetorno, tfQtdHorasExtras, tfQtdHorasCompensar;

    @FXML
    protected void onHelloButtonClick() {

        try {
            LocalTime padrao = LocalTime.of(8, 0);

            LocalTime entrada = LocalTime.of(Integer.parseInt(tfEntrada.getText().substring(0, 2)),
                    Integer.parseInt(tfEntrada.getText().substring(2, 4)));

            LocalTime intervalo = LocalTime.of(Integer.parseInt(tfIntervalo.getText().substring(0, 2)),
                    Integer.parseInt(tfIntervalo.getText().substring(2, 4)));

            LocalTime retorno = LocalTime.of(Integer.parseInt(tfRetorno.getText().substring(0, 2)),
                    Integer.parseInt(tfRetorno.getText().substring(2, 4)));

            LocalTime extra = LocalTime.of(Integer.parseInt(tfQtdHorasExtras.getText().substring(0, 2)),
                    Integer.parseInt(tfQtdHorasExtras.getText().substring(2, 4)));

            LocalTime compensacao = LocalTime.of(Integer.parseInt(tfQtdHorasCompensar.getText().substring(0, 2)),
                    Integer.parseInt(tfQtdHorasCompensar.getText().substring(2, 4)));


            LocalTime total = padrao.plusHours(entrada.getHour()).plusMinutes(entrada.getMinute());

            LocalTime almoco = retorno.minusHours(intervalo.getHour()).minusMinutes(intervalo.getMinute());

            LocalTime saida = almoco.plusHours(total.getHour()).plusMinutes(total.getMinute());


            String sExtra = extra.toString();
            String sCompensacao = compensacao.toString();


            if(sExtra.equals("00:00") && sCompensacao.equals("00:00")){

                resultado.setText(saida.toString());

            } else if(sExtra.equals("00:00") && sCompensacao != "00:00"){

                LocalTime compensar = saida.plusHours(compensacao.getHour()).plusMinutes(compensacao.getMinute());
                resultado.setText(compensar.toString());

            } else {

                LocalTime horaExtra = saida.minusHours(extra.getHour()).minusMinutes(extra.getMinute());
                resultado.setText(horaExtra.toString());

            }
        } catch (StringIndexOutOfBoundsException n) {

            Button buttonOK = new Button();

            Stage stage = new Stage();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Não pode deixar o(s) campo(s) em branco.");

            if(alert.showAndWait().get() == ButtonType.OK){
                stage = (Stage) buttonOK.getScene().getWindow();
                stage.close();
            }
        }
    }
}