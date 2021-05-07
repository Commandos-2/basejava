package com.resume.webapp.model;

import com.resume.webapp.storage.strategy.ObjectStreamStrategy;
import com.resume.webapp.storage.Strategy;

public enum StrategyType {
    OBJECT_STREAM(new ObjectStreamStrategy());
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
