package com.segelzwerg.familyfotoandroid.familyfotoservice;

/**
 * Is thrown if the an result extraction failed.
 */
public class ManagerExtractionException extends Exception {
    /**
     * Is thrown if the an result extraction failed.
     * @param message error message
     */
    public ManagerExtractionException(String message) {
        super(message);
    }

    /**
     * Is thrown if the an result extraction failed.
     * @param message error message
     * @param exception the wrapped exception
     */
    public ManagerExtractionException(String message, Exception exception) {
        super(message, exception);
    }
}
