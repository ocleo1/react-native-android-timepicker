package com.ocleo1.timepicker;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.widget.TimePicker;

import com.ocleo1.R;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;

import java.util.Map;

import javax.annotation.Nullable;

public class ReactTimePickerSpinnerManager extends SimpleViewManager<TimePicker> {

    public static final String REACT_CLASS = "SpinnerTimePickerAndroid";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected TimePicker createViewInstance(ThemedReactContext reactContext) {

        return (TimePicker) LayoutInflater.from(reactContext).inflate(R.layout.time_picker_spinner, null);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put(
                    ReactTimePickerEvent.EVENT_NAME,
                    MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of(
                            "bubbled", "onTimeChange",
                            "captured", "onTimeChangeCapture"
                        )
                    )
                )
                .build();
    }

    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, final TimePicker view) {
        view.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                reactContext.getNativeModule(UIManagerModule.class)
                            .getEventDispatcher()
                            .dispatchEvent(new ReactTimePickerEvent(
                                            view.getId(),
                                            SystemClock.uptimeMillis(),
                                            hourOfDay,
                                            minute)
                            );
            }
        });
    }
}
