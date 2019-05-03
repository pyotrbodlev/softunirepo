package fbdb.app.entities;

import fbdb.app.enums.Prediction;

import javax.persistence.*;

@Entity
@Table(name = "result_prediction")
public class ResultPrediction {
    private int id;
    private Prediction prediction;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
