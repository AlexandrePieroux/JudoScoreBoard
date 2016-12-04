package view.displayScreen;


import controller.Controller;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by alexp on 26-10-16.
 */
public class DisplayScreenController {

    @FXML private GridPane topRow;
    @FXML private VBox firstJudoka;
    @FXML private Text firstJudokaLabel;
    @FXML private HBox firstJudokaPoints;
    @FXML private Text firstJudokaWazaAri;
    @FXML private Text firstJudokaYuko;
    @FXML private HBox firstJudokaShido;

    @FXML private GridPane middleRow;
    @FXML private VBox secondJudoka;
    @FXML private Text secondJudokaLabel;
    @FXML private HBox secondJudokaPoints;
    @FXML private Text secondJudokaWazaAri;
    @FXML private Text secondJudokaYuko;
    @FXML private HBox secondJudokaShido;

    @FXML private VBox category;
    @FXML private Text gender;
    @FXML private Text weight;

    @FXML private HBox combatTimer;
    @FXML private Text combatMinutes;
    @FXML private Text combatSeconds;

    @FXML private HBox immobilizationTimer;
    @FXML private Text immobilizationSeconds;

    public GridPane getTopRow() {
        return topRow;
    }

    public HBox getFirstJudokaPoints() {
        return firstJudokaPoints;
    }

    public Text getFirstJudokaWazaAri() {
        return firstJudokaWazaAri;
    }

    public Text getFirstJudokaYuko() {
        return firstJudokaYuko;
    }

    public HBox getFirstJudokaShido() {
        return firstJudokaShido;
    }


    public GridPane getMiddleRow() {
        return middleRow;
    }

    public HBox getSecondJudokaPoints() {
        return secondJudokaPoints;
    }

    public Text getSecondJudokaWazaAri() {
        return secondJudokaWazaAri;
    }

    public Text getSecondJudokaYuko() {
        return secondJudokaYuko;
    }

    public HBox getSecondJudokaShido() {
        return secondJudokaShido;
    }


    public DisplayScreenController() {
        super();
    }

    public void init(Controller controller) {
        setFontJudoka(this.firstJudoka);
        setFontJudokaPoints(this.firstJudokaPoints);
        setFontJudoka(this.secondJudoka);
        setFontJudokaPoints(this.secondJudokaPoints);
        setFontCategory(this.category);
        setFontTimer(this.combatTimer);
        setFonTimerImmobilization(this.immobilizationTimer);

        this.firstJudokaLabel.textProperty().bind(controller.firstJudokaNameProperty());
        this.firstJudokaWazaAri.textProperty().bind(Bindings.convert(controller.firstJudokaWazaAriProperty()));
        this.firstJudokaYuko.textProperty().bind(Bindings.convert(controller.firstJudokaYukoProperty()));

        this.secondJudokaLabel.textProperty().bind(controller.secondJudokaNameProperty());
        this.secondJudokaWazaAri.textProperty().bind(Bindings.convert(controller.secondJudokaWazaAriProperty()));
        this.secondJudokaYuko.textProperty().bind(Bindings.convert(controller.secondJudokaYukoProperty()));

        controller.genderProperty().addListener((obs, o, n) -> {
            if(n)
                this.gender.setText("Male");
            else
                this.gender.setText("Female");
        });
        this.gender.setText("Male");
        this.weight.textProperty().bind(Bindings.concat(controller.weightProperty(), " kg"));

        this.combatMinutes.textProperty().bind(Bindings.convert(controller.combatTimerMinutesProperty()));
        this.combatSeconds.textProperty().bind(Bindings.format("%02d", controller.combatTimerSecondsProperty()));
        this.immobilizationSeconds.textProperty().bind(Bindings.format("%02d", controller.immobilizationTimerScecondProperty()));

        setShidoEvent(controller, this.firstJudokaShido, true);
        setShidoEvent(controller, this.secondJudokaShido, false);
    }

    private void setFontJudoka(VBox t){
        DoubleProperty fontSize = new SimpleDoubleProperty(10);
        fontSize.bind(t.heightProperty());
        t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
    }

    private void setFontJudokaPoints(HBox t) {
        DoubleProperty fontSize = new SimpleDoubleProperty(10);
        DoubleProperty pointsSpacing = new SimpleDoubleProperty(10);
        fontSize.bind(t.heightProperty().multiply(1.25));
        pointsSpacing.bind(t.widthProperty().divide(7));
        t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        t.spacingProperty().bind(pointsSpacing);
    }

    private void setFontCategory(VBox t){
        DoubleProperty fontSize = new SimpleDoubleProperty(10);
        fontSize.bind(t.heightProperty().divide(3.5));
        t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
    }

    private void setFontTimer(HBox t){
        DoubleProperty fontSize = new SimpleDoubleProperty(10);
        DoubleProperty pointsSpacing = new SimpleDoubleProperty(10);
        fontSize.bind(t.heightProperty());
        pointsSpacing.bind(t.widthProperty().divide(12));
        t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        t.spacingProperty().bind(pointsSpacing);
    }

    private void setFonTimerImmobilization(HBox t) {
        DoubleProperty fontSize = new SimpleDoubleProperty(10);
        DoubleProperty pointsSpacing = new SimpleDoubleProperty(10);
        fontSize.bind(t.heightProperty().divide(2));
        pointsSpacing.bind(t.widthProperty().divide(24));
        t.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
        t.spacingProperty().bind(pointsSpacing);
    }

    private void setShidoEvent(Controller controller, HBox shidoBox, boolean firstJudoka){
        ReadOnlyIntegerProperty p;
        if(firstJudoka)
            p = controller.firstJudokaShidoProperty();
        else
            p = controller.secondJudokaShidoProperty();

        p.addListener((Observable n) -> {
            if(shidoBox.getChildren().size() < p.get()){
                Rectangle card = new Rectangle();
                card.widthProperty().bind(shidoBox.widthProperty().divide(4));
                card.heightProperty().bind(shidoBox.heightProperty().divide(2));
                card.setFill(Color.YELLOW);
                shidoBox.getChildren().add(card);
            } else {
                shidoBox.getChildren().remove(0);
            }

        });
    }
}
