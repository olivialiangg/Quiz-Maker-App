package ui;

import model.EventLog;

/**
 * Defines behaviours that event log printers must support.
 */
public interface LogPrinter {
    String printLog(EventLog el);
}
