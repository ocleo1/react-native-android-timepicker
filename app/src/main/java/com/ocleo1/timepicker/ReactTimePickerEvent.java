package com.ocleo1.timepicker;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ReactTimePickerEvent extends Event<ReactTimePickerEvent> {

    public static final String EVENT_NAME = "ReactTimePickerEvent";

    private int hours;
    private int minutes;

    public ReactTimePickerEvent(int viewId, long timestampMs, int hours, int minutes) {
        super(viewId, timestampMs);
        this.hours = hours;
        this.minutes = minutes;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("hours", getHours());
        eventData.putInt("minutes", getMinutes());
        return eventData;
    }

    private int getHours() {
        return this.hours;
    }

    private int getMinutes() {
        return this.minutes;
    }
}
