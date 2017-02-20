package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryOrder {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public final LocalDateTime estimatedArrival;
    public final List<Article> articles;

    @JsonCreator
    public DeliveryOrder(@JsonProperty("estimatedArrival") LocalDateTime estimatedArrival, @JsonProperty("articles") List<Article> articles) {
        this.estimatedArrival = estimatedArrival;
        this.articles = articles;
    }


}
