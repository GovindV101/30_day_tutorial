package com.litmus7.studentmanager.dto;

/**
 * Enum to represent student grades with inline descriptions.
 */
public enum Grade {
    A("A - Excellent"),
    B("B - Good"),
    C("C - Average"),
    D("D - Pass"),
    F("F - Fail");

    private final String label;

    /**
     * Constructor to store combined grade and description.
     *
     * @param label Combined grade and description.
     */
    Grade(String label) {
        this.label = label;
    }

    /**
     * Overrides toString() so printing the enum shows the label.
     *
     * @return Combined grade and description.
     */
    @Override
    public String toString() {
        return label;
    }
}
