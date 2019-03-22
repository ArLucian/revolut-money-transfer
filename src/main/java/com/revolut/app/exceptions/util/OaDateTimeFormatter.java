package com.revolut.app.exceptions.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.app.exceptions.custom.CustomException;
import com.revolut.app.exceptions.custom.CustomException.ExceptionType;

public final class OaDateTimeFormatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OaDateTimeFormatter.class);

    private OaDateTimeFormatter() {
    }

    public static String getCurrentDateTimeAsString() {
        final GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(new Date());

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar).toString();
        } catch (final DatatypeConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException(ExceptionType.SERVER, e.getMessage());
        }
    }

    public static XMLGregorianCalendar getCurrentDateTimeAsCalendar() {
        final GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(new Date());

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (final DatatypeConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException(ExceptionType.SERVER, e.getMessage());
        }
    }

    public static String convertDateTimeToIso8601Format(final Date date) {
        final GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar).toString();
        } catch (final DatatypeConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
            throw new CustomException(ExceptionType.SERVER, e.getMessage());
        }
    }

}
