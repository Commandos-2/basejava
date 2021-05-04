package com.resume.webapp.model;

import com.resume.webapp.storage.ObjectStreamStorage;
import com.resume.webapp.storage.Strategy;

public enum StrategyType {
    OBJECT_STREAM(new ObjectStreamStorage());
    Strategy realization;

    StrategyType(Strategy realization) {
        this.realization = realization;
    }

    public Strategy getRealization() {
        return realization;
    }

    public void setRealization(Strategy realization) {
        this.realization = realization;
    }
}
