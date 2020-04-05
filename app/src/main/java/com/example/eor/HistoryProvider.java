package com.example.eor;

import java.util.ArrayList;

public class HistoryProvider {

    ArrayList<HistoryLayout> historyLayouts;

    public HistoryProvider() {
    }

    public HistoryProvider(ArrayList<HistoryLayout> historyLayouts) {
        this.historyLayouts = historyLayouts;
    }

    public ArrayList<HistoryLayout> getHistoryLayouts() {
        return historyLayouts;
    }
    public void addItem(HistoryLayout historyLayout){
        historyLayouts.add(historyLayout);
    }
    @Override
    public String toString() {
        return "HistoryProvider{" +
                "historyLayouts=" + historyLayouts +
                '}';
    }
}
