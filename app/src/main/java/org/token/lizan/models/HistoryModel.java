package org.token.lizan.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class HistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int type;
    private String date;

    @Ignore
    public HistoryModel() {
    }

    public HistoryModel(int id, String text, int type, String date) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
